package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import dtos.HistorialDto;
import dtos.PacienteDto;
import modelo.HistorialModelo;
import modelo.PacienteModelo;
import vista.HistorialesAdminVista;

public class HistorialAdminControlador {

	
	private HistorialModelo hm;
	private PacienteModelo pm;
	private HistorialesAdminVista hv;
	private int idPaciente;

	public HistorialAdminControlador(HistorialModelo hm, HistorialesAdminVista hv, int idPaciente) {
		this.hm = hm;
		this.hv = hv;
		this.idPaciente = idPaciente;
		this.pm = new PacienteModelo();

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
		cargarHistorial(idPaciente);
	}

	private void cargarHistorial(int idPaciente) {
		List<HistorialDto> historial = hm.getHistorialPaciente(idPaciente);
		List<PacienteDto> paciente = pm.getPacienteById(idPaciente);
		DefaultTableModel dm = new DefaultTableModel(0, 0);
	    String header[] = new String[] { "Nombre", "Vacunas","Antecedentes", "Diagnostico", "Diagnosticos anteriores",
	    									"Prescripciones", "Informacion Adicional" };
	    dm.setColumnIdentifiers(header);
	    
	    Vector<Object> data = new Vector<Object>();
	    data.add(paciente.get(0).getNombre());
        data.add(historial.get(0).getVacunas());
        data.add(historial.get(0).getAntecedentes());
        data.add(historial.get(0).getDiagnostico());
        data.add(historial.get(0).getDiagnosticosAntiguos());
        data.add(historial.get(0).getPrescripcion());
        data.add(historial.get(0).getInformacionAdicional());
		dm.addRow(data);
	    
		hv.getTable().setModel(dm);

	}
}
