package modelo;

import java.util.List;

import dtos.DiagnosticoDto;
import util.Database;

public class DiagnosticoModelo {

	private Database db = new Database();
	
	public List<DiagnosticoDto> getDiaganosticoByPacienteId(int idPaciente){
		String sql = "Select * From TDiagnosticos where id_paciente = " + idPaciente + " Order by fecha DESC";
		return db.executeQueryPojo(DiagnosticoDto.class, sql);
	}
	
	public void addDiagnostico(DiagnosticoDto diagnostico) {
		String sql = "Insert into TDiagnosticos(id_paciente, diagnostico, fecha, id_medico, hora_creacion, actual) values (?,?,?,?,?,?) ";
		db.executeUpdate(sql, diagnostico.getId_paciente(), diagnostico.getDiagnostico(), diagnostico.getFecha(),
				diagnostico.getId_medico(), diagnostico.getHora_creacion(), diagnostico.getActual());
		
	}
	
	public void updateDiagnosticosNoActual(int id) {
		String sql = "update TDiagnosticos set actual = ? where id = ?";
		db.executeUpdate(sql, "No", id);
	}
	
	public List<DiagnosticoDto> getAllDiagnosticos() {
		String sql = "Select * From TDiagnosticos";
		return db.executeQueryPojo(DiagnosticoDto.class, sql);
	}
}
