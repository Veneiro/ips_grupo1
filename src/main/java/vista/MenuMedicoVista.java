package vista;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.Font;

public class MenuMedicoVista extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JPanel panel_1;
	private JPanel panel_2;
	private JPanel panel_3;
	private JButton btnGestion;
	private JButton btnProponer;
	private JButton btnMensajeria;
	private JPanel panel;
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
	private JButton btnCitasModificadas;
	private JPanel panel_16;
	private JPanel panel_17;
	private JPanel panel_18;
	private JPanel panel_19;

	/**
	 * Create the dialog.
	 */
	public MenuMedicoVista() {
		setBounds(100, 100, 895, 605);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(4, 0, 0, 0));
		contentPanel.add(getPanel_1());
		contentPanel.add(getPanel_2());
		contentPanel.add(getPanel_15());
		contentPanel.add(getPanel_3());
	}

	private JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			panel_1.setLayout(new BorderLayout(0, 0));
			panel_1.add(getBtnGestion(), BorderLayout.CENTER);
			panel_1.add(getPanel(), BorderLayout.NORTH);
			panel_1.add(getPanel_4(), BorderLayout.WEST);
			panel_1.add(getPanel_5(), BorderLayout.SOUTH);
			panel_1.add(getPanel_6(), BorderLayout.EAST);
		}
		return panel_1;
	}
	private JPanel getPanel_2() {
		if (panel_2 == null) {
			panel_2 = new JPanel();
			panel_2.setLayout(new BorderLayout(0, 0));
			panel_2.add(getBtnProponer(), BorderLayout.CENTER);
			panel_2.add(getPanel_7(), BorderLayout.NORTH);
			panel_2.add(getPanel_8(), BorderLayout.WEST);
			panel_2.add(getPanel_9(), BorderLayout.SOUTH);
			panel_2.add(getPanel_10(), BorderLayout.EAST);
		}
		return panel_2;
	}
	private JPanel getPanel_3() {
		if (panel_3 == null) {
			panel_3 = new JPanel();
			panel_3.setLayout(new BorderLayout(0, 0));
			panel_3.add(getBtnMensajeria(), BorderLayout.CENTER);
			panel_3.add(getPanel_11(), BorderLayout.NORTH);
			panel_3.add(getPanel_12(), BorderLayout.WEST);
			panel_3.add(getPanel_13(), BorderLayout.SOUTH);
			panel_3.add(getPanel_14(), BorderLayout.EAST);
		}
		return panel_3;
	}
	public JButton getBtnGestion() {
		if (btnGestion == null) {
			btnGestion = new JButton("Lista y Gesti\u00F3n de Citas");
			btnGestion.setFont(new Font("Tahoma", Font.PLAIN, 18));
		}
		return btnGestion;
	}
	public JButton getBtnProponer() {
		if (btnProponer == null) {
			btnProponer = new JButton("Proponer Nueva Cita");
			btnProponer.setFont(new Font("Tahoma", Font.PLAIN, 18));
		}
		return btnProponer;
	}
	public JButton getBtnMensajeria() {
		if (btnMensajeria == null) {
			btnMensajeria = new JButton("Mensajer\u00EDa");
			btnMensajeria.setFont(new Font("Tahoma", Font.PLAIN, 18));
		}
		return btnMensajeria;
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
		}
		return panel;
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
		}
		return panel_7;
	}
	private JPanel getPanel_8() {
		if (panel_8 == null) {
			panel_8 = new JPanel();
		}
		return panel_8;
	}
	private JPanel getPanel_9() {
		if (panel_9 == null) {
			panel_9 = new JPanel();
		}
		return panel_9;
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
	private JPanel getPanel_14() {
		if (panel_14 == null) {
			panel_14 = new JPanel();
		}
		return panel_14;
	}
	private JPanel getPanel_15() {
		if (panel_15 == null) {
			panel_15 = new JPanel();
			panel_15.setLayout(new BorderLayout(0, 0));
			panel_15.add(getBtnCitasModificadas(), BorderLayout.CENTER);
			panel_15.add(getPanel_16(), BorderLayout.NORTH);
			panel_15.add(getPanel_17(), BorderLayout.WEST);
			panel_15.add(getPanel_18(), BorderLayout.SOUTH);
			panel_15.add(getPanel_19(), BorderLayout.EAST);
		}
		return panel_15;
	}
	public JButton getBtnCitasModificadas() {
		if (btnCitasModificadas == null) {
			btnCitasModificadas = new JButton("Citas Propuestas Modificadas por Admin");
			btnCitasModificadas.setFont(new Font("Tahoma", Font.PLAIN, 18));
		}
		return btnCitasModificadas;
	}
	private JPanel getPanel_16() {
		if (panel_16 == null) {
			panel_16 = new JPanel();
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
}
