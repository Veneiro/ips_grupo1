package modelo;

import java.util.List;

import dtos.AntecedenteDto;
import util.Database;

public class AntecedentesModelo {

	private Database db = new Database();
	
	
	public List<AntecedenteDto> getByPacienteId(int idPaciente){
		String sql = "select * from TAntecedentes where paciente_id = ?";
		return db.executeQueryPojo(AntecedenteDto.class, sql, idPaciente);
	}
	
	public void addAntecedente(int idPaciente, String antecedente, String fecha) {
		String sql = "insert into TAntecedentes (paciente_id,antecedente,fecha_comienzo) values (?,?,?)";
		db.executeUpdate(sql, idPaciente, antecedente, fecha);
	}

	public List<AntecedenteDto> getAntecedentesByPacienteId(int idPaciente) {
		String sql = "select * from TAntecedentes where paciente_id = ?";
		return db.executeQueryPojo(AntecedenteDto.class, sql, idPaciente);
	}

	public void updateAntecedente(String antecedente, String fecha, String antecedenteA, String fechaA) {
		String sql = "update TAntecedentes set antecedente = ?, fecha_comienzo = ? where antecedente = ? AND fecha_comienzo = ?";
		db.executeUpdate(sql, antecedente, fecha, antecedenteA, fechaA);
		
	}
}
