package modelo;

import java.util.List;

import dtos.CauseDto;
import dtos.CitaDto;
import util.Database;

public class CauseModel {

	private static Database db = new Database();

	public void insertCause(CauseDto cdto) {
		String sql = "INSERT INTO TCAUSA (OBSERVATIONS, DATE, HOUR, NAME, ID) "
				+ "VALUES(?,?,?,?,?)";

		db.executeUpdate(sql, cdto.getOBSERVATIONS(), cdto.getDATE(),
				cdto.getHOUR(), cdto.getNAME(), cdto.getID());
	}

	public void insertCita(CitaDto cidto) {
		int id = cidto.getId();
		String sql = "UPDATE TCITAS "
				+ "SET horario_inicio = ?, horario_fin = ?, "
				+ "ubicacion = ?, id_paciente = ?, id_medico = ?"
				+ ", acudio = ?, fecha = ?, informacion = ?"
				+ ", hora_entrada = ?, hora_salida = ?"
				+ "WHERE id = " + id;

		db.executeUpdate(sql, cidto.getHorario_inicio(), cidto.getHorario_fin(),
				cidto.getUbicacion(), cidto.getId_paciente(), cidto.getId_medico(), cidto.getAcudio(),
				cidto.getFecha(), cidto.getInformacion(), cidto.getHora_entrada(),
				cidto.getHora_salida());
	}

	public List<CauseDto> getCauseList() {
		String sql = "SELECT * FROM TCAUSA";
		return db.executeQueryPojo(CauseDto.class, sql);
	}

	public List<CitaDto> getCitasList() {
		String sql = "SELECT * FROM TCITAS";
		return db.executeQueryPojo(CitaDto.class, sql);
	}
}
