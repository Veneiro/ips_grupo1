package controlador;

import java.util.List;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import dtos.AsignacionMedicosDto;
import dtos.CitaDto;
import dtos.CitaPendienteDto;
import dtos.MedicoDto;
import dtos.PacienteDto;
import dtos.RegistroDto;
import modelo.CitaModelo;
import modelo.CitaPendienteModelo;
import modelo.MedicoAsignadoACitaModelo;
import modelo.MedicoModelo;
import modelo.PacienteModelo;
import modelo.RegistroModelo;
import util.SwingUtil;
import vista.AprobarCitasVistaMedico;

public class CitasModificadasMedicoControlador {

	private AprobarCitasVistaMedico acvm;
	private MedicoAsignadoACitaModelo macm = new MedicoAsignadoACitaModelo();
	private MedicoModelo mm = new MedicoModelo();
	private CitaModelo cim = new CitaModelo();
	private PacienteModelo pm = new PacienteModelo();
	private CitaPendienteModelo cpm = new CitaPendienteModelo();
	private List<CitaPendienteDto> citasPendientes;

	public CitasModificadasMedicoControlador(AprobarCitasVistaMedico acvm) {
		this.acvm = acvm;
	}

	public void initializeAprobarCitas(int idMedicoLogin) {
		initializateTable(idMedicoLogin);
		acvm.getBtnConfirmar().addActionListener(
				e -> SwingUtil.exceptionWrapper(() -> insertToDB()));
		acvm.getBtnBack().addActionListener(
				e -> SwingUtil.exceptionWrapper(() -> closeWindow()));
		acvm.getBtnHelp().addActionListener(
				e -> SwingUtil.exceptionWrapper(() -> showHelp()));
		acvm.setLocationRelativeTo(null);
		acvm.setVisible(true);
	}

	private void showHelp() {
		JOptionPane.showMessageDialog(acvm, "Seleccione una de "
				+ "las citas revisadas y modificadas por el administrador "
				+ "para comenzar:\n- Cambie el valor de la columna de aprobado "
				+ "a true si desea aceptar la modificaci?n.\n"
				+ "- En caso contrario deje el valor por defecto y clicke en "
				+ "Continuar, se rechazar?n las citas con valor false\n"
				+ "- Si solo desea salir de la ventana sin aceptar o "
				+ "rechazar clicke en el bot?n Atr?s\n"
				+ "- Si rechaza una cita modificada pero desea volver a "
				+ "proponerla con otros valores por favor creela de nuevo desde "
				+ "la opci?n de Proponer Citas");
	}

	private void closeWindow() {
		acvm.setVisible(false);
	}

	private void insertToDB() {
		for (int i = 0; i < acvm.getTable().getRowCount(); i++) {
			if (acvm.getTable().getValueAt(i, 8).equals("true")) {
				CitaDto cdto = new CitaDto();
				cdto.setId((int) acvm.getTable().getValueAt(i, 0));
				cdto.setId_paciente(getIdPaciente(
						(String) acvm.getTable().getValueAt(i, 1)));
				cdto.setId_medico(
						getIdMedico((String) acvm.getTable().getValueAt(i, 2)));
				cdto.setFecha((String) acvm.getTable().getValueAt(i, 3));
				cdto.setHorario_inicio(
						(String) acvm.getTable().getValueAt(i, 4));
				cdto.setHorario_fin((String) acvm.getTable().getValueAt(i, 5));
				cdto.setUbicacion((String) acvm.getTable().getValueAt(i, 6));
				cim.addCita(cdto);
				insertMedicosAsignadosConfirmados(cdto.getId());
				cim.updateCitaPendiente(cdto.getId());
				acvm.setVisible(false);

				RegistroModelo.addRegistro(new RegistroDto(
						"M?dico " + getMedicoByID(cdto.getId_medico())
								.getNombre(),
						"Ha modificado la cita propuesta. M?dico: "
								+ new MedicoModelo()
										.getListaMedicosById(
												cdto.getId_medico())
										.get(0).getNombre()
								+ "D?a: " + cdto.getFecha() + "Hora: "
								+ cdto.getHora_entrada() + "-"
								+ cdto.getHora_salida()));
				
			} else if (!acvm.getTable().getValueAt(i, 8).equals("true")
					&& !acvm.getTable().getValueAt(i, 8).equals("false")) {
				JOptionPane.showMessageDialog(acvm,
						"The introduced value is not correct : "
								+ "Please introduce True or False");
				
			} else if (acvm.getTable().getValueAt(i, 8).equals("false")) {
				JOptionPane.showConfirmDialog(acvm,
						"Are you sure you want to close? The appointments not "
								+ "approved will be marked as rejected and removed");
				// Borrar de la base de datos citas rechazadas por el m?dico

				// Se borrar?n todas las citas pendientes que aparezcan como
				// falso al darle al bot?n de confirmar

				// El m?dicoo tendr? que volver a proponer otra cita si no
				// estaba conforme con los cambios del admin
				CitaDto cdto = new CitaDto();
				cdto.setId((int) acvm.getTable().getValueAt(i, 0));
				macm.removeMedicos(cdto.getId());
				cim.updateCitaPendiente(cdto.getId());
				JOptionPane.showMessageDialog(acvm,
						"Citas removidas con ?xito");
			}
		}
	}

	private int getIdMedico(String nombre) {
		for (MedicoDto medico : mm.getListaMedicos()) {
			if (medico.getNombre().equals(nombre)) {
				return medico.getId();
			}
		}
		return -1;
	}

	private int getIdPaciente(String nombre) {
		for (PacienteDto paciente : pm.getListaPacientes()) {
			if (paciente.getNombre().equals(nombre)) {
				return paciente.getId();
			}
		}
		return -1;
	}

	private void insertMedicosAsignadosConfirmados(int id) {
		AsignacionMedicosDto amdto = new AsignacionMedicosDto();
		amdto.setId_cita(id);

		for (MedicoDto medico : macm.getMedicosPendientesDeCita(id)) {
			amdto.setId_medico(medico.getId());
			macm.asignarMedicosConfirmados(amdto);
		}
		macm.removeMedicos(id);
	}

	private void initializateTable(int idMedicoLogin) {
		DefaultTableModel dm = new DefaultTableModel(0, 0);
		String header[] = new String[] { "Id Cita", "Nombre Paciente",
				"Nombre M?dico", "Fecha", "Hora Inicio", "Hora fin",
				"Ubicaci?n", "Contacto M?dico", "Aprobada" };
		dm.setColumnIdentifiers(header);

		citasPendientes = cpm.getCitasPorAprobar();
		for (CitaPendienteDto c : citasPendientes) {
			if (c.getESTADO().equals("MedicoReview")
					&& c.getID_MEDICO() == idMedicoLogin) {
				PacienteDto p = pm.getPacienteById(c.getID_PACIENTE()).get(0);
				Vector<Object> data = new Vector<Object>();
				data.add(c.getID());
				data.add(p.getNombre());
				data.add(getMedicoByID(c.getID_MEDICO()).getNombre());
				data.add(c.getFECHA());
				data.add(c.getHORA_ENTRADA());
				data.add(c.getHORA_SALIDA());
				data.add(c.getUBICACION());
				data.add(c.getCONTACTO_MEDICO());
				data.add("false");
				dm.addRow(data);
			}
		}

		acvm.getTable().setModel(dm);
	}

	private MedicoDto getMedicoByID(int id) {
		for (MedicoDto medico : mm.getListaMedicos()) {
			if (medico.getId() == id) {
				return medico;
			}
		}
		return null;
	}
}
