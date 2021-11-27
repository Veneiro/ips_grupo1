package controlador;

import java.io.File;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.table.DefaultTableModel;

import dtos.CitaPendienteDto;
import dtos.MedicoDto;
import dtos.MensajesDto;
import dtos.PacienteDto;
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
	private File f;

	public MensajesControlador(BandejaDeEntradaVista bev,
			MensajeCompletoVista mcv, MensajeEnviar me) {
		this.bev = bev;
		this.mcv = mcv;
		this.me = me;
	}

	public void showBandejaDeEntrada(int idMedico) {
		this.idMedico = idMedico;
		initializateTable();
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
		int Remitente = ((MedicoDto) me.getCbRemitente().getSelectedItem())
				.getId();
		int Destinatario = ((MedicoDto) me.getCbDestinatarios()
				.getSelectedItem()).getId();
		if (f != null) {
			String Adjunto = f.getPath();
			mdto.setADJUNTO(Adjunto);
		}
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
		fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		int selection = fc.showOpenDialog(me);
		if (selection == JFileChooser.APPROVE_OPTION) {
			f = fc.getSelectedFile();
		} else if (selection == JFileChooser.CANCEL_OPTION) {
			fc.setVisible(false);
		}
	}

	private void initializateTable() {
		DefaultTableModel dm = new DefaultTableModel(0, 0);
		String header[] = new String[] { "Enviado por: ", "Asunto", "Mensaje" };
		dm.setColumnIdentifiers(header);

		List<MensajesDto> mensajes = msjm.getMensajes();
		for (MensajesDto m : mensajes) {
			if ((int) m.getDESTINATARIO() == idMedico) {
				Vector<Object> data = new Vector<Object>();
				data.add(getMedicoByID(m.getREMITENTE()).getNombre());
				data.add(m.getASUNTO());
				data.add(m.getMENSAJE());
				dm.addRow(data);
			}
		}

		bev.getTableMessages().setModel(dm);
	}

	private MedicoDto getMedicoByID(int remitente) {
		for (MedicoDto medico : mm.getListaMedicos()) {
			if (medico.getId() == remitente) {
				return medico;
			}
		}
		return null;
	}

}
