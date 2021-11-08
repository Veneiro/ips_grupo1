package controlador;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.DefaultComboBoxModel;

import dtos.CitaPendienteDto;
import dtos.MedicoDto;
import dtos.PacienteDto;
import logic.Admin;
import modelo.CitaPendienteModelo;
import modelo.MedicoModelo;
import util.SwingUtil;
import vista.CrearCitaMedicoVista;

public class MedicoCrearCitasControlador {
	private CrearCitaMedicoVista ccmv;
	private int idMedico;
	private Admin admin = new Admin();
	private CitaPendienteModelo cm = new CitaPendienteModelo();
	private List<PacienteDto> pdto = new ArrayList<PacienteDto>();
	private MedicoModelo mm = new MedicoModelo();
	

	public MedicoCrearCitasControlador(CrearCitaMedicoVista ccmv) {
		this.ccmv = ccmv;
	}

	public void initializate(int idMedico) {
		this.idMedico = idMedico;
		
		ccmv.getBtnEnviar().addActionListener(e -> SwingUtil
				.exceptionWrapper(() -> insertToDB()));
		ccmv.getBtnCancelar().addActionListener(e -> SwingUtil
				.exceptionWrapper(() -> ccmv.setVisible(false)));
		ccmv.getCbPacientes().setModel(new DefaultComboBoxModel<PacienteDto>(admin.getListaPacientes()));
		ccmv.setLocationRelativeTo(null);
		ccmv.setVisible(true);
	}

	private void insertToDB() {
		CitaPendienteDto cidto = new CitaPendienteDto();
		String houre = ccmv.getSpEntryHour().getValue().toString();
		String mine = ccmv.getSpEntryMin().getValue().toString();
		String houro = ccmv.getSpOutHour().getValue().toString();
		String mineo = ccmv.getSpOutMin().getValue().toString();

		cidto.setHORA_ENTRADA(houre + " : " + mine);
		cidto.setHORA_SALIDA(houro + " : " + mineo);
		cidto.setID(valorAbsoluto(new Random().nextInt()));
		cidto.setID_MEDICO(idMedico);
		for (MedicoDto medico : mm.getListaMedicos()) {
			if(medico.getId() == idMedico) {
				cidto.setNOMBRE_MEDICO(medico.getNombre());
			}
		}
		for (PacienteDto paciente : pdto) {
			if(paciente.getNombre().equals(ccmv.getCbPacientes().getSelectedItem().toString())) {
				cidto.setIDPACIENTE(paciente.getId());
			}
		}
		cidto.setNOMBRE_PACIENTE(ccmv.getCbPacientes().getSelectedItem().toString());
		cm.insertCita(cidto);
		ccmv.setVisible(false);
	}
	
	private int valorAbsoluto(int x) {
		return x > 0 ? x : -x;
	}
	
}
