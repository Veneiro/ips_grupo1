package logic;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dtos.CitaDto;
import dtos.JornadaLaboralDto;
import dtos.MedicoDto;
import dtos.PacienteDto;
import modelo.JornadaModelo;
import modelo.LectorDeDatos;
import modelo.MedicoModelo;
import modelo.PacienteModelo;
import records.JornadaLaboralRecord;
import records.RecordAssembler;
import util.ApplicationException;

/**
 * Almacena los médicos y los pacientes.
 * 
 * @author Santiago
 *
 */
public class Admin {

	private List<MedicoDto> medicos;
	private List<PacienteDto> pacientes;

	public Admin() {
		MedicoModelo medicoModelo = new MedicoModelo();
		medicos = medicoModelo.getListaMedicos();
		PacienteModelo pacienteModelo = new PacienteModelo();
		pacientes = pacienteModelo.getListaPacientes();
	}

	public List<MedicoDto> getListaMedicos() {
		return new ArrayList<MedicoDto>(medicos);
	}

	public PacienteDto[] getListaPacientes() {
		return pacientes.toArray(new PacienteDto[pacientes.size()]);
	}

	/**
	 * Devuelve una lista que contiene sólo los médicos cuya representación en
	 * String contenga el filtro pasado.
	 * 
	 * @param filtro
	 * @return lista filtrada de los médicos
	 */
	public List<MedicoDto> filtrarMedicos(String filtro, List<MedicoDto> medicos) {
		List<MedicoDto> listaFiltrada = new ArrayList<MedicoDto>();
		for (MedicoDto m : medicos) {
			if (m.toString().trim().toLowerCase().contains(filtro.toLowerCase())) {
				listaFiltrada.add(m);
			}
		}
		return listaFiltrada;
	}

	public List<PacienteDto> filtrarPacientes(String filtro) {
		List<PacienteDto> listaFiltrada = new ArrayList<PacienteDto>();
		for (PacienteDto p : pacientes) {
			if (p.toString().trim().toLowerCase().contains(filtro.toLowerCase())) {
				listaFiltrada.add(p);
			}
		}
		return listaFiltrada;
	}

	public List<MedicoDto> filtrarMedicosSinCitasColisionantes(Date horaEntrada, Date horaSalida, Date fecha,
			List<MedicoDto> medicos) {
		List<MedicoDto> res = new ArrayList<MedicoDto>();
		Format formatterHora = new SimpleDateFormat("HH:mm");
		Format formatterDia = new SimpleDateFormat("dd-MM-yyyy");
		try {
			fecha = (Date) formatterDia.parseObject(formatterDia.format(fecha));
		} catch (ParseException e1) {
			throw new ApplicationException(e1);
		}
		for (MedicoDto medico : medicos) {
			List<CitaDto> citasDto = new LectorDeDatos().getListaCitasDeMedico(medico);
			if (!algunaCitaColisionante(horaEntrada, horaSalida, fecha, formatterHora, formatterDia, citasDto)) {
				res.add(medico);
			}
		}
		return res;
	}

	public List<MedicoDto> filtrarMedicosConJornadaLaboral(Date horaEntrada, Date horaSalida, Date fecha,
			List<MedicoDto> medicos) {
		List<MedicoDto> res = new ArrayList<MedicoDto>();
		Format formatterDia = new SimpleDateFormat("yyyy-MM-dd");
		try {
			fecha = (Date) formatterDia.parseObject(formatterDia.format(fecha));
		} catch (ParseException e1) {
			throw new ApplicationException(e1);
		}
		JornadaModelo modelo = new JornadaModelo();
		for (MedicoDto medico : medicos) {
			List<JornadaLaboralRecord> jornadas = modelo.findByName(medico.getNombre());
			if (algunaJornadaColisionante(jornadas, fecha, horaEntrada, horaSalida)) {
				res.add(medico);
			}
		}
		return res;
	}

	private boolean algunaJornadaColisionante(List<JornadaLaboralRecord> jornadas, Date fecha, Date horaEntrada,
			Date horaSalida) {
		for (JornadaLaboralRecord jornada : jornadas) {
			JornadaLaboralDto j = RecordAssembler.toDto(jornada);
			if (j.getDia_comienzo() != null && j.getDia_fin() != null) {
				if (fecha.before(j.getDia_comienzo()) || fecha.after(j.getDia_fin())) {
					return true;
				}
				try {
					if (colisionHorarios(horaEntrada, horaSalida, j.getHora_entrada(), j.getHora_salida())) {
						return true;
					}
				} catch (ParseException e) {
					// Se ignora la jornada
				}
			}
		}
		return false;
	}

	public List<MedicoDto> filtrarMedicosPorEspecialidad(String especialidad, List<MedicoDto> medicos) {
		List<MedicoDto> listaFiltrada = new ArrayList<MedicoDto>();
		for (MedicoDto m : medicos) {
			if (m.getEspecialidad() != null)
				if (m.getEspecialidad().equals(especialidad)) {
					listaFiltrada.add(m);
				}
		}
		return listaFiltrada;
	}

	private boolean algunaCitaColisionante(Date horaEntrada, Date horaSalida, Date fecha, Format formatterHora,
			Format formatterDia, List<CitaDto> citasDto) {
		for (CitaDto citaDto : citasDto) {
			try {
				if (citaDto.getFecha() != null) {
					Date fechaAjena = (Date) formatterDia.parseObject(citaDto.getFecha());
					if (fecha.compareTo(fechaAjena) == 0) {
						Date horaEntradaAjena = (Date) formatterHora.parseObject(citaDto.getHorario_inicio());
						Date horaSalidaAjena = (Date) formatterHora.parseObject(citaDto.getHorario_fin());
						if (colisionHorarios(horaEntrada, horaSalida, horaEntradaAjena, horaSalidaAjena)) {
							return true;
						}
					}
				}
			} catch (ParseException e) {
				// Se ignora la cita
			}
		}
		return false;
	}

	/**
	 * Comprueba si hay intersección entre 2 horarios.
	 * 
	 * @param i1 hora de inicio del horario 1
	 * @param f1 hora de fin del horario 1
	 * @param i2 hora de inicio del horario 2
	 * @param f2 hora de fin del horario 2
	 * @return true si tienen tiempo en común, false en caso contrario
	 * @throws ParseException
	 */
	private boolean colisionHorarios(Date i1, Date f1, Date i2, Date f2) throws ParseException {
		Format formatter = new SimpleDateFormat("HH:mm");
		i1 = (Date) formatter.parseObject(formatter.format(i1));
		f1 = (Date) formatter.parseObject(formatter.format(f1));
		i2 = (Date) formatter.parseObject(formatter.format(i2));
		f2 = (Date) formatter.parseObject(formatter.format(f2));
		if (i1.compareTo(i2) == 0 || f1.compareTo(f2) == 0)
			return true;
		if (i1.after(i2) && i1.before(f2))
			return true;
		if (f1.after(i2) && f1.before(f2))
			return true;
		if (i2.after(i1) && i2.before(f1))
			return true;
		if (f2.after(i1) && f2.before(f1))
			return true;
		return false;
	}

}
