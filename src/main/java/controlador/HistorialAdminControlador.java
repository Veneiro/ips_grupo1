package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import dtos.DiagnosticoDto;
import dtos.HistorialDto;
import dtos.MedicoDto;
import dtos.PacienteDto;
import dtos.RegistroDto;
import modelo.DiagnosticoModelo;
import modelo.HistorialModelo;
import modelo.MedicoModelo;
import modelo.PacienteModelo;
import modelo.PrescripcionesModelo;
import modelo.RegistroModelo;
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

    public HistorialAdminControlador(HistorialModelo hm, HistorialesAdminVista hv, int idPaciente) {
	this.hm = hm;
	this.hv = hv;
	this.idPaciente = idPaciente;
	this.pm = new PacienteModelo();
	this.dgm = new DiagnosticoModelo();
	this.prm = new PrescripcionesModelo();
	this.mm = new MedicoModelo();

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
	DefaultTableModel dm = new DefaultTableModel(0, 0);
	String header[] = new String[] { "Nombre", "Vacunas", "Antecedentes", "Informacion Adicional" };
	dm.setColumnIdentifiers(header);

	Vector<Object> data = new Vector<Object>();
	data.add(paciente.get(0).getNombre());
	data.add(historial.get(0).getVacunas());
	data.add(historial.get(0).getAntecedentes());
	data.add(historial.get(0).getInformacionAdicional());
	dm.addRow(data);

	hv.getTable().setModel(dm);

	dm = new DefaultTableModel(0, 0);
	header = new String[] { "Diagnostico", "Fecha", "Medico" };
	dm.setColumnIdentifiers(header);
	List<DiagnosticoDto> diagnosticos = dgm.getDiaganosticoByPacienteId(idPaciente);
	for (DiagnosticoDto diagnostico : diagnosticos) {
	    data = new Vector<Object>();

	    data.add(diagnostico.getDiagnostico());
	    data.add(diagnostico.getFecha());
	    MedicoDto medico = mm.getListaMedicosById(diagnostico.getId_medico()).get(0);
	    data.add(medico.getNombre());
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

    }
}
