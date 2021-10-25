package logic;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import dtos.CitaDto;
import dtos.MedicoDto;
import dtos.PacienteDto;
import modelo.LectorDeDatos;

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
	 * @param paciente el paciente asignado a la cita
	 * @param infoContacto información de contacto de la cita, por defecto la del paciente
	 * @param ubicacion opcional
	 * @param horaInicio opcional
	 * @param horaFin opcional
	 * @return 
	 */
	public Cita crearCita(PacienteDto paciente, String infoContacto, String ubicacion, LocalTime horaInicio,
			LocalTime horaFin) {
		Cita cita = new Cita(paciente, medicosElegidos);
		cita.setContacto(infoContacto);
		cita.setUbicacion(ubicacion);
		cita.setHorario_inicio(horaInicio);
		cita.setHorario_fin(horaFin);
		cita.grabar();
		return cita;
	}

	/**
	 * Dado un inico y fin de un horario, comprueba si algún médico de los elegidos
	 * tiene alguna cita con un horario igual.
	 * 
	 * @param horarioInicio
	 * @param horarioFin
	 * @return true si hay colisión con alguna cita de los médicos, false en caso
	 *         contrario.
	 */
	public boolean hayColisionMismoHorario(LocalTime horarioInicio, LocalTime horarioFin) {
		List<CitaDto> citasDto = new LectorDeDatos().getListaCitasDeMedicos(medicosElegidos);
		for (CitaDto citaDto : citasDto) {
			// Descartar las citas sin horarios fijados
			if (citaDto.getHorario_inicio() != null && citaDto.getHorario_fin() != null) {
				LocalTime i = LocalTime.parse(citaDto.getHorario_inicio());
				LocalTime f = LocalTime.parse(citaDto.getHorario_fin());
				if (colision(i, f, horarioInicio, horarioFin)) {
					return true;
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
	 */
	private boolean colision(LocalTime i1, LocalTime f1, LocalTime i2, LocalTime f2) {
		if (i1.compareTo(i2) == 0 || f1.compareTo(f2) == 0)
			return true;
		if (i1.isAfter(i2) && i1.isBefore(f2))
			return true;
		if (f1.isAfter(i2) && f1.isBefore(f2))
			return true;
		if (i2.isAfter(i1) && i2.isBefore(f1))
			return true;
		if (f2.isAfter(i1) && f2.isBefore(f1))
			return true;
		return false;
	}
}
