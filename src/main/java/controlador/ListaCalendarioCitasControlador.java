package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import dtos.CitaDto;
import dtos.PacienteDto;
import modelo.CitaModelo;
import modelo.PacienteModelo;
import vista.ListaCalendarioCitasVista;

public class ListaCalendarioCitasControlador {

	private ListaCalendarioCitasVista lccv;
	private CitaModelo cm;
	private PacienteModelo pm;
	private int idMedico;
	
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
	}
	
	private void cargarCalendarioCitas(String fecha, int idMedico) {
		
		List<CitaDto> citas = cm.getCitasFecha(fecha, idMedico);
		DefaultTableModel dm = new DefaultTableModel(0, 0);
	    String header[] = new String[] { "idPaciente", "Nombre", "Apellido",
	            "Fecha", "Hora", "Informacion" };
	    dm.setColumnIdentifiers(header);
	    

		for (CitaDto c : citas) {
			PacienteDto p = pm.getPacienteById(c.getId_paciente()).get(0);
			Vector<Object> data = new Vector<Object>();
	        data.add(p.getId());
	        data.add(p.getNombre());
	        data.add(p.getApellido());
	        data.add(c.getFecha());
	        data.add(c.getHorario_inicio());
	        data.add(c.getInformacion());
			dm.addRow(data);
		}
		lccv.getTable().setModel(dm);
	}
	
}
