package controlador;

import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

import dtos.CauseDto;
import dtos.CitaDto;
import dtos.RegistroDto;
import lombok.Getter;
import lombok.Setter;
import modelo.CauseModel;
import modelo.CitaModelo;
import modelo.HistorialModelo;
import modelo.MedicoModelo;
import modelo.PacienteModelo;
import modelo.PrescripcionesModelo;
import modelo.RegistroModelo;
import records.PrescripcionRecord;
import util.NoEditableTableModel;
import util.SwingUtil;
import vista.AddCauseView;
import vista.AppointmentView;
import vista.HistorialesVista;
import vista.NonExistenceView;

@Getter
@Setter
public class PacienteControlador {

	private AppointmentView vista_cita;
	private PacienteModelo modelo_paciente;
	private CauseModel cm;
	private MedicoModelo mm;
	private CitaDto cita;
	private CitaModelo ctm;
	private PrescripcionesModelo pM;

	public PacienteControlador(PacienteModelo pacienteModelo,
			AppointmentView pacienteVista) {
		this.vista_cita = pacienteVista;
		this.modelo_paciente = pacienteModelo;
		this.pM = new PrescripcionesModelo();
		this.cm = new CauseModel();
		this.mm = new MedicoModelo();
		this.ctm = new CitaModelo();
	}

	public void initialize(CitaDto cita, String nombrePaciente) {
		this.cita = cita;
		updateList();

		vista_cita.getBtnContinueButton().addActionListener(
				e -> SwingUtil.exceptionWrapper(() -> insertCitaToDB()));
		vista_cita.getBtnAddCause().addActionListener(
				e -> SwingUtil.exceptionWrapper(() -> addCause()));
		vista_cita.getBtnAddPrescripcion().addActionListener(
				e -> SwingUtil.exceptionWrapper(() -> addPrescripcion()));
		vista_cita.getBtnHistorial().addActionListener(
				e -> SwingUtil.exceptionWrapper(() -> getHistorial()));
		vista_cita.getBtnAcudio().addActionListener(
				e -> SwingUtil.exceptionWrapper(() -> acudio()));
		vista_cita.getBtnNoAcudio().addActionListener(
				e -> SwingUtil.exceptionWrapper(() -> noAcudio()));
		vista_cita.getBtnCancel().addActionListener(e -> SwingUtil
				.exceptionWrapper(() -> vista_cita.setVisible(false)));
		vista_cita.getChckbxToggleHours().addActionListener(
				e -> SwingUtil.exceptionWrapper(() -> toggleHours()));

		vista_cita.getLblPaciente().setText(nombrePaciente);
		String entry = cita.getHorario_inicio();
		String out = cita.getHorario_fin();

		String[] sEntry = entry.split(":");
		String[] sOut = out.split(":");

		vista_cita.getSpEntryHour()
				.setValue(Integer.parseInt(sEntry[0].trim()));
		vista_cita.getSpEntryMin().setValue(Integer.parseInt(sEntry[1].trim()));
		vista_cita.getSpOutHour().setValue(Integer.parseInt(sOut[0].trim()));
		vista_cita.getSpOutMin().setValue(Integer.parseInt(sOut[1].trim()));
		_cargarPrescripciones();
		showVistaCita();
	}

	private void toggleHours() {
		if (vista_cita.getChckbxToggleHours().isSelected()) {
			vista_cita.getSpEntryHour().setEnabled(true);
			vista_cita.getSpEntryMin().setEnabled(true);
			vista_cita.getSpOutHour().setEnabled(true);
			vista_cita.getSpOutMin().setEnabled(true);
		} else {
			vista_cita.getSpEntryHour().setEnabled(false);
			vista_cita.getSpEntryMin().setEnabled(false);
			vista_cita.getSpOutHour().setEnabled(false);
			vista_cita.getSpOutMin().setEnabled(false);
		}

	}

	private void getHistorial() {
		HistorialControlador hc = new HistorialControlador(
				new HistorialModelo(), new HistorialesVista(),
				cita.getId_paciente(), cita.getId_medico());
		hc.inicializar();
	}

	private void acudio() {
		ctm.updateAcudio(cita.getId(), "Acudio");
		JOptionPane.showMessageDialog(null,
				"La actualización " + "se ha realizado correctamente");
		cita.setAcudio("Acudió");
		RegistroModelo
				.addRegistro(new RegistroDto("Médico " + cita.getId_medico(),
						"Indica que el paciente " + cita.getId_paciente()
								+ " acudió a la cita " + cita.getId()));
	}

	private void noAcudio() {
		ctm.updateAcudio(cita.getId(), "No Acudio");
		JOptionPane.showMessageDialog(null,
				"La actualización " + "se ha realizado correctamente");
		cita.setAcudio("No acudió");
		RegistroModelo
				.addRegistro(new RegistroDto("Médico " + cita.getId_medico(),
						"Indica que el paciente " + cita.getId_paciente()
								+ " no acudió a la cita " + cita.getId()));
	}

	private void addCause() {
		AppointmentControler controller = new AppointmentControler(
				new CauseModel(), new AddCauseView(), new NonExistenceView());
		controller.initialize(cita.getId());
		updateList();
	}

	private void addPrescripcion() {
		PrescripcionesControlador controller = new PrescripcionesControlador(
				cita.getId_paciente(), cita.getId_medico());
		controller.inicializar();
		updateList();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void updateList() {
		List<CauseDto> causes = cm.getCauseList();
		DefaultListModel dlm = new DefaultListModel();
		for (CauseDto cause : causes) {
			if (cause.getID() == cita.getId()) {
				dlm.addElement(cause.toString());
			}
		}
		vista_cita.getListCauses().setModel(dlm);
	}

	public void showVistaCita() {
		vista_cita.getSpEntryHour().setEnabled(false);
		vista_cita.getSpEntryMin().setEnabled(false);
		vista_cita.getSpOutHour().setEnabled(false);
		vista_cita.getSpOutMin().setEnabled(false);
		vista_cita.setLocationRelativeTo(null);
		vista_cita.setVisible(true);
	}

	public void insertCitaToDB() {

		CitaDto cidto = new CitaDto();
		String houre = vista_cita.getSpEntryHour().getValue().toString();
		String mine = vista_cita.getSpEntryMin().getValue().toString();
		String houro = vista_cita.getSpOutHour().getValue().toString();
		String mineo = vista_cita.getSpOutMin().getValue().toString();
		if (vista_cita.getChckbxToggleHours().isSelected()) {
			cidto.setHora_entrada(houre + " : " + mine);
			cidto.setHora_salida(houro + " : " + mineo);
		}
		cidto.setHorario_inicio(cita.getHorario_inicio());
		cidto.setHorario_fin(cita.getHorario_fin());
		cidto.setId(cita.getId());
		cidto.setId_medico(cita.getId_medico());
		cidto.setId_paciente(cita.getId_paciente());
		cidto.setNombre_paciente(cita.getNombre_paciente());
		cidto.setFecha(cita.getFecha());
		cidto.setAcudio(cita.getAcudio());
		cm.insertCita(cidto);
		vista_cita.setVisible(false);
	}

	void _cargarPrescripciones() {
		vista_cita
				.setModeloTablaPrescripciones(
						new NoEditableTableModel(
								new String[] { "Nombre", "Indicacciones",
										"Cantidad", "Intervalo", "Duración" },
								0));

		List<PrescripcionRecord> lM = pM
				.getListaPrescripcionesPaciente(cita.getId_paciente());

		for (PrescripcionRecord p : lM) {
			vista_cita.getModeloTablaPrescripciones()
					.addRow(new Object[] { p.getNombre(), p.getIndicaciones(),
							p.getCantidad(), p.getIntervalo(),
							p.getDuracion() });
		}

		vista_cita.getTablePrescripciones()
				.setModel(vista_cita.getModeloTablaPrescripciones());
	}
}
