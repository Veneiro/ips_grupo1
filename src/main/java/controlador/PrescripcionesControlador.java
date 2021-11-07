package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import dtos.PrescripcionDto;
import modelo.PrescripcionesModelo;
import util.NoEditableTableModel;
import util.SwingUtil;
import vista.PrescripcionesVista;

public class PrescripcionesControlador {

    private PrescripcionesVista pV;
    private PrescripcionesModelo pM;
    private int idPaciente;
    private Map<Integer, PrescripcionDto> mapTable;

    public PrescripcionesControlador(int idPaciente) {
	pV = new PrescripcionesVista();
	pM = new PrescripcionesModelo();
	this.idPaciente = idPaciente;
    }

    public void inicializar() {
	pV.setLocationRelativeTo(null);
	pV.setVisible(true);
	cargarPrescripciones();

	pV.getBtnNewButton_1().addActionListener(e -> SwingUtil.exceptionWrapper(() -> addPrescripcion()));

	pV.getTable().getSelectionModel().addListSelectionListener(new ListSelectionListener() {

	    @Override
	    public void valueChanged(ListSelectionEvent e) {
		checkAddButton();
	    }
	});

	pV.getChckbxMedicamento().addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		habilitarMedicamento();
	    }
	});
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
	PrescripcionDto p;

	try {
	    p = new PrescripcionDto();

	    p.setPaciente_id(idPaciente);

	    p.setIndicaciones(pV.getIndicacionesTextPane().getText());

	    p.setMedicamento(false);

	    p.setCantidad(pV.getTextField_Cantidad().getText());

	    p.setIntervalo(pV.getTextField_Intervalo().getText());

	    p.setDuracion(pV.getTextField_Duracion().getText());

	    p.setFecha(Date.from(Instant.now()));

	    pM.addPrescripcion(p);
	    JOptionPane.showMessageDialog(pV, "Prescripción añadida correctamente");
	} catch (Exception e) {
	    e.printStackTrace();
	}

    }

    private void cargarPrescripciones() {
	int fila = 0;

	pV.setModeloTablaPrescripciones(new NoEditableTableModel(
		new String[] { "Nombre", "Indicaciones", "Cantidad", "Intervalo", "Duración" }, 0));

	List<PrescripcionDto> lM = pM.getListaPrescripciones();
	mapTable = new HashMap<>();

	for (PrescripcionDto p : lM) {
	    mapTable.put(fila, p);
	    pV.getModeloTablaPrescripciones().addRow(new Object[] { p.getNombre(), p.getIndicaciones(), p.getCantidad(),
		    p.getIntervalo(), p.getDuracion() });
	}
    }

}
