package logic;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import dtos.CitaDto;
import dtos.JornadaLaboralDto;
import dtos.MedicoDto;
import dtos.PacienteDto;
import modelo.CitaModelo;
import modelo.JornadaModelo;
import modelo.LectorDeDatos;
import modelo.MedicoAsignadoACitaModelo;
import records.JornadaLaboralRecord;
import records.RecordAssembler;
import util.ApplicationException;
import util.Util;

/**
 * Crea una cita en base a un paciente y uno o varios médicos.
 * 
 * @author Santiago
 *
 */
public class CrearCitas {

	private List<MedicoDto> medicosElegidos;

	public CrearCitas() {
		medicosElegidos = new ArrayList<MedicoDto>();
	}

	public void seleccionarMedicos(List<MedicoDto> listaMedicos) {
		for (MedicoDto newMedico : listaMedicos) {
			if (!medicosElegidos.contains(newMedico))
				medicosElegidos.add(newMedico);
		}
	}

	public void deseleccionarMedicos(List<MedicoDto> listaMedicos) {
		for (MedicoDto medicoABorrar : listaMedicos) {
			medicosElegidos.remove(medicoABorrar);
		}
	}

	public String getStringMedicosElegidos() {
		String txt = "";
		for (MedicoDto ms : medicosElegidos) {
			txt += ms.toString() + "\n";
		}
		return txt;
	}

	public boolean hayMedicosElegidos() {
		return !medicosElegidos.isEmpty();
	}

	/**
	 * Crea una cita y la guarda en la base de datos.
	 * 
	 * @param paciente     el paciente asignado a la cita
	 * @param infoContacto información de contacto de la cita, por defecto la del
	 *                     paciente
	 * @param ubicacion    opcional
	 * @param horaInicio   opcional
	 * @param horaFin      opcional
	 * @param especialidad opcional
	 */
	public CitaDto crearCita(PacienteDto paciente, String infoContacto, String ubicacion, Date horaInicio, Date horaFin,
			Date fecha, String especialidad) {
		int id = valorAbsoluto(new Random().nextInt());
		CitaDto cita = new CitaDto();
		cita.setId(id);
		if (horaInicio == null || horaFin == null) {
			cita.setHorario_inicio(null);
			cita.setHorario_fin(null);
		} else {
			cita.setHorario_inicio(Util.dateToIsoHour(horaInicio));
			cita.setHorario_fin(Util.dateToIsoHour(horaFin));
		}
		cita.setUbicacion(ubicacion);
		if (fecha != null)
			cita.setFecha(new SimpleDateFormat("dd-MM-yyyy").format(fecha));
		else
			cita.setFecha(null);
		cita.setId_paciente(paciente.getId());
		if (hayMedicosElegidos())
			cita.setId_medico(medicosElegidos.get(0).getId());
		else
			cita.setId_medico(0);
		cita.setEspecialidad(especialidad);

		grabarCita(cita, medicosElegidos);
		return cita;
	}

	/**
	 * Escribe una cita en la base de datos y la relaciona con los médicos dados.
	 * 
	 * @param cita    nueva cita a introducir
	 * @param medicos lista de médicos asociados a esa cita
	 */
	private void grabarCita(CitaDto cita, List<MedicoDto> medicos) {

		CitaModelo citaModelo = new CitaModelo();
		citaModelo.addCita(cita);

		MedicoAsignadoACitaModelo medicoCitaModelo = new MedicoAsignadoACitaModelo();

		for (MedicoDto medicoDto : medicos) {
			medicoCitaModelo.add(cita.getId(), medicoDto.getId());
		}
	}

	private int valorAbsoluto(int x) {
		return x > 0 ? x : -x;
	}

	/**
	 * Dado un inico y fin de un horario, comprueba si algún médico de los elegidos
	 * tiene alguna cita con un horario igual.
	 * 
	 * @param horaEntrada
	 * @param horaSalida
	 * @return true si hay colisión con alguna cita de los médicos, false en caso
	 *         contrario.
	 */
	public boolean hayColisionMismoHorario(Date horaEntrada, Date horaSalida, Date fecha) {
		List<CitaDto> citasDto = new LectorDeDatos().getListaCitasDeMedicos(medicosElegidos);
		Format formatterHora = new SimpleDateFormat("HH:mm");
		Format formatterDia = new SimpleDateFormat("dd-MM-yyyy");
		try {
			fecha = (Date) formatterDia.parseObject(formatterDia.format(fecha));
		} catch (ParseException e1) {
			throw new ApplicationException(e1);
		}
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

	public boolean fueraDeJornadaLaboral(Date horaEntrada, Date horaSalida, Date fecha) {
		Format formatterDia = new SimpleDateFormat("yyyy-MM-dd");
		try {
			fecha = (Date) formatterDia.parseObject(formatterDia.format(fecha));
		} catch (ParseException e1) {
			throw new ApplicationException(e1);
		}
		JornadaModelo modelo = new JornadaModelo();
		for (MedicoDto medicoDto : medicosElegidos) {
			List<JornadaLaboralRecord> jornadas = modelo.findByName(medicoDto.getNombre());
			for (JornadaLaboralRecord jornada : jornadas) {
				JornadaLaboralDto j = RecordAssembler.toDto(jornada);
				if (j.getDia_comienzo() != null && j.getDia_fin() != null) {
					if (fecha.before(j.getDia_comienzo()) || fecha.after(j.getDia_fin())) {
						return true;
					}
					try {
						if (colisionHorarios(horaEntrada, horaSalida, j.getHora_entrada(),
								j.getHora_salida())) {
							return true;
						}
					} catch (ParseException e) {
						// Se ignora la jornada
					}
				}
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
