package controlador;

import java.util.List;

import dtos.MedicoDto;
import logic.Cita;
import lombok.Getter;
import lombok.Setter;
import modelo.CauseModel;
import util.SendEmail;

@Getter
@Setter
public class AdministradorControlador {

	CauseModel cm = new CauseModel();

	public void avisoUrgente(Cita cita) {
		List<MedicoDto> medics = cita.getMedicos();
		for (MedicoDto medico : medics) {
			sendMail(cita, medico);
		}
	}

	private void sendMail(Cita cita, MedicoDto medico) {
		int id = 0;
		String hora = cita.getHorario_inicio().toString();
		String msg = "";
		
		msg += "<h1 style=\"text-align: left; color: #ff0000;\">";
		msg +=     "CITA URGENTE";
		msg +=   "</h1>";

		msg +=   "<p>";
		msg +=     "<strong>";
		msg +=         "Tiene una CITA URGENTE";
		msg +=     "</strong>";
		msg +=   "</p>";
		msg +=   "<p>";
		msg +=   "</p>";
		msg +=   "</p>";
		msg +=   "<p>";
		msg +=     "Se le ha asignado una <strong>cita urgente</strong> con id <strong>"+ id +"</strong>";
		msg +=   "</p>";
		msg +=   "<p>";
		msg +=   "</p>";
		msg +=   "<table class=\"demoTable\" style=\"height: 54px;\">";
		msg +=   "<thead>";
		msg +=   "<tr>";
		msg +=   "<td><span style=\"color: #c82828;\"><strong>Paciente</strong> </span>: " + cita.getPaciente().getNombre() + "</td>";
		msg +=   "</tr>";
		msg +=   "<tr>";
		msg +=     "<td><span style=\"color: #c82828;\"><strong>Médico</strong> </span>: " + medico.getNombre() + "</td>";
		msg +=   "</tr>";
		msg +=   "</thead>";
		msg +=   "<tbody>";
		msg +=   "<tr>";
		msg +=   "<td>Su cita tiene asignada como hora de inicio las <strong>"+ hora +"</strong> horas y tendrá lugar en la sala <strong>"+ cita.getUbicacion() +"</strong></td>";
		msg +=   "</tr>";
		msg +=   "<tr> <td>Tenga en cuenta que este horaria y sala <strong>pueden estar sujetos a cambios</strong>, se le notificará con tiempo en caso de cualquier cambio</td></tr>";
		msg +=   "</tbody>";
		msg +=   "</table>";
		msg +=   "<p>&nbsp;</p>";
		
		SendEmail.main("Ha recibido una CITA URGENTE", msg, medico.getEmail());
	}
}
