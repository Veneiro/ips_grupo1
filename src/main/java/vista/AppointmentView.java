package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.time.LocalDateTime;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

@SuppressWarnings({ "serial", "rawtypes" })
public class AppointmentView extends JDialog {

	private JPanel contentPanel = new JPanel();
	private JList listCauses;
	private AddCauseView pacientes;
	private JButton btnAddCause;
	private JPanel pnEntryOut = new JPanel();
	private JLabel lblDoublePointsEntry = new JLabel(":");
	private JLabel lblDoublePointsOut = new JLabel(":");
	LocalDateTime ldt = LocalDateTime.now();
	private JLabel lblEntryHour = new JLabel("Hora de Entrada");
	private JSpinner spEntryHour;
	private JSpinner spEntryMin;
	private JSpinner spOutHour;
	private JSpinner spOutMin;
	private JLabel lblOutHour;
	private JButton continueButton;
	private JButton btnUpdate;
	private JScrollPane scrollPane;
	private JButton btnUrgente;

	/**
	 * Create the dialog.
	 */
	public AppointmentView() {
		setTitle("iHospital : Cita");
		setModal(true);
		setResizable(false);
		setBounds(100, 100, 450, 308);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		contentPanel.add(getScrollPane());
		contentPanel.add(getBtnAddCause());
		contentPanel.add(getContentPanel());
		pnEntryOut.add(getLblDoublePointsEntry());
		pnEntryOut.add(getLblDoublePointsOut());
		lblEntryHour.setHorizontalAlignment(SwingConstants.CENTER);
		pnEntryOut.add(getLblEntryHour());
		pnEntryOut.add(getSpEntryHour());
		pnEntryOut.add(getSpEntryMin());
		pnEntryOut.add(getSpOutHour());
		pnEntryOut.add(getSpOutMin());
		pnEntryOut.add(getLblOutHour());
		pnEntryOut.add(getBtnUrgente());
		contentPanel.add(getBtnUpdate());
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			buttonPane.add(getBtnContinueButton());
			getRootPane().setDefaultButton(continueButton);
		}
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
			btnAddCause.setBounds(231, 204, 203, 23);
		}
		return btnAddCause;
	}

	private JPanel getContentPanel() {
		pnEntryOut.setBounds(5, 11, 429, 92);
		pnEntryOut.setLayout(null);
		return pnEntryOut;
	}

	private JLabel getLblDoublePointsEntry() {
		lblDoublePointsEntry.setBounds(87, 33, 46, 14);
		return lblDoublePointsEntry;
	}

	private JLabel getLblDoublePointsOut() {
		lblDoublePointsOut.setBounds(335, 33, 46, 14);
		return lblDoublePointsOut;
	}

	private JLabel getLblEntryHour() {
		lblEntryHour.setBounds(45, 11, 88, 14);
		return lblEntryHour;
	}

	public JSpinner getSpEntryHour() {
		if (spEntryHour == null) {
			spEntryHour = new JSpinner();
			spEntryHour.setValue(ldt.getHour());
			spEntryHour.setBounds(38, 30, 39, 20);
		}
		return spEntryHour;
	}

	public JSpinner getSpEntryMin() {
		if (spEntryMin == null) {
			spEntryMin = new JSpinner();
			spEntryMin.setValue(ldt.getMinute());
			spEntryMin.setBounds(97, 30, 39, 20);
		}
		return spEntryMin;
	}

	public JSpinner getSpOutHour() {
		if (spOutHour == null) {
			spOutHour = new JSpinner();
			spOutHour.setValue(ldt.getHour() + 1);
			spOutHour.setBounds(286, 30, 39, 20);
		}
		return spOutHour;
	}

	public JSpinner getSpOutMin() {
		if (spOutMin == null) {
			spOutMin = new JSpinner();
			spOutMin.setValue(ldt.getMinute());
			spOutMin.setBounds(345, 30, 39, 20);
		}
		return spOutMin;
	}

	private JLabel getLblOutHour() {
		if (lblOutHour == null) {
			lblOutHour = new JLabel("Hora de Salida");
			lblOutHour.setHorizontalAlignment(SwingConstants.CENTER);
			lblOutHour.setBounds(286, 11, 95, 14);
		}
		return lblOutHour;
	}

	public JButton getBtnUpdate() {
		if (btnUpdate == null) {
			btnUpdate = new JButton("Actualizar Lista");
			btnUpdate.setBounds(5, 204, 198, 23);
		}
		return btnUpdate;
	}

	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(5, 106, 429, 87);
			scrollPane.setViewportView(getListCauses());
		}
		return scrollPane;
	}
	public JButton getBtnUrgente() {
		if (btnUrgente == null) {
			btnUrgente = new JButton("CITA URGENTE");
			btnUrgente.setBackground(Color.RED);
			btnUrgente.setBounds(38, 61, 346, 23);
		}
		return btnUrgente;
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
	
	
}
