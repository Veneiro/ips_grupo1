package modelo;

import java.util.List;

import dtos.RegistroDto;
import records.RegistroRecord;
import util.Database;

public class RegistroModelo {

    private Database db = new Database();

    public void addRegistro(RegistroDto r) {
	String sql = "INSERT INTO TREGISTRO (QUIEN, QUE, FECHA, HORA) values (?,?,?,?)";

	db.executeUpdate(sql, r.getQuien(), r.getQue(), r.getFecha(), r.getHora());
    }

    public List<RegistroRecord> findRegistroById(int id) {
	String sql = "SELECT * FROM TREGISTRO WHERE ID = ?";

	return db.executeQueryPojo(RegistroRecord.class, sql, id);
    }

    public List<RegistroRecord> findAll() {
	String sql = "SELECT * FROM TREGISTRO";

	return db.executeQueryPojo(RegistroRecord.class, sql);
    }

    public List<RegistroRecord> findByQuien(String quien) {
	String sql = "SELECT * FROM TREGISTRO WHERE QUIEN LIKE ?";

	return db.executeQueryPojo(RegistroRecord.class, sql, "%" + quien + "%");
    }

    public List<RegistroRecord> findByQue(String que) {
	String sql = "SELECT * FROM TREGISTRO WHERE QUIEN LIKE ?";

	return db.executeQueryPojo(RegistroRecord.class, sql, "%" + que + "%");
    }

    public List<RegistroRecord> findByFecha(String fecha) {
	String sql = "SELECT * FROM TREGISTRO WHERE FECHA LIKE ?";

	return db.executeQueryPojo(RegistroRecord.class, sql, "%" + fecha + "%");
    }
}
