package modelo;

import java.util.List;

import records.PrescripcionRecord;
import util.Database;

public class PrescripcionesModelo {

    private Database db = new Database();

    public List<PrescripcionRecord> getListaPrescripciones() {
	String sql = "SELECT * FROM TPRESCRIPCIONES";
	return db.executeQueryPojo(PrescripcionRecord.class, sql);
    }

    public List<PrescripcionRecord> findByPacienteId(int idPaciente) {
	String sql = "SELECT * FROM TPRESCRIPCIONES WHERE PACIENTE_ID = ?";
	return db.executeQueryPojo(PrescripcionRecord.class, sql, idPaciente);
    }

    public List<PrescripcionRecord> getListaPrescripcionesNoRepetidas() {
	String sql = "SELECT DISTINCT NOMBRE, INDICACIONES, CANTIDAD, INTERVALO, DURACION, MEDICAMENTO FROM TPRESCRIPCIONES ORDER BY NOMBRE";
	return db.executeQueryPojo(PrescripcionRecord.class, sql);
    }

    public List<PrescripcionRecord> findById(int id) {
	String sql = "SELECT * FROM TPRESCRIPCIONES WHERE ID = ?";
	return db.executeQueryPojo(PrescripcionRecord.class, sql, id);
    }

    public void addPrescripcion(PrescripcionRecord p) {
	String sql = "INSERT INTO TPRESCRIPCIONES(NOMBRE, PACIENTE_ID, INDICACIONES, MEDICAMENTO, CANTIDAD, INTERVALO, DURACION, FECHA, HORA) values (?,?,?,?,?,?,?,?,?)";

	db.executeUpdate(sql, p.getNombre(), p.getPaciente_id(), p.getIndicaciones(), p.isMedicamento(),
		p.getCantidad(), p.getIntervalo(), p.getDuracion(), p.getFecha(), p.getHora());
    }

    public List<PrescripcionRecord> getListaPrescripcionesPaciente(int idPaciente) {
	String sql = "SELECT NOMBRE, INDICACIONES, CANTIDAD, INTERVALO, DURACION, FECHA FROM TPRESCRIPCIONES  WHERE PACIENTE_ID = ?  ORDER BY FECHA";
	return db.executeQueryPojo(PrescripcionRecord.class, sql, idPaciente);
    }

    public List<PrescripcionRecord> getListaPrescripcionesNoRepetidasByName(String text) {
	String sql = "SELECT DISTINCT NOMBRE, INDICACIONES, CANTIDAD, INTERVALO, DURACION, MEDICAMENTO FROM TPRESCRIPCIONES WHERE NOMBRE LIKE ? ORDER BY NOMBRE";
	return db.executeQueryPojo(PrescripcionRecord.class, sql, "%" + text + "%");
    }
}
