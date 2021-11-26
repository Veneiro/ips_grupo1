package controlador;

import java.time.Instant;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import dtos.CitaDto;
import dtos.MedicoDto;
import dtos.PacienteDto;
import dtos.RegistroDto;
import modelo.MedicoModelo;
import modelo.PacienteModelo;
import modelo.RegistroModelo;
import util.SendEmail;
import util.SwingUtil;
import util.Util;
import vista.EDOVista;

public class EDOControlador {

    private String nameEdo = "Sin definir", observaciones = "Sin definir";

    private CitaDto cita;

    private MedicoModelo mM;
    private MedicoDto medico;

    private PacienteModelo pM;
    private PacienteDto paciente;

    private EDOVista eV;

    public EDOControlador(CitaDto cita) {
	pM = new PacienteModelo();
	mM = new MedicoModelo();
	eV = new EDOVista();
	paciente = pM.getPacienteById(cita.getId_paciente()).get(0);
	medico = mM.getListaMedicosById(cita.getId_medico()).get(0);
	this.cita = cita;

	inicializar();

    }

    private void inicializar() {
	eV.getBtnAceptar().addActionListener(e -> SwingUtil.exceptionWrapper(() -> sendEmail()));

	DocumentListener listenerAceptar = new DocumentListener() {

	    @Override
	    public void removeUpdate(DocumentEvent e) {
		checkBtnAceptar();
	    }

	    @Override
	    public void insertUpdate(DocumentEvent e) {
		checkBtnAceptar();
	    }

	    @Override
	    public void changedUpdate(DocumentEvent e) {
		checkBtnAceptar();
	    }
	};

	eV.getTextFieldNombre().getDocument().addDocumentListener(listenerAceptar);
	eV.getTextPaneObservaciones().getDocument().addDocumentListener(listenerAceptar);

	eV.setLocationRelativeTo(null);
	eV.setVisible(true);
    }

    private void checkBtnAceptar() {
	if (eV.getTextFieldNombre().getText().isEmpty() && eV.getTextPaneObservaciones().getText().isEmpty())
	    eV.getBtnAceptar().setEnabled(false);
	else
	    eV.getBtnAceptar().setEnabled(true);
    }

    private void sendEmail() {
	Date now = Date.from(Instant.now());
	String moment = Util.dateToIsoString(now) + " " + Util.dateToIsoHour(now);

	if (!eV.getTextFieldNombre().getText().isBlank())
	    nameEdo = eV.getTextFieldNombre().getText();

	if (!eV.getTextPaneObservaciones().getText().isBlank())
	    observaciones = eV.getTextPaneObservaciones().getText();

	String msg = "";

	msg += "<h1 style=\"text-align: left; color: #ff0000;\">";
	msg += "ENFERMEDAD DE DECLARACIÓN OBLIGATORIA";
	msg += "</h1>";

	msg += "<h2>";
	msg += "Nombre: " + nameEdo;
	msg += "</h2>";

	msg += "<h3>";
	msg += "Observaciones: <p>" + observaciones;
	msg += "</p></h3>";

	msg += "<p>";
	msg += "Médico: <strong>" + medico.getNombre() + "</strong>";
	msg += "</p>";

	msg += "<p>";
	msg += "Paciente <strong>" + paciente.getNombre() + " " + paciente.getApellido()
		+ "</strong> (Contacto: <strong>" + paciente.getInfo_contacto() + "</strong>)";
	msg += "</p>";

	if (cita.getUbicacion() != null) {
	    msg += "<p>";
	    msg += "Sala: <strong>" + cita.getUbicacion() + "</strong>";
	    msg += "</p>";
	}

	msg += "<p>";
	msg += "Fecha:  <strong>" + moment + "</strong>";
	msg += "</p>";

	SendEmail.main("Se ha declarado una Enfermedad de Declaración Obligatoria", msg, "gaspar.pisa@gmail.com");
	JOptionPane.showMessageDialog(eV, "Se ha informado a gerencia");
	RegistroModelo.addRegistro(new RegistroDto("Médico " + medico.getId(),
		"Ha informado de una EDO. Nombre: " + nameEdo + ". Observaciones: " + observaciones));
	eV.dispose();
    }
}
