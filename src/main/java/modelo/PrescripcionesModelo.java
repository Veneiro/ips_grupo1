package modelo;

import java.time.Instant;
import java.util.Date;
import java.util.List;

import dtos.PrescripcionDto;
import util.Database;
import util.Util;

public class PrescripcionesModelo {

    private Database db = new Database();

    public List<PrescripcionDto> getListaPrescripciones() {
	String sql = "SELECT * FROM TPRESCRIPCIONES";
	return db.executeQueryPojo(PrescripcionDto.class, sql);
    }

    public List<PrescripcionDto> findById(int id) {
	String sql = "SELECT * FROM TPRESCRIPCIONES WHERE ID = ?";
	return db.executeQueryPojo(PrescripcionDto.class, sql, id);
    }

    public void addPrescripcion(PrescripcionDto p) {
	String sql = "INSERT INTO TPRESCRIPCIONES(NOMBRE, PACIENTE_ID, INDICACIONES, MEDICAMENTO, CANTIDAD, INTERVALO, DURACION, FECHA, HORA) values (?,?,?,?,?,?,?,?,?)";

	db.executeUpdate(sql, p.getNombre(), p.getPaciente_id(), p.getIndicaciones(), p.isMedicamento(),
		p.getCantidad(), p.getIntervalo(), p.getDuracion(), Util.dateToIsoString(Date.from(Instant.now())),
		Util.dateToIsoHour(Date.from(Instant.now())));
    }
}
