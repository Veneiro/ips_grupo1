package modelo;

import java.util.List;

import dtos.EnfermeroDto;
import util.Database;

public class EnfermeroModelo {

	private Database db = new Database();

	public List<EnfermeroDto> getListaEnfermeros() {
		String sql = "SELECT * FROM TENFERMEROS";
		return db.executeQueryPojo(EnfermeroDto.class, sql);
	}

	public List<EnfermeroDto> getListaEnfermerosByName(String name) {
		String sql = "SELECT * FROM TENFERMEROS WHERE (LOWER(NOMBRE) LIKE ?)";
		return db.executeQueryPojo(EnfermeroDto.class, sql, "%"+name+"%");
	}
}
