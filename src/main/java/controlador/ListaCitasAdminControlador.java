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
import vista.ListaCitasAdminVista;

public class ListaCitasAdminControlador {

	private ListaCitasAdminVista lcav;
	private CitaModelo cm;
	private PacienteModelo pm;

	public ListaCitasAdminControlador(ListaCitasAdminVista lcav, CitaModelo cm, PacienteModelo pm) {
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
					int idPaciente = Integer.valueOf(lcav.getTableCitas()
							.getValueAt(lcav.getTableCitas().getSelectedRow(), 0).toString());
					HistorialControlador controller = new HistorialControlador(new HistorialModelo(),
							new HistorialesVista(), idPaciente);
					controller.inicializar();
				}
				else {
					JOptionPane.showMessageDialog(null, "Por favor, seleccione primero un paciente.");
				}

			}
		});
		cargarListaCitas();
	}

	private void cargarListaCitas() {
		List<CitaDto> citas = cm.getAllCitas();
		DefaultTableModel dm = new DefaultTableModel(0, 0);
	    String header[] = new String[] { "idPaciente", "Nombre", "Apellido",
	            "Fecha", "Hora", "Informacion", "Acudio" };
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
	        data.add(c.getAcudio());
			dm.addRow(data);
		}
		lcav.getTableCitas().setModel(dm);
	}
}
