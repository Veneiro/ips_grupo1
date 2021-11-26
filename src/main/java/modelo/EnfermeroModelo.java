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

    public List<EnfermeroDto> getListaEnfermerosBySearch(String name) {
	String sql = "SELECT * FROM TENFERMEROS WHERE (LOWER(NOMBRE) LIKE ? OR LOWER(ID) LIKE ?)";
	return db.executeQueryPojo(EnfermeroDto.class, sql, "%" + name + "%", "%" + name + "%");
    }

    public List<EnfermeroDto> findById(int id) {
	String sql = "SELECT * FROM TENFERMEROS WHERE ID = ?";
	return db.executeQueryPojo(EnfermeroDto.class, sql, id);
    }
}
