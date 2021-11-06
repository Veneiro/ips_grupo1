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
	
	public List<HistorialDto> getDiagnosticoPaciente (int idPaciente) {
		String sql = "Select diagnostico FROM THistoriales h WHERE h.idPaciente = " + idPaciente;
		return db.executeQueryPojo(HistorialDto.class, sql);
	}
	
	public List<HistorialDto> getDiagnosticosAntiguosPaciente (int idPaciente) {
		String sql = "Select diagnosticosAntiguos FROM THistoriales h WHERE h.idPaciente = " + idPaciente;
		return db.executeQueryPojo(HistorialDto.class, sql);
	}
	
	public void updateDiagnosticos(String nuevoDiagnostico, String previosDiagnosticos, int idPaciente) {
		String sql = "Update THistoriales set diagnostico = ?, diagnosticosAntiguos = ? WHERE idPaciente = ?";
		db.executeUpdate(sql, nuevoDiagnostico, previosDiagnosticos, idPaciente);
	}
	
	public List<Object[]> getMasComunDiagnostico(){
		String sql = "Select MAX(contador) FROM (Select count(diagnostico) from THistoriales)";
		return db.executeQueryArray(sql);
	}
}
