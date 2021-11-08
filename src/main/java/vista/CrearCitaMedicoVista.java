package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JList;
import java.awt.GridLayout;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JComboBox;

public class CrearCitaMedicoVista extends JDialog {

	private static final long serialVersionUID = -4122790949612125305L;
	private final JPanel contentPanel = new JPanel();

	private JPanel buttonPane;
	private JButton okButton;
	private JButton cancelButton;
	private JLabel lblTitle;
	private JPanel panel;
	private JPanel panel_2;
	private JPanel pnLabels;
	private JPanel panel_3;
	private JLabel lblHoraEntrada;
	private JLabel lblHoraSalida;
	private JLabel lblUbicación;
	private JPanel panel_5;
	private JPanel panel_6;
	private JPanel panel_7;
	private JTextField txtUbicación;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JButton btnEnviar;
	private JButton btnCancelar;
	private JLabel lblPaciente;
	private JPanel panel_1;
	private JPanel panel_4;
	private JPanel panel_8;
	private JLabel lblNewLabel;
	private JPanel panel_9;
	private JPanel panel_10;
	private JLabel lblEntry;
	private JLabel lblOut;
	private JSpinner spEntryHour;
	private JSpinner spEntryMin;
	private JSpinner spOutHour;
	private JSpinner spOutMin;
	private JComboBox cbPacientes;
	private JLabel lblNewLabel_1;

	/**
	 * Create the dialog.
	 */
	public CrearCitaMedicoVista() {
		setBounds(100, 100, 873, 506);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		contentPanel.add(getLblTitle(), BorderLayout.NORTH);
		contentPanel.add(getPanel(), BorderLayout.CENTER);
		contentPanel.add(getPanel_2(), BorderLayout.SOUTH);
		contentPanel.add(getPanel_1(), BorderLayout.WEST);
		contentPanel.add(getPanel_4(), BorderLayout.EAST);
		getRootPane().setDefaultButton(getBtnEnviar());
	}

	private JLabel getLblTitle() {
		if (lblTitle == null) {
			lblTitle = new JLabel("Crear Cita");
			lblTitle.setFont(new Font("Tahoma", Font.BOLD, 18));
			lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblTitle;
	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setLayout(new GridLayout(0, 2, 0, 0));
			panel.add(getPnLabels());
			panel.add(getPanel_3());
		}
		return panel;
	}

	private JPanel getPanel_2() {
		if (panel_2 == null) {
			panel_2 = new JPanel();
			panel_2.setLayout(new GridLayout(0, 2, 0, 0));
			panel_2.add(getBtnCancelar());
			panel_2.add(getBtnEnviar());
		}
		return panel_2;
	}

	private JPanel getPnLabels() {
		if (pnLabels == null) {
			pnLabels = new JPanel();
			pnLabels.setLayout(new GridLayout(4, 0, 0, 0));
			pnLabels.add(getLblHoraEntrada());
			pnLabels.add(getLblHoraSalida());
			pnLabels.add(getLblUbicación());
			pnLabels.add(getLblPaciente());
		}
		return pnLabels;
	}

	private JPanel getPanel_3() {
		if (panel_3 == null) {
			panel_3 = new JPanel();
			panel_3.setLayout(new GridLayout(4, 0, 0, 0));
			panel_3.add(getPanel_5());
			panel_3.add(getPanel_6());
			panel_3.add(getPanel_7());
			panel_3.add(getPanel_8());
		}
		return panel_3;
	}

	private JLabel getLblHoraEntrada() {
		if (lblHoraEntrada == null) {
			lblHoraEntrada = new JLabel("HORA DE ENTRADA A LA CITA:");
			lblHoraEntrada.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblHoraEntrada.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblHoraEntrada;
	}

	private JLabel getLblHoraSalida() {
		if (lblHoraSalida == null) {
			lblHoraSalida = new JLabel("HORA DE SALIDA DE LA CITA:");
			lblHoraSalida.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblHoraSalida.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblHoraSalida;
	}

	private JLabel getLblUbicación() {
		if (lblUbicación == null) {
			lblUbicación = new JLabel("UBICACI\u00D3N DE LA CITA:");
			lblUbicación.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblUbicación.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblUbicación;
	}

	private JPanel getPanel_5() {
		if (panel_5 == null) {
			panel_5 = new JPanel();
			panel_5.setLayout(new GridLayout(3, 1, 0, 0));
			panel_5.add(getLblNewLabel_3());
			panel_5.add(getPanel_9());
		}
		return panel_5;
	}

	private JPanel getPanel_6() {
		if (panel_6 == null) {
			panel_6 = new JPanel();
			panel_6.setLayout(new GridLayout(3, 1, 0, 0));
			panel_6.add(getLblNewLabel_4());
			panel_6.add(getPanel_10());
		}
		return panel_6;
	}

	private JPanel getPanel_7() {
		if (panel_7 == null) {
			panel_7 = new JPanel();
			panel_7.setLayout(new GridLayout(3, 1, 0, 0));
			panel_7.add(getLblNewLabel_5());
			panel_7.add(getTextField_3());
		}
		return panel_7;
	}

	private JTextField getTextField_3() {
		if (txtUbicación == null) {
			txtUbicación = new JTextField();
			txtUbicación.setColumns(10);
		}
		return txtUbicación;
	}

	private JLabel getLblNewLabel_3() {
		if (lblNewLabel_3 == null) {
			lblNewLabel_3 = new JLabel("");
		}
		return lblNewLabel_3;
	}

	private JLabel getLblNewLabel_4() {
		if (lblNewLabel_4 == null) {
			lblNewLabel_4 = new JLabel("");
		}
		return lblNewLabel_4;
	}

	private JLabel getLblNewLabel_5() {
		if (lblNewLabel_5 == null) {
			lblNewLabel_5 = new JLabel("");
		}
		return lblNewLabel_5;
	}
	public JButton getBtnEnviar() {
		if (btnEnviar == null) {
			btnEnviar = new JButton("ENVIAR A REVISI\u00D3N");
		}
		return btnEnviar;
	}
	public JButton getBtnCancelar() {
		if (btnCancelar == null) {
			btnCancelar = new JButton("CANCELAR");
		}
		return btnCancelar;
	}
	private JLabel getLblPaciente() {
		if (lblPaciente == null) {
			lblPaciente = new JLabel("PACIENTE DE LA CITA:");
			lblPaciente.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblPaciente.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblPaciente;
	}
	private JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
		}
		return panel_1;
	}
	private JPanel getPanel_4() {
		if (panel_4 == null) {
			panel_4 = new JPanel();
		}
		return panel_4;
	}
	private JPanel getPanel_8() {
		if (panel_8 == null) {
			panel_8 = new JPanel();
			panel_8.setLayout(new GridLayout(4, 1, 0, 0));
			panel_8.add(getLblNewLabel_1());
			panel_8.add(getLblNewLabel());
			panel_8.add(getCbPacientes());
		}
		return panel_8;
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("Seleccione un Paciente");
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblNewLabel;
	}
	private JPanel getPanel_9() {
		if (panel_9 == null) {
			panel_9 = new JPanel();
			panel_9.setLayout(null);
			panel_9.add(getLblEntry());
			panel_9.add(getSpEntryHour());
			panel_9.add(getSpEntryMin());
		}
		return panel_9;
	}
	private JPanel getPanel_10() {
		if (panel_10 == null) {
			panel_10 = new JPanel();
			panel_10.setLayout(null);
			panel_10.add(getLblOut());
			panel_10.add(getSpOutHour());
			panel_10.add(getSpOutMin());
		}
		return panel_10;
	}
	private JLabel getLblEntry() {
		if (lblEntry == null) {
			lblEntry = new JLabel(":");
			lblEntry.setHorizontalAlignment(SwingConstants.CENTER);
			lblEntry.setBounds(173, 14, 61, 14);
		}
		return lblEntry;
	}
	private JLabel getLblOut() {
		if (lblOut == null) {
			lblOut = new JLabel(":");
			lblOut.setHorizontalAlignment(SwingConstants.CENTER);
			lblOut.setBounds(172, 14, 63, 14);
		}
		return lblOut;
	}
	public JSpinner getSpEntryHour() {
		if (spEntryHour == null) {
			spEntryHour = new JSpinner();
			spEntryHour.setBounds(84, 11, 110, 20);
		}
		return spEntryHour;
	}
	public JSpinner getSpEntryMin() {
		if (spEntryMin == null) {
			spEntryMin = new JSpinner();
			spEntryMin.setBounds(213, 11, 110, 20);
		}
		return spEntryMin;
	}
	public JSpinner getSpOutHour() {
		if (spOutHour == null) {
			spOutHour = new JSpinner();
			spOutHour.setBounds(78, 11, 113, 20);
		}
		return spOutHour;
	}
	public JSpinner getSpOutMin() {
		if (spOutMin == null) {
			spOutMin = new JSpinner();
			spOutMin.setBounds(212, 11, 113, 20);
		}
		return spOutMin;
	}
	public JComboBox getCbPacientes() {
		if (cbPacientes == null) {
			cbPacientes = new JComboBox();
		}
		return cbPacientes;
	}
	private JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("");
		}
		return lblNewLabel_1;
	}
}
