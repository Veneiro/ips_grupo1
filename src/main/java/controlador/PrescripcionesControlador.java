package controlador;

import java.time.Instant;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import dtos.PrescripcionDto;
import modelo.PrescripcionesModelo;
import util.SwingUtil;
import vista.PrescripcionesVista;

public class PrescripcionesControlador {

    private PrescripcionesVista pV;
    private PrescripcionesModelo pM;
    private int idPaciente;

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

    }

    private void addPrescripcion() {
	PrescripcionDto p;

	try {
	    p = new PrescripcionDto();

	    p.setPaciente_id(idPaciente);

	    p.setIndicaciones(pV.getIndicacionesTextPane().getText());

	    // p.setMedicamento(false);

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
	List<PrescripcionDto> lM = pM.getListaPrescripciones();

	for (int i = 0; i < lM.size(); i++) {

	}
    }

}
