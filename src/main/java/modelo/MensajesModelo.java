package modelo;

import java.util.List;

import dtos.CauseDto;
import dtos.MensajesDto;
import util.Database;

public class MensajesModelo {

	private static Database db = new Database();

	public void sendMensaje(MensajesDto mdto) {
		String sql = "INSERT INTO TMENSAJES (ASUNTO, MENSAJE, REMITENTE, DESTINATARIO, ADJUNTO) "
				+ "VALUES(?,?,?,?,?)";

		db.executeUpdate(sql, mdto.getASUNTO(), mdto.getMENSAJE(),
				mdto.getREMITENTE(), mdto.getDESTINATARIO(), mdto.getADJUNTO());
	}
	
	public List<MensajesDto> getMensajes() {
		String sql = "SELECT * FROM TMENSAJES";
		return db.executeQueryPojo(MensajesDto.class, sql);
	}
}
