package controlador;

import java.io.File;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;

import dtos.MedicoDto;
import dtos.MensajesDto;
import modelo.MedicoModelo;
import modelo.MensajesModelo;
import util.SwingUtil;
import vista.BandejaDeEntradaVista;
import vista.MensajeCompletoVista;
import vista.MensajeEnviar;

public class MensajesControlador {

	private BandejaDeEntradaVista bev;
	private MensajeCompletoVista mcv;
	private MensajeEnviar me;
	private JFileChooser fc = new JFileChooser();

	private MedicoModelo mm = new MedicoModelo();
	private MensajesModelo msjm = new MensajesModelo();
	private int idMedico;

	public MensajesControlador(BandejaDeEntradaVista bev,
			MensajeCompletoVista mcv, MensajeEnviar me) {
		this.bev = bev;
		this.mcv = mcv;
		this.me = me;
	}

	public void showBandejaDeEntrada(int idMedico) {
		this.idMedico = idMedico;
		bev.getBtnSend().addActionListener(
				e -> SwingUtil.exceptionWrapper(() -> showSendMessage()));
		bev.setLocationRelativeTo(null);
		bev.setVisible(true);
	}

	private void showSendMessage() {
		me = new MensajeEnviar();

		setCbModels();

		me.getBtnEnviar().addActionListener(
				e -> SwingUtil.exceptionWrapper(() -> sendMensaje()));
		me.getBtnBack().addActionListener(
				e -> SwingUtil.exceptionWrapper(() -> me.setVisible(false)));
		me.getBtnAdjunto().addActionListener(
				e -> SwingUtil.exceptionWrapper(() -> chooseFile()));
		me.setLocationRelativeTo(bev);
		me.setVisible(true);
	}

	private void sendMensaje() {
		MensajesDto mdto = new MensajesDto();
		String Asunto = me.getTxtAsunto().getText();
		String Mensaje = me.getTxtAreaMessage().getText();
		String Remitente = ((MedicoDto) me.getCbRemitente().getSelectedItem())
				.getNombre();
		String Destinatario = ((MedicoDto) me.getCbDestinatarios()
				.getSelectedItem()).getNombre();
		
		
		mdto.setASUNTO(Asunto);
		mdto.setMENSAJE(Mensaje);
		mdto.setREMITENTE(Remitente);
		mdto.setDESTINATARIO(Destinatario);
		
		msjm.sendMensaje(mdto);
		me.setVisible(false);
	}

	private void setCbModels() {
		DefaultComboBoxModel<MedicoDto> dcbm = new DefaultComboBoxModel<MedicoDto>();
		for (MedicoDto medico : mm.getListaMedicos()) {
			if (medico.getId() != idMedico) {
				dcbm.addElement(medico);
			}
		}
		me.getCbDestinatarios().setModel(dcbm);

		DefaultComboBoxModel<MedicoDto> dcbm1 = new DefaultComboBoxModel<MedicoDto>();
		for (MedicoDto medico : mm.getListaMedicos()) {
			if (medico.getId() == idMedico) {
				dcbm1.addElement(medico);
			}
		}
		me.getCbRemitente().setModel(dcbm1);

	}

	private void chooseFile() {
		fc.setMultiSelectionEnabled(true);
		fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		int selection = fc.showOpenDialog(me);
		if (selection == JFileChooser.APPROVE_OPTION) {
			File[] f = fc.getSelectedFiles();
			for (File file : f) {
				System.out.println(file.getPath());
			}
		} else if (selection == JFileChooser.CANCEL_OPTION) {
			fc.setVisible(false);
		}
	}

}
