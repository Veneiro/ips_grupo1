package modelo;

import java.time.Instant;
import java.util.Date;
import java.util.List;

import records.PrescripcionRecord;
import util.Database;
import util.Util;

public class PrescripcionesModelo {

    private Database db = new Database();

    public List<PrescripcionRecord> getListaPrescripciones() {
	String sql = "SELECT * FROM TPRESCRIPCIONES";
	return db.executeQueryPojo(PrescripcionRecord.class, sql);
    }

    public List<PrescripcionRecord> getListaPrescripcionesNoRepetidas() {
	String sql = "SELECT DISTINCT NOMBRE, INDICACIONES, CANTIDAD, INTERVALO, DURACION FROM TPRESCRIPCIONES";
	return db.executeQueryPojo(PrescripcionRecord.class, sql);
    }

    public List<PrescripcionRecord> findById(int id) {
	String sql = "SELECT * FROM TPRESCRIPCIONES WHERE ID = ?";
	return db.executeQueryPojo(PrescripcionRecord.class, sql, id);
    }

    public void addPrescripcion(PrescripcionRecord p) {
	String sql = "INSERT INTO TPRESCRIPCIONES(NOMBRE, PACIENTE_ID, INDICACIONES, MEDICAMENTO, CANTIDAD, INTERVALO, DURACION, FECHA, HORA) values (?,?,?,?,?,?,?,?,?)";
	}
	public void addMedicamento(MedicamentoDto m) {
		String sql = "INSERT INTO TMEDICAMENTOS (NOMBRE) values (?)";

		db.executeUpdate(sql, p.getNombre(), p.getPaciente_id(), p.getIndicaciones(), p.isMedicamento(),
		p.getCantidad(), p.getIntervalo(), p.getDuracion(), Util.dateToIsoString(Date.from(Instant.now())),
		Util.dateToIsoHour(Date.from(Instant.now())));
	}
	
	public List<PrescripcionDto> getPrescripcionesById(int id){
		String sql = "SELECT * FROM TPrescripciones p where p.id = " + id;
		return db.executeQueryPojo(PrescripcionDto.class, sql);
	}
	
}
