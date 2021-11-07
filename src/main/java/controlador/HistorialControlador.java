
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import dtos.DiagnosticoDto;
import dtos.HistorialDto;
import dtos.PacienteDto;
import modelo.DiagnosticoModelo;
import modelo.HistorialModelo;
import modelo.PacienteModelo;
import modelo.PrescripcionesModelo;
import records.PrescripcionRecord;
import vista.HistorialesVista;

public class HistorialControlador {

    private HistorialModelo hm;
    private PacienteModelo pm;
    private HistorialesVista hv;
    private DiagnosticoModelo dgm;
    private PrescripcionesModelo prm;
    private int idPaciente;

    public HistorialControlador(HistorialModelo hm, HistorialesVista hv, int idPaciente) {
	this.hm = hm;
	this.hv = hv;
	this.idPaciente = idPaciente;
	this.pm = new PacienteModelo();
	this.dgm = new DiagnosticoModelo();
	this.prm = new PrescripcionesModelo();

	inicializarVistaHistorial();
    }

    private void inicializarVistaHistorial() {
	hv.setVisible(true);
    }

    public void inicializar() {
	hv.getBtnSalir().addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		hv.setVisible(false);

	    }
	});
	hv.getBtnModificar().addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		String nuevoDiagnostico = JOptionPane.showInputDialog("Introduzca el nuevo diagnostico del paciente");
		if (!nuevoDiagnostico.isEmpty()) {
		    DiagnosticoDto diagnostico = new DiagnosticoDto();
		    diagnostico.setDiagnostico(nuevoDiagnostico);
		    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		    String fecha = dtf.format(LocalDateTime.now());
		    diagnostico.setFecha(fecha);
		    diagnostico.setId_paciente(idPaciente);
		    dgm.addDiagnostico(diagnostico);
		    cargarHistorial(idPaciente);
		}
	    }
	});
	cargarHistorial(idPaciente);
    }

    private void cargarHistorial(int idPaciente) {
	List<HistorialDto> historial = hm.getHistorialPaciente(idPaciente);
	List<PacienteDto> paciente = pm.getPacienteById(idPaciente);
	DefaultTableModel dm = new DefaultTableModel(0, 0);
	String header[] = new String[] { "Nombre", "Vacunas", "Antecedentes", "Informacion Adicional" };
	dm.setColumnIdentifiers(header);

	Vector<Object> data = new Vector<Object>();
	data.add(paciente.get(0).getNombre());
	data.add(historial.get(0).getVacunas());
	data.add(historial.get(0).getAntecedentes());
	data.add(historial.get(0).getInformacionAdicional());
	dm.addRow(data);

	hv.getTable().setModel(dm);

	dm = new DefaultTableModel(0, 0);
	header = new String[] { "Diagnostico", "Fecha" };
	dm.setColumnIdentifiers(header);
	List<DiagnosticoDto> diagnosticos = dgm.getDiaganosticoByPacienteId(idPaciente);
	for (DiagnosticoDto diagnostico : diagnosticos) {
	    data = new Vector<Object>();

	    data.add(diagnostico.getDiagnostico());
	    data.add(diagnostico.getFecha());
	    dm.addRow(data);
	}

	hv.getTableDiagnosticos().setModel(dm);

	dm = new DefaultTableModel(0, 0);
	header = new String[] { "Indicaciones", "Medicamento", "Cantidad", "Intervalo", "Duracion", "Fecha" };
	dm.setColumnIdentifiers(header);
	List<PrescripcionRecord> prescripciones = prm.findByPacienteId(idPaciente);
	for (PrescripcionRecord prescripcion : prescripciones) {
	    data = new Vector<Object>();
	    data.add(prescripcion.getIndicaciones());
	    data.add(prescripcion.getNombre());
	    data.add(prescripcion.getCantidad());
	    data.add(prescripcion.getIntervalo());
	    data.add(prescripcion.getDuracion());
	    data.add(prescripcion.getFecha());
	    
	    dm.addRow(data);
	}

	hv.getTablePrescriciones().setModel(dm);

    }
}
