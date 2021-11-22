package controlador;

import java.text.SimpleDateFormat;
import java.util.Date;

import modelo.AntecedentesModelo;
import util.SwingUtil;
import vista.NuevoAntecendenteVista;

public class NuevoAntecedenteControlador {

	private int idPaciente;
	private AntecedentesModelo am;
	private HistorialControlador hc;
	private NuevoAntecendenteVista nav;
	
	public NuevoAntecedenteControlador(int idPaciente, HistorialControlador hc) {
		this.hc = hc;
		this.idPaciente = idPaciente;
		this.nav = new NuevoAntecendenteVista();
		this.am  = new AntecedentesModelo();
		
		inicializarVista();
	}

	private void inicializarVista() {
		nav.setVisible(true);
	}
	
	public void inicializar() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		Date date = new Date(System.currentTimeMillis());
		nav.getTextFecha().setText(formatter.format(date));
		nav.getBtnAceptar().addActionListener(e -> SwingUtil.exceptionWrapper(() -> insertarVacuna()));
	}

	private void insertarVacuna() {
		String fecha = nav.getTextFecha().getText();
		String antecedente = nav.getTextAntecedente().getText();
		
		
		am.addAntecedente(idPaciente, antecedente, fecha);
		hc.cargarHistorial(idPaciente);
		nav.setVisible(false);
	}
}
