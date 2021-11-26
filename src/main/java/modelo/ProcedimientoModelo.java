package modelo;

import java.util.List;

import dtos.ProcedimientoDto;
import util.Database;

public class ProcedimientoModelo {
	
	private Database db = new Database();
	
	public List<ProcedimientoDto> getProcedimientosByNombre(String nombre){
		String sql = "select * from TProcedimientos where procedimiento like ?";
		return db.executeQueryPojo(ProcedimientoDto.class, sql, nombre);
	}
	
	public List<ProcedimientoDto> getAllProcedimientos(){
		String sql = "select * from TProcedimientos";
		return db.executeQueryPojo(ProcedimientoDto.class, sql);
	}
	
	public void insertProcedimiento(int cita_id, String procedimiento, String fecha, String hora) {
		String sql = "insert into TProcedimientos (cita_id, procedimiento, fecha, hora) values (?,?,?,?)";
		db.executeUpdate(sql, cita_id, procedimiento, fecha, hora);
		
	}

	public List<ProcedimientoDto> findProcedimiento(int id, String nombreProcedimiento, String fecha, String hora) {
		String sql = "select * from TProcedimientos where cita_id = ? AND procedimiento = ? AND fecha = ? AND hora = ?";
		return db.executeQueryPojo(ProcedimientoDto.class, sql, id, nombreProcedimiento, fecha, hora);
	}

	public void updateProcedimiento(String procedimiento, String fecha, String hora, int cita_id, String procedimientoA, String fechaA, String horaA) {
		String sql = "update TProcedimientos set procedimiento = ?, fecha = ?, hora = ? where cita_id = ? AND procedimiento = ? AND fecha = ? AND hora = ?";
		db.executeUpdate(sql, procedimiento, fecha, hora, cita_id, procedimientoA, fechaA, horaA);
	}
}
