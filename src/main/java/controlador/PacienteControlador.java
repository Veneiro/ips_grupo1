package controlador;

import java.util.List;

import javax.swing.DefaultListModel;

import dtos.CauseDto;
import dtos.CitaDto;
import lombok.Getter;
import lombok.Setter;
import modelo.CauseModel;
import modelo.MedicoModelo;
import modelo.PacienteModelo;
import modelo.PrescripcionesModelo;
import records.PrescripcionRecord;
import util.NoEditableTableModel;
import util.SwingUtil;
import vista.AddCauseView;
import vista.AppointmentView;
import vista.NonExistenceView;

@Getter
@Setter
public class PacienteControlador {

    private AppointmentView vista_cita;
    private PacienteModelo modelo_paciente;
    private CauseModel cm;
    private MedicoModelo mm;
    private PrescripcionesModelo pM;
    private CitaDto cita;

    public PacienteControlador(PacienteModelo pacienteModelo, AppointmentView pacienteVista) {
	this.vista_cita = pacienteVista;
	this.modelo_paciente = pacienteModelo;
	this.pM = new PrescripcionesModelo();
	this.cm = new CauseModel();
	this.mm = new MedicoModelo();
    }

    public void initialize(CitaDto cita, String nombrePaciente) {
	this.cita = cita;
	updateList();
	vista_cita.getBtnContinueButton().addActionListener(e -> SwingUtil.exceptionWrapper(() -> insertCitaToDB()));
	vista_cita.getBtnAddCause().addActionListener(e -> SwingUtil.exceptionWrapper(() -> addCause()));
	vista_cita.getBtnAddPrescripcion().addActionListener(e -> SwingUtil.exceptionWrapper(() -> addPrescripcion()));
	vista_cita.getLblPaciente().setText(nombrePaciente);
	String entry = cita.getHorario_inicio();
	String out = cita.getHorario_fin();

	String[] sEntry = entry.split(":");
	String[] sOut = out.split(":");

	vista_cita.getSpEntryHour().setValue(Integer.parseInt(sEntry[0].trim()));
	vista_cita.getSpEntryMin().setValue(Integer.parseInt(sEntry[1].trim()));
	vista_cita.getSpOutHour().setValue(Integer.parseInt(sOut[0].trim()));
	vista_cita.getSpOutMin().setValue(Integer.parseInt(sOut[1].trim()));

	_cargarPrescripciones();
	showVistaCita();
    }

    private void addCause() {
	AppointmentControler controller = new AppointmentControler(new CauseModel(), new AddCauseView(),
		new NonExistenceView());
	controller.initialize(cita.getId());
	updateList();
    }

    private void addPrescripcion() {
	PrescripcionesControlador controller = new PrescripcionesControlador(cita.getId_paciente(), this);
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
	vista_cita.setLocationRelativeTo(null);
	vista_cita.setVisible(true);
    }

    public void insertCitaToDB() {

	CitaDto cidto = new CitaDto();
	String houre = vista_cita.getSpEntryHour().getValue().toString();
	String mine = vista_cita.getSpEntryMin().getValue().toString();
	String houro = vista_cita.getSpOutHour().getValue().toString();
	String mineo = vista_cita.getSpOutMin().getValue().toString();

	cidto.setHora_entrada(houre + " : " + mine);
	cidto.setHora_salida(houro + " : " + mineo);
	cidto.setHorario_inicio(cita.getHorario_inicio());
	cidto.setHorario_fin(cita.getHorario_fin());
	cidto.setId(cita.getId());
	cidto.setId_medico(cita.getId_medico());
	cidto.setId_paciente(cita.getId_paciente());
	cidto.setNombre_paciente(cita.getNombre_paciente());
	cidto.setFecha(cita.getFecha());
	cm.insertCita(cidto);
	vista_cita.setVisible(false);
    }

    void _cargarPrescripciones() {
	vista_cita.setModeloTablaPrescripciones(new NoEditableTableModel(
		new String[] { "Nombre", "Indicacciones", "Cantidad", "Intervalo", "Duración" }, 0));

	List<PrescripcionRecord> lM = pM.getListaPrescripcionesPaciente(cita.getId_paciente());

	for (PrescripcionRecord p : lM) {
	    vista_cita.getModeloTablaPrescripciones().addRow(new Object[] { p.getNombre(), p.getIndicaciones(),
		    p.getCantidad(), p.getIntervalo(), p.getDuracion() });
	}

	vista_cita.getTablePrescripciones().setModel(vista_cita.getModeloTablaPrescripciones());
    }
}
