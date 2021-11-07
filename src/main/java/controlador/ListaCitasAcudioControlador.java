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
import vista.ListaCitasAcudioVista;

public class ListaCitasAcudioControlador {

	private ListaCitasAcudioVista lcv;
	private CitaModelo cm;
	private PacienteModelo pm;
	private int idMedico;

	public ListaCitasAcudioControlador(CitaModelo modelo_cita, ListaCitasAcudioVista vista_citas, PacienteModelo modelo_paciente, 
										int idMedico) {
		this.cm = modelo_cita;
		this.lcv = vista_citas;
		this.pm = modelo_paciente;
		this.idMedico = idMedico;

		inicializarVistaAcudioCita();
	}

	private void inicializarVistaAcudioCita() {
		lcv.setVisible(true);
	}

	public void inicializar() {
		
		lcv.getBtnAcudio().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (Integer.valueOf(lcv.getTableCitas().getSelectedRow()) != -1) {
					int acudio = Integer.valueOf(lcv.getTableCitas()
							.getValueAt(lcv.getTableCitas().getSelectedRow(), 6).toString());
					if (acudio == 0) {
						//cm.updateAcudio(Integer.valueOf(lcv.getTableCitas()
							//	.getValueAt(lcv.getTableCitas().getSelectedRow(), 0).toString()));
						JOptionPane.showMessageDialog(null, "La actualización " + "se ha realizado correctamente");
						cargarListaCitas(1);
					} else {
						JOptionPane.showMessageDialog(null,
								"Actualizacion erronea," + " el paciente ya acudio a dicha cita");
					}
				} else {
					JOptionPane.showMessageDialog(null, "Por favor, seleccione primero un paciente.");
				}
			}
		});
		lcv.getBtnHistorial().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (Integer.valueOf(lcv.getTableCitas().getSelectedRow()) != -1) {
					int idPaciente = Integer.valueOf(lcv.getTableCitas()
													.getValueAt(lcv.getTableCitas().getSelectedRow(), 0).toString());
					HistorialControlador controller = new HistorialControlador(new HistorialModelo(), new HistorialesVista(), 
							idPaciente);
					controller.inicializar();
				}else {
					JOptionPane.showMessageDialog(null, "Por favor, seleccione primero un paciente.");
				}
				
			}
		});
		cargarListaCitas(idMedico);
	}

	private void cargarListaCitas(int idMedico) {
		List<CitaDto> citas = cm.getListaCitasMedico(idMedico);
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
		lcv.getTableCitas().setModel(dm);
		
		

	}
}
