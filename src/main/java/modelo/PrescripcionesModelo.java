package modelo;

import java.time.Instant;
import java.util.Date;
import java.util.List;

import dtos.MedicamentoDto;
import dtos.PrescripcionDto;
import util.Database;
import util.Util;

public class PrescripcionesModelo {

	private Database db = new Database();

	public List<PrescripcionDto> getListaPrescripciones() {
		String sql = "SELECT NOMBRE FROM TPRESCRIPCIONES";
		return db.executeQueryPojo(PrescripcionDto.class, sql);
	}

	public List<MedicamentoDto> getListaMedicamentos() {
		String sql = "SELECT NOMBRE FROM TMEDICAMENTOS";
		return db.executeQueryPojo(MedicamentoDto.class, sql);
	}

	public void addPrescripcion(PrescripcionDto p) {
		String sql = "INSERT INTO TPRESCRIPCIONES(INDICACIONES, MEDICAMENTO, CANTIDAD, INTERVALO, DURACION, FECHA, HORA) values (?,?,?,?,?,?,?)";

		db.executeUpdate(sql, p.getIndicaciones(), p.getMedicamento(), p.getCantidad(), p.getIntervalo(),
				p.getDuracion(), Util.dateToIsoString(Date.from(Instant.now())),
				Util.dateToIsoHour(Date.from(Instant.now())));
	}

	public void addMedicamento(MedicamentoDto m) {
		String sql = "INSERT INTO TMEDICAMENTOS (NOMBRE) values (?)";

		db.executeUpdate(sql, m.getNombre());
	}
	
	public List<PrescripcionDto> getPrescripcionesById(int id){
		String sql = "SELECT * FROM TPrescripciones p where p.id = " + id;
		return db.executeQueryPojo(PrescripcionDto.class, sql);
	}
}
