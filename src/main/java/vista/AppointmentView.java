package vista;

import java.awt.Color;
import java.awt.Font;
import java.time.LocalDateTime;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import lombok.Getter;
import lombok.Setter;
import util.NoEditableTableModel;

@Getter
public class AppointmentView extends JDialog {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private JPanel contentPanel = new JPanel();
    private JList<String> listCauses;
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
    private JScrollPane scrollPane;
    private JLabel lblPaciente;
    private JButton btnAddPrescripcion;
    private JTable tablePrescripciones;
    @Setter
    private NoEditableTableModel modeloTablaPrescripciones;
    private JScrollPane scrollPanePrescripciones;
    private JButton btnEDO;
    private JPanel buttonPane;
    private JButton btnHistorial;
    private JButton btnAcudio;
    private JButton btnNoAcudio;
    private JScrollPane scrollPaneDiagnosticos;
    private JTable tableDiagnosticos;
    private JButton btnProcedimientos;

    /**
     * Create the dialog.
     */
    public AppointmentView() {
	setTitle("iHospital : Cita");
	//setModal(true);
	setResizable(false);
	setBounds(100, 100, 450, 700);
	getContentPane().setLayout(null);
	contentPanel.setBounds(0, 0, 434, 614);
	contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
	getContentPane().add(contentPanel);
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
	pnEntryOut.add(getLblPaciente());

	btnAddPrescripcion = new JButton("A\u00F1adir prescripci\u00F3n");
	btnAddPrescripcion.setBounds(5, 388, 429, 25);
	contentPanel.add(btnAddPrescripcion);

	{
	    buttonPane = new JPanel();
	    buttonPane.setBounds(0, 630, 434, 33);
	    getContentPane().add(buttonPane);
	    buttonPane.setLayout(null);
	    buttonPane.add(getBtnContinueButton());
	    getRootPane().setDefaultButton(continueButton);
	    buttonPane.add(getBtnEDO());
	}

	scrollPanePrescripciones = new JScrollPane();
	scrollPanePrescripciones.setBounds(5, 242, 429, 133);

	modeloTablaPrescripciones = new NoEditableTableModel(
		new String[] { "Nombre", "Indicacciones", "Cantidad", "Intervalo", "Duración" }, 0);
	tablePrescripciones = new JTable(modeloTablaPrescripciones);
	tablePrescripciones.setFillsViewportHeight(true);
	tablePrescripciones.setBorder(new LineBorder(new Color(0, 0, 0)));

	scrollPanePrescripciones.setViewportView(tablePrescripciones);
	contentPanel.add(scrollPanePrescripciones);
	contentPanel.add(getBtnHistorial());
	contentPanel.add(getBtnAcudio());
	contentPanel.add(getBtnNoAcudio());
	contentPanel.add(getScrollPaneDiagnosticos());
	contentPanel.add(getBtnProcedimientos());
    }

    public JButton getBtnContinueButton() {
	if (continueButton == null) {
	    continueButton = new JButton("Continuar");
	    continueButton.setBounds(335, 0, 89, 23);
	    continueButton.setActionCommand("OK");
	}
	return continueButton;
    }

    public JList<String> getListCauses() {
	if (listCauses == null) {
	    listCauses = new JList<>();
	}
	return listCauses;
    }

    public JButton getBtnAddCause() {
	if (btnAddCause == null) {
	    btnAddCause = new JButton("A\u00F1adir Causa");
	    btnAddCause.setBounds(5, 206, 429, 23);
	}
	return btnAddCause;
    }

    private JPanel getContentPanel() {
	pnEntryOut.setBounds(5, 11, 429, 92);
	pnEntryOut.setLayout(null);
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
	    spEntryHour.setValue(ldt.getHour());
	    spEntryHour.setBounds(39, 61, 39, 20);
	}
	return spEntryHour;
    }

    public JSpinner getSpEntryMin() {
	if (spEntryMin == null) {
	    spEntryMin = new JSpinner();
	    spEntryMin.setValue(ldt.getMinute());
	    spEntryMin.setBounds(98, 61, 39, 20);
	}
	return spEntryMin;
    }

    public JSpinner getSpOutHour() {
	if (spOutHour == null) {
	    spOutHour = new JSpinner();
	    spOutHour.setValue(ldt.getHour() + 1);
	    spOutHour.setBounds(287, 61, 39, 20);
	}
	return spOutHour;
    }

    public JSpinner getSpOutMin() {
	if (spOutMin == null) {
	    spOutMin = new JSpinner();
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
	return btnAddPrescripcion;
    }

    public JButton getBtnEDO() {
	if (btnEDO == null) {
	    btnEDO = new JButton("EDO");
	    btnEDO.setForeground(new Color(255, 0, 0));
	    btnEDO.setFont(new Font("Tahoma", Font.BOLD, 11));
	    btnEDO.setBounds(10, 0, 89, 23);
	}
	return btnEDO;
    }
	public JButton getBtnHistorial() {
		if (btnHistorial == null) {
			btnHistorial = new JButton("Historial");
			btnHistorial.setBounds(5, 521, 429, 21);
		}
		return btnHistorial;
	}
	public JButton getBtnAcudio() {
		if (btnAcudio == null) {
			btnAcudio = new JButton("Acudio");
			btnAcudio.setBounds(5, 552, 204, 21);
		}
		return btnAcudio;
	}
	public JButton getBtnNoAcudio() {
		if (btnNoAcudio == null) {
			btnNoAcudio = new JButton("No Acudio");
			btnNoAcudio.setBounds(219, 552, 215, 21);
		}
		return btnNoAcudio;
	}
	private JScrollPane getScrollPaneDiagnosticos() {
		if (scrollPaneDiagnosticos == null) {
			scrollPaneDiagnosticos = new JScrollPane();
			scrollPaneDiagnosticos.setBounds(5, 420, 429, 102);
			
			tableDiagnosticos = new JTable();
			scrollPaneDiagnosticos.setViewportView(tableDiagnosticos);
		}
		return scrollPaneDiagnosticos;
	}
	public JButton getBtnProcedimientos() {
		if (btnProcedimientos == null) {
			btnProcedimientos = new JButton("Procedimiento");
			btnProcedimientos.setBounds(5, 589, 429, 21);
		}
		return btnProcedimientos;
	}
}
