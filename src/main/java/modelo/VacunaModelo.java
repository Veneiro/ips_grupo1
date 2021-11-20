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
}
