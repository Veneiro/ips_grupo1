package controlador;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

import dtos.AsignacionMedicosDto;
import dtos.CitaPendienteDto;
import dtos.MedicoDto;
import dtos.PacienteDto;
import logic.Admin;
import modelo.CitaPendienteModelo;
import modelo.MedicoAsignadoACitaModelo;
import modelo.MedicoModelo;
import modelo.PacienteModelo;
import util.SwingUtil;
import vista.CrearCitaMedicoVista;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class MedicoCrearCitasControlador {
	private CrearCitaMedicoVista ccmv;
	private int idMedico;
	private Admin admin = new Admin();
	private CitaPendienteModelo cm = new CitaPendienteModelo();
	private MedicoModelo mm = new MedicoModelo();
	private PacienteModelo pm = new PacienteModelo();
	private LocalDateTime ldt = LocalDateTime.now();
	private DefaultListModel modelMedicos = new DefaultListModel();
	private MedicoAsignadoACitaModelo mam = new MedicoAsignadoACitaModelo();

	public MedicoCrearCitasControlador(CrearCitaMedicoVista ccmv) {
		this.ccmv = ccmv;
	}

	public void initializate(int idMedico) {
		this.idMedico = idMedico;

		ccmv.getBtnEnviar().addActionListener(
				e -> SwingUtil.exceptionWrapper(() -> insertToDB()));
		ccmv.getBtnCancelar().addActionListener(
				e -> SwingUtil.exceptionWrapper(() -> ccmv.setVisible(false)));
		ccmv.getBtnAdd().addActionListener(
				e -> SwingUtil.exceptionWrapper(() -> addMedicToList()));
		ccmv.getListMedicos().setModel(modelMedicos);
		ccmv.getCbPacientes().setModel(new DefaultComboBoxModel<PacienteDto>(
				admin.getListaPacientes()));
		DefaultComboBoxModel dlm = new DefaultComboBoxModel();
		for (MedicoDto medico : mm.getListaMedicos()) {
			if (medico.getId() != idMedico) {
				dlm.addElement(medico);
			}
		}
		ccmv.getCbMedicos().setModel(dlm);

		Date d = new Date();
		ccmv.getDateChooser().setDate(d);

		ccmv.getSpEntryHour().setValue(ldt.getHour());
		ccmv.getSpEntryMin().setValue(ldt.getMinute());
		ccmv.getSpOutHour().setValue(ldt.getHour() + 1);
		ccmv.getSpOutMin().setValue(ldt.getMinute());

		ccmv.setLocationRelativeTo(null);
		ccmv.setVisible(true);
	}

	private void addMedicToList() {
		MedicoDto medico = ((MedicoDto) ccmv.getCbMedicos().getSelectedItem());
		if (!isInList(medico)) {
			modelMedicos.addElement(medico);
		} else {
			JOptionPane.showMessageDialog(ccmv,
					"El médico seleccionado ya está asignado a esta cita");
		}
	}

	private boolean isInList(MedicoDto medico) {
		for (int i = 0; i < ccmv.getListMedicos().getModel().getSize(); i++) {
			if (ccmv.getListMedicos().getModel().getElementAt(i)
					.equals(medico)) {
				return true;
			}
		}
		return false;
	}

	private void insertToDB() {
		// CITA PENDIENTE CREACIÓN E INSERCIÓN EN BASE DE DATOS
		CitaPendienteDto cidto = new CitaPendienteDto();
		String houre = String.format("%02d", ccmv.getSpEntryHour().getValue());
		String mine = String.format("%02d", ccmv.getSpEntryMin().getValue());
		String houro = String.format("%02d", ccmv.getSpOutHour().getValue());
		String mineo = String.format("%02d", ccmv.getSpOutMin().getValue());

		cidto.setHORA_ENTRADA(houre + " : " + mine);
		cidto.setHORA_SALIDA(houro + " : " + mineo);
		Integer id = valorAbsoluto(new Random().nextInt());
		cidto.setID(id);
		cidto.setID_MEDICO(idMedico);

		for (MedicoDto medico : mm.getListaMedicos()) {
			if (medico.getId() == idMedico) {
				cidto.setCONTACTO_MEDICO(medico.getEmail());
			}
		}

		cidto.setUBICACION(ccmv.getTxtUbicacion().getText());

		for (PacienteDto paciente : pm.getListaPacientes()) {
			if (paciente.getNombre().equals(
					ccmv.getCbPacientes().getSelectedItem().toString())) {
				cidto.setID_PACIENTE(paciente.getId());
			}
		}

		Date citaFecha = ccmv.getDateChooser().getDate();
		LocalDate fecha = citaFecha.toInstant().atZone(ZoneId.systemDefault())
				.toLocalDate();
		cidto.setFECHA(fecha.get(ChronoField.DAY_OF_MONTH) + "-"
				+ fecha.get(ChronoField.MONTH_OF_YEAR) + "-"
				+ fecha.get(ChronoField.YEAR));
		cidto.setESTADO("AdminReview");
		cm.insertCita(cidto);

		// ASGINACIÓN DE MEDICOS PROVISIONAL, CREACIÓN DE DE DTO E INSERCIÓN
		// EN BASE DE DATOS
		AsignacionMedicosDto amdto = new AsignacionMedicosDto();
		List<MedicoDto> medicosPendiente = new ArrayList<MedicoDto>();
		amdto.setId_cita(id);
		
		for (int i = 0; i < ccmv.getListMedicos().getModel().getSize(); i++) {
			medicosPendiente.add((MedicoDto) ccmv.getListMedicos().getModel()
					.getElementAt(i));
		}
		for (MedicoDto medico : medicosPendiente) {
			amdto.setId_medico(medico.getId());
			mam.asignarMedicosPendientes(amdto);
		}
		for (MedicoDto medico : mm.getListaMedicos()) {
			if(cidto.getID_MEDICO() == medico.getId()) {
				amdto.setId_medico(medico.getId());
				mam.asignarMedicosPendientes(amdto);
			}
		}
		ccmv.setVisible(false);
	}

	private int valorAbsoluto(int x) {
		return x > 0 ? x : -x;
	}

}
