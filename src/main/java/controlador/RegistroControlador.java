package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import dtos.RegistroDto;
import modelo.RegistroModelo;
import records.RegistroRecord;
import util.NoEditableTableModel;
import util.Util;
import vista.RegistroVista;

public class RegistroControlador {

    private RegistroVista rV;
    private RegistroModelo rM;

    public RegistroControlador() {
	this.rV = new RegistroVista();
	this.rM = new RegistroModelo();
    }

    public void inicializar() {

	cargarListaRegistros();

	rV.getTextFieldBusqueda().getDocument().addDocumentListener(new DocumentListener() {

	    @Override
	    public void removeUpdate(DocumentEvent e) {
		cargarListaRegistros();
	    }

	    @Override
	    public void insertUpdate(DocumentEvent e) {
		cargarListaRegistros();
	    }

	    @Override
	    public void changedUpdate(DocumentEvent e) {
		cargarListaRegistros();
	    }
	});

	rV.getChckbxDesde().addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		cargarListaRegistros();
	    }
	});

	rV.getDesdeSpinner().addChangeListener(new ChangeListener() {

	    @Override
	    public void stateChanged(ChangeEvent e) {
		cargarListaRegistros();
	    }
	});

	RegistroModelo.addRegistro(new RegistroDto("Administrativo", "Consulta registro"));

	rV.setVisible(true);
    }

    private void cargarListaRegistros() {
	List<RegistroRecord> lR = new ArrayList<>();

	List<RegistroRecord> aux = new ArrayList<>();

	rV.setModeloTabla(new NoEditableTableModel(new String[] { "Quién", "Qué", "Fecha", "Hora" }, 0));

	if (rV.getTextFieldBusqueda().getText().trim().isEmpty()) {
	    lR = rM.findAll();

	} else {
	    lR = rM.findBySearch(rV.getTextFieldBusqueda().getText());
	}

	if (rV.getChckbxDesde().isSelected()) {
	    for (RegistroRecord r : rM.findByFecha(Util.dateToIsoString((Date) rV.getDesdeSpinner().getValue()))) {
		if (lR.contains(r))
		    aux.add(r);
	    }
	    lR = aux;
	    aux = new ArrayList<>();
	}

	for (RegistroRecord r : lR) {
	    rV.getModeloTabla().addRow(new Object[] { r.getQuien(), r.getQue(), r.getFecha(), r.getHora() });
	}

	rV.getTable().setModel(rV.getModeloTabla());

    }

}
