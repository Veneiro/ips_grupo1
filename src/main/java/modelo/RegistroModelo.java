package modelo;

import java.util.List;

import dtos.RegistroDto;
import records.RegistroRecord;
import util.Database;

public class RegistroModelo {

    private static Database db = new Database();

    public static void addRegistro(RegistroDto r) {
	String sql = "INSERT INTO TREGISTRO (QUIEN, QUE, FECHA, HORA) values (?,?,?,?)";

	db.executeUpdate(sql, r.getQuien(), r.getQue(), util.Util.dateToIsoString(r.getFecha()),
		util.Util.dateToIsoHour(r.getFecha()));
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
	String sql = "SELECT * FROM TREGISTRO WHERE FECHA = ?";

	return db.executeQueryPojo(RegistroRecord.class, sql, fecha);
    }

    public List<RegistroRecord> findBySearch(String text) {
	String sql = "SELECT * FROM TREGISTRO WHERE QUIEN LIKE ? OR QUE LIKE ?";

	return db.executeQueryPojo(RegistroRecord.class, sql, "%" + text + "%", "%" + text + "%");
    }
}
