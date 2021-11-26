package controlador;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

import dtos.PacienteDto;
import dtos.VacunaDto;
import modelo.PacienteModelo;
import modelo.VacunaModelo;
import util.SwingUtil;
import vista.EstadisticasVacunacionGerenteVista;

public class EstadisticasVacunacionControlador {

	private PacienteModelo pm;
	private VacunaModelo vm;
	private EstadisticasVacunacionGerenteVista evgv;

	public EstadisticasVacunacionControlador() {
		this.vm = new VacunaModelo();
		this.evgv = new EstadisticasVacunacionGerenteVista();
		this.pm = new PacienteModelo();
		inicializarEstadisticasVista();
	}

	private void inicializarEstadisticasVista() {
		evgv.setVisible(true);

	}

	public void inicializar() {
		evgv.getBtnCalcular().addActionListener(e -> SwingUtil.exceptionWrapper(() -> calcularEstadisticas()));
		List<VacunaDto> vacunas = vm.getAllNotRepitedVacunas();
		List<String> nombres = new ArrayList<>();
		for (VacunaDto v : vacunas) {
			if (!v.getVacuna().contains("ERROR"))
				nombres.add(v.getVacuna());
		}
		evgv.getComboBox().setModel(new DefaultComboBoxModel<String>(nombres.toArray(new String[0])));
	}

	private void calcularEstadisticas() {
		String desde = evgv.getTextDesde().getText();
		String hasta = evgv.getTextHasta().getText();
		String vacuna = String.valueOf(evgv.getComboBox().getSelectedItem());
		List<VacunaDto> vacunas = vm.getVacunasByNombre(vacuna);
		List<VacunaDto> vacunasFiltered = new ArrayList<>();
		for (VacunaDto v : vacunas) {
			try {
				if (v.getFecha() != null) {
					Date dateVacuna = new SimpleDateFormat("dd-mm-yyyy").parse(v.getFecha());
					Date dateDesde = new SimpleDateFormat("dd-mm-yyyy").parse(desde);
					Date dateHasta = new SimpleDateFormat("dd-mm-yyyy").parse(hasta);
					if (dateVacuna.compareTo(dateDesde) >= 0 && dateVacuna.compareTo(dateHasta) <= 0) {
						vacunasFiltered.add(v);
					}
				}
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
		}
		evgv.getLblPacientesVacunadosR().setText(String.valueOf(vacunasFiltered.size()));
		List<PacienteDto> pacientes = pm.getListaPacientes();
		List<PacienteDto> pacientesFiltered = new ArrayList<>();
		List<Integer> idPacientes = new ArrayList<>();
		
		for(VacunaDto v : vacunasFiltered) {
			idPacientes.add(v.getPaciente_id());
		}
		
		for(PacienteDto paciente: pacientes) {
			if(!idPacientes.contains(paciente.getId())) {
				pacientesFiltered.add(paciente);
			}
		}
		
		evgv.getLblPacientesNoVacunadosR().setText(String.valueOf(pacientesFiltered.size()));
		
		evgv.getLblProcentageR().setText(String.valueOf((vacunasFiltered.size()*100)/pacientes.size())+ "%");
		
		DefaultTableModel dm = new DefaultTableModel(0, 0);
		String header[] = new String[] { "Nombre", "Vacuna", "Fecha", "Hora", "Estado"};
		dm.setColumnIdentifiers(header);
		for (VacunaDto v : vacunasFiltered) {
			PacienteDto p = pm.getPacienteById(v.getPaciente_id()).get(0);
			Vector<Object> data = new Vector<Object>();
			
			data.add(p.getNombre());
			data.add(vacuna);
			data.add(v.getFecha());
			data.add(v.getHora());
			data.add("Vacunado");
			dm.addRow(data);
		}
		
		for(PacienteDto paciente: pacientesFiltered) {
			Vector<Object> data = new Vector<Object>();
			
			data.add(paciente.getNombre());
			data.add(vacuna);
			data.add("-");
			data.add("-");
			data.add("No Vacunado");
			dm.addRow(data);
		}
		
		evgv.getTable().setModel(dm);
		
		

	}

}
