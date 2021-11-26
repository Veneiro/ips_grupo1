package controlador;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import dtos.CitaDto;
import dtos.ProcedimientoDto;
import modelo.ProcedimientoModelo;
import util.SwingUtil;
import vista.AniadirProcedimientoVista;
import vista.ListaProcedimientosVista;

public class ListaProcedimientosControlador {

	private CitaDto cita;
	private ListaProcedimientosVista lpv;
	private ProcedimientoModelo pm;

	public ListaProcedimientosControlador(CitaDto cita) {
		this.cita = cita;
		this.lpv = new ListaProcedimientosVista();
		this.pm = new ProcedimientoModelo();

		inicializarListaProcedimientos();
	}

	private void inicializarListaProcedimientos() {
		lpv.setVisible(true);
	}

	public void inicializar() {
		lpv.getBtnAProcedimiento().addActionListener(e -> SwingUtil.exceptionWrapper(() -> aniadirProcedimiento()));
		lpv.getBtnModificar().addActionListener(e -> SwingUtil.exceptionWrapper(() -> modificarProcedimiento()));
		lpv.getBtnBuscar().addActionListener(e -> SwingUtil.exceptionWrapper(() -> buscarProcedimiento()));
		cargarListaProcedimientos("");
	}

	public void cargarListaProcedimientos(String nombre) {
		if (nombre.isEmpty()) {
			List<ProcedimientoDto> procedimientos = pm.getAllProcedimientos();
			
			DefaultTableModel dm = new DefaultTableModel(0, 0);
			String header[] = new String[] { "Procedimiento", "Fecha", "Hora"};
			dm.setColumnIdentifiers(header);

			for (ProcedimientoDto p : procedimientos) {
				Vector<Object> data = new Vector<Object>();
				data.add(p.getProcedimiento());
				data.add(p.getFecha());
				data.add(p.getHora());
				dm.addRow(data);
			}
			lpv.getTable().setModel(dm);
		}else {
			List<ProcedimientoDto> procedimientos = pm.getProcedimientosByNombre(nombre);
			DefaultTableModel dm = new DefaultTableModel(0, 0);
			String header[] = new String[] { "Procedimiento", "Fecha", "Hora"};
			dm.setColumnIdentifiers(header);

			for (ProcedimientoDto p : procedimientos) {
				Vector<Object> data = new Vector<Object>();
				data.add(p.getProcedimiento());
				data.add(p.getFecha());
				data.add(p.getHora());
				dm.addRow(data);
			}
			lpv.getTable().setModel(dm);
		}
	}

	private void buscarProcedimiento() {
		
		cargarListaProcedimientos(lpv.getTextBuscar().getText());

	}

	private void modificarProcedimiento() {
		AniadirProcedimientoVista apv = new AniadirProcedimientoVista();
		apv.setVisible(true);
		String nombreProcedimiento = String.valueOf(lpv.getTable().getValueAt(lpv.getTable().getSelectedRow(), 0));
		String fecha  = String.valueOf(lpv.getTable().getValueAt(lpv.getTable().getSelectedRow(), 1));
		String hora = String.valueOf(lpv.getTable().getValueAt(lpv.getTable().getSelectedRow(), 2));
		
		List<ProcedimientoDto> procedimiento = pm.findProcedimiento(cita.getId(), nombreProcedimiento, fecha, hora);
		
		apv.getTextFecha().setText(procedimiento.get(0).getFecha()); 
		apv.getTextHora().setText(procedimiento.get(0).getHora());
		
		apv.getBtnAceptar().addActionListener(e -> SwingUtil.exceptionWrapper(() -> mProcedimiento(apv)));
		
	}

	private void mProcedimiento(AniadirProcedimientoVista apv) {
		String nombreProcedimientoA = String.valueOf(lpv.getTable().getValueAt(lpv.getTable().getSelectedRow(), 0));
		String fechaA  = String.valueOf(lpv.getTable().getValueAt(lpv.getTable().getSelectedRow(), 1));
		String horaA = String.valueOf(lpv.getTable().getValueAt(lpv.getTable().getSelectedRow(), 2));
		
		String nombreProcedimiento = String.valueOf(apv.getComboBox().getSelectedItem());
		String fecha = apv.getTextFecha().getText();
		String hora = apv.getTextHora().getText();
		
		pm.updateProcedimiento(nombreProcedimientoA +"-ERROR" , fechaA, horaA, cita.getId(), nombreProcedimientoA, fechaA, horaA);
		
		pm.insertProcedimiento(cita.getId(), nombreProcedimiento, fecha, hora);
		
		apv.setVisible(false);
		cargarListaProcedimientos("");
	}

	private void aniadirProcedimiento() {
		
		AniadirProcedimientoVista apv = new AniadirProcedimientoVista();
		apv.setVisible(true);
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		Date date = new Date(System.currentTimeMillis());
		apv.getTextFecha().setText(formatter.format(date));
		apv.getTextHora().setText(String.valueOf(LocalDateTime.now().getHour())
				+ ":" + String.valueOf(LocalDateTime.now().getMinute()));
		apv.getBtnAceptar().addActionListener(e -> SwingUtil.exceptionWrapper(() -> nuevoProcedimiento(apv)));
		
		
	}

	private void nuevoProcedimiento(AniadirProcedimientoVista apv) {
		pm.insertProcedimiento(cita.getId(), String.valueOf(apv.getComboBox().getSelectedItem()),
				apv.getTextFecha().getText(), apv.getTextHora().getText());
		apv.setVisible(false);
		cargarListaProcedimientos("");
	}

}
