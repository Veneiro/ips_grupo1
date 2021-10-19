package modelo;

import java.util.List;

import dtos.MedicoDto;
import util.Database;

public class MedicoModelo {

	private Database db = new Database();

	public List<MedicoDto> getListaMedicos() {
		String sql = "SELECT * FROM TMEDICOS";
		return db.executeQueryPojo(MedicoDto.class, sql);
	}

	public List<MedicoDto> getListaMedicosByName(String name) {
		String sql = "SELECT * FROM TMEDICOS WHERE (LOWER(NOMBRE) LIKE ? )";
		return db.executeQueryPojo(MedicoDto.class, sql, "%"+name+"%");
	}
}
