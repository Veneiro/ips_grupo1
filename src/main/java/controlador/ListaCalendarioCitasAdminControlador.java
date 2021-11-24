package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import dtos.CitaDto;
import dtos.PacienteDto;
import dtos.RegistroDto;
import modelo.CitaModelo;
import modelo.HistorialModelo;
import modelo.PacienteModelo;
import modelo.RegistroModelo;
import vista.HistorialesAdminVista;
import vista.ListaCalendarioCitasAdminVista;
import vista.ModificarCitaVista;

public class ListaCalendarioCitasAdminControlador {

    private ListaCalendarioCitasAdminVista lcav;
    private CitaModelo cm;
    private PacienteModelo pm;
    private List<CitaDto> citas;

    public ListaCalendarioCitasAdminControlador(ListaCalendarioCitasAdminVista lcav, CitaModelo cm, PacienteModelo pm) {
	this.lcav = lcav;
	this.cm = cm;
	this.pm = pm;

	inicializarVistaCitasAdmin();
    }

    private void inicializarVistaCitasAdmin() {
	RegistroModelo.addRegistro(new RegistroDto("Administrativo", "Consulta calendario citas"));
	lcav.setVisible(true);
    }

    public void inicializar() {

	lcav.getBtnHistorial().addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {
//				if (Integer.valueOf(lcav.getTableCitas().getSelectedRow()) != -1) {
//					String nombre = (String) lcav.getTableCitas()
//							.getValueAt(lcav.getTableCitas().getSelectedRow(), 0);
//					String fecha = (String)lcav.getTableCitas()
//							.getValueAt(lcav.getTableCitas().getSelectedRow(), 1);
//					
//					int idPaciente = pm.getIdPacienteByNombreAndFechaCita(nombre, fecha).get(0).getId();
//					
//					HistorialControlador controller = new HistorialControlador(new HistorialModelo(),
//							new HistorialesVista(), idPaciente);
//					controller.inicializar();
//				}
//				else {
//					JOptionPane.showMessageDialog(null, "Por favor, seleccione primero un paciente.");
//				}
		// ESTA NUEVA IMPLEMENTACIÓN NO GENERA ERROR SI SE SELECCIONA UNA CITA SIN FECHA
		int filaSeleccionada = lcav.getTableCitas().getSelectedRow();
		if (filaSeleccionada != -1) {
		    int idPaciente = citas.get(filaSeleccionada).getId_paciente();
		    HistorialAdminControlador controller = new HistorialAdminControlador(new HistorialModelo(),
			    new HistorialesAdminVista(), idPaciente);
		    controller.inicializar();
		} else {
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
	lcav.getBtnModificar().addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {
		int filaSeleccionada = lcav.getTableCitas().getSelectedRow();
		if (filaSeleccionada != -1) {
		    CitaDto citaSeleccionada = citas.get(filaSeleccionada);
		    ModificarCitaVista dialog = new ModificarCitaVista(citaSeleccionada);
		    dialog.setLocationRelativeTo(null);
		    dialog.visible(true);
		}

	    }

	});
	cargarListaCitas();
    }

    private void cargarListaCitas() {
	citas = cm.getAllCitas();
	DefaultTableModel dm = new DefaultTableModel(0, 0);
	String header[] = new String[] { "Nombre", "Fecha", "Hora Inicio", "Hora fin", "Informacion", "Acudio",
		"Hora Entrada", "Hora Salida" };
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
	    data.add(c.getHora_entrada());
	    data.add(c.getHora_salida());
	    dm.addRow(data);
	}
	lcav.getTableCitas().setModel(dm);
    }

    private void cargarListaCitasFecha(String fecha) {
	citas = cm.getAllCitasFecha(fecha);
	DefaultTableModel dm = new DefaultTableModel(0, 0);
	String header[] = new String[] { "Nombre", "Fecha", "Hora Inicio", "Hora fin", "Informacion", "Acudio",
		"Hora Entrada", "Hora Salida" };
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
	    data.add(c.getHora_entrada());
	    data.add(c.getHora_salida());
	    dm.addRow(data);
	}
	lcav.getTableCitas().setModel(dm);
    }
}
