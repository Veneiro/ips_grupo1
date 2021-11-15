package modelo;

import java.util.List;

import dtos.PacienteDto;
import util.Database;

public class PacienteModelo {

    private Database db = new Database();

    public List<PacienteDto> getPacienteById(int id) {
	String sql = "SELECT * FROM TPACIENTES p WHERE p.id = " + id;
	return db.executeQueryPojo(PacienteDto.class, sql);
    }

    public List<PacienteDto> getIdPacienteByNombreAndFechaCita(String nombre, String fecha) {
	String sql = "SELECT p.id FROM TPACIENTES p, TCITAS c WHERE p.nombre = ? AND c.id_paciente = p.id "
		+ "AND c.fecha = ?";
	return db.executeQueryPojo(PacienteDto.class, sql, nombre, fecha);
    }

    public List<PacienteDto> getListaPacientes() {
	String sql = "SELECT * FROM TPACIENTES";
	return db.executeQueryPojo(PacienteDto.class, sql);
    }
}
