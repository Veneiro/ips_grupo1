package logic;

import java.util.ArrayList;
import java.util.List;

import dtos.MedicoDto;
import dtos.PacienteDto;
import modelo.MedicoModelo;
import modelo.PacienteModelo;

/**
 * Almacena los médicos y los pacientes.
 * 
 * @author Santiago
 *
 */
public class Admin {

	private List<MedicoDto> medicos;
	private List<PacienteDto> pacientes;

	public Admin() {
		MedicoModelo medicoModelo = new MedicoModelo();
		medicos = medicoModelo.getListaMedicos();
		PacienteModelo pacienteModelo = new PacienteModelo();
		pacientes = pacienteModelo.getListaPacientes();
	}

	public List<MedicoDto> getListaMedicos() {
		return new ArrayList<MedicoDto>(medicos);
	}

	public PacienteDto[] getListaPacientes() {
		return pacientes.toArray(new PacienteDto[pacientes.size()]);
	}

	/**
	 * Devuelve una lista que contiene sólo los médicos cuya representación en
	 * String contenga el filtro pasado.
	 * 
	 * @param filtro
	 * @return lista filtrada de los médicos
	 */
	public List<MedicoDto> filtrarMedicos(String filtro) {
		List<MedicoDto> listaFiltrada = new ArrayList<MedicoDto>();
		for (MedicoDto m : medicos) {
			if (m.toString().trim().toLowerCase().contains(filtro.toLowerCase())) {
				listaFiltrada.add(m);
			}
		}
		return listaFiltrada;
	}
	
	public List<PacienteDto> filtrarPacientes(String filtro) {
		List<PacienteDto> listaFiltrada = new ArrayList<PacienteDto>();
		for (PacienteDto p : pacientes) {
			if (p.toString().trim().toLowerCase().contains(filtro.toLowerCase())) {
				listaFiltrada.add(p);
			}
		}
		return listaFiltrada;
	}
}
