package modelo;

import java.util.List;

import dtos.CitaDto;
import dtos.MedicoDto;

/**
 * @author Santiago
 *
 */
public class EscritorDeDatos {

	/**
	 * Escribe una cita en la base de datos y la relaciona con los médicos dados.
	 * 
	 * @param cita    nueva cita a introducir
	 * @param medicos lista de médicos asociados a esa cita
	 */
	public void grabarCita(CitaDto cita, List<MedicoDto> medicos) {

		CitaModelo citaModelo = new CitaModelo();
		citaModelo.addCita(cita);
		
		MedicoAsignadoACitaModelo medicoCitaModelo = new MedicoAsignadoACitaModelo();

		for (MedicoDto medicoDto : medicos) {
			medicoCitaModelo.add(cita.getId(), medicoDto.getId());
		}
	}
}
