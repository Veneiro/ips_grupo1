package vista;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
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
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controlador.AdministradorControlador;
import dtos.CitaDto;
import dtos.MedicoDto;
import dtos.PacienteDto;
import logic.Admin;
import logic.CrearCitas;
import util.ApplicationException;
import javax.swing.SpinnerNumberModel;

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
	private JButton btnA?adir;
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
	private JSpinner spHoraEntrada;
	private JSpinner spHoraSalida;
	private JSpinner spDia;
	private JLabel lblDia;
	private JCheckBox chBoxDefinirHorario;
	private JComboBox<String> cbEspecialidades;
	private JLabel lblEspecialidades;
	private JCheckBox chckbxEspecialidad;
	private JCheckBox chckbxUrgente;
	private AdministradorControlador ac = new AdministradorControlador();
	LocalDateTime ldt = LocalDateTime.now();
	private JCheckBox chckbxFiltroNombre;
	private JCheckBox chckbxFiltroCita;
	private JCheckBox chckbxFiltroJornada;
	private JButton btnBuscarHorario;
	private JSpinner spDuracion;
	private JLabel lblDuracion;
	private boolean horarioEncontrado;

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
		setBounds(100, 100, 930, 414);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getCbPacientes());
		contentPane.add(getLblPaciente());
		contentPane.add(getSPMedicos());
		contentPane.add(getLblMedicos());
		contentPane.add(getBtnA?adir());
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
		contentPane.add(getSpHoraEntrada());
		contentPane.add(getSpHoraSalida());
		contentPane.add(getSpDia());
		contentPane.add(getLblDia());
		contentPane.add(getChBoxDefinirHorario());
		contentPane.add(getCbEspecialidades());
		contentPane.add(getLblEspecialidades());
		contentPane.add(getChckbxEspecialidad());
		contentPane.add(getChckbxUrgente());
		contentPane.add(getChckbxFiltroNombre());
		contentPane.add(getChckbxFiltroCita());
		contentPane.add(getChckbxFiltroJornada());
		contentPane.add(getBtnBuscarHorario());
		contentPane.add(getSpDuracion());
		contentPane.add(getLblDuracion());
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
			sPMedicos.setBounds(438, 51, 124, 180);
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
			lblMedicos = new JLabel("M\u00E9dicos disponibles");
			lblMedicos.setLabelFor(getListMedicos());
			lblMedicos.setDisplayedMnemonic('M');
			lblMedicos.setBounds(438, 13, 108, 16);
		}
		return lblMedicos;
	}

	private JButton getBtnA?adir() {
		if (btnA?adir == null) {
			btnA?adir = new JButton("A\u00F1adir");
			btnA?adir.setMnemonic('A');
			btnA?adir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					seleccionarMedicosParaCita();
				}
			});
			btnA?adir.setBounds(572, 49, 95, 25);
		}
		return btnA?adir;
	}

	private void seleccionarMedicosParaCita() {
		List<MedicoDto> medicosSeleccionados = getListMedicos().getSelectedValuesList();
		creadorCitas.seleccionarMedicos(medicosSeleccionados);
		actualizarTxtAreaMedicos();
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
			btnQuitar.setBounds(696, 49, 97, 25);
		}
		return btnQuitar;
	}

	private void deseleccionarMedicosParaCita() {
		List<MedicoDto> medicosSeleccionados = getListMedicos().getSelectedValuesList();
		creadorCitas.deseleccionarMedicos(medicosSeleccionados);
		actualizarTxtAreaMedicos();
	}

	private void actualizarTxtAreaMedicos() {
		getTxtAreaMedicosElegidos().setText(creadorCitas.getStringMedicosElegidos());
	}

	private JTextArea getTxtAreaMedicosElegidos() {
		if (txtAreaMedicosElegidos == null) {
			txtAreaMedicosElegidos = new JTextArea();
			txtAreaMedicosElegidos.setEditable(false);
			txtAreaMedicosElegidos.setBounds(228, 49, 189, 221);
		}
		return txtAreaMedicosElegidos;
	}

	private JLabel getLblMdicosElegidos() {
		if (lblMdicosElegidos == null) {
			lblMdicosElegidos = new JLabel("M\u00E9dicos elegidos");
			lblMdicosElegidos.setBounds(232, 13, 124, 16);
		}
		return lblMdicosElegidos;
	}

	private JButton getBtnConfirmar() {
		if (btnConfirmar == null) {
			btnConfirmar = new JButton("Confirmar");
			btnConfirmar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					crearCita();
				}

			});
			btnConfirmar.setFont(new Font("Tahoma", Font.PLAIN, 16));
			btnConfirmar.setMnemonic('C');
			btnConfirmar.setBounds(762, 328, 108, 36);
		}
		return btnConfirmar;
	}

	/**
	 * Crea una cita seg?n los datos introducidos. Si se ha escrito en los 2 campos
	 * del horario, se considera que se quer?a establecer uno y se comprueba el
	 * formato. Antes de crear la cita comprueba que no colisione su horario con los
	 * de los m?dicos, pero permitir? crearla igual despu?s de un aviso.
	 */
	private void crearCita() {
		String HE = String.valueOf(ldt.getHour()) + " : " + String.valueOf(ldt.getMinute());
		Date horaEntrada = new Date(ldt.getYear(), ldt.getHour(), ldt.getMinute());
		Date horaSalida = new Date(ldt.getYear(), ldt.getHour() + 1, ldt.getMinute());
		Date fecha = null;
		if (getSpHoraEntrada().isEnabled()) {
			horaEntrada = (Date) getSpHoraEntrada().getValue();
			horaSalida = (Date) getSpHoraSalida().getValue();

			if (compararHoras(horaEntrada, horaSalida) > 0) {
				JOptionPane.showMessageDialog(this, "La hora de fin no puede ir antes que la hora de inicio");
				return;
			}
			fecha = (Date) getSpDia().getValue();
			if (!horarioEncontrado) {
				if (creadorCitas.hayColisionMismoHorario(horaEntrada, horaSalida, fecha)) {
					int r = JOptionPane.showConfirmDialog(this,
							"Hay m?dicos con citas en el mismo horario. ?Proseguir igualmente?");
					if (r != JOptionPane.YES_OPTION)
						return;
				}
				if (creadorCitas.fueraDeJornadaLaboral(horaEntrada, horaSalida, fecha)) {
					int r = JOptionPane.showConfirmDialog(this,
							"La cita no est? dentro de la jornada laboral de los m?dicos. ?Proseguir igualmente?");
					if (r != JOptionPane.YES_OPTION)
						return;
				}
			}
		}

		String ubicacion = cadenaVaciaANull(getTxtUbicacion().getText());
		String infoContacto = cadenaVaciaANull(getTxtContacto().getText());

		PacienteDto p = (PacienteDto) getCbPacientes().getSelectedItem();
		String especialidad = null;
		if (getChckbxEspecialidad().isSelected())
			especialidad = (String) getCbEspecialidades().getSelectedItem();
		CitaDto cita = creadorCitas.crearCita(p, infoContacto, ubicacion, horaEntrada, horaSalida, fecha, especialidad);

		if (getChckbxUrgente().isSelected()) {
			ac.avisoUrgente(cita, HE, p);
		}

		JOptionPane.showMessageDialog(this, "Cita creada");
	}

	/**
	 * Compara 2 fechas en base s?lo a sus horas y minutos
	 * 
	 * @param fecha1
	 * @param fecha2
	 * @return 1 si la hora de la fecha 1 es mayor que la de la fecha 2, -1 si es al
	 *         rev?s y 0 si son iguales
	 */
	private int compararHoras(Date fecha1, Date fecha2) {
		try {
			Format formatter = new SimpleDateFormat("HH:mm");

			String hora1Str = formatter.format(fecha1);
			Date hora1 = (Date) formatter.parseObject(hora1Str);
			String hora2Str = formatter.format(fecha2);
			Date hora2 = (Date) formatter.parseObject(hora2Str);

			return hora1.compareTo(hora2);
		} catch (ParseException e) {
			throw new ApplicationException(e);
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
			txtFiltro.setBounds(685, 101, 97, 22);
			txtFiltro.setColumns(10);
		}
		return txtFiltro;
	}

	private JLabel getLblFiltro() {
		if (lblFiltro == null) {
			lblFiltro = new JLabel("Filtrar por nombre:");
			lblFiltro.setDisplayedMnemonic('i');
			lblFiltro.setLabelFor(getTxtFiltro());
			lblFiltro.setBounds(685, 85, 97, 16);
		}
		return lblFiltro;
	}

	private JButton getBtnFiltrar() {
		if (btnFiltrar == null) {
			btnFiltrar = new JButton("Filtrar");
			btnFiltrar.setMnemonic('F');
			btnFiltrar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					filtrarMedicos();
				}
			});
			btnFiltrar.setBounds(572, 245, 97, 25);
		}
		return btnFiltrar;
	}

	private void filtrarMedicos() {
		List<MedicoDto> medicosValidos = admin.getListaMedicos();
		if (getChckbxFiltroNombre().isSelected()) {
			String filtro = getTxtFiltro().getText().trim().toLowerCase();
			medicosValidos = admin.filtrarMedicos(filtro, medicosValidos);
		}
		if (getChckbxFiltroCita().isSelected()) {
			if (getSpHoraEntrada().isEnabled()) {
				Date horaEntrada = (Date) getSpHoraEntrada().getValue();
				Date horaSalida = (Date) getSpHoraSalida().getValue();
				Date fecha = (Date) getSpDia().getValue();
				medicosValidos = admin.filtrarMedicosSinCitasColisionantes(horaEntrada, horaSalida, fecha,
						medicosValidos);
			} else {
				JOptionPane.showMessageDialog(this, "Necesitas definir un horario antes");
				return;
			}
		}
		if (getChckbxEspecialidad().isSelected()) {
			String especialidad = (String) getCbEspecialidades().getSelectedItem();
			medicosValidos = admin.filtrarMedicosPorEspecialidad(especialidad, medicosValidos);
		}
		if (getChckbxFiltroJornada().isSelected()) {
			if (getSpHoraEntrada().isEnabled()) {
				Date horaEntrada = (Date) getSpHoraEntrada().getValue();
				Date horaSalida = (Date) getSpHoraSalida().getValue();
				Date fecha = (Date) getSpDia().getValue();
				medicosValidos = admin.filtrarMedicosConJornadaLaboral(horaEntrada, horaSalida, fecha,
						medicosValidos);
			} else {
				JOptionPane.showMessageDialog(this, "Necesitas definir un horario antes");
				return;
			}
		}
		modeloListMedicos.clear();
		for (MedicoDto m : medicosValidos) {
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
			btnDesfiltrar.setBounds(685, 245, 97, 25);
		}
		return btnDesfiltrar;
	}

	private JTextField getTxtContacto() {
		if (txtContacto == null) {
			txtContacto = new JTextField();
			txtContacto.setBounds(103, 345, 116, 22);
			txtContacto.setColumns(10);
		}
		return txtContacto;
	}

	private JLabel getLblInfoContacto() {
		if (lblInfoContacto == null) {
			lblInfoContacto = new JLabel("Info. contacto:");
			lblInfoContacto.setDisplayedMnemonic('I');
			lblInfoContacto.setLabelFor(getTxtContacto());
			lblInfoContacto.setBounds(12, 348, 95, 16);
		}
		return lblInfoContacto;
	}

	private JTextField getTxtUbicacion() {
		if (txtUbicacion == null) {
			txtUbicacion = new JTextField();
			txtUbicacion.setBounds(103, 302, 116, 22);
			txtUbicacion.setColumns(10);
		}
		return txtUbicacion;
	}

	private JLabel getLblUbic() {
		if (lblUbic == null) {
			lblUbic = new JLabel("Ubicaci\u00F3n:");
			lblUbic.setDisplayedMnemonic('U');
			lblUbic.setLabelFor(getTxtUbicacion());
			lblUbic.setBounds(12, 305, 71, 16);
		}
		return lblUbic;
	}

	private JLabel getLblInicio() {
		if (lblInicio == null) {
			lblInicio = new JLabel("Hora inicio (HH/mm):");
			lblInicio.setDisplayedMnemonic('H');
			lblInicio.setBounds(12, 178, 108, 14);
		}
		return lblInicio;
	}

	private JLabel getLblFin() {
		if (lblFin == null) {
			lblFin = new JLabel("Hora fin (HH/mm):");
			lblFin.setDisplayedMnemonic('O');
			lblFin.setLabelFor(lblFin);
			lblFin.setBounds(14, 211, 95, 14);
		}
		return lblFin;
	}

	private JSpinner getSpHoraEntrada() {
		if (spHoraEntrada == null) {
			spHoraEntrada = new JSpinner();
			spHoraEntrada.setEnabled(false);
			spHoraEntrada.setModel(new SpinnerDateModel(new Date(), null, null, Calendar.HOUR_OF_DAY));
			spHoraEntrada.setEditor(new JSpinner.DateEditor(spHoraEntrada, "HH:mm"));
			spHoraEntrada.setBounds(119, 174, 108, 22);
		}
		return spHoraEntrada;
	}

	private JSpinner getSpHoraSalida() {
		if (spHoraSalida == null) {
			spHoraSalida = new JSpinner();
			spHoraSalida.setEnabled(false);
			spHoraSalida.setModel(new SpinnerDateModel(new Date(), null, null, Calendar.HOUR_OF_DAY));
			spHoraSalida.setEditor(new JSpinner.DateEditor(spHoraSalida, "HH:mm"));
			spHoraSalida.setBounds(119, 207, 108, 22);
		}
		return spHoraSalida;
	}

	private JSpinner getSpDia() {
		if (spDia == null) {
			spDia = new JSpinner();
			spDia.setEnabled(false);
			spDia.setModel(new SpinnerDateModel(new Date(), null, null, Calendar.DAY_OF_YEAR));
			spDia.setEditor(new JSpinner.DateEditor(spDia, "dd-MM-yyyy"));
			spDia.setBounds(54, 132, 86, 17);
		}
		return spDia;
	}

	private JLabel getLblDia() {
		if (lblDia == null) {
			lblDia = new JLabel("D\u00EDa:");
			lblDia.setBounds(14, 131, 44, 21);
		}
		return lblDia;
	}

	private JCheckBox getChBoxDefinirHorario() {
		if (chBoxDefinirHorario == null) {
			chBoxDefinirHorario = new JCheckBox("Definir horario:");
			chBoxDefinirHorario.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					habilitarCamposHorario(getChBoxDefinirHorario().isSelected());
				}
			});
			chBoxDefinirHorario.setBounds(12, 101, 146, 23);
		}
		return chBoxDefinirHorario;
	}

	private void habilitarCamposHorario(boolean selected) {
		getSpDia().setEnabled(selected);
		getSpHoraEntrada().setEnabled(selected);
		getSpHoraSalida().setEnabled(selected);
		getBtnBuscarHorario().setEnabled(selected);
	}

	private JComboBox<String> getCbEspecialidades() {
		if (cbEspecialidades == null) {
			cbEspecialidades = new JComboBox<String>();
			cbEspecialidades.setEnabled(false);
			cbEspecialidades.setModel(new DefaultComboBoxModel<String>(new String[] { "Alergolog\u00EDa",
					"Anatom\u00EDa Patol\u00F3gica", "Anestesiolog\u00EDa y Reanimaci\u00F3n",
					"Angiolog\u00EDa y Cirug\u00EDa Vascular", "Aparato Digestivo", "Cardiolog\u00EDa",
					"Cirug\u00EDa Cardiovascular", "Cirug\u00EDa General y del Aparato Digestivo",
					"Cirug\u00EDa Oral y Maxilofacial", "Cirug\u00EDa Ortop\u00E9dica y Traumatolog\u00EDa",
					"Cirug\u00EDa Pedi\u00E1trica", "Cirug\u00EDa Pl\u00E1stica, Est\u00E9tica y Reparadora",
					"Cirug\u00EDa Tor\u00E1cica", "Dermatolog\u00EDa M\u00E9dico-Quir\u00FArgica y Venereolog\u00EDa",
					"Endocrinolog\u00EDa y Nutrici\u00F3n", "Farmacolog\u00EDa Cl\u00EDnica", "Geriatr\u00EDa",
					"Hematolog\u00EDa y Hemoterapia", "Inmunolog\u00EDa", "Medicina del Trabajo",
					"Medicina Familiar y Comunitaria", "Medicina F\u00EDsica y Rehabilitaci\u00F3n",
					"Medicina Intensiva", "Medicina Interna", "Medicina Nuclear",
					"Medicina Preventiva y Salud P\u00FAblica", "Nefrolog\u00EDa", "Neumolog\u00EDa",
					"Neurocirug\u00EDa", "Neurofisiolog\u00EDa Cl\u00EDnica", "Neurolog\u00EDa",
					"Obstetricia y Ginecolog\u00EDa", "Oftalmolog\u00EDa", "Oncolog\u00EDa M\u00E9dica",
					"Oncolog\u00EDa Radioter\u00E1pica", "Otorrinolaringolog\u00EDa",
					"Pediatr\u00EDa y sus \u00C1reas Espec\u00EDficas", "Psiquiatr\u00EDa", "Radiodiagn\u00F3stico",
					"Reumatolog\u00EDa", "Urolog\u00EDa" }));
			cbEspecialidades.setBounds(737, 209, 167, 22);
		}
		return cbEspecialidades;
	}

	private JLabel getLblEspecialidades() {
		if (lblEspecialidades == null) {
			lblEspecialidades = new JLabel("Especialidades:");
			lblEspecialidades.setDisplayedMnemonic('P');
			lblEspecialidades.setBounds(737, 194, 105, 16);
		}
		return lblEspecialidades;
	}

	private JCheckBox getChckbxEspecialidad() {
		if (chckbxEspecialidad == null) {
			chckbxEspecialidad = new JCheckBox("Asignar especialidad");
			chckbxEspecialidad.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					getCbEspecialidades().setEnabled(chckbxEspecialidad.isSelected());
				}
			});
			chckbxEspecialidad.setBounds(568, 208, 140, 23);
		}
		return chckbxEspecialidad;
	}

	private JCheckBox getChckbxUrgente() {
		if (chckbxUrgente == null) {
			chckbxUrgente = new JCheckBox("CITA URGENTE");
			chckbxUrgente.setHorizontalAlignment(SwingConstants.CENTER);
			chckbxUrgente.setBounds(640, 337, 116, 23);
		}
		return chckbxUrgente;
	}

	private JCheckBox getChckbxFiltroNombre() {
		if (chckbxFiltroNombre == null) {
			chckbxFiltroNombre = new JCheckBox("Por nombre");
			chckbxFiltroNombre.setBounds(568, 101, 97, 23);
		}
		return chckbxFiltroNombre;
	}

	private JCheckBox getChckbxFiltroCita() {
		if (chckbxFiltroCita == null) {
			chckbxFiltroCita = new JCheckBox("Sin citas conflictivas");
			chckbxFiltroCita.setBounds(568, 130, 159, 23);
		}
		return chckbxFiltroCita;
	}

	private JCheckBox getChckbxFiltroJornada() {
		if (chckbxFiltroJornada == null) {
			chckbxFiltroJornada = new JCheckBox("Dentro de sus jornadas");
			chckbxFiltroJornada.setBounds(568, 162, 167, 23);
		}
		return chckbxFiltroJornada;
	}
	private JButton getBtnBuscarHorario() {
		if (btnBuscarHorario == null) {
			btnBuscarHorario = new JButton("Buscar horario");
			btnBuscarHorario.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						buscarHorario();
					} catch (ParseException e1) {
						JOptionPane.showMessageDialog(null, "No se ha encontrado un horario v?lido para los m?dicos seleccionados");
					}
				}
			});
			btnBuscarHorario.setEnabled(false);
			btnBuscarHorario.setBounds(77, 256, 124, 23);
		}
		return btnBuscarHorario;
	}
	
	private void buscarHorario() throws ParseException {
		int duracion = (int) getSpDuracion().getValue();
		
		Date[] fechas = creadorCitas.buscarHorario(duracion);
		if (fechas == null) {
			JOptionPane.showMessageDialog(this, "No se ha encontrado un horario v?lido para los m?dicos seleccionados");
			return;
		}
		
		Date dia = fechas[0];
		Date horaEntrada = fechas[1];
		Date horaSalida = fechas[2];
		Format formatterDia = new SimpleDateFormat("yyyy-MM-dd");
		Format formatterHora = new SimpleDateFormat("HH:mm");
		String diaStr = formatterDia.format(dia);
		String horaEntradaStr = formatterHora.format(horaEntrada);
		String horaSalidaStr = formatterHora.format(horaSalida);
		String msg = "Se ha encontrado un horario disponible:\nD?a: " + diaStr + "\nHora de entrada: "
				+ horaEntradaStr + "\nHora de salida: " + horaSalidaStr
				+ "\n?Aceptas?";
		int respuesta = JOptionPane.showConfirmDialog(this, msg);
		if (respuesta != JOptionPane.YES_OPTION)
			return;
		
		getSpDia().setValue(formatterDia.parseObject(diaStr));
		getSpHoraEntrada().setValue(formatterHora.parseObject(horaEntradaStr));
		getSpHoraSalida().setValue(formatterHora.parseObject(horaSalidaStr));
		this.horarioEncontrado = true;
	}
	private JSpinner getSpDuracion() {
		if (spDuracion == null) {
			spDuracion = new JSpinner();
			spDuracion.setModel(new SpinnerNumberModel(0, 0, null, 1));
			spDuracion.setBounds(12, 257, 46, 20);
		}
		return spDuracion;
	}
	private JLabel getLblDuracion() {
		if (lblDuracion == null) {
			lblDuracion = new JLabel("Minutos:");
			lblDuracion.setBounds(10, 236, 58, 14);
		}
		return lblDuracion;
	}
}
