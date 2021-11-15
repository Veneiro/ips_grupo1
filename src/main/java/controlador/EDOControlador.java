package controlador;

import java.time.Instant;
import java.util.Date;

import dtos.CitaDto;
import dtos.MedicoDto;
import dtos.PacienteDto;
import dtos.RegistroDto;
import modelo.MedicoModelo;
import modelo.PacienteModelo;
import modelo.RegistroModelo;
import util.SendEmail;
import util.Util;

public class EDOControlador {

    private String nameEdo;

    private CitaDto cita;

    private MedicoModelo mM;
    private MedicoDto medico;

    private PacienteModelo pM;
    private PacienteDto paciente;

    public EDOControlador(String nameEdo, CitaDto cita) {
	pM = new PacienteModelo();
	mM = new MedicoModelo();
	paciente = pM.getPacienteById(cita.getId_paciente()).get(0);
	medico = mM.getListaMedicosById(cita.getId_medico()).get(0);
	this.cita = cita;
	this.nameEdo = nameEdo;
	sendEmail();
	RegistroModelo.addRegistro(new RegistroDto("Médico " + medico.getId(), "Ha informado de una EDO: " + nameEdo));
    }

    private void sendEmail() {
	Date now = Date.from(Instant.now());
	String moment = Util.dateToIsoString(now) + " " + Util.dateToIsoHour(now);
	String msg = "";

	msg += "<h1 style=\"text-align: left; color: #ff0000;\">";
	msg += "ENFERMEDAD DE DECLARACIÓN OBLIGATORIA";
	msg += "</h1>";

	msg += "<p>";
	msg += "<strong>" + nameEdo + "</strong>";
	msg += "</p>";

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
    }
}
