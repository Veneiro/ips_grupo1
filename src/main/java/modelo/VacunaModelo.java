package modelo;

import java.util.List;

import dtos.VacunaDto;
import util.Database;

public class VacunaModelo {

	private Database db = new Database();
	
	public List<VacunaDto> getVacunasByPacienteId(int id){
		String sql = "select * from TVacunas v where v.paciente_id = ?";
		return db.executeQueryPojo(VacunaDto.class, sql, id);
	}
	
	public List<VacunaDto> getAllNotRepitedVacunas(){
		String sql = "select DISTINCT vacuna from TVacunas";
		return db.executeQueryPojo(VacunaDto.class, sql);
	}
	
	public void addVacuna(int idPaciente, String fecha, String hora, String vacuna) {
		String sql = "insert into TVacunas (paciente_id, fecha, hora, vacuna) values (?,?,?,?)";
		db.executeUpdate(sql, idPaciente, fecha, hora, vacuna);
	}
	
	public void updateVacuna( String vacunaN, String horaN, String fechaN, String vacunaA, String horaA, String fecha, int idPaciente) {
		String sql = "update TVacunas set vacuna = ?, hora = ?, fecha = ? where vacuna = ? AND hora = ? AND fecha = ? AND paciente_id = ?";
		db.executeUpdate(sql, vacunaN, horaN, fechaN, vacunaA, horaA, fecha, idPaciente);
	}
	
	public List<VacunaDto> getVacunasByNombre(String vacuna){
		String sql = "select * from TVacunas where vacuna = ?";
		return db.executeQueryPojo(VacunaDto.class, sql, vacuna);
	}
}
