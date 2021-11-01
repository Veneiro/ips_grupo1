package modelo;

import java.util.List;

import dtos.JornadaLaboralDto;
import util.Database;
import util.Util;

public class JornadaModelo {

    private Database db = new Database();

    public void addJornada(JornadaLaboralDto j) {
	String sql = "INSERT INTO TJORNADALABORAL(NOMBRE_TRABAJADOR, DIA_COMIENZO, HORA_ENTRADA, DIA_FIN, HORA_SALIDA, LUNES, MARTES, MIERCOLES, JUEVES, VIERNES, SABADO, DOMINGO) values (?,?,?,?,?,?,?,?,?,?,?,?)";

	db.executeUpdate(sql, j.getNombreEmpleado(), Util.dateToIsoString(j.getDiaComienzo()),
		Util.dateToIsoHour(j.getHoraEntrada()), Util.dateToIsoString(j.getDiaFin()),
		Util.dateToIsoHour(j.getHoraSalida()), j.isLunes(), j.isMartes(), j.isMiercoles(), j.isJueves(),
		j.isViernes(), j.isSabado(), j.isDomingo());
    }

    public void updateJornada(JornadaLaboralDto j) {
	String sql = "UPDATE TJORNADALABORAL SET NOMBRE_TRABAJADOR = ?, DIA_COMIENZO = ?, HORA_ENTRADA = ?, DIA_FIN = ?, HORA_SALIDA = ?, LUNES = ?, MARTES = ?, MIERCOLES = ?, JUEVES = ?, VIERNES = ?, SABADO = ?, DOMINGO = ? where id = ?";

	db.executeUpdate(sql, j.getNombreEmpleado(), Util.dateToIsoString(j.getDiaComienzo()),
		Util.dateToIsoHour(j.getHoraEntrada()), Util.dateToIsoString(j.getDiaFin()),
		Util.dateToIsoHour(j.getHoraSalida()), j.isLunes(), j.isMartes(), j.isMiercoles(), j.isJueves(),
		j.isViernes(), j.isSabado(), j.isDomingo());
    }

    public List<JornadaLaboralDto> findJornadaById(int id) {
	String sql = "SELECT * FROM TJORNADALABORAL WHERE ID = ?";

	return db.executeQueryPojo(JornadaLaboralDto.class, sql, id);
    }
}
