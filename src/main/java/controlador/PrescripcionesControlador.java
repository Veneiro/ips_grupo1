package controlador;

import java.util.List;

import javax.swing.JOptionPane;

import dtos.MedicamentoDto;
import dtos.PrescripcionDto;
import modelo.PrescripcionesModelo;
import util.SwingUtil;
import vista.PrescripcionesVista;

public class PrescripcionesControlador {

	private PrescripcionesVista pV;
	private PrescripcionesModelo pM;

	public PrescripcionesControlador() {
		pV = new PrescripcionesVista();
		pM = new PrescripcionesModelo();
	}

	public void inicializar() {
		pV.setLocationRelativeTo(null);
		pV.setVisible(true);
		cargarMedicamentos();

		pV.getBtnNuevoMedicamento().addActionListener(e -> SwingUtil.exceptionWrapper(() -> nuevoMedicamento()));

		pV.getBtnNewButton_1().addActionListener(e -> SwingUtil.exceptionWrapper(() -> addPrescripcion()));

	}

	private void nuevoMedicamento() {
		String nombre = JOptionPane.showInputDialog("Introduzca el nombre del medicamento");
		MedicamentoDto m = new MedicamentoDto(nombre);
		pM.addMedicamento(m);
		cargarMedicamentos();
	}

	private void addPrescripcion() {
		PrescripcionDto p;

		try {
			p = new PrescripcionDto();

			p.setIndicaciones(pV.getIndicacionesTextPane().getText());

			if (pV.getTable().isColumnSelected(0) && pV.getTable().getValueAt(pV.getTable().getSelectedRow(),
					pV.getTable().getSelectedColumn()) != null)
				p.setMedicamento(pV.getTable()
						.getValueAt(pV.getTable().getSelectedRow(), pV.getTable().getSelectedColumn()).toString());
			else {
				p.setMedicamento(null);
			}

			p.setCantidad(pV.getTextField_Cantidad().getText());

			p.setIntervalo(pV.getTextField_Intervalo().getText());

			p.setDuracion(pV.getTextField_Duracion().getText());

			pM.addPrescripcion(p);
			JOptionPane.showMessageDialog(pV, "Prescripción añadida correctamente");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void cargarMedicamentos() {
		List<MedicamentoDto> lM = pM.getListaMedicamentos();
		for (int i = 0; i < lM.size(); i++) {
			pV.getTable().getModel().setValueAt(lM.get(i).getNombre(), i, 0);
		}
	}

}
