package controlador;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import modelo.PrescripcionesModelo;
import records.PrescripcionRecord;
import util.NoEditableTableModel;
import util.SwingUtil;
import util.Util;
import vista.PrescripcionesVista;

public class PrescripcionesControlador {

    private PrescripcionesVista pV;
    private PrescripcionesModelo pM;
    private int idPaciente;
    private Map<Integer, PrescripcionRecord> mapTable;

    public PrescripcionesControlador(int idPaciente) {
	pV = new PrescripcionesVista();
	pM = new PrescripcionesModelo();
	if (idPaciente > 0)
	    this.idPaciente = idPaciente;
    }

    public void inicializar() {

	cargarPrescripciones();
	habilitarMedicamento();
	checkAddButton();

	pV.getBtnNewButton_1().addActionListener(e -> SwingUtil.exceptionWrapper(() -> addPrescripcion()));

	pV.getBtnAsignar().addActionListener(e -> SwingUtil.exceptionWrapper(() -> asignarPrescripcion()));

	pV.getTextFieldNombre().getDocument().addDocumentListener(new DocumentListener() {

	    @Override
	    public void removeUpdate(DocumentEvent e) {
		checkAddButton();
	    }

	    @Override
	    public void insertUpdate(DocumentEvent e) {
		checkAddButton();
	    }

	    @Override
	    public void changedUpdate(DocumentEvent e) {
		checkAddButton();
	    }
	});

	pV.getIndicacionesTextPane().getDocument().addDocumentListener(new DocumentListener() {

	    @Override
	    public void removeUpdate(DocumentEvent e) {
		checkAddButton();
	    }

	    @Override
	    public void insertUpdate(DocumentEvent e) {
		checkAddButton();
	    }

	    @Override
	    public void changedUpdate(DocumentEvent e) {
		checkAddButton();
	    }
	});

	pV.getChckbxMedicamento().addItemListener(new ItemListener() {

	    @Override
	    public void itemStateChanged(ItemEvent e) {
		habilitarMedicamento();
	    }
	});

	pV.setLocationRelativeTo(null);
	pV.setVisible(true);
    }

    private void habilitarMedicamento() {
	boolean check = pV.getChckbxMedicamento().isSelected();
	pV.getTextField_Cantidad().setEnabled(check);
	pV.getTextField_Intervalo().setEnabled(check);
	pV.getTextField_Duracion().setEnabled(check);
    }

    private void checkAddButton() {
	if (pV.getTextFieldNombre().getText().isEmpty() || pV.getIndicacionesTextPane().getText().isEmpty())
	    pV.getBtnNewButton_1().setEnabled(false);
	else
	    pV.getBtnNewButton_1().setEnabled(true);
    }

    private void addPrescripcion() {
	PrescripcionRecord p;

	try {
	    p = new PrescripcionRecord();

	    p.setNombre(pV.getTextFieldNombre().getText());

	    p.setIndicaciones(pV.getIndicacionesTextPane().getText());

	    p.setMedicamento(pV.getChckbxMedicamento().isSelected());

	    p.setCantidad(pV.getTextField_Cantidad().getText());

	    p.setIntervalo(pV.getTextField_Intervalo().getText());

	    p.setDuracion(pV.getTextField_Duracion().getText());

	    pM.addPrescripcion(p);
	    JOptionPane.showMessageDialog(pV, "Prescripción añadida correctamente, no olvide asignarla.");
	} catch (Exception e) {
	    e.printStackTrace();
	}
	cargarPrescripciones();
    }

    private void asignarPrescripcion() {
	PrescripcionRecord p = mapTable.get(pV.getTable().getSelectedRow());
	;

	try {
	    p.setPaciente_id(idPaciente);

	    p.setFecha(Util.dateToIsoString(Date.from(Instant.now())));

	    p.setHora(Util.dateToIsoHour(Date.from(Instant.now())));

	    pM.addPrescripcion(p);
	    JOptionPane.showMessageDialog(pV, "Prescripción asignada correctamente");
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void cargarPrescripciones() {
	int fila = 0;

	pV.setModeloTablaPrescripciones(new NoEditableTableModel(
		new String[] { "Nombre", "Indicaciones", "Cantidad", "Intervalo", "Duración" }, 0));

	List<PrescripcionRecord> lM = pM.getListaPrescripcionesNoRepetidas();
	mapTable = new HashMap<>();

	for (PrescripcionRecord p : lM) {
	    mapTable.put(fila, p);
	    pV.getModeloTablaPrescripciones().addRow(new Object[] { p.getNombre(), p.getIndicaciones(), p.getCantidad(),
		    p.getIntervalo(), p.getDuracion() });
	    fila++;
	}

	pV.getTable().setModel(pV.getModeloTablaPrescripciones());
    }

}
