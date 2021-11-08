package modelo;

import java.util.List;

import dtos.CitaDto;
import util.Database;

public class CitaModelo {

	private Database db = new Database();

	public List<CitaDto> getListaCitasMedico(int id) {
		String sql = "SELECT * FROM TCITAS c where id_medico = " + id;
		return db.executeQueryPojo(CitaDto.class, sql);
	}

	public void updateAcudio(int id, String texto) {
		String sql = "UPDATE TCITAS SET acudio = ? WHERE id = " + id;
		db.executeUpdate(sql, texto);
	}

	public List<CitaDto> getListaCitasFecha(String fecha) {
		String sql = "SELECT * FROM TCITAS c WHERE c.fecha = " + fecha;
		return db.executeQueryPojo(CitaDto.class, sql);
	}

	public List<CitaDto> getAllCitas() {
		String sql = "SELECT * FROM TCITAS";
		return db.executeQueryPojo(CitaDto.class, sql);
	}

	public List<CitaDto> getCitasFecha(String fecha, int idMedico) {
		String sql = "SELECT * FROM TCITAS c WHERE c.fecha = ? AND c.id_medico = ?";
		return db.executeQueryPojo(CitaDto.class, sql, fecha, idMedico);
	}

	public List<CitaDto> getAllCitasFecha(String fecha) {
		String sql = "SELECT * FROM TCITAS c WHERE c.fecha = ?";
		return db.executeQueryPojo(CitaDto.class, sql, fecha);
	}
	
	public void updateCitaPendiente(int id) {
		String sql = "DELETE FROM TCITASPENDIENTES WHERE id = " + id;
		db.executeUpdate(sql, null);
	}

	public void addCita(CitaDto cita) {
		String sql = "INSERT INTO TCITAS (id,horario_inicio,horario_fin,ubicacion,contacto,id_paciente,id_medico,acudio,fecha,informacion,especialidad, hora_entrada, hora_salida, nombre_paciente)"
				+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?, ?, ?, ?)";
		db.executeUpdate(sql, cita.getId(), cita.getHorario_inicio(), cita.getHorario_fin(), cita.getUbicacion(),
				cita.getContacto(), cita.getId_paciente(), cita.getId_medico(), cita.getAcudio(), cita.getFecha(),
				cita.getInformacion(), cita.getEspecialidad(), null, null, cita.getNombre_paciente());
	}

	public void updateCita(CitaDto cita) {
		String sql = "UPDATE TCITAS set horario_inicio = ?, horario_fin = ?, ubicacion = ?, contacto = ?, fecha = ? where id = ?";
		db.executeUpdate(sql, cita.getHorario_inicio(), cita.getHorario_fin(), cita.getUbicacion(), cita.getContacto(),
				cita.getFecha(), cita.getId());
	}
}
