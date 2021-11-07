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

import dtos.DiagnosticoDto;
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
			
			List<DiagnosticoDto> diagnosticos = hm.getAllDiagnosticos();
			List<DiagnosticoDto> diagnosticosFiltered = new ArrayList<>();
			for (DiagnosticoDto diagnostico : diagnosticos) {
			try {
				if (diagnostico.getFecha() != null) {
					Date dateCita = new SimpleDateFormat("dd-mm-yyyy").parse(diagnostico.getFecha());
					Date dateDesde = new SimpleDateFormat("dd-mm-yyyy").parse(egv.getTextDesde().getText());
					Date dateHasta = new SimpleDateFormat("dd-mm-yyyy").parse(egv.getTextHasta().getText());
					if (dateCita.compareTo(dateDesde) >= 0 && dateCita.compareTo(dateHasta) <= 0) {
						diagnosticosFiltered.add(diagnostico);
					}
				}
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
		}
			
			
			List<String> enfermedades = new ArrayList<>();
			for (DiagnosticoDto diagnostico : diagnosticosFiltered) {
				List<HistorialDto> historial = hm.getHistorialPaciente(diagnostico.getId_paciente());
				enfermedades.add(diagnostico.getDiagnostico());
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
			double porcentaje = ((double)(maxEntry.getValue())/(double)(diagnosticosFiltered.size()))*100;
			egv.getLblResultadoPorcentaje().setText(String.valueOf(porcentaje)+ "%");
			egv.getLblResultadoNumero().setText(maxEntry.getValue().toString());
			
			DefaultTableModel dm = new DefaultTableModel(0, 0);
		    String header[] = new String[] { "Nombre", "Enfermedad",
		    		"Numero de pacientes con la enfermedad"};
		    dm.setColumnIdentifiers(header);
		    
		    
		    

			for (DiagnosticoDto c : diagnosticosFiltered)
			{
				PacienteDto p = pm.getPacienteById(c.getId_paciente()).get(0);
				
				Vector<Object> data = new Vector<Object>();
		        data.add(p.getNombre());
		        data.add(c.getDiagnostico());
		        data.add(cuentaEnfermedades.get(c.getDiagnostico()));
		        
				dm.addRow(data);
			}
			
			egv.getTableEstadisticas().setModel(dm);
		}
	
	}
}
