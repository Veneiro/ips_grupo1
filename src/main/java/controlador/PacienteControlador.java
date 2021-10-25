package controlador;

import java.util.List;

import javax.swing.DefaultListModel;

import dtos.CauseDto;
import dtos.CitaDto;
import lombok.Getter;
import lombok.Setter;
import modelo.CauseModel;
import modelo.PacienteModelo;
import util.SendEmail;
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

	public PacienteControlador(PacienteModelo pacienteModelo,
			AppointmentView pacienteVista) {
		this.vista_cita = pacienteVista;
		this.modelo_paciente = pacienteModelo;
		this.cm = new CauseModel();
	}

	public void initialize() {
		vista_cita.getBtnUpdate().addActionListener(
				e -> SwingUtil.exceptionWrapper(() -> updateList()));
		vista_cita.getBtnContinueButton().addActionListener(
				e -> SwingUtil.exceptionWrapper(() -> insertCitaToDB()));
		vista_cita.getBtnAddCause().addActionListener(
				e -> SwingUtil.exceptionWrapper(() -> addCause()));
		showVistaCita();
	}

	private void addCause() {
		AppointmentControler controller = new AppointmentControler(
				new CauseModel(), new AddCauseView(), new NonExistenceView());
		controller.initialize();
		updateList();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void updateList() {
		List<CauseDto> causes = cm.getCauseList();
		DefaultListModel dlm = new DefaultListModel();
		for (CauseDto cause : causes) {
			dlm.addElement(cause.toString());
		}
		vista_cita.getListCauses().setModel(dlm);
	}

	public void showVistaCita() {
		vista_cita.setLocationRelativeTo(null);
		vista_cita.setVisible(true);
	}

	public void insertCitaToDB() {
		List<CitaDto> citas = cm.getCitasList();
		int id = 0;
		for (CitaDto cita : citas) {
			if (cita.getId() > id) {
				id = cita.getId();
			}
		}

		if (id == 0 && citas.size() == 0) {
			id = 0;
		} else {
			id++;
		}

		CitaDto cidto = new CitaDto();
		String houre = vista_cita.getSpEntryHour().getValue().toString();
		String mine = vista_cita.getSpEntryMin().getValue().toString();
		String houro = vista_cita.getSpOutHour().getValue().toString();
		String mineo = vista_cita.getSpOutMin().getValue().toString();

		cidto.setHorario_inicio(houre + " : " + mine);
		cidto.setHorario_fin(houro + " : " + mineo);
		cidto.setId(id);
		cidto.setId_medico(1);
		cidto.setId_paciente(1);
		cm.insertCita(cidto);
	}
}
