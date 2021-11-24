package controlador;

import util.SwingUtil;
import vista.MenuMedicoVista;

public class MenuMedicoControlador {

	private MenuMedicoVista mmv;
	private ListaCalendarioCitasControlador lcc;

	public MenuMedicoControlador(MenuMedicoVista mmv,
			ListaCalendarioCitasControlador lcc) {

		this.mmv = mmv;
		this.lcc = lcc;
	}
	
	public void initializate() {
		mmv.getBtnGestion().addActionListener(
				e -> SwingUtil.exceptionWrapper(() -> lcc.inicializar()));
		lcc.actionProponer(mmv);
		lcc.citasMod(mmv);
		
		mmv.setLocationRelativeTo(null);
		mmv.setVisible(true);
	}
}
