package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.GridLayout;

public class BandejaDeEntradaVista extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JPanel buttonPane;
	private JLabel lblBandejaDeEntrada;
	private JPanel pnMessages;
	private JPanel panel_1;
	private JPanel panel_2;
	private JPanel panel_3;
	private JPanel panel_4;
	private JScrollPane scrollPane;
	private JTable tableMessages;
	private JButton btnOpen;
	private JButton btnSend;
	private JPanel panel;
	private JPanel panel_5;
	private JButton btnHelp;
	private JPanel panel_6;
	private JPanel panel_7;
	private JPanel panel_8;
	private JPanel panel_10;
	private JPanel panel_11;
	private JPanel panel_12;
	private JPanel panel_13;
	private JPanel panel_15;
	private JPanel panel_16;
	private JPanel panel_18;
	private JPanel panel_19;
	private JPanel panel_20;

	/**
	 * Create the dialog.
	 */
	public BandejaDeEntradaVista() {
		setBounds(100, 100, 907, 585);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		contentPanel.add(getLblBandejaDeEntrada(), BorderLayout.NORTH);
		contentPanel.add(getPnMessages(), BorderLayout.CENTER);
		getContentPane().add(getButtonPane(), BorderLayout.SOUTH);
	}

	private JPanel getButtonPane() {
		if (buttonPane == null) {
			buttonPane = new JPanel();
			buttonPane.setLayout(new GridLayout(0, 5, 0, 0));
			buttonPane.add(getPanel_5());
			buttonPane.add(getPanel_7());
			buttonPane.add(getPanel());
			buttonPane.add(getPanel_8());
			buttonPane.add(getPanel_6());
		}
		return buttonPane;
	}

	private JLabel getLblBandejaDeEntrada() {
		if (lblBandejaDeEntrada == null) {
			lblBandejaDeEntrada = new JLabel("BANDEJA DE ENTRADA");
			lblBandejaDeEntrada.setFont(new Font("Tahoma", Font.BOLD, 18));
			lblBandejaDeEntrada.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblBandejaDeEntrada;
	}

	private JPanel getPnMessages() {
		if (pnMessages == null) {
			pnMessages = new JPanel();
			pnMessages.setLayout(new BorderLayout(0, 0));
			pnMessages.add(getPanel_1(), BorderLayout.WEST);
			pnMessages.add(getPanel_2(), BorderLayout.NORTH);
			pnMessages.add(getPanel_3(), BorderLayout.EAST);
			pnMessages.add(getPanel_4(), BorderLayout.SOUTH);
			pnMessages.add(getScrollPane(), BorderLayout.CENTER);
		}
		return pnMessages;
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
		}
		return panel_2;
	}

	private JPanel getPanel_3() {
		if (panel_3 == null) {
			panel_3 = new JPanel();
		}
		return panel_3;
	}

	private JPanel getPanel_4() {
		if (panel_4 == null) {
			panel_4 = new JPanel();
		}
		return panel_4;
	}

	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setViewportView(getTableMessages());
		}
		return scrollPane;
	}

	private JTable getTableMessages() {
		if (tableMessages == null) {
			tableMessages = new JTable();
			tableMessages.setModel(new DefaultTableModel(new Object[][] {},
					new String[] { "Enviado por: ", "Asunto", "Mensaje" }));
		}
		return tableMessages;
	}

	public JButton getBtnOpen() {
		if (btnOpen == null) {
			btnOpen = new JButton("Abrir Mensaje");
			btnOpen.setFont(new Font("Tahoma", Font.BOLD, 14));
		}
		return btnOpen;
	}

	public JButton getBtnSend() {
		if (btnSend == null) {
			btnSend = new JButton("Enviar Mensaje");
			btnSend.setFont(new Font("Tahoma", Font.BOLD, 14));
		}
		return btnSend;
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setLayout(new BorderLayout(0, 0));
			panel.add(getBtnOpen());
			panel.add(getPanel_18(), BorderLayout.WEST);
			panel.add(getPanel_19(), BorderLayout.EAST);
			panel.add(getPanel_20(), BorderLayout.SOUTH);
		}
		return panel;
	}
	private JPanel getPanel_5() {
		if (panel_5 == null) {
			panel_5 = new JPanel();
			panel_5.setLayout(new BorderLayout(0, 0));
			panel_5.add(getBtnHelp());
			panel_5.add(getPanel_10(), BorderLayout.WEST);
			panel_5.add(getPanel_11(), BorderLayout.EAST);
			panel_5.add(getPanel_12(), BorderLayout.SOUTH);
		}
		return panel_5;
	}
	public JButton getBtnHelp() {
		if (btnHelp == null) {
			btnHelp = new JButton("Ayuda");
			btnHelp.setFont(new Font("Tahoma", Font.BOLD, 14));
		}
		return btnHelp;
	}
	private JPanel getPanel_6() {
		if (panel_6 == null) {
			panel_6 = new JPanel();
			panel_6.setLayout(new BorderLayout(0, 0));
			panel_6.add(getBtnSend());
			panel_6.add(getPanel_13(), BorderLayout.WEST);
			panel_6.add(getPanel_15(), BorderLayout.EAST);
			panel_6.add(getPanel_16(), BorderLayout.SOUTH);
		}
		return panel_6;
	}
	private JPanel getPanel_7() {
		if (panel_7 == null) {
			panel_7 = new JPanel();
		}
		return panel_7;
	}
	private JPanel getPanel_8() {
		if (panel_8 == null) {
			panel_8 = new JPanel();
		}
		return panel_8;
	}
	private JPanel getPanel_10() {
		if (panel_10 == null) {
			panel_10 = new JPanel();
		}
		return panel_10;
	}
	private JPanel getPanel_11() {
		if (panel_11 == null) {
			panel_11 = new JPanel();
		}
		return panel_11;
	}
	private JPanel getPanel_12() {
		if (panel_12 == null) {
			panel_12 = new JPanel();
		}
		return panel_12;
	}
	private JPanel getPanel_13() {
		if (panel_13 == null) {
			panel_13 = new JPanel();
		}
		return panel_13;
	}
	private JPanel getPanel_15() {
		if (panel_15 == null) {
			panel_15 = new JPanel();
		}
		return panel_15;
	}
	private JPanel getPanel_16() {
		if (panel_16 == null) {
			panel_16 = new JPanel();
		}
		return panel_16;
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
}
