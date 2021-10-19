package logic;

import java.util.ArrayList;
import java.util.List;

import dtos.MedicoDto;
import dtos.PacienteDto;
import modelo.LectorDeDatos;

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
		LectorDeDatos lector = new LectorDeDatos();
		medicos = lector.getListaMedicos();
		pacientes = lector.getListaPacientes();
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
			if (m.toString().trim().toLowerCase().contains(filtro)) {
				listaFiltrada.add(m);
			}
		}
		return listaFiltrada;
	}
}
