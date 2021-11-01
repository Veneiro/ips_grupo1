package modelo;

import util.Database;

public class MedicoAsignadoACitaModelo {

	private Database db = new Database();
	
	public void add(int id_cita, int id_medico) {
		String sql = "INSERT INTO TASIGNADOA (id_cita, id_medico) VALUES (?, ?)";
		db.executeUpdate(sql, id_cita, id_medico);
	}
}
