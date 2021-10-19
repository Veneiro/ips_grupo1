package modelo;

import java.util.List;

import dtos.HistorialDto;
import util.Database;

public class HistorialModelo {

	private Database db = new Database();
	
	public List<HistorialDto> getHistorialPaciente(int idPaciente) {
		String sql = "Select * FROM THistoriales h WHERE h.idPaciente = " + idPaciente;
		return db.executeQueryPojo(HistorialDto.class, sql);
	}
}
