package controlador;

import util.SwingUtil;
import vista.VacunaMenuVista;

public class VacunaMenuControlador {

	private HistorialControlador hc;
	private VacunaMenuVista vmv;
	private int idPaciente;
	private int idMedico;
	
	public VacunaMenuControlador(HistorialControlador hc, int idPaciente, int idMedico) {
		 this.hc = hc;
		 this.vmv = new VacunaMenuVista();
		 this.idPaciente = idPaciente;
		 this.idMedico = idMedico;
		 
		 inicalizarVacunaMenu();
	}

	private void inicalizarVacunaMenu() {
		vmv.setVisible(true);
	}
	
	public void inicializar() {
		
		vmv.getBtnModificar().addActionListener(e -> SwingUtil.exceptionWrapper(() -> inicializarModificarVista()));
		vmv.getBtnNueva().addActionListener(e -> SwingUtil.exceptionWrapper(() -> inicializarNuevaVacunaVista()));
	}

	private void inicializarNuevaVacunaVista() {
		
	}

	private void inicializarModificarVista() {
		ModificarVacunaControlador mc = new ModificarVacunaControlador(hc, idPaciente, idMedico);
		mc.inicializar();
	}
	
}
