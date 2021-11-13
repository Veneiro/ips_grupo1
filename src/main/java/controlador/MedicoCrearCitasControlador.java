package controlador;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalField;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.swing.DefaultComboBoxModel;

import dtos.CitaPendienteDto;
import dtos.MedicoDto;
import dtos.PacienteDto;
import logic.Admin;
import modelo.CitaPendienteModelo;
import modelo.MedicoModelo;
import modelo.PacienteModelo;
import util.SwingUtil;
import vista.CrearCitaMedicoVista;

public class MedicoCrearCitasControlador {
	private CrearCitaMedicoVista ccmv;
	private int idMedico;
	private Admin admin = new Admin();
	private CitaPendienteModelo cm = new CitaPendienteModelo();
	private List<PacienteDto> pdto = new ArrayList<PacienteDto>();
	private MedicoModelo mm = new MedicoModelo();
	private PacienteModelo pm = new PacienteModelo();
	private LocalDateTime ldt = LocalDateTime.now();

	public MedicoCrearCitasControlador(CrearCitaMedicoVista ccmv) {
		this.ccmv = ccmv;
	}

	public void initializate(int idMedico) {
		this.idMedico = idMedico;

		ccmv.getBtnEnviar().addActionListener(
				e -> SwingUtil.exceptionWrapper(() -> insertToDB()));
		ccmv.getBtnCancelar().addActionListener(
				e -> SwingUtil.exceptionWrapper(() -> ccmv.setVisible(false)));
		ccmv.getCbPacientes().setModel(new DefaultComboBoxModel<PacienteDto>(
				admin.getListaPacientes()));

		Date d = new Date();
		ccmv.getDateChooser().setDate(d);

		ccmv.getSpEntryHour().setValue(ldt.getHour());
		ccmv.getSpEntryMin().setValue(ldt.getMinute());
		ccmv.getSpOutHour().setValue(ldt.getHour() + 1);
		ccmv.getSpOutMin().setValue(ldt.getMinute());

		ccmv.setLocationRelativeTo(null);
		ccmv.setVisible(true);
	}

	private void insertToDB() {
		CitaPendienteDto cidto = new CitaPendienteDto();
		String houre = String.format("%02d", ccmv.getSpEntryHour().getValue());
		String mine = String.format("%02d", ccmv.getSpEntryMin().getValue());
		String houro = String.format("%02d", ccmv.getSpOutHour().getValue());
		String mineo = String.format("%02d", ccmv.getSpOutMin().getValue());

		cidto.setHORA_ENTRADA(houre + " : " + mine);
		cidto.setHORA_SALIDA(houro + " : " + mineo);
		cidto.setID(valorAbsoluto(new Random().nextInt()));
		cidto.setID_MEDICO(idMedico);
		for (MedicoDto medico : mm.getListaMedicos()) {
			if (medico.getId() == idMedico) {
				cidto.setNOMBRE_MEDICO(medico.getNombre());
				cidto.setCONTACTO_MEDICO(medico.getEmail());
			}
		}
		cidto.setUBICACION(ccmv.getTxtUbicacion().getText());
		for (PacienteDto paciente : pm.getListaPacientes()) {
			if (paciente.getNombre().equals(
					ccmv.getCbPacientes().getSelectedItem().toString())) {
				cidto.setIDPACIENTE(paciente.getId());
			}
		}
		cidto.setNOMBRE_PACIENTE(
				ccmv.getCbPacientes().getSelectedItem().toString());
		Date citaFecha = ccmv.getDateChooser().getDate();
		LocalDate fecha = citaFecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		cidto.setFECHA(fecha.get(ChronoField.DAY_OF_MONTH) + "-" + fecha.get(ChronoField.MONTH_OF_YEAR) + "-"
				+ fecha.get(ChronoField.YEAR));

		cm.insertCita(cidto);
		ccmv.setVisible(false);
	}

	private int valorAbsoluto(int x) {
		return x > 0 ? x : -x;
	}

}
