package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

@SuppressWarnings("rawtypes")
public class ModificarCitaAdminVista extends JDialog {

	private static final long serialVersionUID = -5270197079170765016L;
	private final JPanel contentPanel = new JPanel();

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
	private JButton btnModificar;
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
	private JLabel lblFecha;
	private JPanel panel_11;
	private JPanel panel_12;
	private JPanel panel_13;
	private JPanel panel_14;
	private JDateChooser dateChooser;
	private JPanel panel_15;
	private JPanel panel_16;
	private JPanel panel_17;
	private JPanel panel_18;
	private JLabel lblNewLabel_2;
	private JPanel panel_19;
	private JPanel panel_20;
	private JComboBox cbMedicos;
	private JButton btnAdd;
	private JList listMedicos;
	private JScrollPane scrollPane;
	private JPanel panel_21;
	private JButton btnRemove;
	private JPanel panel_22;
	private JPanel panel_23;
	private JPanel panel_24;
	private JPanel panel_25;
	private JPanel panel_26;
	private JPanel panel_27;
	private JPanel panel_28;
	private JPanel panel_29;
	private JPanel panel_30;

	/**
	 * Create the dialog.
	 */
	public ModificarCitaAdminVista() {
		setBounds(100, 100, 926, 606);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		contentPanel.add(getLblTitle(), BorderLayout.NORTH);
		contentPanel.add(getPanel(), BorderLayout.CENTER);
		contentPanel.add(getPanel_2(), BorderLayout.SOUTH);
		contentPanel.add(getPanel_1(), BorderLayout.WEST);
		contentPanel.add(getPanel_4(), BorderLayout.EAST);
		getRootPane().setDefaultButton(getBtnModificar());
	}

	private JLabel getLblTitle() {
		if (lblTitle == null) {
			lblTitle = new JLabel("Modificar Cita");
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
			panel_2.add(getBtnModificar());
		}
		return panel_2;
	}

	private JPanel getPnLabels() {
		if (pnLabels == null) {
			pnLabels = new JPanel();
			pnLabels.setLayout(new GridLayout(6, 0, 0, 0));
			pnLabels.add(getLblFecha());
			pnLabels.add(getLblHoraEntrada());
			pnLabels.add(getLblHoraSalida());
			pnLabels.add(getLblPaciente());
			pnLabels.add(getLblNewLabel_2());
			pnLabels.add(getLblUbicación());
		}
		return pnLabels;
	}

	private JPanel getPanel_3() {
		if (panel_3 == null) {
			panel_3 = new JPanel();
			panel_3.setLayout(new GridLayout(6, 0, 0, 0));
			panel_3.add(getPanel_11());
			panel_3.add(getPanel_5());
			panel_3.add(getPanel_6());
			panel_3.add(getPanel_25());
			panel_3.add(getPanel_22());
			panel_3.add(getPanel_7());
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
			panel_7.add(getPanel_12());
		}
		return panel_7;
	}

	public JTextField getTxtUbicacion() {
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

	public JButton getBtnModificar() {
		if (btnModificar == null) {
			btnModificar = new JButton("APLICAR MODIFICACI\u00D3N");
		}
		return btnModificar;
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
			panel_9.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			panel_9.add(getSpEntryHour());
			panel_9.add(getLblEntry());
			panel_9.add(getSpEntryMin());
		}
		return panel_9;
	}

	private JPanel getPanel_10() {
		if (panel_10 == null) {
			panel_10 = new JPanel();
			panel_10.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			panel_10.add(getSpOutHour());
			panel_10.add(getLblOut());
			panel_10.add(getSpOutMin());
		}
		return panel_10;
	}

	private JLabel getLblEntry() {
		if (lblEntry == null) {
			lblEntry = new JLabel(":");
			lblEntry.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblEntry;
	}

	private JLabel getLblOut() {
		if (lblOut == null) {
			lblOut = new JLabel(":");
			lblOut.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblOut;
	}

	public JSpinner getSpEntryHour() {
		if (spEntryHour == null) {
			spEntryHour = new JSpinner();
			spEntryHour.setModel(new SpinnerNumberModel(0, 0, 23, 1));
		}
		return spEntryHour;
	}

	public JSpinner getSpEntryMin() {
		if (spEntryMin == null) {
			spEntryMin = new JSpinner();
			spEntryMin.setModel(new SpinnerNumberModel(0, 0, 59, 1));
		}
		return spEntryMin;
	}

	public JSpinner getSpOutHour() {
		if (spOutHour == null) {
			spOutHour = new JSpinner();
			spOutHour.setModel(new SpinnerNumberModel(0, 0, 23, 1));
		}
		return spOutHour;
	}

	public JSpinner getSpOutMin() {
		if (spOutMin == null) {
			spOutMin = new JSpinner();
			spOutMin.setModel(new SpinnerNumberModel(0, 0, 59, 1));
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

	private JLabel getLblFecha() {
		if (lblFecha == null) {
			lblFecha = new JLabel("FECHA DE LA CITA:");
			lblFecha.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblFecha.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblFecha;
	}

	private JPanel getPanel_11() {
		if (panel_11 == null) {
			panel_11 = new JPanel();
			panel_11.setLayout(new GridLayout(3, 1, 0, 0));
			panel_11.add(getPanel_15());
			panel_11.add(getPanel_16());
		}
		return panel_11;
	}

	private JPanel getPanel_12() {
		if (panel_12 == null) {
			panel_12 = new JPanel();
			panel_12.setLayout(new BorderLayout(0, 0));
			panel_12.add(getPanel_13(), BorderLayout.WEST);
			panel_12.add(getTxtUbicacion());
			panel_12.add(getPanel_14(), BorderLayout.EAST);
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

	public JDateChooser getDateChooser() {
		if (dateChooser == null) {
			dateChooser = new JDateChooser();
		}
		return dateChooser;
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
			panel_16.setLayout(new BorderLayout(0, 0));
			panel_16.add(getDateChooser());
			panel_16.add(getPanel_17(), BorderLayout.WEST);
			panel_16.add(getPanel_18(), BorderLayout.EAST);
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

	private JLabel getLblNewLabel_2() {
		if (lblNewLabel_2 == null) {
			lblNewLabel_2 = new JLabel("M\u00C9DICOS DE LA CITA:");
			lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		}
		return lblNewLabel_2;
	}
	private JPanel getPanel_19() {
		if (panel_19 == null) {
			panel_19 = new JPanel();
			panel_19.setLayout(new GridLayout(2, 0, 0, 0));
			panel_19.add(getPanel_20());
			panel_19.add(getScrollPane_1());
		}
		return panel_19;
	}
	
	public JList getListMedicos() {
		if (listMedicos == null) {
			listMedicos = new JList();
		}
		return listMedicos;
	}
	
	private JPanel getPanel_20() {
		if (panel_20 == null) {
			panel_20 = new JPanel();
			panel_20.setLayout(new GridLayout(0, 2, 0, 0));
			panel_20.add(getPanel_28());
			panel_20.add(getPanel_21());
		}
		return panel_20;
	}
	public JComboBox getCbMedicos() {
		if (cbMedicos == null) {
			cbMedicos = new JComboBox();
		}
		return cbMedicos;
	}
	public JButton getBtnAdd() {
		if (btnAdd == null) {
			btnAdd = new JButton("Add");
		}
		return btnAdd;
	}
	private JScrollPane getScrollPane_1() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setViewportView(getListMedicos());
		}
		return scrollPane;
	}
	private JPanel getPanel_21() {
		if (panel_21 == null) {
			panel_21 = new JPanel();
			panel_21.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 9));
			panel_21.add(getBtnAdd());
			panel_21.add(getBtnRemove());
		}
		return panel_21;
	}
	public JButton getBtnRemove() {
		if (btnRemove == null) {
			btnRemove = new JButton("Remove");
		}
		return btnRemove;
	}
	private JPanel getPanel_22() {
		if (panel_22 == null) {
			panel_22 = new JPanel();
			panel_22.setLayout(new BorderLayout(0, 0));
			panel_22.add(getPanel_19(), BorderLayout.CENTER);
			panel_22.add(getPanel_23(), BorderLayout.WEST);
			panel_22.add(getPanel_24(), BorderLayout.EAST);
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
			panel_25.setLayout(new BorderLayout(0, 0));
			panel_25.add(getPanel_8());
			panel_25.add(getPanel_26(), BorderLayout.WEST);
			panel_25.add(getPanel_27(), BorderLayout.EAST);
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
			panel_28.setLayout(new BorderLayout(0, 0));
			panel_28.add(getCbMedicos());
			panel_28.add(getPanel_29(), BorderLayout.NORTH);
			panel_28.add(getPanel_30(), BorderLayout.SOUTH);
		}
		return panel_28;
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
}
