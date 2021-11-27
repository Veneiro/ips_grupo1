package controlador;

import util.SwingUtil;
import vista.BandejaDeEntradaVista;
import vista.MensajeCompletoVista;
import vista.MensajeEnviar;
import vista.MenuMedicoVista;

public class MenuMedicoControlador {

	private MenuMedicoVista mmv;
	private ListaCalendarioCitasControlador lcc;
	private MensajesControlador mc;

	public MenuMedicoControlador(MenuMedicoVista mmv,
			ListaCalendarioCitasControlador lcc) {
		this.mc = new MensajesControlador(new BandejaDeEntradaVista(),
				new MensajeCompletoVista(), new MensajeEnviar());
		this.mmv = mmv;
		this.lcc = lcc;
	}

	public void initializate(int idMedico) {
		mmv.getBtnGestion().addActionListener(
				e -> SwingUtil.exceptionWrapper(() -> lcc.inicializar()));
		mmv.getBtnMensajeria().addActionListener(e -> SwingUtil
				.exceptionWrapper(() -> mc.showBandejaDeEntrada(idMedico)));
		lcc.actionProponer(mmv);
		lcc.citasMod(mmv);

		mmv.setLocationRelativeTo(null);
		mmv.setVisible(true);
	}
}
