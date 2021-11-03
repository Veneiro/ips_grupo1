package logic;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import dtos.CitaDto;
import dtos.MedicoDto;
import dtos.PacienteDto;
import lombok.Getter;
import lombok.Setter;
import modelo.EscritorDeDatos;

/**
 * Cita con el formato de la aplicación.
 * 
 * @author Santiago
 *
 */
@Getter
@Setter
public class Cita {

	private PacienteDto paciente;
	private List<MedicoDto> medicos;
	private LocalTime horario_inicio;
	private LocalTime horario_fin;
	private String ubicacion;
	private String contacto;
	private String nombre_paciente;
	private int id;

	public Cita(PacienteDto paciente, List<MedicoDto> medicos) {
		this.paciente = paciente;
		this.medicos = new ArrayList<MedicoDto>(medicos);
		setHorario_inicio(null);
		setHorario_fin(null);
		setUbicacion(null);
		setContacto(null);
	}

	public PacienteDto getPaciente() {
		return paciente;
	}

	public List<MedicoDto> getMedicos() {
		return new ArrayList<MedicoDto>(medicos);
	}

	/**
	 * Guarda la cita en la base de datos.
	 */
	public void grabar() {
		int id = valorAbsoluto(new Random().nextInt());
		int id_paciente = paciente.getId();
		CitaDto c = new CitaDto();
		c.setId(id);
		c.setContacto(contacto);
		if (horario_inicio == null || horario_fin == null) {
			c.setHorario_inicio(null);
			c.setHorario_fin(null);
		} else {
			c.setHorario_inicio(horario_inicio.toString());
			c.setHorario_fin(horario_fin.toString());
		}
		c.setUbicacion(ubicacion);
		c.setId_paciente(id_paciente);

		new EscritorDeDatos().grabarCita(c, getMedicos());
	}

	private int valorAbsoluto(int x) {
		return x > 0 ? x : -x;
	}

	public LocalTime getHorario_inicio() {
		return horario_inicio;
	}

	public void setHorario_inicio(LocalTime horario_inicio) {
		this.horario_inicio = horario_inicio;
	}

	public LocalTime getHorario_fin() {
		return horario_fin;
	}

	public void setHorario_fin(LocalTime horario_fin) {
		this.horario_fin = horario_fin;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public String getContacto() {
		return contacto;
	}

	public void setContacto(String contacto) {
		this.contacto = contacto;
	}
}
