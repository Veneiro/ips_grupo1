
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import dtos.HistorialDto;
import modelo.HistorialModelo;
import vista.HistorialesVista;

public class HistorialControlador {

	private HistorialModelo hm;
	private HistorialesVista hv;
	private int idPaciente;

	public HistorialControlador(HistorialModelo hm, HistorialesVista hv, int idPaciente) {
		this.hm = hm;
		this.hv = hv;
		this.idPaciente = idPaciente;

		inicializarVistaHistorial();
	}

	private void inicializarVistaHistorial() {
		hv.setVisible(true);
	}

	public void inicializar() {
		hv.getBtnSalir().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				hv.setVisible(false);

			}
		});
		cargarHistorial(idPaciente);
	}

	private void cargarHistorial(int idPaciente) {
		List<HistorialDto> historial = hm.getHistorialPaciente(idPaciente);
		if (historial.size() != 0) {
			hv.getTxtpnHistorial().setText("idPaciente: " + historial.get(0).getIdPaciente() + "\n"
											+ "Vacunas: " + historial.get(0).getVacunas() + "\n"
											+ "Antecedentes: " + historial.get(0).getAntecedentes() + "\n"
											+ "Informaicion adicional: " + historial.get(0).getInformacionAdicional());
		}else {
			hv.getTxtpnHistorial().setText("idPaciente: " + "\n" + 
											"Vacunas: " + "\n" + 
											"Antecedentes: " + "\n" + 
											"Informaicion adicional: ");
		}

	}
}
