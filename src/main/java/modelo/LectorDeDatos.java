package modelo;

import java.util.ArrayList;
import java.util.List;

import dtos.CitaDto;
import dtos.MedicoDto;
import dtos.PacienteDto;
import util.Database;

/**
 * @author Santiago
 *
 */
public class LectorDeDatos {

	private static String TMEDICOS_SELECT = "SELECT * FROM TMEDICOS";
	private static String TPACIENTES_SELECT = "SELECT * FROM TPACIENTES";
	private static String TCITAS_MEDICO_SELECT = "SELECT c.* FROM TCITAS c, TASIGNADOA a "
			+ "WHERE c.id = a.id_cita AND a.id_medico = ?";

	private Database db = new Database();

	/**
	 * Extrae de la base de datos todos los médicos existentes.
	 * 
	 * @return lista de todos los médicos
	 */
	public List<MedicoDto> getListaMedicos() {
		return db.executeQueryPojo(MedicoDto.class, TMEDICOS_SELECT);
	}

	/**
	 * Extrae de la base de datos todos los pacientes existentes.
	 * 
	 * @return lista de todos los pacientes
	 */
	public List<PacienteDto> getListaPacientes() {
		return db.executeQueryPojo(PacienteDto.class, TPACIENTES_SELECT);
	}

	/**
	 * Obtiene las citas de los médicos indicados.
	 * 
	 * @param medicos lista de los médicos cuyas citas se quieren obtener
	 * @return lista de las citas de esos médicos
	 */
	public List<CitaDto> getListaCitasDeMedicos(List<MedicoDto> medicos) {
		List<CitaDto> lista = new ArrayList<CitaDto>();

		for (MedicoDto medico : medicos)
			lista.addAll(db.executeQueryPojo(CitaDto.class, TCITAS_MEDICO_SELECT, medico.getId()));

		return lista;
	}
	
	/**
	 * Obtiene las citas del médico indicado.
	 * 
	 * @param medico médico cuyas citas se quieren obtener
	 * @return lista de las citas de ese médico
	 */
	public List<CitaDto> getListaCitasDeMedico(MedicoDto medico) {
		List<CitaDto> lista = new ArrayList<CitaDto>();

		lista.addAll(db.executeQueryPojo(CitaDto.class, TCITAS_MEDICO_SELECT, medico.getId()));

		return lista;
	}
}
