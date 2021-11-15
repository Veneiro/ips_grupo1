package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.time.LocalDateTime;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JToggleButton;
import javax.swing.JCheckBox;

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

	/**
	 * Create the dialog.
	 */
	public AppointmentView() {
		setTitle("iHospital : Cita");
		setModal(true);
		setResizable(false);
		setBounds(100, 100, 450, 423);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		contentPanel.add(getPnList(), BorderLayout.CENTER);
		contentPanel.add(getPnButtons(), BorderLayout.SOUTH);
		getPnList().setLayout(new GridLayout(0, 1, 0, 0));
		getPnList().add(getScrollPane());
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
		getPnButtons().add(getBtnAddCause());
		getPnButtons().add(getBtnAddPrescripcion());
		getPnButtons().add(getBtnHistorial());
		pnButtons.add(getPnAcudioSiNo());
		getPnAcudioSiNo().add(getBtnAcudio());
		getPnAcudioSiNo().add(getBtnNoAcudio());
		getButtonPane();
	}
	
	private JPanel getButtonPane() {
		if(buttonPane == null) {
			buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			buttonPane.add(getBtnCancel());
			buttonPane.add(getBtnContinueButton());
			getRootPane().setDefaultButton(continueButton);
		}
		return buttonPane;
	}

	public JButton getBtnHistorial() {
		if(btnHistorial == null) {
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
		if(btnAddPrescripcion == null) {
			btnAddPrescripcion = new JButton("A\u00F1adir prescripci\u00F3n");
			btnAddPrescripcion.setBounds(5, 238, 429, 25);
		}
		return btnAddPrescripcion;
	}

	public JButton getBtnAcudio() {
		if(btnAcudio == null) {
			btnAcudio = new JButton("Acudio");
			btnAcudio.setBounds(5, 308, 214, 25);
		}
		return btnAcudio;
	}

	public JButton getBtnNoAcudio() {
		if(btnNoAcudio == null) {
			btnNoAcudio = new JButton("No Acudio");
			btnNoAcudio.setBounds(220, 308, 214, 25);
		}
		return btnNoAcudio;
	}
	private JPanel getPnList() {
		if (pnList == null) {
			pnList = new JPanel();
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
}
