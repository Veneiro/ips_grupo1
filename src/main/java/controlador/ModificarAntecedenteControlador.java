package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import dtos.AntecedenteDto;
import modelo.AntecedentesModelo;
import vista.ModificarAntecedenteVista;

public class ModificarAntecedenteControlador {

	
	private int idPaciente;
	private AntecedentesModelo am;
	private HistorialControlador hc;
	private ModificarAntecedenteVista mav;
	
	public ModificarAntecedenteControlador(HistorialControlador hc, int idPaciente, int idMedico) {
		this.hc = hc;
		this.idPaciente = idPaciente;
		this.mav = new ModificarAntecedenteVista();
		this.am = new AntecedentesModelo();
		
		inicializarVistaModificarVacuna();
	}

	private void inicializarVistaModificarVacuna() {
		
		mav.setVisible(true);
	}
	
	public void inicializar() {
		
		mav.getBtnModificar().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String antecedente = JOptionPane.showInputDialog("Introduzca la antecedente");
				String fecha = JOptionPane.showInputDialog("Introduzca la fecha en la que se produjo");
				String antecedenteA = String.valueOf(mav.getTableAntecedentes().getValueAt(mav.getTableAntecedentes().getSelectedRow(), 0));
				String fechaA  = String.valueOf(mav.getTableAntecedentes().getValueAt(mav.getTableAntecedentes().getSelectedRow(), 1));
				if (antecedente.isEmpty()) {
					antecedente = antecedenteA;
				}
				
				if (fecha.isEmpty()) {
					fecha = fechaA;
				}
				
				
				am.addAntecedente(idPaciente, antecedente, fecha);
				
				am.updateAntecedente(antecedenteA + "-ERROR", fechaA, antecedenteA, fechaA, idPaciente);
				
				hc.cargarHistorial(idPaciente);
				cargarListaAntecedentes();
			}
			
		});
		
		cargarListaAntecedentes();
	}

	private void cargarListaAntecedentes() {
		DefaultTableModel dm = new DefaultTableModel(0, 0);
		String[] header = new String[] { "Antecedente", "Fecha Comienzo" };
		dm.setColumnIdentifiers(header);
		List<AntecedenteDto> antecedentes = am.getAntecedentesByPacienteId(idPaciente);
		for (AntecedenteDto antecedente : antecedentes) {
			Vector<Object> data = new Vector<Object>();
			data.add(antecedente.getAntecedente());
			data.add(antecedente.getFecha_comienzo());
			dm.addRow(data);
		}
		
		mav.getTableAntecedentes().setModel(dm);
		
	}
}
