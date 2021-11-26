package controlador;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Random;

import javax.swing.JOptionPane;

import dtos.UserDto;
import modelo.CitaModelo;
import modelo.HistorialModelo;
import modelo.LoginModelo;
import modelo.PacienteModelo;
import util.SendEmail;
import util.SwingUtil;
import util.Util;
import vista.AdminVista;
import vista.CrearCitaMedicoVista;
import vista.EstadisticasGerenteVista;
import vista.ListaCalendarioCitasVista;
import vista.LoginView;
import vista.MenuGerenteVista;

public class LoginControlador {

    private static final long PASSWORD_EXPIRATION_HOURS = 24;

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private LoginView lV;
    private LoginModelo lM;

    public LoginControlador(LoginView lV) {
	this.lV = lV;
	this.lM = new LoginModelo();
    }

    public void login() {
	comprobarCaducados();

	String user = lV.getTextFieldUser().getText();
	char[] password = lV.getPasswordField().getPassword();

	UserDto u = null;
	if (!lM.findUser(user).isEmpty())
	    u = lM.findUser(user).get(0);

	if (u != null && encriptar(password).equals(u.password)) {
	    switch (u.category.toString()) {
	    case "MEDICO": {
		ListaCalendarioCitasControlador controller = new ListaCalendarioCitasControlador(
			new ListaCalendarioCitasVista(), new CitaModelo(), new PacienteModelo(), u.id_asociado,
			new MedicoCrearCitasControlador(new CrearCitaMedicoVista()));
		controller.inicializar();
		break;
	    }
	    case "ADMINISTRATIVO": {
		AdminVista.main(null);
		break;
	    }
	    case "GERENTE": {
		MenuGerenteVista mgv = new MenuGerenteVista();
		mgv.setVisible(true);
		mgv.getBtnEnfermedades().addActionListener(
			a -> SwingUtil.exceptionWrapper(() -> inicializarEstadisticasEnfermedades()));
		mgv.getBtnVacunas()
			.addActionListener(a -> SwingUtil.exceptionWrapper(() -> inicializarEstadisticasVacunas()));

		break;
	    }
	    }
	} else {
	    JOptionPane.showMessageDialog(lV.getFrmIhospitalLogin(), "Usuario y/o contraseña incorrecto/s.");
	}

    }

    private void inicializarEstadisticasVacunas() {
	EstadisticasVacunacionControlador evc = new EstadisticasVacunacionControlador();
	evc.inicializar();
    }

    private void inicializarEstadisticasEnfermedades() {
	EstadisticasGerenteControlador egc = new EstadisticasGerenteControlador(new HistorialModelo(), new CitaModelo(),
		new PacienteModelo(), new EstadisticasGerenteVista());
	egc.inicilizar();
    }

    private void comprobarCaducados() {
	for (UserDto u : lM.findAll()) {
	    if (u.getExpiration() != null) {
		LocalDateTime ex = LocalDateTime.parse(u.getExpiration(), formatter);
		if (ex.isBefore(LocalDateTime.now())) {
		    lM.expire(u.getId());
		}
	    }
	}
    }

    private String encriptar(char[] p) {
	String ret = "";
	for (char c : p) {
	    Character ch = Character.valueOf((char) (c + 10));
	    ret = ret.concat(ch.toString());
	}
	return ret;
    }

    public void forgotPassword() {
	String email = JOptionPane.showInputDialog("Introduzca su e-mail");
	LocalDateTime now = LocalDateTime.now();

	LocalDateTime tomorrow = now.plusHours(PASSWORD_EXPIRATION_HOURS);

	// LocalDateTime tomorrow = now.plusMinutes(2);

	String expiration = tomorrow.format(formatter);

	if (email != null) {

	    UserDto u = null;
	    if (!lM.findUserByEmail(email).isEmpty())
		u = lM.findUserByEmail(email).get(0);

	    if (u != null) {
		String newPassword = generatePassword();

		lM.setPassword(u.getId(), encriptar(newPassword.toCharArray()));
		lM.setExpiration(u.getId(), expiration);

		sendEmail(email, newPassword);

		JOptionPane.showMessageDialog(lV.getFrmIhospitalLogin(),
			"Se le ha enviado un correo con la nueva contraseña.");
	    } else {
		JOptionPane.showMessageDialog(lV.getFrmIhospitalLogin(), "No existe cuenta para este e-mail.");
	    }
	}
    }

    private String generatePassword() {
	int leftLimit = 48; // numeral '0'
	int rightLimit = 122; // letter 'z'
	int targetStringLength = 6;
	Random random = new Random();

	String generatedString = random.ints(leftLimit, rightLimit + 1)
		.filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97)).limit(targetStringLength)
		.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();

	return generatedString;
    }

    private void sendEmail(String email, String newPassword) {
	Date now = Date.from(Instant.now());
	String moment = Util.dateToIsoString(now) + " " + Util.dateToIsoHour(now);

	String msg = "";

	msg += "<h1>";
	msg += "iHospital: Nueva contraseña";
	msg += "</h1>";

	msg += "<h3>";
	msg += "Esta contraseña será válida durante " + PASSWORD_EXPIRATION_HOURS + " horas.";
	msg += "</h3>";

	msg += "<p>";
	msg += "Su nueva contraseña es <strong>" + newPassword + "</strong>";
	msg += "</p>";

	msg += "<p>";
	msg += "Fecha:  <strong>" + moment + "</strong>";
	msg += "</p>";

	SendEmail.main("iHospital: Nueva contraseña", msg, email);
    }
}
