package modelo;

import java.util.List;

import dtos.AsignacionMedicosDto;
import dtos.MedicoDto;
import util.Database;

public class MedicoAsignadoACitaModelo {

	private Database db = new Database();

	public void add(int id_cita, int id_medico) {
		String sql = "INSERT INTO TASIGNADOA (id_cita, id_medico) VALUES (?, ?)";
		db.executeUpdate(sql, id_cita, id_medico);
	}

	public List<MedicoDto> getMedicosDeCita(int id_cita) {
		String sql = "SELECT m.* FROM TMEDICOS m, TASIGNADOA a where "
				+ "m.id = a.id_medico and a.id_cita = ?";
		return db.executeQueryPojo(MedicoDto.class, sql, id_cita);
	}

	public void asignarMedicosPendientes(
			AsignacionMedicosDto amdto) {
		String sql = "INSERT INTO TASIGNADOAPENDIENTE "
					+ "(id_cita, id_medico) VALUES (?, ?)";
		db.executeUpdate(sql, amdto.getId_cita(), amdto.getId_medico());
	}
	
	public void asignarMedicosConfirmados(
			AsignacionMedicosDto amdto) {
		String sql = "INSERT INTO TASIGNADOA "
					+ "(id_cita, id_medico) VALUES (?, ?)";
		db.executeUpdate(sql, amdto.getId_cita(), amdto.getId_medico());
	}
	
	public List<MedicoDto> getMedicosPendientesDeCita(int id_cita) {
		String sql = "SELECT m.* FROM TMEDICOS m, TASIGNADOAPENDIENTE a where "
				+ "m.id = a.id_medico and a.id_cita = ?";
		return db.executeQueryPojo(MedicoDto.class, sql, id_cita);
	}
	
	public List<AsignacionMedicosDto> getMedicosPendientesAprobarCita() {
		String sql = "SELECT * FROM TASIGNADOAPENDIENTE";
		return db.executeQueryPojo(AsignacionMedicosDto.class, sql);
	}

	public List<MedicoDto> getAllMedicosExceptoDeCita(int id_cita) {
		String sql = "SELECT * FROM TMEDICOS where id NOT IN ( "
				+ "SELECT id_medico FROM TASIGNADOA WHERE id_cita == ?)";
		return db.executeQueryPojo(MedicoDto.class, sql, id_cita);
	}

	public void removeMedicos(int id) {
		String sql = "DELETE FROM TASIGNADOAPENDIENTE WHERE ID_CITA = ?";
		db.executeUpdate(sql, id);
	}
}
