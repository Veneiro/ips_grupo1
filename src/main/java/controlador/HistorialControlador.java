
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import dtos.HistorialDto;
import dtos.PacienteDto;
import modelo.HistorialModelo;
import modelo.PacienteModelo;
import vista.HistorialesVista;

public class HistorialControlador {

	private HistorialModelo hm;
	private PacienteModelo pm;
	private HistorialesVista hv;
	private int idPaciente;

	public HistorialControlador(HistorialModelo hm, HistorialesVista hv, int idPaciente) {
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
		hv.getBtnModificar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showInputDialog("Introduzca el nuevo diagnostico del paciente");
			}
		});
		cargarHistorial(idPaciente);
	}

	private void cargarHistorial(int idPaciente) {
		List<HistorialDto> historial = hm.getHistorialPaciente(idPaciente);
		List<PacienteDto> paciente = pm.getPacienteById(idPaciente);
//		if (historial.size() != 0) {
//			hv.getTxtpnHistorial().setText("idPaciente: " + historial.get(0).getIdPaciente() + "\n"
//											+ "Vacunas: " + historial.get(0).getVacunas() + "\n"
//											+ "Antecedentes: " + historial.get(0).getAntecedentes() + "\n"
//											+ "Informaicion adicional: " + historial.get(0).getInformacionAdicional());
//		}else {
//			hv.getTxtpnHistorial().setText("idPaciente: " + "\n" + 
//											"Vacunas: " + "\n" + 
//											"Antecedentes: " + "\n" + 
//											"Informaicion adicional: ");
//		}
		
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
