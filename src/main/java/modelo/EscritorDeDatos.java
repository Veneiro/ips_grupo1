package modelo;

import java.util.List;

import dtos.CitaDto;
import dtos.MedicoDto;
import util.Database;

/**
 * @author Santiago
 *
 */
public class EscritorDeDatos {

	private static String TCITAS_ADD = "INSERT INTO TCITAS VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static String TASIGNADOA_ADD = "INSERT INTO TASIGNADOA VALUES (?,?)";

	private Database db = new Database();

	/**
	 * Escribe una cita en la base de datos y la relaciona con los médicos dados.
	 * 
	 * @param cita    nueva cita a introducir
	 * @param medicos lista de médicos asociados a esa cita
	 */
	public void grabarCita(CitaDto cita, List<MedicoDto> medicos) {

		db.executeUpdate(TCITAS_ADD, cita.getId(), cita.getHorario_inicio(), cita.getHorario_fin(), cita.getUbicacion(),
				cita.getContacto(), cita.getId_paciente(),1 ,  cita.getAcudio(), cita.getFecha());

		for (MedicoDto medicoDto : medicos) {
			db.executeUpdate(TASIGNADOA_ADD, cita.getId(), medicoDto.getId());
		}
	}
}
