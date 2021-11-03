package logic;

import java.util.ArrayList;
import java.util.List;

import dtos.CitaDto;
import dtos.MedicoDto;
import modelo.MedicoAsignadoACitaModelo;
import util.ApplicationException;

public class ModificarCitas {

	private CitaDto cita;
	private List<MedicoDto> medicosExistentes;
	private List<MedicoDto> medicosA�adibles;
	private List<MedicoDto> medicosElegidos;

	private MedicoAsignadoACitaModelo modeloMedicoCita = new MedicoAsignadoACitaModelo();

	public ModificarCitas(CitaDto cita) {
		medicosElegidos = new ArrayList<MedicoDto>();
		setCita(cita);
		setMedicosExistentes(modeloMedicoCita.getMedicosDeCita(cita.getId()));
		setMedicosA�adibles(modeloMedicoCita.getAllMedicosExceptoDeCita(cita.getId()));
	}

	public void seleccionarMedicos(List<MedicoDto> listaMedicos) {
		for (MedicoDto newMedico : listaMedicos) {
			if (!medicosElegidos.contains(newMedico))
				medicosElegidos.add(newMedico);
		}
	}

	public void deseleccionarMedicos(List<MedicoDto> listaMedicos) {
		for (MedicoDto medicoABorrar : listaMedicos) {
			medicosElegidos.remove(medicoABorrar);
		}
	}

	public String getStringMedicosElegidos() {
		String txt = "";
		for (MedicoDto ms : medicosElegidos) {
			txt += ms.toString() + "\n";
		}
		return txt;
	}

	public boolean hayMedicosElegidos() {
		return !medicosElegidos.isEmpty();
	}
	
	public CitaDto getCita() {
		return cita;
	}

	public List<MedicoDto> getMedicosExistentes() {
		return new ArrayList<MedicoDto>(medicosExistentes);
	}

	public List<MedicoDto> getMedicosA�adibles() {
		return new ArrayList<MedicoDto>(medicosA�adibles);
	}

	/**
	 * Devuelve una lista que contiene s�lo los m�dicos cuya representaci�n en
	 * String contenga el filtro pasado.
	 * 
	 * @param filtro
	 * @return lista filtrada de los m�dicos
	 */
	public List<MedicoDto> filtrarMedicos(String filtro) {
		List<MedicoDto> listaFiltrada = new ArrayList<MedicoDto>();
		for (MedicoDto m : medicosA�adibles) {
			if (m.toString().trim().toLowerCase().contains(filtro.toLowerCase())) {
				listaFiltrada.add(m);
			}
		}
		return listaFiltrada;
	}

	public List<MedicoDto> filtrarMedicosPorEspecialidad(String especialidad) {
		List<MedicoDto> listaFiltrada = new ArrayList<MedicoDto>();
		for (MedicoDto m : medicosA�adibles) {
			if (m.getEspecialidad() != null)
				if (m.getEspecialidad().equals(especialidad)) {
					listaFiltrada.add(m);
				}
		}
		return listaFiltrada;
	}
	
	public void actualizarCita() {
		MedicoAsignadoACitaModelo medicoCitaModelo = new MedicoAsignadoACitaModelo();

		for (MedicoDto medicoDto : medicosElegidos) {
			medicoCitaModelo.add(cita.getId(), medicoDto.getId());
		}
	}

	private void setCita(CitaDto cita) {
		if (cita == null)
			throw new ApplicationException("La cita no puede ser null");
		this.cita = cita;
	}

	private void setMedicosExistentes(List<MedicoDto> medicosExistentes) {
		this.medicosExistentes = medicosExistentes;
	}

	private void setMedicosA�adibles(List<MedicoDto> medicosA�adibles) {
		this.medicosA�adibles = medicosA�adibles;
	}
}
