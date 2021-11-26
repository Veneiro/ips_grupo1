package vista;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import javax.swing.border.EmptyBorder;

import dtos.CitaDto;
import dtos.MedicoDto;
import logic.ModificarCitas;
import util.ApplicationException;

/**
 * @author Santiago
 *
 */
public class ModificarCitaVista extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	private ModificarCitas modificadorCitas;
	private JComboBox<String> cbEspecialidades;
	private JLabel lblEspecialidades;
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
	private JSpinner spHoraEntrada;
	private JSpinner spHoraSalida;
	private JSpinner spDia;
	private JLabel lblDia;
	private JCheckBox chBoxDefinirHorario;
	private JLabel lblEspecialidadCita;
	private JScrollPane scrollPane;
	private JTextArea textArea;
	private JCheckBox chckbxUrgente;
	private JCheckBox chckbxFiltroNombre;
	private JCheckBox chckbxFiltroCita;
	private JCheckBox chckbxFiltroEspecialidad;
	private JCheckBox chckbxFiltroJornada;
	private JSpinner spDuracion;
	private JLabel lblDuracion;
	private JButton btnBuscarHorario;
	private boolean horarioEncontrado;

	/**
	 * Create the frame.
	 */
	public ModificarCitaVista(CitaDto cita) {
		this.modificadorCitas = new ModificarCitas(cita);
		this.horarioEncontrado = false;
		setTitle("iHospital : Modificar Cita");
		setResizable(false);
		setModal(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1028, 513);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getCbEspecialidades());
		contentPane.add(getLblEspecialidades());
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
		contentPane.add(getSpHoraEntrada());
		contentPane.add(getSpHoraSalida());
		contentPane.add(getSpDia());
		contentPane.add(getLblDia());
		contentPane.add(getChBoxDefinirHorario());
		contentPane.add(getLblEspecialidadCita());
		contentPane.add(getScrollPane());
		contentPane.add(getChckbxUrgente());
		contentPane.add(getChckbxFiltroNombre());
		contentPane.add(getChckbxFiltroCita());
		contentPane.add(getChckbxFiltroEspecialidad());
		contentPane.add(getChckbxFiltroJornada());
		contentPane.add(getSpDuracion());
		contentPane.add(getLblDuracion());
		contentPane.add(getBtnBuscarHorario());
	}

	public void visible(boolean visible) {
		setVisible(visible);
	}

	private JComboBox<String> getCbEspecialidades() {
		if (cbEspecialidades == null) {
			cbEspecialidades = new JComboBox<String>();
			cbEspecialidades.setBounds(507, 159, 167, 22);
			cbEspecialidades.setModel(new DefaultComboBoxModel<String>(new String[] {"Alergolog\u00EDa", "Anatom\u00EDa Patol\u00F3gica", "Anestesiolog\u00EDa y Reanimaci\u00F3n", "Angiolog\u00EDa y Cirug\u00EDa Vascular", "Aparato Digestivo", "Cardiolog\u00EDa", "Cirug\u00EDa Cardiovascular", "Cirug\u00EDa General y del Aparato Digestivo", "Cirug\u00EDa Oral y Maxilofacial", "Cirug\u00EDa Ortop\u00E9dica y Traumatolog\u00EDa", "Cirug\u00EDa Pedi\u00E1trica", "Cirug\u00EDa Pl\u00E1stica, Est\u00E9tica y Reparadora", "Cirug\u00EDa Tor\u00E1cica", "Dermatolog\u00EDa M\u00E9dico-Quir\u00FArgica y Venereolog\u00EDa", "Endocrinolog\u00EDa y Nutrici\u00F3n", "Farmacolog\u00EDa Cl\u00EDnica", "Geriatr\u00EDa", "Hematolog\u00EDa y Hemoterapia", "Inmunolog\u00EDa", "Medicina del Trabajo", "Medicina Familiar y Comunitaria", "Medicina F\u00EDsica y Rehabilitaci\u00F3n", "Medicina Intensiva", "Medicina Interna", "Medicina Nuclear", "Medicina Preventiva y Salud P\u00FAblica", "Nefrolog\u00EDa", "Neumolog\u00EDa", "Neurocirug\u00EDa", "Neurofisiolog\u00EDa Cl\u00EDnica", "Neurolog\u00EDa", "Obstetricia y Ginecolog\u00EDa", "Oftalmolog\u00EDa", "Oncolog\u00EDa M\u00E9dica", "Oncolog\u00EDa Radioter\u00E1pica", "Otorrinolaringolog\u00EDa", "Pediatr\u00EDa y sus \u00C1reas Espec\u00EDficas", "Psiquiatr\u00EDa", "Radiodiagn\u00F3stico", "Reumatolog\u00EDa", "Urolog\u00EDa"}));
		}
		return cbEspecialidades;
	}

	private JLabel getLblEspecialidades() {
		if (lblEspecialidades == null) {
			lblEspecialidades = new JLabel("Especialidades:");
			lblEspecialidades.setDisplayedMnemonic('P');
			lblEspecialidades.setLabelFor(getCbEspecialidades());
			lblEspecialidades.setBounds(507, 143, 105, 16);
		}
		return lblEspecialidades;
	}

	private JScrollPane getSPMedicos() {
		if (sPMedicos == null) {
			sPMedicos = new JScrollPane();
			sPMedicos.setBounds(235, 49, 124, 180);
			sPMedicos.setViewportView(getListMedicos());
		}
		return sPMedicos;
	}

	private JList<MedicoDto> getListMedicos() {
		if (listMedicos == null) {
			modeloListMedicos = new DefaultListModel<MedicoDto>();
			for (MedicoDto medico : modificadorCitas.getMedicosAñadibles()) {
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
			lblMedicos.setBounds(235, 11, 124, 16);
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
			btnAñadir.setBounds(382, 46, 95, 25);
		}
		return btnAñadir;
	}

	private void seleccionarMedicosParaCita() {
		List<MedicoDto> medicosSeleccionados = getListMedicos().getSelectedValuesList();
		modificadorCitas.seleccionarMedicos(medicosSeleccionados);
		actualizarTxtAreaMedicos();
		if (modificadorCitas.hayMedicosElegidos()) {
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
			btnQuitar.setBounds(497, 46, 97, 25);
		}
		return btnQuitar;
	}

	private void deseleccionarMedicosParaCita() {
		List<MedicoDto> medicosSeleccionados = getListMedicos().getSelectedValuesList();
		modificadorCitas.deseleccionarMedicos(medicosSeleccionados);
		actualizarTxtAreaMedicos();
		if (!modificadorCitas.hayMedicosElegidos()) {
			getBtnConfirmar().setEnabled(false);
		}
	}

	private void actualizarTxtAreaMedicos() {
		getTxtAreaMedicosElegidos().setText(modificadorCitas.getStringMedicosElegidos());
	}

	private JTextArea getTxtAreaMedicosElegidos() {
		if (txtAreaMedicosElegidos == null) {
			txtAreaMedicosElegidos = new JTextArea();
			txtAreaMedicosElegidos.setEditable(false);
			txtAreaMedicosElegidos.setBounds(22, 51, 189, 178);
		}
		return txtAreaMedicosElegidos;
	}

	private JLabel getLblMdicosElegidos() {
		if (lblMdicosElegidos == null) {
			lblMdicosElegidos = new JLabel("M\u00E9dicos elegidos");
			lblMdicosElegidos.setBounds(22, 11, 124, 16);
		}
		return lblMdicosElegidos;
	}

	private JButton getBtnConfirmar() {
		if (btnConfirmar == null) {
			btnConfirmar = new JButton("Confirmar");
			btnConfirmar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					modificarCita();
				}

			});
			btnConfirmar.setFont(new Font("Tahoma", Font.PLAIN, 16));
			btnConfirmar.setMnemonic('C');
			btnConfirmar.setBounds(860, 416, 108, 36);
		}
		return btnConfirmar;
	}

	private void modificarCita() {
		
		Date horaEntrada = null;
		Date horaSalida = null;
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
				if (modificadorCitas.hayColisionMismoHorario(horaEntrada, horaSalida, fecha)) {
					int r = JOptionPane.showConfirmDialog(this,
							"Hay médicos con citas en el mismo horario. ¿Proseguir igualmente?");
					if (r != JOptionPane.YES_OPTION)
						return;
				}
				if (modificadorCitas.fueraDeJornadaLaboral(horaEntrada, horaSalida, fecha)) {
					int r = JOptionPane.showConfirmDialog(this,
							"La cita no está dentro de la jornada laboral de los médicos. ¿Proseguir igualmente?");
					if (r != JOptionPane.YES_OPTION)
						return;
				}
			}
		}
		

		String ubicacion = cadenaVaciaANull(getTxtUbicacion().getText());
		String infoContacto = cadenaVaciaANull(getTxtContacto().getText());
		
		modificadorCitas.actualizarCita(infoContacto, ubicacion, horaEntrada, horaSalida, fecha);

		JOptionPane.showMessageDialog(this, "Cita modificada");
	}

	private String cadenaVaciaANull(String ubicacion) {
		if (ubicacion.trim().isEmpty())
			return null;
		return ubicacion;
	}

	/**
	 * Compara 2 fechas en base sólo a sus horas y minutos
	 * 
	 * @param fecha1
	 * @param fecha2
	 * @return 1 si la hora de la fecha 1 es mayor que la de la fecha 2, -1 si es al
	 *         revés y 0 si son iguales
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
	
	private JTextField getTxtFiltro() {
		if (txtFiltro == null) {
			txtFiltro = new JTextField();
			txtFiltro.setBounds(497, 110, 97, 22);
			txtFiltro.setColumns(10);
		}
		return txtFiltro;
	}

	private JLabel getLblFiltro() {
		if (lblFiltro == null) {
			lblFiltro = new JLabel("Filtrar por nombre");
			lblFiltro.setDisplayedMnemonic('i');
			lblFiltro.setLabelFor(getTxtFiltro());
			lblFiltro.setBounds(497, 93, 97, 16);
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
			btnFiltrar.setBounds(382, 248, 97, 25);
		}
		return btnFiltrar;
	}

	private void filtrarMedicos() {
		List<MedicoDto> medicosValidos = modificadorCitas.getMedicosAñadibles();
		if (getChckbxFiltroNombre().isSelected()) {
			String filtro = getTxtFiltro().getText().trim().toLowerCase();
			medicosValidos = modificadorCitas.filtrarMedicos(filtro, medicosValidos);
		}
		if (getChckbxFiltroCita().isSelected()) {
			if (getSpHoraEntrada().isEnabled()) {
				Date horaEntrada = (Date) getSpHoraEntrada().getValue();
				Date horaSalida = (Date) getSpHoraSalida().getValue();
				Date fecha = (Date) getSpDia().getValue();
				medicosValidos = modificadorCitas.filtrarMedicosSinCitasColisionantes(horaEntrada, horaSalida, fecha,
						medicosValidos);
			} else {
				JOptionPane.showMessageDialog(this, "Necesitas definir un horario antes");
				return;
			}
		}
		if (getChckbxFiltroEspecialidad().isSelected()) {
			String especialidad = (String) getCbEspecialidades().getSelectedItem();
			medicosValidos = modificadorCitas.filtrarMedicosPorEspecialidad(especialidad, medicosValidos);
		}
		if (getChckbxFiltroJornada().isSelected()) {
			if (getSpHoraEntrada().isEnabled()) {
				Date horaEntrada = (Date) getSpHoraEntrada().getValue();
				Date horaSalida = (Date) getSpHoraSalida().getValue();
				Date fecha = (Date) getSpDia().getValue();
				medicosValidos = modificadorCitas.filtrarMedicosConJornadaLaboral(horaEntrada, horaSalida, fecha,
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
		for (MedicoDto m : modificadorCitas.getMedicosAñadibles()) {
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
			btnDesfiltrar.setBounds(512, 248, 97, 25);
		}
		return btnDesfiltrar;
	}

	private JTextField getTxtContacto() {
		if (txtContacto == null) {
			txtContacto = new JTextField();
			txtContacto.setBounds(402, 363, 116, 22);
			txtContacto.setColumns(10);
			txtContacto.setText(modificadorCitas.getCita().getContacto());
		}
		return txtContacto;
	}

	private JLabel getLblInfoContacto() {
		if (lblInfoContacto == null) {
			lblInfoContacto = new JLabel("Info. contacto:");
			lblInfoContacto.setDisplayedMnemonic('I');
			lblInfoContacto.setLabelFor(getTxtContacto());
			lblInfoContacto.setBounds(314, 366, 95, 16);
		}
		return lblInfoContacto;
	}

	private JTextField getTxtUbicacion() {
		if (txtUbicacion == null) {
			txtUbicacion = new JTextField();
			txtUbicacion.setBounds(402, 313, 116, 22);
			txtUbicacion.setColumns(10);;
			txtUbicacion.setText(modificadorCitas.getCita().getUbicacion());
		}
		return txtUbicacion;
	}

	private JLabel getLblUbic() {
		if (lblUbic == null) {
			lblUbic = new JLabel("Ubicaci\u00F3n:");
			lblUbic.setDisplayedMnemonic('U');
			lblUbic.setLabelFor(getTxtUbicacion());
			lblUbic.setBounds(321, 316, 71, 16);
		}
		return lblUbic;
	}

	private JLabel getLblInicio() {
		if (lblInicio == null) {
			lblInicio = new JLabel("Hora inicio (HH/mm):");
			lblInicio.setDisplayedMnemonic('H');
			lblInicio.setBounds(22, 278, 124, 14);
		}
		return lblInicio;
	}

	private JLabel getLblFin() {
		if (lblFin == null) {
			lblFin = new JLabel("Hora fin (HH/mm):");
			lblFin.setDisplayedMnemonic('O');
			lblFin.setLabelFor(lblFin);
			lblFin.setBounds(22, 313, 113, 14);
		}
		return lblFin;
	}

	private JSpinner getSpHoraEntrada() {
		if (spHoraEntrada == null) {
			spHoraEntrada = new JSpinner();
			spHoraEntrada.setEnabled(false);
			Date hora = pasarAHora(modificadorCitas.getCita().getHorario_inicio());
			spHoraEntrada.setModel(new SpinnerDateModel(hora, null, null, Calendar.HOUR_OF_DAY));
			spHoraEntrada.setEditor(new JSpinner.DateEditor(spHoraEntrada, "HH:mm"));
			spHoraEntrada.setBounds(156, 274, 108, 22);
		}
		return spHoraEntrada;
	}

	private JSpinner getSpHoraSalida() {
		if (spHoraSalida == null) {
			spHoraSalida = new JSpinner();
			spHoraSalida.setEnabled(false);
			Date hora = pasarAHora(modificadorCitas.getCita().getHorario_fin());
			spHoraSalida.setModel(new SpinnerDateModel(hora, null, null, Calendar.HOUR_OF_DAY));
			spHoraSalida.setEditor(new JSpinner.DateEditor(spHoraSalida, "HH:mm"));
			spHoraSalida.setBounds(156, 309, 108, 22);
		}
		return spHoraSalida;
	}

	private JSpinner getSpDia() {
		if (spDia == null) {
			spDia = new JSpinner();
			spDia.setEnabled(false);
			Date fecha = pasarAFecha(modificadorCitas.getCita().getFecha());
			spDia.setModel(new SpinnerDateModel(fecha, null, null, Calendar.DAY_OF_YEAR));
			spDia.setEditor(new JSpinner.DateEditor(spDia, "dd-MM-yyyy"));
			spDia.setBounds(156, 339, 86, 20);
		}
		return spDia;
	}

	private Date pasarAFecha(String fecha) {
		if (fecha == null)
			return new Date();
		try {
			return new SimpleDateFormat("dd-MM-yyyy").parse(fecha);
		} catch (ParseException e) {
			return new Date();
		}
	}
	
	private Date pasarAHora(String hora) {
		if (hora == null)
			return new Date();
		try {
			return (Date) new SimpleDateFormat("HH:mm").parseObject(hora);
		} catch (ParseException e) {
			return new Date();
		}
	}

	private JLabel getLblDia() {
		if (lblDia == null) {
			lblDia = new JLabel("D\u00EDa:");
			lblDia.setBounds(80, 338, 44, 21);
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
			chBoxDefinirHorario.setBounds(22, 250, 146, 23);
		}
		return chBoxDefinirHorario;
	}

	private void habilitarCamposHorario(boolean selected) {
		getSpDia().setEnabled(selected);
		getSpHoraEntrada().setEnabled(selected);
		getSpHoraSalida().setEnabled(selected);
		getBtnBuscarHorario().setEnabled(selected);
	}
	private JLabel getLblEspecialidadCita() {
		if (lblEspecialidadCita == null) {
			lblEspecialidadCita = new JLabel("Especialidad de la cita:");
			lblEspecialidadCita.setBounds(618, 12, 132, 14);
		}
		return lblEspecialidadCita;
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(760, 6, 219, 42);
			scrollPane.setViewportView(getTextArea());
		}
		return scrollPane;
	}
	private JTextArea getTextArea() {
		if (textArea == null) {
			textArea = new JTextArea();
			textArea.setEditable(false);
			String texto = modificadorCitas.getCita().getEspecialidad();
			if (texto == null)
				textArea.setText("NINGUNA");
			else
				textArea.setText(texto);
		}
		return textArea;
	}
	private JCheckBox getChckbxUrgente() {
		if (chckbxUrgente == null) {
			chckbxUrgente = new JCheckBox("CITA URGENTE");
			chckbxUrgente.setBounds(669, 425, 167, 23);
		}
		return chckbxUrgente;
	}
	private JCheckBox getChckbxFiltroNombre() {
		if (chckbxFiltroNombre == null) {
			chckbxFiltroNombre = new JCheckBox("Por nombre");
			chckbxFiltroNombre.setBounds(380, 110, 97, 23);
		}
		return chckbxFiltroNombre;
	}
	private JCheckBox getChckbxFiltroCita() {
		if (chckbxFiltroCita == null) {
			chckbxFiltroCita = new JCheckBox("Sin citas conflictivas");
			chckbxFiltroCita.setBounds(382, 188, 136, 23);
		}
		return chckbxFiltroCita;
	}
	private JCheckBox getChckbxFiltroEspecialidad() {
		if (chckbxFiltroEspecialidad == null) {
			chckbxFiltroEspecialidad = new JCheckBox("Por especialidad");
			chckbxFiltroEspecialidad.setBounds(379, 156, 116, 23);
		}
		return chckbxFiltroEspecialidad;
	}
	private JCheckBox getChckbxFiltroJornada() {
		if (chckbxFiltroJornada == null) {
			chckbxFiltroJornada = new JCheckBox("Dentro de sus jornadas");
			chckbxFiltroJornada.setBounds(382, 214, 140, 23);
		}
		return chckbxFiltroJornada;
	}
	private JSpinner getSpDuracion() {
		if (spDuracion == null) {
			spDuracion = new JSpinner();
			spDuracion.setBounds(24, 416, 46, 20);
		}
		return spDuracion;
	}
	private JLabel getLblDuracion() {
		if (lblDuracion == null) {
			lblDuracion = new JLabel("Minutos:");
			lblDuracion.setBounds(22, 395, 58, 14);
		}
		return lblDuracion;
	}
	private JButton getBtnBuscarHorario() {
		if (btnBuscarHorario == null) {
			btnBuscarHorario = new JButton("Buscar horario");
			btnBuscarHorario.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						buscarHorario();
					} catch (ParseException e1) {
						JOptionPane.showMessageDialog(null, "No se ha encontrado un horario válido para los médicos seleccionados");
					}
				}
			});
			btnBuscarHorario.setEnabled(false);
			btnBuscarHorario.setBounds(89, 415, 124, 23);
		}
		return btnBuscarHorario;
	}
	
	private void buscarHorario() throws ParseException {
		int duracion = (int) getSpDuracion().getValue();
		
		Date[] fechas = modificadorCitas.buscarHorario(duracion);
		if (fechas == null) {
			JOptionPane.showMessageDialog(this, "No se ha encontrado un horario válido para los médicos seleccionados");
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
		String msg = "Se ha encontrado un horario disponible:\nDía: " + diaStr + "\nHora de entrada: "
				+ horaEntradaStr + "\nHora de salida: " + horaSalidaStr
				+ "\n¿Aceptas?";
		int respuesta = JOptionPane.showConfirmDialog(this, msg);
		if (respuesta != JOptionPane.YES_OPTION)
			return;
		
		getSpDia().setValue(formatterDia.parseObject(diaStr));
		getSpHoraEntrada().setValue(formatterHora.parseObject(horaEntradaStr));
		getSpHoraSalida().setValue(formatterHora.parseObject(horaSalidaStr));
		this.horarioEncontrado = true;
	}
}
