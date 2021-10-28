package modelo;

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
}