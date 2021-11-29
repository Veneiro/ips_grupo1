package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import dtos.CitaDto;
import dtos.PacienteDto;
import dtos.RegistroDto;
import modelo.CitaModelo;
import modelo.PacienteModelo;
import modelo.RegistroModelo;
import util.SwingUtil;
import vista.AppointmentView;
import vista.AprobarCitasVistaMedico;
import vista.ListaCalendarioCitasVista;
import vista.MenuMedicoVista;

public class ListaCalendarioCitasControlador {

	private ListaCalendarioCitasVista lccv;
	private CitaModelo cm;
	private PacienteModelo pm;
	private int idMedico;
	List<CitaDto> citas;
	PacienteControlador pc = new PacienteControlador(new PacienteModelo(),
			new AppointmentView());
	PacienteDto p = new PacienteDto();
	private MedicoCrearCitasControlador mccc;

	public ListaCalendarioCitasControlador(ListaCalendarioCitasVista lccv,
			CitaModelo cm, PacienteModelo pm, int idMedico,
			MedicoCrearCitasControlador medicoCrearCitasControlador) {
		this.lccv = lccv;
		this.cm = cm;
		this.pm = pm;
		this.idMedico = idMedico;
		this.mccc = medicoCrearCitasControlador;

		inicializarVistaListaCalendarioCitas();
	}

	public void inicializarVistaListaCalendarioCitas() {
		RegistroModelo.addRegistro(new RegistroDto("Médico " + idMedico,
				"Consulta calendario citas"));
	}

	public void inicializar() {

		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		Date date = new Date(System.currentTimeMillis());
		cargarCalendarioCitas(formatter.format(date), idMedico);
		//primeraCargaCalendario(idMedico);

		lccv.getBtnBuscar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String fecha = lccv.getTextFieldFecha().getText();
				cargarCalendarioCitas(fecha, idMedico);

			}
		});
		lccv.getBtnGestionarCita()
				.addActionListener(e -> SwingUtil.exceptionWrapper(() -> pc
						.initialize(citas.get(lccv.getTable().getSelectedRow()),
								p.getNombre())));
		lccv.setVisible(true);
	}
	
	public void citasMod(MenuMedicoVista mmv) {
		mmv.getBtnCitasModificadas().addActionListener(e -> SwingUtil
				.exceptionWrapper(() -> loadCitasMod()));
	}
	
	public void actionProponer(MenuMedicoVista mmv) {
		mmv.getBtnProponer().addActionListener(
				e -> SwingUtil.exceptionWrapper(() -> mccc.initializate(idMedico)));
	}

	private void loadCitasMod() {
		CitasModificadasMedicoControlador cmmc = new CitasModificadasMedicoControlador(
				new AprobarCitasVistaMedico());
		cmmc.initializeAprobarCitas(idMedico);
	}

	private void cargarCalendarioCitas(String fecha, int idMedico) {

		citas = cm.getCitasFecha(fecha, idMedico);
		DefaultTableModel dm = new DefaultTableModel(0, 0);
		String header[] = new String[] { "Id Cita", "Nombre", "Fecha",
				"Hora Inicio", "Hora fin", "Informacion", "Acudio",
				"Hora Entrada", "Hora Salida" };
		dm.setColumnIdentifiers(header);

		for (CitaDto c : citas) {
			PacienteDto p = pm.getPacienteById(c.getId_paciente()).get(0);
			Vector<Object> data = new Vector<Object>();
			data.add(c.getId());
			data.add(p.getNombre());
			this.p.setNombre(p.getNombre());
			data.add(c.getFecha());
			data.add(c.getHorario_inicio());
			data.add(c.getHorario_fin());
			data.add(c.getInformacion());
			data.add(c.getAcudio());
			data.add(c.getHora_entrada());
			data.add(c.getHora_salida());
			dm.addRow(data);
		}
		lccv.getTable().setModel(dm);
	}

	private void primeraCargaCalendario(int idMedico) {

		citas = cm.getCitasMedico(idMedico);
		DefaultTableModel dm = new DefaultTableModel(0, 0);
		String header[] = new String[] { "Id Cita", "Nombre", "Fecha",
				"Hora Inicio", "Hora fin", "Informacion", "Acudio",
				"Hora Entrada", "Hora Salida" };
		dm.setColumnIdentifiers(header);

		for (CitaDto c : citas) {
			PacienteDto p = pm.getPacienteById(c.getId_paciente()).get(0);
			Vector<Object> data = new Vector<Object>();
			data.add(c.getId());
			data.add(p.getNombre());
			this.p.setNombre(p.getNombre());
			data.add(c.getFecha());
			data.add(c.getHorario_inicio());
			data.add(c.getHorario_fin());
			data.add(c.getInformacion());
			data.add(c.getAcudio());
			data.add(c.getHora_entrada());
			data.add(c.getHora_salida());
			dm.addRow(data);
		}
		lccv.getTable().setModel(dm);
	}

}
