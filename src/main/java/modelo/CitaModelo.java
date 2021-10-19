package modelo;

import java.util.List;

import dtos.CitaDto;
import util.Database;

public class CitaModelo {

	private Database db = new Database();
	
	public List<CitaDto> getListaCitasMedico(int id) {
		String sql = "SELECT * FROM TCITAS c where id_medico = " + id;
		return db.executeQueryPojo(CitaDto.class, sql);
	}
	
	public void updateAcudio(int id) {
		String sql = "UPDATE TCITAS SET acudio = 1 WHERE id = " + id;
		db.executeUpdate(sql);
	}
	
	public List<CitaDto> getListaCitasFecha(String fecha){
		String sql = "SELECT * FROM TCITAS c WHERE c.fecha = " + fecha;
		return db.executeQueryPojo(CitaDto.class, sql);
	}
	
	public List<CitaDto> getAllCitas(){
		String sql = "SELECT * FROM TCITAS";
		return db.executeQueryPojo(CitaDto.class, sql);
	}
	
	public List<CitaDto> getCitasFecha(String fecha, int idMedico){
		String sql = "SELECT * FROM TCITAS c WHERE c.fecha = ? AND c.id_medico = ?";
		return db.executeQueryPojo(CitaDto.class, sql, fecha, idMedico);
	}
}
