package controlador;

import javax.swing.JFileChooser;

import util.SwingUtil;
import vista.BandejaDeEntradaVista;
import vista.MensajeCompletoVista;
import vista.MensajeEnviar;

public class MensajesControlador {

	private BandejaDeEntradaVista bev;
	private MensajeCompletoVista mcv;
	private MensajeEnviar me;
	private JFileChooser fc = new JFileChooser();

	public MensajesControlador(BandejaDeEntradaVista bev,
			MensajeCompletoVista mcv, MensajeEnviar me) {
		this.bev = bev;
		this.mcv = mcv;
		this.me = me;
	}

	public void showBandejaDeEntrada() {
		bev.getBtnSend().addActionListener(
				e -> SwingUtil.exceptionWrapper(() -> showSendMessage()));
		bev.setLocationRelativeTo(null);
		bev.setVisible(true);
	}

	private void showSendMessage() {
		me = new MensajeEnviar();
		
		me.getBtnBack().addActionListener(
				e -> SwingUtil.exceptionWrapper(() -> me.setVisible(false)));
		me.getBtnAdjunto().addActionListener(
				e -> SwingUtil.exceptionWrapper(() -> chooseFile()));
		me.setLocationRelativeTo(bev);
		me.setVisible(true);
	}

	private void chooseFile() {
		int selection = fc.showOpenDialog(me);
		if(selection == JFileChooser.APPROVE_OPTION) {
			
		} else if (selection == JFileChooser.APPROVE_OPTION) {
			
		} else if(selection == JFileChooser.CANCEL_OPTION) {
			fc.setVisible(false);
		}
	}

}
