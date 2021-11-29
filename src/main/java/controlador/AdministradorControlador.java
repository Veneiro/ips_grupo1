package controlador;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import dtos.AsignacionMedicosDto;
import dtos.CitaDto;
import dtos.CitaPendienteDto;
import dtos.MedicoDto;
import dtos.PacienteDto;
import dtos.RegistroDto;
import lombok.Getter;
import lombok.Setter;
import modelo.CauseModel;
import modelo.CitaModelo;
import modelo.CitaPendienteModelo;
import modelo.MedicoAsignadoACitaModelo;
import modelo.MedicoModelo;
import modelo.PacienteModelo;
import modelo.RegistroModelo;
import util.SendEmail;
import util.SwingUtil;
import vista.AprobarCitasVista;
import vista.ModificarCitaAdminVista;

@Getter
@Setter
@SuppressWarnings({ "rawtypes", "unchecked" })
public class AdministradorControlador {

	private MedicoAsignadoACitaModelo macm = new MedicoAsignadoACitaModelo();
	private CauseModel cm = new CauseModel();
	private MedicoModelo mm = new MedicoModelo();
	private CitaModelo cim = new CitaModelo();
	private PacienteModelo pm = new PacienteModelo();
	private CitaPendienteModelo cpm = new CitaPendienteModelo();
	private AprobarCitasVista acv;
	private CitaPendienteDto cpdto;
	private CitaPendienteDto cita = new CitaPendienteDto();
	private List<CitaPendienteDto> citasPendientes;
	private ModificarCitaAdminVista mcav = new ModificarCitaAdminVista();
	private DefaultListModel modelMedicos = new DefaultListModel();

	public AdministradorControlador() {
	}

	public AdministradorControlador(AprobarCitasVista acv) {
		this.acv = acv;
	}

	public void initializeAprobarCitas() {
		initializateTable();
		acv.getBtnConfirmar().addActionListener(
				e -> SwingUtil.exceptionWrapper(() -> insertToDB()));
		acv.getBtnBack().addActionListener(
				e -> SwingUtil.exceptionWrapper(() -> closeWindow()));
		acv.getBtnModificar().addActionListener(
				e -> SwingUtil.exceptionWrapper(() -> showModificarCita()));
		acv.getBtnHelp().addActionListener(
				e -> SwingUtil.exceptionWrapper(() -> showHelp()));

		acv.setLocationRelativeTo(null);
		acv.setVisible(true);
	}

	private void showHelp() {
		JOptionPane.showMessageDialog(acv, "Opciones:\n1 - Selecciona una "
				+ "cita propuesta y cambia el ultimo valor a true "
				+ "para aceptar\n2 - Selecciona una cita propuesta y haz click "
				+ "en el botón Modificar Cita para cambiar alguno de "
				+ "los valores propuestos");
	}

	private void showModificarCita() {
		int idcita = (int) acv.getTable()
				.getValueAt(acv.getTable().getSelectedRow(), 0);
		for (CitaPendienteDto citaPendienteDto : citasPendientes) {
			if (citaPendienteDto.getID() == idcita) {
				cpdto = citaPendienteDto;
			}
		}
		
		cita.setID(cpdto.getID());
		cita.setFECHA(cpdto.getFECHA());
		cita.setHORA_ENTRADA(cpdto.getHORA_ENTRADA());
		cita.setHORA_SALIDA(cpdto.getHORA_SALIDA());
		cita.setID_PACIENTE(cpdto.getID_PACIENTE());
		cita.setID_MEDICO(cpdto.getID_MEDICO());
		cita.setUBICACION(cpdto.getUBICACION());
		
		initializeAdminModify(cpdto);
		mcav.getBtnModificar().addActionListener(
				e -> SwingUtil.exceptionWrapper(() -> updateDB(cpdto)));
		mcav.getBtnAdd().addActionListener(
				e -> SwingUtil.exceptionWrapper(() -> addMedicToList()));
		mcav.getBtnRemove().addActionListener(
				e -> SwingUtil.exceptionWrapper(() -> removeMedicFromList()));
	}

	private void removeMedicFromList() {
		MedicoDto medico = (MedicoDto) mcav.getListMedicos().getSelectedValue();
		modelMedicos.removeElement(medico);
	}

	private void initializeAdminModify(CitaPendienteDto citaPendiente) {

		String date = citaPendiente.getFECHA();
		try {
			Date d = new SimpleDateFormat("dd-MM-yyyy").parse(date);
			mcav.getDateChooser().setDate(d);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String entryHour = citaPendiente.getHORA_ENTRADA();
		String[] entrySplit = entryHour.split(":");
		mcav.getSpEntryHour().setValue(Integer.valueOf(entrySplit[0].trim()));
		mcav.getSpEntryMin().setValue(Integer.valueOf(entrySplit[1].trim()));
		;

		String outHour = citaPendiente.getHORA_SALIDA();
		String[] outSplit = outHour.split(":");
		mcav.getSpOutHour().setValue(Integer.valueOf(outSplit[0].trim()));
		mcav.getSpOutMin().setValue(Integer.valueOf(outSplit[1].trim()));

		mcav.getTxtUbicacion().setText(citaPendiente.getUBICACION());

		DefaultComboBoxModel dcm = new DefaultComboBoxModel();
		for (PacienteDto p : pm.getListaPacientes()) {
			if (p.getId() == (citaPendiente.getID_PACIENTE())) {
				dcm.addElement(p);
			}
		}
		for (PacienteDto p : pm.getListaPacientes()) {
			if (p.getId() != (citaPendiente.getID_PACIENTE())) {
				dcm.addElement(p);
			}
		}
		mcav.getCbPacientes().setModel(dcm);

		DefaultComboBoxModel dlm = new DefaultComboBoxModel();
		for (MedicoDto medico : mm.getListaMedicos()) {
			if (medico.getId() != citaPendiente.getID_MEDICO()) {
				dlm.addElement(medico);
			}
		}
		mcav.getCbMedicos().setModel(dlm);

		// Lista Auxiliar
		List<AsignacionMedicosDto> aux = new ArrayList<AsignacionMedicosDto>();
		// Saco los medicos asignados a la cita y guardo sus ids
		for (AsignacionMedicosDto medico : macm
				.getMedicosPendientesAprobarCita()) {
			if (medico.getId_cita() == citaPendiente.getID()) {
				aux.add(medico);
			}
		}

		// Paso por la lista de medicos y compruebo cual/cuales están añadidos a
		// la cita pendiente
		for (MedicoDto medico : mm.getListaMedicos()) {
			for (AsignacionMedicosDto asignacionMedicosDto : aux) {
				if (asignacionMedicosDto.getId_medico() == medico.getId()) {
					modelMedicos.addElement((MedicoDto) medico);
				}
			}
		}

		// Asigno el modelo creado a la lista
		mcav.getListMedicos().setModel(modelMedicos);

		mcav.setLocationRelativeTo(null);
		mcav.setVisible(true);
	}

	private PacienteDto getPacienteById(int id) {
		for (PacienteDto p : pm.getListaPacientes()) {
			if (p.getId() == id) {
				return p;
			}
		}
		return null;
	}

	private void addMedicToList() {
		MedicoDto medico = ((MedicoDto) mcav.getCbMedicos().getSelectedItem());
		if (!isInList(medico)) {
			modelMedicos.addElement(medico);
		} else {
			JOptionPane.showMessageDialog(mcav,
					"El médico seleccionado ya está asignado a esta cita");
		}
	}

	private boolean isInList(MedicoDto medico) {
		for (int i = 0; i < modelMedicos.getSize(); i++) {
			if ((((MedicoDto) modelMedicos.getElementAt(i)).getId()) == (medico
					.getId())) {
				return true;
			}
		}
		return false;
	}

	private void updateDataBase(CitaPendienteDto citaPendiente) {
		cpdto.setUBICACION(mcav.getTxtUbicacion().getText());
		Date citaFecha = mcav.getDateChooser().getDate();
		LocalDate fecha = citaFecha.toInstant().atZone(ZoneId.systemDefault())
				.toLocalDate();
		cpdto.setFECHA(fecha.get(ChronoField.DAY_OF_MONTH) + "-"
				+ fecha.get(ChronoField.MONTH_OF_YEAR) + "-"
				+ fecha.get(ChronoField.YEAR));
		cpdto.setESTADO("MedicoReview");

		String houre = String.format("%02d", mcav.getSpEntryHour().getValue());
		String mine = String.format("%02d", mcav.getSpEntryMin().getValue());
		String houro = String.format("%02d", mcav.getSpOutHour().getValue());
		String mineo = String.format("%02d", mcav.getSpOutMin().getValue());

		cpdto.setHORA_ENTRADA(houre + " : " + mine);
		cpdto.setHORA_SALIDA(houro + " : " + mineo);

		cpdto.setID(citaPendiente.getID());
		cpdto.setCONTACTO_MEDICO(citaPendiente.getCONTACTO_MEDICO());
		cpdto.setID_MEDICO(citaPendiente.getID_MEDICO());
		cpdto.setID_PACIENTE(citaPendiente.getID_PACIENTE());
		updateDBMedicosAsignados(citaPendiente.getID());
	}

	private void updateDBMedicosAsignados(int id) {
		macm.removeMedicos(id);
		AsignacionMedicosDto amdto = new AsignacionMedicosDto();
		List<MedicoDto> medicosPendiente = new ArrayList<MedicoDto>();
		amdto.setId_cita(id);

		for (int i = 0; i < mcav.getListMedicos().getModel().getSize(); i++) {
			medicosPendiente.add((MedicoDto) mcav.getListMedicos().getModel()
					.getElementAt(i));
		}
		for (MedicoDto medico : medicosPendiente) {
			amdto.setId_medico(medico.getId());
			macm.asignarMedicosPendientes(amdto);
		}
	}

	private void insertMedicosAsignadosConfirmados(int id) {
		AsignacionMedicosDto amdto = new AsignacionMedicosDto();
		amdto.setId_cita(id);

		for (MedicoDto medico : macm.getMedicosPendientesDeCita(id)) {
			amdto.setId_medico(medico.getId());
			macm.asignarMedicosConfirmados(amdto);
		}
		macm.removeMedicos(id);
	}

	private void updateDB(CitaPendienteDto cpdto) {
		JOptionPane.showConfirmDialog(mcav, "Si confirma la opción esta cita "
				+ "será redirigida al médico para obtener confirmación de los "
				+ "valores cambiados");
		updateDataBase(cpdto);
		cpm.updateCita(cpdto);
		mcav.setVisible(false);
		sendMailAdviseMedic(this.cita);
		
		RegistroModelo.addRegistro(new RegistroDto("Admin",
				"Ha modificado la cita propuesta. Médico: "
					+ new MedicoModelo().getListaMedicosById(cpdto.getID_MEDICO()).get(0).getNombre() + "Día: "
					+ cpdto.getFECHA() + "Hora: " + cpdto.getHORA_ENTRADA() + "-" + cpdto.getHORA_SALIDA()));
	}

	private void sendMailAdviseMedic(CitaPendienteDto cita) {

		String msg = "";
		msg += "<h2 style=\"text-align: center;\"><span style=\"color: #ff0000;\"><strong>SE LE HA MODIFICADO UNA CITA PROPUESTA</strong></span></h2>";
		msg += "<h4><em>A continuaci&oacute;n se muestran su propuesta de cita y la modificada por el Administrativo para que pueda comprobar y revisar los datos:</em></h4>";
		msg += "<p><em>Modificada:</em></p>";
		msg += "<table style=\"border-collapse: collapse; width: 100%; height: 108px;\" border=\"1\">";
		msg += "<tbody>";
		msg += "<tr style=\"height: 18px;\">";
		msg += "<td style=\"width: 50%; height: 18px;\"><span style=\"background-color: #ffff99;\">Fecha</span></td>";
		msg += "<td style=\"width: 50%; height: 18px;\">" + cpdto.getFECHA() + "</td>";
		msg += "</tr>";
		msg += "<tr style=\"height: 18px;\">";
		msg += "<td style=\"width: 50%; height: 18px;\"><span style=\"background-color: #ffff99;\">Hora Entrada</span></td>";
		msg += "<td style=\"width: 50%; height: 18px;\">" + cpdto.getHORA_ENTRADA() + "</td>";
		msg += "</tr>";
		msg += "<tr style=\"height: 18px;\">";
		msg += "<td style=\"width: 50%; height: 18px;\"><span style=\"background-color: #ffff99;\">Hora Salida</span></td>";
		msg += "<td style=\"width: 50%; height: 18px;\">" + cpdto.getHORA_SALIDA() + "</td>";
		msg += "</tr>";
		msg += "<tr style=\"height: 18px;\">";
		msg += "<td style=\"width: 50%; height: 18px;\"><span style=\"background-color: #ffff99;\">Paciente</span></td>";
		msg += "<td style=\"width: 50%; height: 18px;\">" + getPacienteById(cpdto.getID_PACIENTE()) + "</td>";
		msg += "</tr>";
		msg += "<tr style=\"height: 18px;\">";
		msg += "<td style=\"width: 50%; height: 18px;\"><span style=\"background-color: #ffff99;\">M&eacute;dicos</span></td>";
		msg += "<td style=\"width: 50%; height: 18px;\">" + getMedicoByID(cpdto.getID_MEDICO()) + "</td>";
		msg += "</tr>";
		msg += "<tr style=\"height: 18px;\">";
		msg += "<td style=\"width: 50%; height: 18px;\"><span style=\"background-color: #ffff99;\">Ubicaci&oacute;n</span></td>";
		msg += "<td style=\"width: 50%; height: 18px;\">" + cpdto.getUBICACION() + "</td>";
		msg += "</tr>";
		msg += "</tbody>";
		msg += "</table>";
		msg += "<p><em>Original:</em></p>";
		msg += "<table style=\"border-collapse: collapse; width: 100%;\" border=\"1\">";
		msg += "<tbody>";
		msg += "<tr>";
		msg += "<td style=\"width: 50%;\"><span style=\"background-color: #ffff99;\">Fecha</span></td>";
		msg += "<td style=\"width: 50%;\">" + cita.getFECHA() + "</td>";
		msg += "</tr>";
		msg += "<tr>";
		msg += "<td style=\"width: 50%;\"><span style=\"background-color: #ffff99;\">Hora Entrada</span></td>";
		msg += "<td style=\"width: 50%;\">" + cita.getHORA_ENTRADA() + "</td>";
		msg += "</tr>";
		msg += "<tr>";
		msg += "<td style=\"width: 50%;\"><span style=\"background-color: #ffff99;\">Hora Salida</span></td>";
		msg += "<td style=\"width: 50%;\">" + cita.getHORA_SALIDA() + "</td>";
		msg += "</tr>";
		msg += "<tr>";
		msg += "<td style=\"width: 50%;\"><span style=\"background-color: #ffff99;\">Paciente</span></td>";
		msg += "<td style=\"width: 50%;\">" + getPacienteById(cita.getID_PACIENTE()) + "</td>";
		msg += "</tr>";
		msg += "<tr>";
		msg += "<td style=\"width: 50%;\"><span style=\"background-color: #ffff99;\">M&eacute;dicos</span></td>";
		msg += "<td style=\"width: 50%;\">" + getMedicoByID(cita.getID_MEDICO()) + "</td>";
		msg += "</tr>";
		msg += "<tr>";
		msg += "<td style=\"width: 50%;\"><span style=\"background-color: #ffff99;\">Ubicaci&oacute;n</span></td>";
		msg += "<td style=\"width: 50%;\">" + cita.getUBICACION() + "</td>";
		msg += "</tr>";
		msg += "</tbody>";
		msg += "</table>";
		
		MedicoDto m = null;
		for (MedicoDto medico : mm.getListaMedicos()) {
			if (medico.getId() == cpdto.getID_MEDICO()) {
				m = medico;
			}
		}
		SendEmail.main("Una proposición de cita ha sido modificada", msg,
				m.getEmail());
	}

	private void initializateTable() {
		DefaultTableModel dm = new DefaultTableModel(0, 0);
		String header[] = new String[] { "Id Cita", "Nombre Paciente",
				"Nombre Médico", "Fecha", "Hora Inicio", "Hora fin",
				"Ubicación", "Contacto Médico", "Aprobada" };
		dm.setColumnIdentifiers(header);

		citasPendientes = cpm.getCitasPorAprobar();
		for (CitaPendienteDto c : citasPendientes) {
			if (c.getESTADO().equals("AdminReview")) {
				PacienteDto p = pm.getPacienteById(c.getID_PACIENTE()).get(0);
				Vector<Object> data = new Vector<Object>();
				data.add(c.getID());
				data.add(p.getNombre());
				data.add(getMedicoByID(c.getID_MEDICO()).getNombre());
				data.add(c.getFECHA());
				data.add(c.getHORA_ENTRADA());
				data.add(c.getHORA_SALIDA());
				data.add(c.getUBICACION());
				data.add(c.getCONTACTO_MEDICO());
				data.add("false");
				dm.addRow(data);
			}
		}

		acv.getTable().setModel(dm);
	}

	private MedicoDto getMedicoByID(int id) {
		for (MedicoDto medico : mm.getListaMedicos()) {
			if (medico.getId() == id) {
				return medico;
			}
		}
		return null;
	}

	@SuppressWarnings({ "serial", "unused" })
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
			PacienteDto p = pm.getPacienteById(c.getID_PACIENTE()).get(0);
			i++;
			model.addRow(new Object[0]);
			model.setValueAt(c.getID(), i, 0);
			model.setValueAt(c.getHORA_ENTRADA(), i, 1);
			model.setValueAt(c.getHORA_SALIDA(), i, 2);
			model.setValueAt(c.getUBICACION(), i, 3);
			model.setValueAt(p.getNombre(), i, 4);
			model.setValueAt(false, i, 6);
		}
	}

	private void closeWindow() {
		acv.setVisible(false);
	}

	private void insertToDB() {
		for (int i = 0; i < acv.getTable().getRowCount(); i++) {
			if (acv.getTable().getValueAt(i, 8).equals("true")) {
				CitaDto cdto = new CitaDto();
				cdto.setId((int) acv.getTable().getValueAt(i, 0));
				cdto.setId_paciente(getIdPaciente(
						(String) acv.getTable().getValueAt(i, 1)));
				cdto.setId_medico(
						getIdMedico((String) acv.getTable().getValueAt(i, 2)));
				cdto.setFecha((String) acv.getTable().getValueAt(i, 3));
				cdto.setHorario_inicio(
						(String) acv.getTable().getValueAt(i, 4));
				cdto.setHorario_fin((String) acv.getTable().getValueAt(i, 5));
				cdto.setUbicacion((String) acv.getTable().getValueAt(i, 6));
				cim.addCita(cdto);
				insertMedicosAsignadosConfirmados(cdto.getId());
				cim.updateCitaPendiente(cdto.getId());
				acv.setVisible(false);
			} else if (!acv.getTable().getValueAt(i, 7).equals("true")
					&& !acv.getTable().getValueAt(i, 7).equals("false")) {
				JOptionPane.showMessageDialog(acv,
						"The introduced value is not correct : Please "
								+ "introduce True or False");
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

	private void sendMail(CitaDto cita, MedicoDto medico, String horaEntrada,
			PacienteDto p) {
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
		msg += "Se le ha asignado una <strong>cita urgente</strong> con id <strong>"
				+ id + "</strong>";
		msg += "</p>";
		msg += "<p>";
		msg += "</p>";
		msg += "<table class=\"demoTable\" style=\"height: 54px;\">";
		msg += "<thead>";
		msg += "<tr>";
		msg += "<td><span style=\"color: #c82828;\"><strong>Paciente</strong> </span>: "
				+ p.getNombre() + "</td>";
		msg += "</tr>";
		msg += "<tr>";
		msg += "<td><span style=\"color: #c82828;\"><strong>Médico</strong> </span>: "
				+ medico.getNombre() + "</td>";
		msg += "</tr>";
		msg += "</thead>";
		msg += "<tbody>";
		msg += "<tr>";
		msg += "<td>Su cita tiene asignada como hora de inicio las <strong>"
				+ hora + "</strong> horas y tendrá lugar en la sala <strong>"
				+ cita.getUbicacion() + "</strong></td>";
		msg += "</tr>";
		msg += "<tr> <td>Tenga en cuenta que este horaria y sala <strong>pueden estar sujetos a cambios</strong>, se le notificará con tiempo en caso de cualquier cambio</td></tr>";
		msg += "</tbody>";
		msg += "</table>";
		msg += "<p>&nbsp;</p>";

		SendEmail.main("Ha recibido una CITA URGENTE", msg, medico.getEmail());
	}
}
