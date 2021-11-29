package controlador;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import dtos.DiagnosticoDto;
import modelo.DiagnosticoModelo;
import util.SwingUtil;
import vista.AddDiagnosticoVista;

public class AddDiagnosticoControlador {

	private AddDiagnosticoVista adv;
	private HistorialControlador hc;
	private DiagnosticoModelo dgm;
	private int idPaciente;
	private int idMedico;

	public AddDiagnosticoControlador(int idPaciente, int idMedico, HistorialControlador hc) {
		this.idPaciente = idPaciente;
		this.idMedico = idMedico;
		this.adv = new AddDiagnosticoVista();
		this.hc = hc;
		this.dgm = new DiagnosticoModelo();
		inicializarVista();
	}

	private void inicializarVista() {
		adv.setVisible(true);
	}

	public void inicializar() {
		adv.getTextHora().setText(
				String.valueOf(LocalDateTime.now().getHour()) + ":" + String.valueOf(LocalDateTime.now().getMinute()));
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String fecha = dtf.format(LocalDateTime.now());
		adv.getTextFecha().setText(fecha);
		adv.getBtnAceptar().addActionListener(e -> SwingUtil.exceptionWrapper(() -> addDiagnostico()));
	}

	private void addDiagnostico() {
		if (!adv.getTextDiagnostico().getText().isEmpty()) {
			List<DiagnosticoDto> diagnosticos = new ArrayList<>();
			diagnosticos = dgm.getAllDiagnosticos();
			for (DiagnosticoDto d : diagnosticos) {
				dgm.updateDiagnosticosNoActual(d.getId());
			}
			DiagnosticoDto diagnostico = new DiagnosticoDto();
			diagnostico.setDiagnostico(adv.getTextDiagnostico().getText());
			diagnostico.setFecha(adv.getTextFecha().getText());
			diagnostico.setId_paciente(idPaciente);
			diagnostico.setId_medico(idMedico);
			diagnostico.setHora_creacion(adv.getTextHora().getText());
			diagnostico.setActual("Sí");
			dgm.addDiagnostico(diagnostico);
		}

		hc.cargarHistorial(idPaciente);
		adv.setVisible(false);
	}

}
