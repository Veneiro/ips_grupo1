package vista;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controlador.AdministradorControlador;
import dtos.MedicoDto;
import dtos.PacienteDto;
import logic.Admin;
import logic.Cita;
import logic.CrearCitas;

/**
 * @author Santiago
 *
 */
public class CitaVista extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	private Admin admin;
	private CrearCitas creadorCitas;
	private JComboBox<PacienteDto> cbPacientes;
	private JLabel lblPaciente;
	private JScrollPane sPMedicos;
	private JList<MedicoDto> listMedicos;
	private JLabel lblMedicos;
	private JButton btnAñadir;
	private JButton btnQuitar;
	private JTextArea txtAreaMedicosElegidos;
	private JLabel lblMdicosElegidos;
	private JButton btnConfirmar;
	private DefaultListModel<MedicoDto> modeloListMedicos;
	private JTextField txtFiltro;
	private JLabel lblFiltro;
	private JButton btnFiltrar;
	private JButton btnDesfiltrar;
	private JTextField txtContacto;
	private JLabel lblInfoContacto;
	private JTextField txtUbicacion;
	private JLabel lblUbic;
	private JLabel lblInicio;
	private JLabel lblFin;
	private JTextField txtHoraInicio;
	private JTextField txtHoraFin;
	private JCheckBox chckbxUrgente;
	private AdministradorControlador ac = new AdministradorControlador();

	/**
	 * Create the frame.
	 */
	public CitaVista(Admin admin, CrearCitas creadorCitas) {
		this.admin = admin;
		this.creadorCitas = creadorCitas;
		setTitle("iHospital : Crear Cita");
		setResizable(false);
		setModal(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(100, 100, 606, 441);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getCbPacientes());
		contentPane.add(getLblPaciente());
		contentPane.add(getSPMedicos());
		contentPane.add(getLblMedicos());
		contentPane.add(getBtnAñadir());
		contentPane.add(getBtnQuitar());
		contentPane.add(getTxtAreaMedicosElegidos());
		contentPane.add(getLblMdicosElegidos());
		contentPane.add(getBtnConfirmar());
		contentPane.add(getTxtFiltro());
		contentPane.add(getLblFiltro());
		contentPane.add(getBtnFiltrar());
		contentPane.add(getBtnDesfiltrar());
		contentPane.add(getTxtContacto());
		contentPane.add(getLblInfoContacto());
		contentPane.add(getTxtUbicacion());
		contentPane.add(getLblUbic());
		contentPane.add(getLblInicio());
		contentPane.add(getLblFin());
		contentPane.add(getTxtHoraInicio());
		contentPane.add(getTxtHoraFin());
		contentPane.add(getChckbxUrgente());
	}
	
	public void visible(boolean visible) {
		setVisible(visible);
	}

	private JComboBox<PacienteDto> getCbPacientes() {
		if (cbPacientes == null) {
			cbPacientes = new JComboBox<PacienteDto>();
			cbPacientes.setBounds(12, 49, 167, 22);
			cbPacientes.setModel(new DefaultComboBoxModel<PacienteDto>(admin.getListaPacientes()));
		}
		return cbPacientes;
	}

	private JLabel getLblPaciente() {
		if (lblPaciente == null) {
			lblPaciente = new JLabel("Paciente");
			lblPaciente.setDisplayedMnemonic('P');
			lblPaciente.setLabelFor(getCbPacientes());
			lblPaciente.setBounds(12, 13, 56, 16);
		}
		return lblPaciente;
	}

	private JScrollPane getSPMedicos() {
		if (sPMedicos == null) {
			sPMedicos = new JScrollPane();
			sPMedicos.setBounds(229, 50, 124, 180);
			sPMedicos.setViewportView(getListMedicos());
		}
		return sPMedicos;
	}

	private JList<MedicoDto> getListMedicos() {
		if (listMedicos == null) {
			modeloListMedicos = new DefaultListModel<MedicoDto>();
			for (MedicoDto medico : admin.getListaMedicos()) {
				modeloListMedicos.addElement(medico);
			}
			listMedicos = new JList<MedicoDto>(modeloListMedicos);
		}
		return listMedicos;
	}

	private JLabel getLblMedicos() {
		if (lblMedicos == null) {
			lblMedicos = new JLabel("M\u00E9dicos");
			lblMedicos.setLabelFor(getListMedicos());
			lblMedicos.setDisplayedMnemonic('M');
			lblMedicos.setBounds(229, 13, 56, 16);
		}
		return lblMedicos;
	}

	private JButton getBtnAñadir() {
		if (btnAñadir == null) {
			btnAñadir = new JButton("A\u00F1adir");
			btnAñadir.setMnemonic('A');
			btnAñadir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					seleccionarMedicosParaCita();
				}
			});
			btnAñadir.setBounds(367, 48, 95, 25);
		}
		return btnAñadir;
	}

	private void seleccionarMedicosParaCita() {
		List<MedicoDto> medicosSeleccionados = getListMedicos().getSelectedValuesList();
		creadorCitas.seleccionarMedicos(medicosSeleccionados);
		actualizarTxtAreaMedicos();
		if (creadorCitas.hayMedicosElegidos()) {
			getBtnConfirmar().setEnabled(true);
		}
	}

	private JButton getBtnQuitar() {
		if (btnQuitar == null) {
			btnQuitar = new JButton("Quitar");
			btnQuitar.setMnemonic('Q');
			btnQuitar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					deseleccionarMedicosParaCita();
				}
			});
			btnQuitar.setBounds(480, 50, 97, 25);
		}
		return btnQuitar;
	}

	private void deseleccionarMedicosParaCita() {
		List<MedicoDto> medicosSeleccionados = getListMedicos().getSelectedValuesList();
		creadorCitas.deseleccionarMedicos(medicosSeleccionados);
		actualizarTxtAreaMedicos();
		if (!creadorCitas.hayMedicosElegidos()) {
			getBtnConfirmar().setEnabled(false);
		}
	}

	private void actualizarTxtAreaMedicos() {
		getTxtAreaMedicosElegidos().setText(creadorCitas.getStringMedicosElegidos());
	}

	private JTextArea getTxtAreaMedicosElegidos() {
		if (txtAreaMedicosElegidos == null) {
			txtAreaMedicosElegidos = new JTextArea();
			txtAreaMedicosElegidos.setEditable(false);
			txtAreaMedicosElegidos.setBounds(12, 157, 189, 128);
		}
		return txtAreaMedicosElegidos;
	}

	private JLabel getLblMdicosElegidos() {
		if (lblMdicosElegidos == null) {
			lblMdicosElegidos = new JLabel("M\u00E9dicos elegidos");
			lblMdicosElegidos.setBounds(12, 117, 124, 16);
		}
		return lblMdicosElegidos;
	}

	private JButton getBtnConfirmar() {
		JDialog j = this;
		if (btnConfirmar == null) {
			btnConfirmar = new JButton("Confirmar");
			btnConfirmar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					crearCita();
					j.setVisible(false);
				}

			});
			btnConfirmar.setFont(new Font("Tahoma", Font.PLAIN, 16));
			btnConfirmar.setEnabled(false);
			btnConfirmar.setMnemonic('C');
			btnConfirmar.setBounds(450, 316, 108, 36);
		}
		return btnConfirmar;
	}

	/**
	 * Crea una cita según los datos introducidos. Si se ha escrito en los 2 campos
	 * del horario, se considera que se quería establecer uno y se comprueba el
	 * formato. Antes de crear la cita comprueba que no colisione su horario con los
	 * de los médicos, pero permitirá crearla igual después de un aviso.
	 */
	private void crearCita() {
		LocalTime horarioInicio = null;
		LocalTime horarioFin = null;

		
		//TODO  Cambiarlo a un spinner con formato hora minuto
		if (!getTxtHoraInicio().getText().trim().isEmpty() && !getTxtHoraFin().getText().trim().isEmpty()) {
			try {
				horarioInicio = LocalTime.parse(getTxtHoraInicio().getText());
				horarioFin = LocalTime.parse(getTxtHoraFin().getText());
				long diff = ChronoUnit.MINUTES.between(horarioInicio, horarioFin);
				if (diff < 0) {
					JOptionPane.showMessageDialog(this, "La hora de fin no puede ir antes que la hora de inicio");
					return;
				}
			} catch (DateTimeParseException e) {
				JOptionPane.showMessageDialog(this, "Horas de inicio o fin incorrectas");
				return;
			}

			if (creadorCitas.hayColisionMismoHorario(horarioInicio, horarioFin)) {
				int r = JOptionPane.showConfirmDialog(this,
						"Hay médicos con citas en el mismo horario. ¿Proseguir igualmente?");
				if (r != JOptionPane.YES_OPTION)
					return;
			}
		}

		String ubicacion = cadenaVaciaANull(getTxtUbicacion().getText());
		String infoContacto = cadenaVaciaANull(getTxtContacto().getText());
		
		PacienteDto p = (PacienteDto) getCbPacientes().getSelectedItem();
		Cita cita = creadorCitas.crearCita(p, infoContacto, ubicacion, horarioInicio, horarioFin);
		
		if(getChckbxUrgente().isEnabled()) {
			ac.avisoUrgente(cita);
		}
	}

	private String cadenaVaciaANull(String ubicacion) {
		if (ubicacion.trim().isEmpty())
			return null;
		return ubicacion;
	}

	private JTextField getTxtFiltro() {
		if (txtFiltro == null) {
			txtFiltro = new JTextField();
			txtFiltro.setBounds(365, 134, 97, 22);
			txtFiltro.setColumns(10);
		}
		return txtFiltro;
	}

	private JLabel getLblFiltro() {
		if (lblFiltro == null) {
			lblFiltro = new JLabel("Filtrar m\u00E9dicos");
			lblFiltro.setDisplayedMnemonic('i');
			lblFiltro.setLabelFor(getTxtFiltro());
			lblFiltro.setBounds(365, 100, 97, 16);
		}
		return lblFiltro;
	}

	private JButton getBtnFiltrar() {
		if (btnFiltrar == null) {
			btnFiltrar = new JButton("Filtrar");
			btnFiltrar.setMnemonic('F');
			btnFiltrar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					filtrarMedicos(getTxtFiltro().getText().trim().toLowerCase());
				}
			});
			btnFiltrar.setBounds(480, 133, 97, 25);
		}
		return btnFiltrar;
	}

	private void filtrarMedicos(String filtro) {
		modeloListMedicos.clear();
		for (MedicoDto m : admin.filtrarMedicos(filtro)) {
			modeloListMedicos.addElement(m);
		}
		getBtnDesfiltrar().setEnabled(true);
	}

	private void desFiltrar() {
		modeloListMedicos.clear();
		for (MedicoDto m : admin.getListaMedicos()) {
			modeloListMedicos.addElement(m);
		}
		getBtnDesfiltrar().setEnabled(false);
	}

	private JButton getBtnDesfiltrar() {
		if (btnDesfiltrar == null) {
			btnDesfiltrar = new JButton("Desfiltrar");
			btnDesfiltrar.setMnemonic('D');
			btnDesfiltrar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					desFiltrar();
				}
			});
			btnDesfiltrar.setEnabled(false);
			btnDesfiltrar.setBounds(480, 171, 97, 25);
		}
		return btnDesfiltrar;
	}

	private JTextField getTxtContacto() {
		if (txtContacto == null) {
			txtContacto = new JTextField();
			txtContacto.setBounds(436, 236, 116, 22);
			txtContacto.setColumns(10);
		}
		return txtContacto;
	}

	private JLabel getLblInfoContacto() {
		if (lblInfoContacto == null) {
			lblInfoContacto = new JLabel("Info. contacto:");
			lblInfoContacto.setDisplayedMnemonic('I');
			lblInfoContacto.setLabelFor(getTxtContacto());
			lblInfoContacto.setBounds(436, 218, 95, 16);
		}
		return lblInfoContacto;
	}

	private JTextField getTxtUbicacion() {
		if (txtUbicacion == null) {
			txtUbicacion = new JTextField();
			txtUbicacion.setBounds(312, 236, 116, 22);
			txtUbicacion.setColumns(10);
		}
		return txtUbicacion;
	}

	private JLabel getLblUbic() {
		if (lblUbic == null) {
			lblUbic = new JLabel("Ubicaci\u00F3n:");
			lblUbic.setDisplayedMnemonic('U');
			lblUbic.setLabelFor(getTxtUbicacion());
			lblUbic.setBounds(229, 242, 71, 16);
		}
		return lblUbic;
	}

	private JLabel getLblInicio() {
		if (lblInicio == null) {
			lblInicio = new JLabel("Hora inicio (HH/mm):");
			lblInicio.setDisplayedMnemonic('H');
			lblInicio.setLabelFor(getTxtHoraInicio());
			lblInicio.setBounds(23, 316, 124, 14);
		}
		return lblInicio;
	}

	private JLabel getLblFin() {
		if (lblFin == null) {
			lblFin = new JLabel("Hora fin (HH/mm):");
			lblFin.setDisplayedMnemonic('O');
			lblFin.setLabelFor(lblFin);
			lblFin.setBounds(23, 355, 113, 14);
		}
		return lblFin;
	}

	private JTextField getTxtHoraInicio() {
		if (txtHoraInicio == null) {
			txtHoraInicio = new JTextField();
			txtHoraInicio.setBounds(188, 316, 86, 20);
			txtHoraInicio.setColumns(10);
		}
		return txtHoraInicio;
	}

	private JTextField getTxtHoraFin() {
		if (txtHoraFin == null) {
			txtHoraFin = new JTextField();
			txtHoraFin.setBounds(188, 352, 86, 20);
			txtHoraFin.setColumns(10);
		}
		return txtHoraFin;
	}
	private JCheckBox getChckbxUrgente() {
		if (chckbxUrgente == null) {
			chckbxUrgente = new JCheckBox("CITA URGENTE");
			chckbxUrgente.setHorizontalAlignment(SwingConstants.CENTER);
			chckbxUrgente.setBounds(312, 325, 116, 23);
		}
		return chckbxUrgente;
	}
}
