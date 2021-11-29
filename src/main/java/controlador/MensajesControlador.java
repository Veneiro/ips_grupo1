package controlador;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

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
	private File f;
	private String path;

	private LocalDateTime ldt = LocalDateTime.now();

	public MensajesControlador(BandejaDeEntradaVista bev, MensajeCompletoVista mcv, MensajeEnviar me) {
		this.bev = bev;
		this.mcv = mcv;
		this.me = me;
	}

	public void showBandejaDeEntrada(int idMedico) {
		this.idMedico = idMedico;
		initializateTable();
		bev.getBtnSend().addActionListener(e -> SwingUtil.exceptionWrapper(() -> showSendMessage()));
		bev.getBtnOpen().addActionListener(e -> SwingUtil.exceptionWrapper(() -> showOpenMessage()));
		bev.getBtnHelp().addActionListener(e -> SwingUtil.exceptionWrapper(() -> showHelp()));
		bev.setLocationRelativeTo(null);
		bev.setVisible(true);
	}

	private void showHelp() {
		String helpMessage = "";
		helpMessage += "----AYUDA----\n";
		helpMessage += "|Bandeja de entrada|:\n";
		helpMessage += "    - Pulsa el botón Enviar para enviar un mensaje a un compañero\n";
		helpMessage += "    - Clicka en un mensaje de la bandeja de entrada y pulsa el botón Abrir Mensaje para leerlo con detalle\n";
		helpMessage += "|Enviar Mensaje|:\n";
		helpMessage += "  En esta ventana podrás seleccionar el destinatario del mensaje y escribir un asunto y cuerpo del mensaje\n";
		helpMessage += "  Los botones que se ven abajo sirven para lo siguiente:\n";
		helpMessage += "    - Si pulsas el botón Adjuntar archivo se te permitirá seleccionar un archivo de tu ordenador para enviarlo"
				+ "junto al mensaje\n";
		helpMessage += "    - Si pulsas el botón Enviar el mensaje será enviado y aparecerá en la bandeja de entrada del compañero"
				+ "destinatario del mismo\n";
		helpMessage += "    - El botón Atrás te permitirá volver a la bandeja de entrada y cancelar el envío\n";
		helpMessage += "|Vista completa del mensaje|:\n";
		helpMessage += "  En esta vista se te completarán los campos con todos los detalles del mensaje seleccionado con más información"
				+ "que la vista en la bandeja de entrada\n";
		helpMessage += "  Los botones de la parte inferior sirven para lo siguiente:\n";
		helpMessage += "    - El botón responder te permitirá responder al médico que envía ese mensaje de manera rápida, los datos se"
				+ "autocompletarán en una ventana para enviar el mensaje y te permitirá cambiar el mensaje, asunto y "
				+ "adjuntar un archivo\n";
		helpMessage += "    - El botón archivo adjunto te permitirá visualizar el archivo enviado por el otro médico en caso de que haya"
				+ "enviado algún archivo\n";
		helpMessage += "    - El botón Atrás, al igual que en la ventana de Enviar te permitirá regresar a la Bandeja de Entrada\n";
		JOptionPane.showMessageDialog(bev, helpMessage);
	}

	private void showOpenMessage() {
		if (bev.getTableMessages().getSelectedRow() != -1) {
			loadData();
			setButtons();
			mcv.setLocationRelativeTo(bev);
			mcv.setVisible(true);
		} else {
			JOptionPane.showMessageDialog(bev, "Por favor seleccione un mensaje");
		}
	}

	private void setButtons() {
		mcv.getBtnBack().addActionListener(e -> SwingUtil.exceptionWrapper(() -> mcv.setVisible(false)));
		mcv.getBtnResponder().addActionListener(e -> SwingUtil.exceptionWrapper(() -> responderMensaje()));
		mcv.getBtnAdjunto().addActionListener(e -> SwingUtil.exceptionWrapper(() -> openFile()));
	}

	private void openFile() {
		if (f == null) {
			JOptionPane.showMessageDialog(mcv, "El mensaje no contiene archivos adjuntos");
		} else {
			Desktop d = Desktop.getDesktop();
			try {
				d.open(f);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void responderMensaje() {
		me = new MensajeEnviar();

		setCbModels();
		loadDataResponder();

		me.getBtnEnviar().addActionListener(e -> SwingUtil.exceptionWrapper(() -> sendMensaje()));
		me.getBtnBack().addActionListener(e -> SwingUtil.exceptionWrapper(() -> me.setVisible(false)));
		me.getBtnAdjunto().addActionListener(e -> SwingUtil.exceptionWrapper(() -> chooseFile()));
		me.setLocationRelativeTo(bev);
		me.setVisible(true);
	}

	private void loadDataResponder() {
		String Remitente = mcv.getTxtRemitente().getText();
		String Destinatario = mcv.getTxtDestinatario().getText();
		String Asunto = mcv.getTxtAsunto().getText();

		for (int i = 0; i < me.getCbRemitente().getModel().getSize(); i++) {
			if (Remitente.equals(me.getCbRemitente().getModel().getElementAt(i))) {
				me.getCbRemitente().setSelectedIndex(i);
			}
			if (Destinatario.equals(me.getCbDestinatarios().getModel().getElementAt(i))) {
				me.getCbDestinatarios().setSelectedIndex(i);
			}
		}
		me.getCbDestinatarios().setEnabled(false);

		me.getTxtAsunto().setText("Re: " + Asunto);
	}

	private void loadData() {
		MensajesDto mdto = new MensajesDto();
		String Remitente = (String) bev.getTableMessages().getValueAt(bev.getTableMessages().getSelectedRow(), 0);
		String Asunto = (String) bev.getTableMessages().getValueAt(bev.getTableMessages().getSelectedRow(), 1);
		String Mensaje = (String) bev.getTableMessages().getValueAt(bev.getTableMessages().getSelectedRow(), 2);
		for (MensajesDto mensaje : msjm.getMensajes()) {
			if ((getMedicoById(mensaje.getREMITENTE()).getNombre().equals(Remitente))
					&& mensaje.getASUNTO().equals(Asunto) && mensaje.getMENSAJE().equals(Mensaje)) {
				mdto = mensaje;
			}
		}
		mcv.getTxtRemitente().setText((getMedicoById(mdto.getREMITENTE()).getNombre()));
		mcv.getTxtDestinatario().setText((getMedicoById(mdto.getDESTINATARIO()).getNombre()));
		mcv.getTxtAsunto().setText(mdto.getASUNTO());
		mcv.getTxtAreaMessage().setText(mdto.getMENSAJE());
		f = new File(mdto.getADJUNTO());
	}

	private MedicoDto getMedicoById(int id) {
		for (MedicoDto medico : mm.getListaMedicos()) {
			if (medico.getId() == id) {
				return medico;
			}
		}
		return null;
	}

	private void showSendMessage() {
		me = new MensajeEnviar();

		setCbModels();

		me.getBtnEnviar().addActionListener(e -> SwingUtil.exceptionWrapper(() -> sendMensaje()));
		me.getBtnBack().addActionListener(e -> SwingUtil.exceptionWrapper(() -> me.setVisible(false)));
		me.getBtnAdjunto().addActionListener(e -> SwingUtil.exceptionWrapper(() -> chooseFile()));
		me.setLocationRelativeTo(bev);
		me.setVisible(true);
	}

	private void sendMensaje() {
		MensajesDto mdto = new MensajesDto();
		String Asunto = me.getTxtAsunto().getText();
		String Mensaje = me.getTxtAreaMessage().getText();
		int Remitente = ((MedicoDto) me.getCbRemitente().getSelectedItem()).getId();
		int Destinatario = ((MedicoDto) me.getCbDestinatarios().getSelectedItem()).getId();
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
		mcv.setVisible(false);
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
