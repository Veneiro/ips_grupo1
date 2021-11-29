package modelo;

import java.util.List;

import dtos.CitaPendienteDto;
import util.Database;

public class CitaPendienteModelo {

	private static Database db = new Database();

	public void insertCita(CitaPendienteDto cita) {
		String sql = "INSERT INTO TCITASPENDIENTES (ID,HORA_ENTRADA,"
				+ "HORA_SALIDA,UBICACION,"
				+ "ID_PACIENTE,ID_MEDICO, FECHA, CONTACTO_MEDICO, ESTADO)"
				+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		db.executeUpdate(sql, cita.getID(), cita.getHORA_ENTRADA(),
				cita.getHORA_SALIDA(), cita.getUBICACION(),
				cita.getID_PACIENTE(), cita.getID_MEDICO(), cita.getFECHA(),
				cita.getCONTACTO_MEDICO(), cita.getESTADO());
	}

	public void updateCita(CitaPendienteDto cita) {
		String sql = "UPDATE TCITASPENDIENTES set HORA_ENTRADA = ?, "
				+ "HORA_SALIDA = ?, UBICACION = ?, ID_PACIENTE = ?, "
				+ "ID_MEDICO = ?, FECHA = ?, CONTACTO_MEDICO = ?, "
				+ "ESTADO = ? WHERE ID = ?";
		db.executeUpdate(sql, cita.getHORA_ENTRADA(), cita.getHORA_SALIDA(),
				cita.getUBICACION(), cita.getID_PACIENTE(), cita.getID_MEDICO(),
				cita.getFECHA(), cita.getCONTACTO_MEDICO(), cita.getESTADO(),
				cita.getID());
	}

	public List<CitaPendienteDto> getCitasPorAprobar() {
		String sql = "SELECT * FROM TCITASPENDIENTES";
		return db.executeQueryPojo(CitaPendienteDto.class, sql);
	}

	public List<CitaPendienteDto> getCitasByState(String state) {
		String sql = "SELECT * FROM TCITASPENDIENTES WHERE ESTADO=" + state;
		return db.executeQueryPojo(CitaPendienteDto.class, sql);
	}

}
