package controlador;

import java.time.LocalTime;
import java.util.List;

import dtos.CitaDto;
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
			sendMail(cita.getId(), cita.getHorario_inicio(), cita.getUbicacion(), cita.getPaciente().getId(), medico);
		}
	}

	private void sendMail(int i, LocalTime localTime, String ubicacion, int idPaciente, MedicoDto medico) {
		int id = 0;
		String hora = localTime.toString();
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
		msg +=   "<td><span style=\"color: #c82828;\"><strong>IdPaciente</strong> </span>: " + idPaciente + "</td>";
		msg +=   "</tr>";
		msg +=   "<tr>";
		msg +=     "<td><span style=\"color: #c82828;\"><strong>IdMédico</strong> </span>: " + medico.getId() + "</td>";
		msg +=   "</tr>";
		msg +=   "</thead>";
		msg +=   "<tbody>";
		msg +=   "<tr>";
		msg +=   "<td>Su cita tiene asignada como hora de inicio las <strong>"+ hora +"</strong> horas y tendrá lugar en la sala <strong>"+ ubicacion +"</strong></td>";
		msg +=   "</tr>";
		msg +=   "<tr> <td>Tenga en cuenta que este horaria y sala <strong>pueden estar sujetos a cambios</strong>, se le notificará con tiempo en caso de cualquier cambio</td></tr>";
		msg +=   "</tbody>";
		msg +=   "</table>";
		msg +=   "<p>&nbsp;</p>";
		
		
		
		
		
		
		
		
		List<CitaDto> citas = cm.getCitasList();
				
//		String msg = "================================\n";
//		msg += "==========CITA URGENTE==========\n";
//		msg += "================================\n";
//		for (CitaDto cita : citas) {
//			if (cita.getId() == id) {
//				msg += "Usted, con id " + cita.getId_medico()
//						+ " tiene una cita urgente con el paciente "
//						+ cita.getId_paciente() + ".\n";
//				hora = cita.getHorario_inicio();
//			}
//		}
//		msg += "Esta cita está FECHADA para las " + hora + " horas";
		SendEmail.main("Ha recibido una CITA URGENTE", msg, medico.getEmail());
	}
}
