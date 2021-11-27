package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class MensajeCompletoVista extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JPanel buttonPane;
	private JButton btnAdjunto;
	private JButton btnBack;
	private JPanel panel;
	private JPanel panel_1;
	private JPanel panel_2;
	private JLabel lblNewLabel;
	private JPanel panel_3;
	private JPanel panel_4;
	private JPanel panel_5;
	private JPanel panel_6;
	private JPanel panel_7;
	private JPanel panel_8;
	private JPanel panel_9;
	private JPanel panel_10;
	private JPanel panel_11;
	private JPanel panel_12;
	private JPanel panel_13;
	private JPanel panel_14;
	private JPanel panel_15;
	private JPanel panel_16;
	private JPanel panel_17;
	private JPanel panel_18;
	private JPanel panel_19;
	private JPanel panel_20;
	private JPanel panel_21;
	private JPanel panel_22;
	private JPanel panel_23;
	private JPanel panel_24;
	private JPanel panel_25;
	private JPanel panel_26;
	private JPanel panel_27;
	private JPanel panel_28;
	private JLabel lblRemitente;
	private JPanel panel_31;
	private JPanel panel_32;
	private JPanel panel_33;
	private JPanel panel_34;
	private JPanel panel_35;
	private JPanel panel_36;
	private JPanel panel_37;
	private JPanel panel_38;
	private JPanel panel_39;
	private JPanel panel_40;
	private JPanel panel_41;
	private JPanel panel_42;
	private JLabel lblDestinatario;
	private JLabel lblAsunto;
	private JTextField txtRemitente;
	private JTextField txtDestinatario;
	private JTextField txtAsunto;
	private JLabel lblNewLabel_1;
	private JScrollPane scrollPane;
	private JTextArea txtAreaMessage;
	private JPanel panel_29;
	private JPanel panel_30;
	private JPanel panel_43;
	private JPanel panel_44;
	private JPanel panel_45;
	private JPanel panel_46;
	private JPanel panel_47;
	private JPanel panel_48;

	/**
	 * Create the dialog.
	 */
	public MensajeCompletoVista() {
		setBounds(100, 100, 878, 512);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		contentPanel.add(getLblNewLabel(), BorderLayout.NORTH);
		contentPanel.add(getPanel_3(), BorderLayout.CENTER);
		contentPanel.add(getPanel_4(), BorderLayout.WEST);
		contentPanel.add(getPanel_5(), BorderLayout.EAST);
		contentPanel.add(getPanel_6(), BorderLayout.SOUTH);
		getContentPane().add(getButtonPane(), BorderLayout.SOUTH);
	}
	
	private JPanel getButtonPane() {
		if (buttonPane == null) {
			buttonPane = new JPanel();
			buttonPane.setLayout(new GridLayout(0, 5, 0, 0));
			buttonPane.add(getPanel());
			buttonPane.add(getPanel_1());
			buttonPane.add(getPanel_47());
			buttonPane.add(getPanel_48());
			buttonPane.add(getPanel_2());
		}
		return buttonPane;
	}

	private JButton getBtnAdjunto() {
		if (btnAdjunto == null) {
			btnAdjunto = new JButton("Archivo Adjunto");
			btnAdjunto.setFont(new Font("Tahoma", Font.BOLD, 14));
		}
		return btnAdjunto;
	}
	private JButton getBtnBack() {
		if (btnBack == null) {
			btnBack = new JButton("Atr\u00E1s");
			btnBack.setFont(new Font("Tahoma", Font.BOLD, 14));
		}
		return btnBack;
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setLayout(new BorderLayout(0, 0));
			panel.add(getBtnBack(), BorderLayout.CENTER);
			panel.add(getPanel_29(), BorderLayout.EAST);
			panel.add(getPanel_30(), BorderLayout.SOUTH);
			panel.add(getPanel_43(), BorderLayout.WEST);
		}
		return panel;
	}
	private JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
		}
		return panel_1;
	}
	private JPanel getPanel_2() {
		if (panel_2 == null) {
			panel_2 = new JPanel();
			panel_2.setLayout(new BorderLayout(0, 0));
			panel_2.add(getBtnAdjunto());
			panel_2.add(getPanel_44(), BorderLayout.WEST);
			panel_2.add(getPanel_45(), BorderLayout.SOUTH);
			panel_2.add(getPanel_46(), BorderLayout.EAST);
		}
		return panel_2;
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("Mensaje");
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblNewLabel;
	}
	private JPanel getPanel_3() {
		if (panel_3 == null) {
			panel_3 = new JPanel();
			panel_3.setLayout(new GridLayout(2, 0, 0, 0));
			panel_3.add(getPanel_7());
			panel_3.add(getPanel_8());
		}
		return panel_3;
	}
	private JPanel getPanel_4() {
		if (panel_4 == null) {
			panel_4 = new JPanel();
		}
		return panel_4;
	}
	private JPanel getPanel_5() {
		if (panel_5 == null) {
			panel_5 = new JPanel();
		}
		return panel_5;
	}
	private JPanel getPanel_6() {
		if (panel_6 == null) {
			panel_6 = new JPanel();
		}
		return panel_6;
	}
	private JPanel getPanel_7() {
		if (panel_7 == null) {
			panel_7 = new JPanel();
			panel_7.setLayout(new GridLayout(0, 2, 0, 0));
			panel_7.add(getPanel_9());
			panel_7.add(getPanel_10());
		}
		return panel_7;
	}
	private JPanel getPanel_8() {
		if (panel_8 == null) {
			panel_8 = new JPanel();
			panel_8.setLayout(new BorderLayout(0, 0));
			panel_8.add(getLblNewLabel_1(), BorderLayout.NORTH);
			panel_8.add(getScrollPane(), BorderLayout.CENTER);
		}
		return panel_8;
	}
	private JPanel getPanel_9() {
		if (panel_9 == null) {
			panel_9 = new JPanel();
			panel_9.setLayout(new GridLayout(3, 0, 0, 0));
			panel_9.add(getPanel_11());
			panel_9.add(getPanel_12());
			panel_9.add(getPanel_13());
		}
		return panel_9;
	}
	private JPanel getPanel_10() {
		if (panel_10 == null) {
			panel_10 = new JPanel();
			panel_10.setLayout(new GridLayout(3, 0, 0, 0));
			panel_10.add(getPanel_14());
			panel_10.add(getPanel_15());
			panel_10.add(getPanel_16());
		}
		return panel_10;
	}
	private JPanel getPanel_11() {
		if (panel_11 == null) {
			panel_11 = new JPanel();
			panel_11.setLayout(new BorderLayout(0, 0));
			panel_11.add(getPanel_17(), BorderLayout.NORTH);
			panel_11.add(getPanel_18(), BorderLayout.WEST);
			panel_11.add(getPanel_19(), BorderLayout.SOUTH);
			panel_11.add(getPanel_20(), BorderLayout.EAST);
			panel_11.add(getLblRemitente(), BorderLayout.CENTER);
		}
		return panel_11;
	}
	private JPanel getPanel_12() {
		if (panel_12 == null) {
			panel_12 = new JPanel();
			panel_12.setLayout(new BorderLayout(0, 0));
			panel_12.add(getPanel_21(), BorderLayout.NORTH);
			panel_12.add(getPanel_22(), BorderLayout.WEST);
			panel_12.add(getPanel_23(), BorderLayout.SOUTH);
			panel_12.add(getPanel_24(), BorderLayout.EAST);
			panel_12.add(getLblDestinatario(), BorderLayout.CENTER);
		}
		return panel_12;
	}
	private JPanel getPanel_13() {
		if (panel_13 == null) {
			panel_13 = new JPanel();
			panel_13.setLayout(new BorderLayout(0, 0));
			panel_13.add(getPanel_25(), BorderLayout.NORTH);
			panel_13.add(getPanel_26(), BorderLayout.WEST);
			panel_13.add(getPanel_27(), BorderLayout.SOUTH);
			panel_13.add(getPanel_28(), BorderLayout.EAST);
			panel_13.add(getLblAsunto(), BorderLayout.CENTER);
		}
		return panel_13;
	}
	private JPanel getPanel_14() {
		if (panel_14 == null) {
			panel_14 = new JPanel();
			panel_14.setLayout(new BorderLayout(0, 0));
			panel_14.add(getPanel_31(), BorderLayout.NORTH);
			panel_14.add(getPanel_32(), BorderLayout.WEST);
			panel_14.add(getPanel_33(), BorderLayout.SOUTH);
			panel_14.add(getPanel_34(), BorderLayout.EAST);
			panel_14.add(getTxtRemitente(), BorderLayout.CENTER);
		}
		return panel_14;
	}
	private JPanel getPanel_15() {
		if (panel_15 == null) {
			panel_15 = new JPanel();
			panel_15.setLayout(new BorderLayout(0, 0));
			panel_15.add(getPanel_35(), BorderLayout.NORTH);
			panel_15.add(getPanel_36(), BorderLayout.WEST);
			panel_15.add(getPanel_37(), BorderLayout.SOUTH);
			panel_15.add(getPanel_38(), BorderLayout.EAST);
			panel_15.add(getTxtDestinatario(), BorderLayout.CENTER);
		}
		return panel_15;
	}
	private JPanel getPanel_16() {
		if (panel_16 == null) {
			panel_16 = new JPanel();
			panel_16.setLayout(new BorderLayout(0, 0));
			panel_16.add(getPanel_39(), BorderLayout.WEST);
			panel_16.add(getPanel_40(), BorderLayout.NORTH);
			panel_16.add(getPanel_41(), BorderLayout.EAST);
			panel_16.add(getPanel_42(), BorderLayout.SOUTH);
			panel_16.add(getTxtAsunto(), BorderLayout.CENTER);
		}
		return panel_16;
	}
	private JPanel getPanel_17() {
		if (panel_17 == null) {
			panel_17 = new JPanel();
		}
		return panel_17;
	}
	private JPanel getPanel_18() {
		if (panel_18 == null) {
			panel_18 = new JPanel();
		}
		return panel_18;
	}
	private JPanel getPanel_19() {
		if (panel_19 == null) {
			panel_19 = new JPanel();
		}
		return panel_19;
	}
	private JPanel getPanel_20() {
		if (panel_20 == null) {
			panel_20 = new JPanel();
		}
		return panel_20;
	}
	private JPanel getPanel_21() {
		if (panel_21 == null) {
			panel_21 = new JPanel();
		}
		return panel_21;
	}
	private JPanel getPanel_22() {
		if (panel_22 == null) {
			panel_22 = new JPanel();
		}
		return panel_22;
	}
	private JPanel getPanel_23() {
		if (panel_23 == null) {
			panel_23 = new JPanel();
		}
		return panel_23;
	}
	private JPanel getPanel_24() {
		if (panel_24 == null) {
			panel_24 = new JPanel();
		}
		return panel_24;
	}
	private JPanel getPanel_25() {
		if (panel_25 == null) {
			panel_25 = new JPanel();
		}
		return panel_25;
	}
	private JPanel getPanel_26() {
		if (panel_26 == null) {
			panel_26 = new JPanel();
		}
		return panel_26;
	}
	private JPanel getPanel_27() {
		if (panel_27 == null) {
			panel_27 = new JPanel();
		}
		return panel_27;
	}
	private JPanel getPanel_28() {
		if (panel_28 == null) {
			panel_28 = new JPanel();
		}
		return panel_28;
	}
	private JLabel getLblRemitente() {
		if (lblRemitente == null) {
			lblRemitente = new JLabel("Remitente del Mensaje:");
			lblRemitente.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblRemitente.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblRemitente;
	}
	private JPanel getPanel_31() {
		if (panel_31 == null) {
			panel_31 = new JPanel();
		}
		return panel_31;
	}
	private JPanel getPanel_32() {
		if (panel_32 == null) {
			panel_32 = new JPanel();
		}
		return panel_32;
	}
	private JPanel getPanel_33() {
		if (panel_33 == null) {
			panel_33 = new JPanel();
		}
		return panel_33;
	}
	private JPanel getPanel_34() {
		if (panel_34 == null) {
			panel_34 = new JPanel();
		}
		return panel_34;
	}
	private JPanel getPanel_35() {
		if (panel_35 == null) {
			panel_35 = new JPanel();
		}
		return panel_35;
	}
	private JPanel getPanel_36() {
		if (panel_36 == null) {
			panel_36 = new JPanel();
		}
		return panel_36;
	}
	private JPanel getPanel_37() {
		if (panel_37 == null) {
			panel_37 = new JPanel();
		}
		return panel_37;
	}
	private JPanel getPanel_38() {
		if (panel_38 == null) {
			panel_38 = new JPanel();
		}
		return panel_38;
	}
	private JPanel getPanel_39() {
		if (panel_39 == null) {
			panel_39 = new JPanel();
		}
		return panel_39;
	}
	private JPanel getPanel_40() {
		if (panel_40 == null) {
			panel_40 = new JPanel();
		}
		return panel_40;
	}
	private JPanel getPanel_41() {
		if (panel_41 == null) {
			panel_41 = new JPanel();
		}
		return panel_41;
	}
	private JPanel getPanel_42() {
		if (panel_42 == null) {
			panel_42 = new JPanel();
		}
		return panel_42;
	}
	private JLabel getLblDestinatario() {
		if (lblDestinatario == null) {
			lblDestinatario = new JLabel("Destinatario del Mensaje:");
			lblDestinatario.setHorizontalAlignment(SwingConstants.CENTER);
			lblDestinatario.setFont(new Font("Tahoma", Font.BOLD, 14));
		}
		return lblDestinatario;
	}
	private JLabel getLblAsunto() {
		if (lblAsunto == null) {
			lblAsunto = new JLabel("Asunto del Mensaje:");
			lblAsunto.setHorizontalAlignment(SwingConstants.CENTER);
			lblAsunto.setFont(new Font("Tahoma", Font.BOLD, 14));
		}
		return lblAsunto;
	}
	private JTextField getTxtRemitente() {
		if (txtRemitente == null) {
			txtRemitente = new JTextField();
			txtRemitente.setEditable(false);
			txtRemitente.setColumns(10);
		}
		return txtRemitente;
	}
	private JTextField getTxtDestinatario() {
		if (txtDestinatario == null) {
			txtDestinatario = new JTextField();
			txtDestinatario.setEditable(false);
			txtDestinatario.setColumns(10);
		}
		return txtDestinatario;
	}
	private JTextField getTxtAsunto() {
		if (txtAsunto == null) {
			txtAsunto = new JTextField();
			txtAsunto.setEditable(false);
			txtAsunto.setColumns(10);
		}
		return txtAsunto;
	}
	private JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("  Mensaje:");
			lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		}
		return lblNewLabel_1;
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setViewportView(getTxtAreaMessage());
		}
		return scrollPane;
	}
	private JTextArea getTxtAreaMessage() {
		if (txtAreaMessage == null) {
			txtAreaMessage = new JTextArea();
			txtAreaMessage.setEditable(false);
		}
		return txtAreaMessage;
	}
	private JPanel getPanel_29() {
		if (panel_29 == null) {
			panel_29 = new JPanel();
		}
		return panel_29;
	}
	private JPanel getPanel_30() {
		if (panel_30 == null) {
			panel_30 = new JPanel();
		}
		return panel_30;
	}
	private JPanel getPanel_43() {
		if (panel_43 == null) {
			panel_43 = new JPanel();
		}
		return panel_43;
	}
	private JPanel getPanel_44() {
		if (panel_44 == null) {
			panel_44 = new JPanel();
		}
		return panel_44;
	}
	private JPanel getPanel_45() {
		if (panel_45 == null) {
			panel_45 = new JPanel();
		}
		return panel_45;
	}
	private JPanel getPanel_46() {
		if (panel_46 == null) {
			panel_46 = new JPanel();
		}
		return panel_46;
	}
	private JPanel getPanel_47() {
		if (panel_47 == null) {
			panel_47 = new JPanel();
		}
		return panel_47;
	}
	private JPanel getPanel_48() {
		if (panel_48 == null) {
			panel_48 = new JPanel();
		}
		return panel_48;
	}
}
