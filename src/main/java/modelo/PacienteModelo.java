package modelo;


import java.util.List;

import dtos.PacienteDto;
import util.Database;

public class PacienteModelo {

	private Database db = new Database();
	
	public List<PacienteDto> getPacienteById(int id) {
		String sql = "SELECT * FROM TPACIENTES p WHERE p.id = " + id;
		return db.executeQueryPojo(PacienteDto.class, sql);
	}
}
