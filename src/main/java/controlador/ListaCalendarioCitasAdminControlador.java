package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import dtos.CitaDto;
import dtos.PacienteDto;
import modelo.CitaModelo;
import modelo.HistorialModelo;
import modelo.PacienteModelo;
import vista.HistorialesVista;
import vista.ListaCalendarioCitasAdminVista;

public class ListaCalendarioCitasAdminControlador {

	private ListaCalendarioCitasAdminVista lcav;
	private CitaModelo cm;
	private PacienteModelo pm;

	public ListaCalendarioCitasAdminControlador(ListaCalendarioCitasAdminVista lcav, CitaModelo cm, PacienteModelo pm) {
		this.lcav = lcav;
		this.cm = cm;
		this.pm = pm;

		inicializarVistaCitasAdmin();
	}

	private void inicializarVistaCitasAdmin() {
		lcav.setVisible(true);
	}

	public void inicializar() {

		lcav.getBtnHistorial().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (Integer.valueOf(lcav.getTableCitas().getSelectedRow()) != -1) {
					String nombre = (String) lcav.getTableCitas()
							.getValueAt(lcav.getTableCitas().getSelectedRow(), 0);
					String fecha = (String)lcav.getTableCitas()
							.getValueAt(lcav.getTableCitas().getSelectedRow(), 1);
					
					int idPaciente = pm.getIdPacienteByNombreAndFechaCita(nombre, fecha).get(0).getId();
					
					HistorialControlador controller = new HistorialControlador(new HistorialModelo(),
							new HistorialesVista(), idPaciente);
					controller.inicializar();
				}
				else {
					JOptionPane.showMessageDialog(null, "Por favor, seleccione primero un paciente.");
				}

			}
		});
		lcav.getBtnBuscar().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String fecha = lcav.getTextFecha().getText();
				cargarListaCitasFecha(fecha);
				
			}
		});
		cargarListaCitas();
	}

	private void cargarListaCitas() {
		List<CitaDto> citas = cm.getAllCitas();
		DefaultTableModel dm = new DefaultTableModel(0, 0);
	    String header[] = new String[] { "Nombre", "Fecha", "Hora Inicio", "Hora fin",
	    		"Informacion", "Acudio" };
	    dm.setColumnIdentifiers(header);
	    

		for (CitaDto c : citas) {
			PacienteDto p = pm.getPacienteById(c.getId_paciente()).get(0);
			Vector<Object> data = new Vector<Object>();
	        data.add(p.getNombre());
	        data.add(c.getFecha());
	        data.add(c.getHorario_inicio());
	        data.add(c.getHorario_fin());
	        data.add(c.getInformacion());
	        data.add(c.getAcudio());
			dm.addRow(data);
		}
		lcav.getTableCitas().setModel(dm);
	}
	
	private void cargarListaCitasFecha(String fecha) {
		List<CitaDto> citas = cm.getAllCitasFecha(fecha);
		DefaultTableModel dm = new DefaultTableModel(0, 0);
		String header[] = new String[] { "Nombre", "Fecha", "Hora Inicio", "Hora fin",
	    		"Informacion", "Acudio" };
	    dm.setColumnIdentifiers(header);
	    

		for (CitaDto c : citas) {
			PacienteDto p = pm.getPacienteById(c.getId_paciente()).get(0);
			Vector<Object> data = new Vector<Object>();
			data.add(p.getNombre());
	        data.add(c.getFecha());
	        data.add(c.getHorario_inicio());
	        data.add(c.getHorario_fin());
	        data.add(c.getInformacion());
	        data.add(c.getAcudio());
			dm.addRow(data);
		}
		lcav.getTableCitas().setModel(dm);
	}
}
