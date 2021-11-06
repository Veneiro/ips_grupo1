package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import dtos.CitaDto;
import dtos.HistorialDto;
import dtos.PacienteDto;
import modelo.CitaModelo;
import modelo.HistorialModelo;
import modelo.PacienteModelo;
import vista.EstadisticasGerenteVista;

public class EstadisticasGerenteControlador {

	private HistorialModelo hm;
	private CitaModelo cm;
	private PacienteModelo pm;
	private EstadisticasGerenteVista egv;

	public EstadisticasGerenteControlador(HistorialModelo hm, CitaModelo cm, PacienteModelo pm,
			EstadisticasGerenteVista egv) {
		this.hm = hm;
		this.cm = cm;
		this.pm = pm;
		this.egv = egv;
		inicializarVistaEstadisticasGerente();
	}

	private void inicializarVistaEstadisticasGerente() {
		egv.setVisible(true);

	}

	public void inicilizar() {
		egv.getBtnCalcular().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				calcularEstadisticas();
			}
		});
	}
	
	private void calcularEstadisticas() {
		if (!egv.getTextDesde().getText().isEmpty() && !egv.getTextHasta().getText().isEmpty()) {
			List<CitaDto> citas = cm.getAllCitas();
			List<CitaDto> citasFiltered = new ArrayList<>();
			for (CitaDto cita : citas) {
				try {
					if (cita.getFecha() != null) {
						Date dateCita = new SimpleDateFormat("dd-mm-yyyy").parse(cita.getFecha());
						Date dateDesde = new SimpleDateFormat("dd-mm-yyyy").parse(egv.getTextDesde().getText());
						Date dateHasta = new SimpleDateFormat("dd-mm-yyyy").parse(egv.getTextHasta().getText());
						if (dateCita.compareTo(dateDesde) >= 0 && dateCita.compareTo(dateHasta) <= 0) {
							citasFiltered.add(cita);
						}
					}
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
			}
			List<String> enfermedades = new ArrayList<>();
			for (CitaDto cita : citasFiltered) {
				List<HistorialDto> historial = hm.getHistorialPaciente(cita.getId_paciente());
				enfermedades.add(historial.get(0).getDiagnostico());
			}
			HashMap<String, Integer> cuentaEnfermedades = new HashMap<>();
			int counter = 0;
			for (String enfermedad : enfermedades) {
				for (String enfermedad2 : enfermedades) {
					if (enfermedad.equals(enfermedad2)) {
						counter++;
					}
				}
				if (!cuentaEnfermedades.containsKey(enfermedad)) {
					cuentaEnfermedades.put(enfermedad, counter);
				}
				counter = 0;
			}
			Map.Entry<String, Integer> maxEntry = null;

			for (Map.Entry<String, Integer> entry : cuentaEnfermedades.entrySet())
			{
			    if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0)
			    {
			        maxEntry = entry;
			    }
			}
			
			egv.getLblResultadoMasComun().setText(maxEntry.getKey());
			double porcentaje = ((double)(maxEntry.getValue())/(double)(citas.size()))*100;
			egv.getLblResultadoPorcentaje().setText(String.valueOf(porcentaje)+ "%");
			egv.getLblResultadoNumero().setText(maxEntry.getValue().toString());
			
			DefaultTableModel dm = new DefaultTableModel(0, 0);
		    String header[] = new String[] { "Nombre", "Enfermedad",
		    		"Numero de pacientes con la enfermedad"};
		    dm.setColumnIdentifiers(header);
		    
		    
		    

			for (CitaDto c : citasFiltered)
			{
				PacienteDto p = pm.getPacienteById(c.getId_paciente()).get(0);
				HistorialDto h = hm.getHistorialPaciente(c.getId_paciente()).get(0);
				
				Vector<Object> data = new Vector<Object>();
		        data.add(p.getNombre());
		        data.add(h.getDiagnostico());
		        data.add(cuentaEnfermedades.get(h.getDiagnostico()));
		        
				dm.addRow(data);
			}
			
			egv.getTableEstadisticas().setModel(dm);
		}
	
	}
}
