package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import vista.ListaCalendarioCitasVista;

public class ListaCalendarioCitasControlador {

	private ListaCalendarioCitasVista lccv;
	private CitaModelo cm;
	private PacienteModelo pm;
	private int idMedico;
	private List<CitaDto> citas;
	
	public ListaCalendarioCitasControlador(ListaCalendarioCitasVista lccv, CitaModelo cm, PacienteModelo pm
											, int idMedico) {
		this.lccv = lccv;
		this.cm = cm;
		this.pm = pm;
		this.idMedico = idMedico;
		
		inicializarVistaListaCalendarioCitas();
	}
	
	public void inicializarVistaListaCalendarioCitas() {
		lccv.setVisible(true);
	}
	
	public void inicializar() {
		
		SimpleDateFormat formatter= new SimpleDateFormat("dd-MM-yyyy");
		Date date = new Date(System.currentTimeMillis());
		cargarCalendarioCitas(formatter.format(date), idMedico);
		lccv.getBtnBuscar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String fecha = lccv.getTextFieldFecha().getText();
				cargarCalendarioCitas(fecha, idMedico);
				
			}
		});
		lccv.getBtnHistorial().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int filaSeleccionada = lccv.getTable().getSelectedRow();
				if (filaSeleccionada != -1) {
					int idPaciente = citas.get(filaSeleccionada).getId_paciente();
					HistorialControlador controller = new HistorialControlador(new HistorialModelo(),
							new HistorialesVista(), idPaciente);
					controller.inicializar();
				} else {
					JOptionPane.showMessageDialog(null, "Por favor, seleccione primero un paciente.");
				}
				
			}
		});
	}
	
	private void cargarCalendarioCitas(String fecha, int idMedico) {
		
		citas = cm.getCitasFecha(fecha, idMedico);
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
		lccv.getTable().setModel(dm);
	}
	
}
