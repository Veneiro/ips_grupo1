package controlador;

import java.util.List;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;
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
		DefaultTableModel dm = new DefaultTableModel(0, 0);
		String header[] = new String[] { "ID Cita", "Hora Inicio", "Hora fin", "Ubicación", "Nombre Paciente",
				"Nombre Médico", "Contacto Médico", "Aprobada" };
		dm.setColumnIdentifiers(header);

		List<CitaPendienteDto> citasPendientes = cpm.getCitasPorAprobar();
		for (CitaPendienteDto c : citasPendientes) {
			PacienteDto p = pm.getPacienteById(c.getIDPACIENTE()).get(0);
			Vector<Object> data = new Vector<Object>();
			data.add(c.getID());
			data.add(c.getHORA_ENTRADA());
			data.add(c.getHORA_SALIDA());
			data.add(c.getUBICACION());
			data.add(p.getNombre());
			data.add(c.getNOMBRE_MEDICO());
			data.add(c.getCONTACTO_MEDICO());
			data.add("false");
			dm.addRow(data);
		}

		acv.getTable().setModel(dm);
	}

	private void initializateTable2() {
		// THE MODEL OF OUR TABLE
		DefaultTableModel model = new DefaultTableModel(0, 0) {
			public Class<?> getColumnClass(int column) {
				switch (column) {
				case 0:
					return String.class;
				case 1:
					return String.class;
				case 2:
					return String.class;
				case 3:
					return String.class;
				case 4:
					return String.class;
				case 5:
					return String.class;
				case 6:
					return String.class;
				case 7:
					return Boolean.class;
				default:
					return String.class;
				}
			}
		};

		acv.getTable().setModel(model);
		model.addColumn("ID Cita");
		model.addColumn("Hora Inicio");
		model.addColumn("Hora Fin");
		model.addColumn("Ubicación");
		model.addColumn("Nombre Paciente");
		model.addColumn("Nombre Médico");
		model.addColumn("Contacto Médico");
		model.addColumn("Aprobada");

		List<CitaPendienteDto> citasPendientes = cpm.getCitasPorAprobar();
		int i = 0;
		for (CitaPendienteDto c : citasPendientes) {
			PacienteDto p = pm.getPacienteById(c.getIDPACIENTE()).get(0);
			i++;
			model.addRow(new Object[0]);
			model.setValueAt(c.getID(), i, 0);
			model.setValueAt(c.getHORA_ENTRADA(), i, 1);
			model.setValueAt(c.getHORA_SALIDA(), i, 2);
			model.setValueAt(c.getUBICACION(), i, 3);
			model.setValueAt(p.getNombre(), i, 4);
			model.setValueAt(c.getNOMBRE_MEDICO(), i, 5);
			model.setValueAt(false, i, 6);
		}
	}

	private void closeWindow() {
		acv.setVisible(false);
	}

	private void insertToDB() {
		for (int i = 0; i < acv.getTable().getRowCount(); i++) {
			if (acv.getTable().getValueAt(i, 7).equals("true")) {
				CitaDto cdto = new CitaDto();
				cdto.setId((int) acv.getTable().getValueAt(i, 0));
				cdto.setHorario_inicio((String) acv.getTable().getValueAt(i, 1));
				cdto.setHorario_fin((String) acv.getTable().getValueAt(i, 2));
				cdto.setUbicacion((String) acv.getTable().getValueAt(i, 3));
				cdto.setNombre_paciente((String) acv.getTable().getValueAt(i, 4));
				cdto.setId_paciente(getIdPaciente((String) acv.getTable().getValueAt(i, 4)));
				cdto.setId_medico(getIdMedico((String) acv.getTable().getValueAt(i, 5)));
				cim.addCita(cdto);
				cim.updateCitaPendiente(cdto.getId());
				acv.setVisible(false);
			} else if (!acv.getTable().getValueAt(i, 7).equals("true")
					&& !acv.getTable().getValueAt(i, 7).equals("false")) {
				JOptionPane.showMessageDialog(acv,
						"The introduced value is not correct : Please introduce True or False");
				;
			}
		}
	}

	private int getIdMedico(String nombre) {
		for (MedicoDto medico : mm.getListaMedicos()) {
			if (medico.getNombre().equals(nombre)) {
				return medico.getId();
			}
		}
		return -1;
	}

	private int getIdPaciente(String nombre) {
		for (PacienteDto paciente : pm.getListaPacientes()) {
			if (paciente.getNombre().equals(nombre)) {
				return paciente.getId();
			}
		}
		return -1;
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
