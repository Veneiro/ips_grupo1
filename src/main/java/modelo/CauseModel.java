package modelo;

import java.util.List;

import dtos.CauseDto;
import dtos.CitaDto;
import util.Database;

public class CauseModel {

	private static Database db = new Database();

	public void insertCause(CauseDto cdto) {
		String sql = "INSERT INTO TCAUSA (OBSERVATIONS, DATE, HOUR, NAME, ID) VALUES(?,?,?,?,?)";

		db.executeUpdate(sql, cdto.getOBSERVATIONS(), cdto.getDATE(),
				cdto.getHOUR(), cdto.getNAME(), cdto.getID());
	}

	public void insertCita(CitaDto cidto) {
		String sql = "INSERT INTO TCITAS (id, horario_inicio, horario_fin, ubicacion, contacto, id_paciente, id_medico, acudio, fecha) VALUES(?,?,?,?,?,?,?,?,?)";

		db.executeUpdate(sql, cidto.getId(), cidto.getHorario_inicio(),
				cidto.getHorario_fin(), cidto.getUbicacion(),
				cidto.getContacto(), cidto.getId_paciente(),
				cidto.getId_medico(), cidto.getAcudio(), cidto.getFecha());
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