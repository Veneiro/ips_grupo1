package modelo;

import java.util.List;

import dtos.TrabajadorDto;
import util.Database;

public class TrabajadorModelo {

    private Database db = new Database();

    public List<TrabajadorDto> findByIdMedico(int id) {
	String sql = "SELECT * FROM TTRABAJADORES WHERE CATEGORIA = 'MEDICO' AND ID_MEDICO = ?";
	return db.executeQueryPojo(TrabajadorDto.class, sql, id);
    }

    public List<TrabajadorDto> findByIdEnfermero(int id) {
	String sql = "SELECT * FROM TTRABAJADORES WHERE CATEGORIA = 'ENFERMERO' AND ID_ENFERMERO = ?";
	return db.executeQueryPojo(TrabajadorDto.class, sql, id);
    }

    public List<TrabajadorDto> findById(int id) {
	String sql = "SELECT * FROM TTRABAJADORES WHERE ID = ?";
	return db.executeQueryPojo(TrabajadorDto.class, sql, id);
    }
}
