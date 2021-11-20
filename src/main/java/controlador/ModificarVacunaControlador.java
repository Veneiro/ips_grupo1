package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import dtos.VacunaDto;
import modelo.VacunaModelo;
import vista.ModificarVacunaVista;

public class ModificarVacunaControlador {

	
	private int idPaciente;
	private VacunaModelo vm;
	private HistorialControlador hc;
	private ModificarVacunaVista mvv;
	
	public ModificarVacunaControlador(HistorialControlador hc, int idPaciente, int idMedico) {
		this.hc = hc;
		this.idPaciente = idPaciente;
		this.mvv = new ModificarVacunaVista();
		this.vm = new VacunaModelo();
		
		inicializarVistaModificarVacuna();
	}

	private void inicializarVistaModificarVacuna() {
		
		mvv.setVisible(true);
	}
	
	public void inicializar() {
		
		mvv.getBtnModificar().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String vacuna = JOptionPane.showInputDialog("Introduzca la vacuna");
				String hora = JOptionPane.showInputDialog("Introduzca la hora en la que se produjo");
				
			}
			
		});
		
		cargarListaVacunas();
	}

	private void cargarListaVacunas() {
		DefaultTableModel dm = new DefaultTableModel(0, 0);
		String[] header = new String[] { "Vacuna", "Fecha", "Hora" };
		dm.setColumnIdentifiers(header);
		List<VacunaDto> vacunas = vm.getVacunasByPacienteId(idPaciente);
		for (VacunaDto vacuna : vacunas) {
			Vector<Object> data = new Vector<Object>();
			data.add(vacuna.getVacuna());
			data.add(vacuna.getFecha());
			data.add(vacuna.getHora());
			
			dm.addRow(data);
		}
		
		mvv.getTableVacunas().setModel(dm);
		
	}
	
	
}
