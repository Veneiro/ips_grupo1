package modelo;

import java.util.Date;
import java.util.List;

import dtos.JornadaLaboralDto;
import records.JornadaLaboralRecord;
import util.Database;
import util.Util;

public class JornadaModelo {

    private Database db = new Database();

    public void addJornada(JornadaLaboralDto j) {
	String sql = "INSERT INTO TJORNADALABORAL(NOMBRE_TRABAJADOR, DIA_COMIENZO, HORA_ENTRADA, DIA_FIN, HORA_SALIDA, LUNES, MARTES, MIERCOLES, JUEVES, VIERNES, SABADO, DOMINGO) values (?,?,?,?,?,?,?,?,?,?,?,?)";

	db.executeUpdate(sql, j.getNombre_trabajador(), Util.dateToIsoString(j.getDia_comienzo()),
		Util.dateToIsoHour(j.getHora_entrada()), Util.dateToIsoString(j.getDia_fin()),
		Util.dateToIsoHour(j.getHora_salida()), j.isLunes(), j.isMartes(), j.isMiercoles(), j.isJueves(),
		j.isViernes(), j.isSabado(), j.isDomingo());
    }

    public void updateJornada(int id, JornadaLaboralDto j) {
	String sql = "UPDATE TJORNADALABORAL SET NOMBRE_TRABAJADOR = ?, DIA_COMIENZO = ?, HORA_ENTRADA = ?, DIA_FIN = ?, HORA_SALIDA = ?, LUNES = ?, MARTES = ?, MIERCOLES = ?, JUEVES = ?, VIERNES = ?, SABADO = ?, DOMINGO = ? where id = ?";

	db.executeUpdate(sql, j.getNombre_trabajador(), Util.dateToIsoString(j.getDia_comienzo()),
		Util.dateToIsoHour(j.getHora_entrada()), Util.dateToIsoString(j.getDia_fin()),
		Util.dateToIsoHour(j.getHora_salida()), j.isLunes(), j.isMartes(), j.isMiercoles(), j.isJueves(),
		j.isViernes(), j.isSabado(), j.isDomingo(), id);
    }

    public List<JornadaLaboralRecord> findJornadaById(int id) {
	String sql = "SELECT * FROM TJORNADALABORAL WHERE ID = ?";

	return db.executeQueryPojo(JornadaLaboralRecord.class, sql, id);
    }

    public List<JornadaLaboralRecord> findAll() {
	String sql = "SELECT * FROM TJORNADALABORAL";

	return db.executeQueryPojo(JornadaLaboralRecord.class, sql);
    }

    public List<JornadaLaboralRecord> findByName(String nombreTrabajador) {
	String sql = "SELECT * FROM TJORNADALABORAL WHERE NOMBRE_TRABAJADOR LIKE ?";

	return db.executeQueryPojo(JornadaLaboralRecord.class, sql, "%" + nombreTrabajador + "%");
    }

    public List<JornadaLaboralRecord> findJornadaByComienzo(Date diaComienzo) {
	String sql = "SELECT * FROM TJORNADALABORAL WHERE DIA_COMIENZO = ?";

	return db.executeQueryPojo(JornadaLaboralRecord.class, sql, Util.dateToIsoString(diaComienzo));
    }

    public List<JornadaLaboralRecord> findJornadaByFin(Date diaFin) {
	String sql = "SELECT * FROM TJORNADALABORAL WHERE DIA_COMIENZO = ?";

	return db.executeQueryPojo(JornadaLaboralRecord.class, sql, Util.dateToIsoString(diaFin));
    }

    public List<JornadaLaboralRecord> findJornadaByEntrada(Date horaEntrada) {
	String sql = "SELECT * FROM TJORNADALABORAL WHERE HORA_ENTRADA = ?";

	return db.executeQueryPojo(JornadaLaboralRecord.class, sql, Util.dateToIsoHour(horaEntrada));
    }

    public List<JornadaLaboralRecord> findJornadaBySalida(Date horaSalida) {
	String sql = "SELECT * FROM TJORNADALABORAL WHERE HORA_SALIDA = ?";

	return db.executeQueryPojo(JornadaLaboralRecord.class, sql, Util.dateToIsoHour(horaSalida));
    }
}
