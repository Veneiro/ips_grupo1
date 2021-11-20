package controlador;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.time.Instant;
import java.util.ArrayList;
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
    private PacienteControlador pC;
    private int idPaciente;
    private Map<Integer, PrescripcionRecord> mapTable;

    public PrescripcionesControlador(int idPaciente, PacienteControlador pC) {
	pV = new PrescripcionesVista();
	pM = new PrescripcionesModelo();
	this.pC = pC;
	if (idPaciente > 0)
	    this.idPaciente = idPaciente;
    }

    public void inicializar() {

	cargarPrescripciones();
	habilitarMedicamento();
	checkAddButton();

	pV.getBtnCargar().addActionListener(e -> SwingUtil.exceptionWrapper(() -> cargarPrescripcion()));

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

	pV.getTextFieldBuscar().getDocument().addDocumentListener(new DocumentListener() {

	    @Override
	    public void removeUpdate(DocumentEvent e) {
		cargarPrescripciones();
	    }

	    @Override
	    public void insertUpdate(DocumentEvent e) {
		cargarPrescripciones();
	    }

	    @Override
	    public void changedUpdate(DocumentEvent e) {
		cargarPrescripciones();
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
	    pV.getBtnAsignar().setEnabled(false);
	else
	    pV.getBtnAsignar().setEnabled(true);
    }

    private void asignarPrescripcion() {
	PrescripcionRecord p;

	try {
	    p = new PrescripcionRecord();

	    p.setPaciente_id(idPaciente);

	    p.setNombre(pV.getTextFieldNombre().getText());

	    p.setIndicaciones(pV.getIndicacionesTextPane().getText());

	    p.setMedicamento(pV.getChckbxMedicamento().isSelected());

	    p.setCantidad(pV.getTextField_Cantidad().getText());

	    p.setIntervalo(pV.getTextField_Intervalo().getText());

	    p.setDuracion(pV.getTextField_Duracion().getText());

	    p.setFecha(Util.dateToIsoString(Date.from(Instant.now())));

	    p.setHora(Util.dateToIsoHour(Date.from(Instant.now())));

	    pM.addPrescripcion(p);
	    JOptionPane.showMessageDialog(pV, "Prescripción asignada correctamente.");
	    pC._cargarPrescripciones();
	} catch (Exception e) {
	    e.printStackTrace();
	}
	cargarPrescripciones();
    }

    private void cargarPrescripcion() {
	PrescripcionRecord p = mapTable.get(pV.getTable().getSelectedRow());

	try {
	    pV.getTextFieldNombre().setText(p.getNombre());
	    pV.getChckbxMedicamento().setSelected(p.isMedicamento());
	    if (p.isMedicamento()) {
		pV.getTextField_Cantidad().setText(p.getCantidad());
		pV.getTextField_Intervalo().setText(p.getIntervalo());
		pV.getTextField_Duracion().setText(p.getDuracion());
	    }
	    pV.getIndicacionesTextPane().setText(p.getIndicaciones());
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void cargarPrescripciones() {
	int fila = 0;

	pV.setModeloTablaPrescripciones(new NoEditableTableModel(new String[] { "Nombre" }, 0));

	List<PrescripcionRecord> lP = new ArrayList<>();
	mapTable = new HashMap<>();

	if (pV.getTextFieldBuscar().getText().trim().isEmpty()) {
	    lP = pM.getListaPrescripcionesNoRepetidas();
	} else {
	    lP = pM.getListaPrescripcionesNoRepetidasByName(pV.getTextFieldBuscar().getText());
	}

	for (PrescripcionRecord p : lP) {
	    mapTable.put(fila, p);
	    pV.getModeloTablaPrescripciones().addRow(new Object[] { p.getNombre(), p.getIndicaciones(), p.getCantidad(),
		    p.getIntervalo(), p.getDuracion() });
	    fila++;
	}

	pV.getTable().setModel(pV.getModeloTablaPrescripciones());
    }

}
