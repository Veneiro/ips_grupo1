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

    public List<MedicoDto> getListaMedicosBySearch(String name) {
	String sql = "SELECT * FROM TMEDICOS WHERE (LOWER(NOMBRE) LIKE ? OR LOWER(ID) LIKE ?)";
	return db.executeQueryPojo(MedicoDto.class, sql, "%" + name + "%", "%" + name + "%");
    }
    
    public List<MedicoDto> getListaMedicosById(int id) {
    	String sql = "SELECT * FROM TMEDICOS WHERE id = ?";
    	return db.executeQueryPojo(MedicoDto.class, sql, id);
        }
}
