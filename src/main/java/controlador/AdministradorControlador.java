package controlador;

import java.util.List;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import dtos.CitaDto;
import dtos.CitaPendienteDto;
import dtos.MedicoDto;
import dtos.PacienteDto;
import lombok.Getter;
import lombok.Setter;
import modelo.CauseModel;
import modelo.CitaModelo;
import modelo.CitaPendienteModelo;
import modelo.MedicoModelo;
import modelo.PacienteModelo;
import util.SendEmail;
import util.SwingUtil;
import vista.AprobarCitasVista;

@Getter
@Setter
public class AdministradorControlador {

	CauseModel cm = new CauseModel();
	MedicoModelo mm = new MedicoModelo();
	CitaModelo cim = new CitaModelo();
	PacienteModelo pm = new PacienteModelo();
	CitaPendienteModelo cpm = new CitaPendienteModelo();
	private AprobarCitasVista acv;

	public AdministradorControlador() {
		
	}

	public AdministradorControlador(AprobarCitasVista acv) {
		this.acv = acv;
	}

	public void initializeAprobarCitas() {
		initializateTable();
		acv.getBtnConfirmar().addActionListener(e -> SwingUtil.exceptionWrapper(() -> insertToDB()));
		acv.getBtnBack().addActionListener(e -> SwingUtil.exceptionWrapper(() -> closeWindow()));
		acv.setLocationRelativeTo(null);
		acv.setVisible(true);
	}

	private void initializateTable() {
		List<CitaPendienteDto> citasPendientes = cpm.getCitasPorAprobar();
		DefaultTableModel dm = new DefaultTableModel(0, 0);
		String header[] = new String[] { "ID Cita", "Hora Inicio", "Hora fin", "Ubicación", "Nombre Paciente",
				"Nombre Médico", "Contacto Médico", "Aprobada" };
		dm.setColumnIdentifiers(header);

		for (CitaPendienteDto c : citasPendientes) {
			PacienteDto p = pm.getPacienteById(c.getIDPACIENTE()).get(0);
			Vector<Object> data = new Vector<Object>();
			data.add(c.getID());
			data.add(c.getHORA_ENTRADA());
			data.add(c.getHORA_SALIDA());
			data.add(c.getUBICACION());
			data.add(p.getNombre());
			data.add(c.getNOMBRE_MEDICO());
			data.add(Boolean.FALSE);
			dm.addRow(data);
		}
		acv.getTable().setModel(dm);
	}

	private void closeWindow() {
		acv.setVisible(false);
	}

	private void insertToDB() {
		
	}

	public void avisoUrgente(CitaDto cita, String horaEntrada, PacienteDto p) {
		List<MedicoDto> medicos = mm.getListaMedicos();
		for (MedicoDto medico : medicos) {
			if (medico.getId() == cita.getId_medico()) {
				sendMail(cita, medico, horaEntrada, p);
			}
		}
	}

	private void sendMail(CitaDto cita, MedicoDto medico, String horaEntrada, PacienteDto p) {
		int id = 0;
		String hora = horaEntrada;
		String msg = "";

		msg += "<h1 style=\"text-align: left; color: #ff0000;\">";
		msg += "CITA URGENTE";
		msg += "</h1>";

		msg += "<p>";
		msg += "<strong>";
		msg += "Tiene una CITA URGENTE";
		msg += "</strong>";
		msg += "</p>";
		msg += "<p>";
		msg += "</p>";
		msg += "</p>";
		msg += "<p>";
		msg += "Se le ha asignado una <strong>cita urgente</strong> con id <strong>" + id + "</strong>";
		msg += "</p>";
		msg += "<p>";
		msg += "</p>";
		msg += "<table class=\"demoTable\" style=\"height: 54px;\">";
		msg += "<thead>";
		msg += "<tr>";
		msg += "<td><span style=\"color: #c82828;\"><strong>Paciente</strong> </span>: " + p.getNombre() + "</td>";
		msg += "</tr>";
		msg += "<tr>";
		msg += "<td><span style=\"color: #c82828;\"><strong>Médico</strong> </span>: " + medico.getNombre() + "</td>";
		msg += "</tr>";
		msg += "</thead>";
		msg += "<tbody>";
		msg += "<tr>";
		msg += "<td>Su cita tiene asignada como hora de inicio las <strong>" + hora
				+ "</strong> horas y tendrá lugar en la sala <strong>" + cita.getUbicacion() + "</strong></td>";
		msg += "</tr>";
		msg += "<tr> <td>Tenga en cuenta que este horaria y sala <strong>pueden estar sujetos a cambios</strong>, se le notificará con tiempo en caso de cualquier cambio</td></tr>";
		msg += "</tbody>";
		msg += "</table>";
		msg += "<p>&nbsp;</p>";

		SendEmail.main("Ha recibido una CITA URGENTE", msg, medico.getEmail());
	}
}
