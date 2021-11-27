package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.time.LocalDateTime;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.TableModel;

import util.NoEditableTableModel;

@SuppressWarnings({ "serial", "rawtypes" })
public class AppointmentView extends JDialog {

	private JPanel contentPanel = new JPanel();
	private JList listCauses;
	private AddCauseView pacientes;
	private JButton btnAddCause;
	private JPanel pnEntryOut = new JPanel();
	private JLabel lblDoublePointsEntry = new JLabel(":");
	private JLabel lblDoublePointsOut = new JLabel(":");
	private LocalDateTime ldt = LocalDateTime.now();
	private JLabel lblEntryHour = new JLabel("Hora de Entrada");
	private JSpinner spEntryHour;
	private JSpinner spEntryMin;
	private JSpinner spOutHour;
	private JSpinner spOutMin;
	private JLabel lblOutHour;
	private JButton continueButton;
	private JScrollPane scrollPane;
	private JLabel lblPaciente;
	private JButton btnAddPrescripcion;
	private JButton btnHistorial;
	private JButton btnAcudio;
	private JButton btnNoAcudio;
	private JPanel buttonPane;
	private JPanel pnList;
	private JPanel pnButtons;
	private JPanel pnName;
	private JPanel pnLabels;
	private JPanel pnHours;
	private JPanel pnEntry;
	private JPanel pnOut;
	private JPanel pnAcudioSiNo;
	private JButton btnCancel;
	private JPanel panel;
	private JPanel panel_1;
	private JCheckBox chckbxToggleHours;
	private JScrollPane scrollPanePrescripciones;
	private JTable tablePrescripciones;
	private NoEditableTableModel modeloTablaPrescripciones;
	private JPanel panel_2;
	private JPanel panel_3;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JPanel panel_4;
	private JLabel lblNewLabel_2;
	private JPanel panel_5;
	private JPanel panel_6;
	private JLabel lblNewLabel_3;
	private JPanel panel_7;
	private JButton btnEdo;
	private JButton btnProcedimientos;

	/**
	 * Create the dialog.
	 */
	public AppointmentView() {
		setTitle("iHospital : Cita");
		setResizable(false);
		setBounds(100, 100, 698, 659);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		contentPanel.add(getPnButtons(), BorderLayout.SOUTH);
		getPnList().setLayout(new GridLayout(2, 1, 0, 0));
		contentPanel.add(getContentPanel(), BorderLayout.NORTH);
		pnEntryOut.setLayout(new GridLayout(3, 0, 0, 0));
		pnEntryOut.add(getPnName());
		pnEntryOut.add(getPnLabels());
		pnEntryOut.add(getPnHours());
		lblEntryHour.setHorizontalAlignment(SwingConstants.CENTER);
		getPnEntry().add(getSpEntryHour());
		getPnEntry().add(getLblDoublePointsEntry());
		getPnOut().add(getSpOutHour());
		getPnOut().add(getLblDoublePointsOut());
		lblEntryHour.setHorizontalAlignment(SwingConstants.CENTER);
		getPnLabels().add(getLblEntryHour());
		getPnEntry().add(getSpEntryMin());
		getPnOut().add(getSpOutMin());
		getPnLabels().add(getPanel_1());
		getPnLabels().add(getLblOutHour());
		getPnName().add(getLblPaciente());
		getPnButtons().setLayout(new GridLayout(4, 1, 0, 0));
		getPnButtons().add(getBtnAddPrescripcion());
		getPnButtons().add(getBtnProcedimientos());
		getPnButtons().add(getBtnHistorial());
		pnButtons.add(getPnAcudioSiNo());
		getPnAcudioSiNo().add(getBtnAcudio());
		getPnAcudioSiNo().add(getBtnNoAcudio());
		getButtonPane();
		contentPanel.add(getPanel_2(), BorderLayout.CENTER);
	}

	private JScrollPane getScrollPanePrescripciones() {
		if (scrollPanePrescripciones == null) {
			scrollPanePrescripciones = new JScrollPane();
			scrollPanePrescripciones.setBounds(5, 242, 429, 133);
			scrollPanePrescripciones.setViewportView(getTablePrescripciones());
		}
		return scrollPanePrescripciones;
	}

	public JTable getTablePrescripciones() {
		if (tablePrescripciones == null) {
			modeloTablaPrescripciones = new NoEditableTableModel(
					new String[] { "Nombre", "Indicacciones", "Cantidad",
							"Intervalo", "Duración" },
					0);
			tablePrescripciones = new JTable(modeloTablaPrescripciones);
			tablePrescripciones.setFillsViewportHeight(true);
			tablePrescripciones.setBorder(new LineBorder(new Color(0, 0, 0)));
		}
		return tablePrescripciones;
	}

	private JPanel getButtonPane() {
		if (buttonPane == null) {
			buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			buttonPane.add(getBtnEdo());
			buttonPane.add(getBtnCancel());
			buttonPane.add(getBtnContinueButton());
			getRootPane().setDefaultButton(continueButton);
		}
		return buttonPane;
	}

	public JButton getBtnHistorial() {
		if (btnHistorial == null) {
			btnHistorial = new JButton("Historial");
			btnHistorial.setBounds(5, 273, 429, 25);
		}
		return btnHistorial;
	}

	public JButton getBtnContinueButton() {
		if (continueButton == null) {
			continueButton = new JButton("Continuar");
			continueButton.setActionCommand("OK");
		}
		return continueButton;
	}

	public JList getListCauses() {
		if (listCauses == null) {
			listCauses = new JList();
		}
		return listCauses;
	}

	public JButton getBtnAddCause() {
		if (btnAddCause == null) {
			btnAddCause = new JButton("A\u00F1adir Causa");
			btnAddCause.setBounds(5, 204, 429, 23);
		}
		return btnAddCause;
	}

	private JPanel getContentPanel() {
		pnEntryOut.setBounds(5, 11, 429, 92);
		return pnEntryOut;
	}

	private JLabel getLblDoublePointsEntry() {
		lblDoublePointsEntry.setBounds(88, 64, 13, 14);
		return lblDoublePointsEntry;
	}

	private JLabel getLblDoublePointsOut() {
		lblDoublePointsOut.setBounds(336, 64, 13, 14);
		return lblDoublePointsOut;
	}

	private JLabel getLblEntryHour() {
		lblEntryHour.setBounds(39, 42, 95, 14);
		return lblEntryHour;
	}

	public JSpinner getSpEntryHour() {
		if (spEntryHour == null) {
			spEntryHour = new JSpinner();
			spEntryHour.setModel(new SpinnerNumberModel(9, 0, 23, 1));
			spEntryHour.setValue(ldt.getHour());
			spEntryHour.setBounds(39, 61, 39, 20);
		}
		return spEntryHour;
	}

	public JSpinner getSpEntryMin() {
		if (spEntryMin == null) {
			spEntryMin = new JSpinner();
			spEntryMin.setModel(new SpinnerNumberModel(54, 0, 59, 1));
			spEntryMin.setValue(ldt.getMinute());
			spEntryMin.setBounds(98, 61, 39, 20);
		}
		return spEntryMin;
	}

	public JSpinner getSpOutHour() {
		if (spOutHour == null) {
			spOutHour = new JSpinner();
			spOutHour.setModel(new SpinnerNumberModel(10, 0, 23, 1));
			spOutHour.setValue(ldt.getHour() + 1);
			spOutHour.setBounds(287, 61, 39, 20);
		}
		return spOutHour;
	}

	public JSpinner getSpOutMin() {
		if (spOutMin == null) {
			spOutMin = new JSpinner();
			spOutMin.setModel(new SpinnerNumberModel(55, 0, 59, 1));
			spOutMin.setValue(ldt.getMinute());
			spOutMin.setBounds(346, 61, 39, 20);
		}
		return spOutMin;
	}

	private JLabel getLblOutHour() {
		if (lblOutHour == null) {
			lblOutHour = new JLabel("Hora de Salida");
			lblOutHour.setHorizontalAlignment(SwingConstants.CENTER);
			lblOutHour.setBounds(287, 42, 95, 14);
		}
		return lblOutHour;
	}

	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(5, 106, 429, 87);
			scrollPane.setViewportView(getListCauses());
		}
		return scrollPane;
	}

	/**
	 * @param pacientes the pacientes to set
	 */
	public void setPacientes(AddCauseView pacientes) {
		this.pacientes = pacientes;
	}

	/**
	 * @return the pacientes
	 */
	public AddCauseView getPacientes() {
		return pacientes;
	}

	public JLabel getLblPaciente() {
		if (lblPaciente == null) {
			lblPaciente = new JLabel();
			lblPaciente.setFont(new Font("Tahoma", Font.BOLD, 18));
			lblPaciente.setHorizontalAlignment(SwingConstants.CENTER);
			lblPaciente.setBounds(39, 11, 346, 14);
		}
		return lblPaciente;
	}

	public JButton getBtnAddPrescripcion() {
		if (btnAddPrescripcion == null) {
			btnAddPrescripcion = new JButton("A\u00F1adir prescripci\u00F3n");
			btnAddPrescripcion.setBounds(5, 238, 429, 25);
		}
		return btnAddPrescripcion;
	}

	public JButton getBtnAcudio() {
		if (btnAcudio == null) {
			btnAcudio = new JButton("Acudio");
			btnAcudio.setBounds(5, 308, 214, 25);
		}
		return btnAcudio;
	}

	public JButton getBtnNoAcudio() {
		if (btnNoAcudio == null) {
			btnNoAcudio = new JButton("No Acudio");
			btnNoAcudio.setBounds(220, 308, 214, 25);
		}
		return btnNoAcudio;
	}

	private JPanel getPnList() {
		if (pnList == null) {
			pnList = new JPanel();
			pnList.add(getPanel_4());
			pnList.add(getPanel_5());
		}
		return pnList;
	}

	private JPanel getPnButtons() {
		if (pnButtons == null) {
			pnButtons = new JPanel();
		}
		return pnButtons;
	}

	private JPanel getPnName() {
		if (pnName == null) {
			pnName = new JPanel();
		}
		return pnName;
	}

	private JPanel getPnLabels() {
		if (pnLabels == null) {
			pnLabels = new JPanel();
			pnLabels.setLayout(new GridLayout(0, 3, 0, 0));
		}
		return pnLabels;
	}

	private JPanel getPnHours() {
		if (pnHours == null) {
			pnHours = new JPanel();
			pnHours.setLayout(new GridLayout(0, 3, 0, 0));
			pnHours.add(getPnEntry());
			pnHours.add(getPanel());
			pnHours.add(getPnOut());
		}
		return pnHours;
	}

	private JPanel getPnEntry() {
		if (pnEntry == null) {
			pnEntry = new JPanel();
			pnEntry.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		}
		return pnEntry;
	}

	private JPanel getPnOut() {
		if (pnOut == null) {
			pnOut = new JPanel();
		}
		return pnOut;
	}

	private JPanel getPnAcudioSiNo() {
		if (pnAcudioSiNo == null) {
			pnAcudioSiNo = new JPanel();
			pnAcudioSiNo.setLayout(new GridLayout(0, 2, 0, 0));
		}
		return pnAcudioSiNo;
	}

	public JButton getBtnCancel() {
		if (btnCancel == null) {
			btnCancel = new JButton("Cancelar");
		}
		return btnCancel;
	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.add(getChckbxToggleHours());
		}
		return panel;
	}

	private JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
		}
		return panel_1;
	}

	public JCheckBox getChckbxToggleHours() {
		if (chckbxToggleHours == null) {
			chckbxToggleHours = new JCheckBox("Toggle Hours");
		}
		return chckbxToggleHours;
	}
	private JPanel getPanel_2() {
		if (panel_2 == null) {
			panel_2 = new JPanel();
			panel_2.setLayout(new BorderLayout(0, 0));
			panel_2.add(getPnList());
			panel_2.add(getPanel_3(), BorderLayout.NORTH);
		}
		return panel_2;
	}
	private JPanel getPanel_3() {
		if (panel_3 == null) {
			panel_3 = new JPanel();
			panel_3.setLayout(new GridLayout(3, 0, 0, 0));
			panel_3.add(getLblNewLabel());
			panel_3.add(getLblNewLabel_1());
		}
		return panel_3;
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("");
		}
		return lblNewLabel;
	}
	private JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("CAUSAS Y PRESCRIPCIONES");
			lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblNewLabel_1;
	}
	private JPanel getPanel_4() {
		if (panel_4 == null) {
			panel_4 = new JPanel();
			panel_4.setLayout(new BorderLayout(0, 0));
			panel_4.add(getScrollPane());
			panel_4.add(getLblNewLabel_2(), BorderLayout.NORTH);
		}
		return panel_4;
	}
	private JLabel getLblNewLabel_2() {
		if (lblNewLabel_2 == null) {
			lblNewLabel_2 = new JLabel("Lista Causas:");
		}
		return lblNewLabel_2;
	}
	private JPanel getPanel_5() {
		if (panel_5 == null) {
			panel_5 = new JPanel();
			panel_5.setLayout(new BorderLayout(0, 0));
			panel_5.add(getScrollPanePrescripciones());
			panel_5.add(getPanel_6(), BorderLayout.NORTH);
		}
		return panel_5;
	}
	private JPanel getPanel_6() {
		if (panel_6 == null) {
			panel_6 = new JPanel();
			panel_6.setLayout(new GridLayout(2, 0, 0, 0));
			panel_6.add(getPanel_7());
			panel_6.add(getLblNewLabel_3());
		}
		return panel_6;
	}
	private JLabel getLblNewLabel_3() {
		if (lblNewLabel_3 == null) {
			lblNewLabel_3 = new JLabel("Lista Prescripciones:");
		}
		return lblNewLabel_3;
	}
	private JPanel getPanel_7() {
		if (panel_7 == null) {
			panel_7 = new JPanel();
			panel_7.setLayout(new GridLayout(1, 0, 0, 0));
			panel_7.add(getBtnAddCause());
		}
		return panel_7;
	}

	public void setModeloTablaPrescripciones(
			NoEditableTableModel noEditableTableModel) {
		getTablePrescripciones().setModel(noEditableTableModel);
	}

	public NoEditableTableModel getModeloTablaPrescripciones() {
		return (NoEditableTableModel) getTablePrescripciones().getModel();
	}
	public JButton getBtnEdo() {
		if (btnEdo == null) {
			btnEdo = new JButton("EDO");
			btnEdo.setBackground(Color.RED);
		}
		return btnEdo;
	}
	public JButton getBtnProcedimientos() {
		if (btnProcedimientos == null) {
			btnProcedimientos = new JButton("Procedimientos");
		}
		return btnProcedimientos;
	}
}
