package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import dtos.AntecedenteDto;
import dtos.DiagnosticoDto;
import dtos.HistorialDto;
import dtos.MedicoDto;
import dtos.PacienteDto;
import dtos.RegistroDto;
import dtos.VacunaDto;
import modelo.AntecedentesModelo;
import modelo.DiagnosticoModelo;
import modelo.HistorialModelo;
import modelo.MedicoModelo;
import modelo.PacienteModelo;
import modelo.PrescripcionesModelo;
import modelo.RegistroModelo;
import modelo.VacunaModelo;
import records.PrescripcionRecord;
import vista.HistorialesAdminVista;

public class HistorialAdminControlador {

	private HistorialModelo hm;
	private PacienteModelo pm;
	private HistorialesAdminVista hv;
	private DiagnosticoModelo dgm;
	private PrescripcionesModelo prm;
	private int idPaciente;
	private MedicoModelo mm;
	private VacunaModelo vm;
	private AntecedentesModelo am;

	public HistorialAdminControlador(HistorialModelo hm, HistorialesAdminVista hv, int idPaciente) {
		this.hm = hm;
		this.hv = hv;
		this.idPaciente = idPaciente;
		this.pm = new PacienteModelo();
		this.dgm = new DiagnosticoModelo();
		this.prm = new PrescripcionesModelo();
		this.mm = new MedicoModelo();
		this.vm = new VacunaModelo();
		this.am = new AntecedentesModelo();

		inicializarVistaHistorial();
	}

	private void inicializarVistaHistorial() {
		RegistroModelo.addRegistro(new RegistroDto("Administrativo", "Consulta historial"));
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
		List<PacienteDto> paciente = pm.getPacienteById(idPaciente);
		List<AntecedenteDto> antecedentes = am.getByPacienteId(idPaciente);
		DefaultTableModel dm = new DefaultTableModel(0, 0);
		String header[] = new String[] { "Nombre", "Antecedentes", "Fecha Comienzo", "Informacion Adicional" };
		dm.setColumnIdentifiers(header);

		Vector<Object> data = new Vector<Object>();
		for (AntecedenteDto antecedente : antecedentes) {
			data = new Vector<Object>();
			data.add(paciente.get(0).getNombre());
			data.add(antecedente.getAntecedente());
			data.add(antecedente.getFecha_comienzo());
			data.add(antecedente.getInformacion());
			dm.addRow(data);
		}

		hv.getTable().setModel(dm);

		dm = new DefaultTableModel(0, 0);
		header = new String[] { "Diagnostico", "Fecha", "Medico", "Hora", "Actual" };
		dm.setColumnIdentifiers(header);
		List<DiagnosticoDto> diagnosticos = dgm.getDiaganosticoByPacienteId(idPaciente);
		for (DiagnosticoDto diagnostico : diagnosticos) {
			data = new Vector<Object>();
			data.add(diagnostico.getDiagnostico());
			data.add(diagnostico.getFecha());
			MedicoDto medico = mm.getListaMedicosById(diagnostico.getId_medico()).get(0);
			data.add(medico.getNombre());
			data.add(diagnostico.getHora_creacion());
			data.add(diagnostico.getActual());
			dm.addRow(data);
		}

		hv.getTableDiagnosticos().setModel(dm);

		dm = new DefaultTableModel(0, 0);
		header = new String[] { "Indicaciones", "Medicamento", "Cantidad", "Intervalo", "Duracion", "Fecha" };
		dm.setColumnIdentifiers(header);
		List<PrescripcionRecord> prescripciones = prm.findByPacienteId(idPaciente);
		for (PrescripcionRecord prescripcion : prescripciones) {
			data = new Vector<Object>();
			data.add(prescripcion.getIndicaciones());
			data.add(prescripcion.getNombre());
			data.add(prescripcion.getCantidad());
			data.add(prescripcion.getIntervalo());
			data.add(prescripcion.getDuracion());
			data.add(prescripcion.getFecha());

			dm.addRow(data);
		}
		
		hv.getTablePrescriciones().setModel(dm);
		
		dm = new DefaultTableModel(0, 0);
		header = new String[] { "Vacuna", "Fecha", "Hora" };
		dm.setColumnIdentifiers(header);
		List<VacunaDto> vacunas = vm.getVacunasByPacienteId(idPaciente);
		for (VacunaDto vacuna : vacunas) {
			data = new Vector<Object>();
			data.add(vacuna.getVacuna());
			data.add(vacuna.getFecha());
			data.add(vacuna.getHora());
			
			dm.addRow(data);
		}
		
		hv.getTableVacunas().setModel(dm);

	}
}
