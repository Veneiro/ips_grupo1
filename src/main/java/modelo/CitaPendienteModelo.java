package modelo;

import java.util.List;

import dtos.CitaPendienteDto;
import util.Database;

public class CitaPendienteModelo {

	private static Database db = new Database();

	public void insertCita(CitaPendienteDto cita) {
		String sql = "INSERT INTO TCITASPENDIENTES (ID,HORA_ENTRADA,"
				+ "HORA_SALIDA,NOMBRE_PACIENTE,NOMBRE_MEDICO,UBICACION,"
				+ "CONTACTO_MEDICO,IDPACIENTE,ID_MEDICO, FECHA, ESTADO)"
				+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		db.executeUpdate(sql, cita.getID(), cita.getHORA_ENTRADA(),
				cita.getHORA_SALIDA(), cita.getNOMBRE_PACIENTE(),
				cita.getNOMBRE_MEDICO(), cita.getUBICACION(),
				cita.getCONTACTO_MEDICO(), cita.getIDPACIENTE(),
				cita.getID_MEDICO(), cita.getFECHA(), cita.getESTADO());
	}

	public void updateCita(CitaPendienteDto cita) {
		String sql = "UPDATE TCITASPENDIENTES set HORA_ENTRADA = ?, "
				+ "HORA_SALIDA = ?, NOMBRE_PACIENTE = ?, NOMBRE_MEDICO = ?, "
				+ "UBICACION = ?, CONTACTO_MEDICO = ?, IDPACIENTE = ?, "
				+ "ID_MEDICO = ?, FECHA = ?, ESTADO = ? WHERE ID = ?";
		db.executeUpdate(sql, cita.getHORA_ENTRADA(), cita.getHORA_SALIDA(),
				cita.getNOMBRE_PACIENTE(), cita.getNOMBRE_MEDICO(),
				cita.getUBICACION(), cita.getCONTACTO_MEDICO(),
				cita.getIDPACIENTE(), cita.getID_MEDICO(), cita.getFECHA(),
				cita.getESTADO(), cita.getID());
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
