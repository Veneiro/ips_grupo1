package controlador;

import util.SwingUtil;
import vista.AntecedenteMenuVista;

public class AntecedentesMenuControlador {

	private HistorialControlador hc;
	private AntecedenteMenuVista amv;
	private int idPaciente;
	private int idMedico;
	
	public AntecedentesMenuControlador(HistorialControlador hc, int idPaciente, int idMedico) {
		 this.hc = hc;
		 this.amv = new AntecedenteMenuVista();
		 this.idPaciente = idPaciente;
		 this.idMedico = idMedico;
		 
		 inicalizarVacunaMenu();
	}

	private void inicalizarVacunaMenu() {
		amv.setVisible(true);
	}
	
	public void inicializar() {
		
		amv.getBtnModificar().addActionListener(e -> SwingUtil.exceptionWrapper(() -> inicializarModificarVista()));
		amv.getBtnNueva().addActionListener(e -> SwingUtil.exceptionWrapper(() -> inicializarNuevaVacunaVista()));
	}

	private void inicializarNuevaVacunaVista() {
		NuevoAntecedenteControlador nac = new NuevoAntecedenteControlador(idPaciente, hc);
		nac.inicializar();
	}

	private void inicializarModificarVista() {
		ModificarAntecedenteControlador mac = new ModificarAntecedenteControlador(hc, idPaciente, idMedico);
		mac.inicializar();
	}
}
