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

import dtos.MedicoDto;
import dtos.PacienteDto;
import logic.Admin;
import logic.CrearCitas;
import util.ApplicationException;

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
	private JButton btnA�adir;
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
	private JTextField textField;

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
		setBounds(100, 100, 719, 441);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getCbPacientes());
		contentPane.add(getLblPaciente());
		contentPane.add(getSPMedicos());
		contentPane.add(getLblMedicos());
		contentPane.add(getBtnA�adir());
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
		contentPane.add(getTextField());
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
			sPMedicos.setBounds(290, 51, 124, 180);
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
			lblMedicos.setBounds(325, 13, 56, 16);
		}
		return lblMedicos;
	}

	private JButton getBtnA�adir() {
		if (btnA�adir == null) {
			btnA�adir = new JButton("A\u00F1adir");
			btnA�adir.setMnemonic('A');
			btnA�adir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					seleccionarMedicosParaCita();
				}
			});
			btnA�adir.setBounds(439, 48, 95, 25);
		}
		return btnA�adir;
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
			btnQuitar.setBounds(563, 48, 97, 25);
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
		if (btnConfirmar == null) {
			btnConfirmar = new JButton("Confirmar");
			btnConfirmar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					crearCita();
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
	 * Crea una cita seg�n los datos introducidos. Si se ha escrito en los 2 campos
	 * del horario, se considera que se quer�a establecer uno y se comprueba el
	 * formato. Antes de crear la cita comprueba que no colisione su horario con los
	 * de los m�dicos, pero permitir� crearla igual despu�s de un aviso.
	 */
	private void crearCita() {
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
			if (creadorCitas.hayColisionMismoHorario(horaEntrada, horaSalida, fecha)) {
				int r = JOptionPane.showConfirmDialog(this,
						"Hay m�dicos con citas en el mismo horario. �Proseguir igualmente?");
				if (r != JOptionPane.YES_OPTION)
					return;
			}
			if (creadorCitas.fueraDeJornadaLaboral(horaEntrada, horaSalida, fecha)) {
				int r = JOptionPane.showConfirmDialog(this,
						"La cita no est� dentro de la jornada laboral de los m�dicos. �Proseguir igualmente?");
				if (r != JOptionPane.YES_OPTION)
					return;
			}
		}

		String ubicacion = cadenaVaciaANull(getTxtUbicacion().getText());
		String infoContacto = cadenaVaciaANull(getTxtContacto().getText());

		PacienteDto p = (PacienteDto) getCbPacientes().getSelectedItem();
		creadorCitas.crearCita(p, infoContacto, ubicacion, horaEntrada, horaSalida, fecha);

		JOptionPane.showMessageDialog(this, "Cita creada");
	}

	/**
	 * Compara 2 fechas en base s�lo a sus horas y minutos
	 * 
	 * @param fecha1
	 * @param fecha2
	 * @return 1 si la hora de la fecha 1 es mayor que la de la fecha 2, -1 si es al
	 *         rev�s y 0 si son iguales
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
			txtFiltro.setBounds(450, 134, 97, 22);
			txtFiltro.setColumns(10);
		}
		return txtFiltro;
	}

	private JLabel getLblFiltro() {
		if (lblFiltro == null) {
			lblFiltro = new JLabel("Filtrar m\u00E9dicos");
			lblFiltro.setDisplayedMnemonic('i');
			lblFiltro.setLabelFor(getTxtFiltro());
			lblFiltro.setBounds(450, 117, 97, 16);
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
			btnFiltrar.setBounds(583, 133, 97, 25);
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
			btnDesfiltrar.setBounds(583, 174, 97, 25);
		}
		return btnDesfiltrar;
	}

	private JTextField getTxtContacto() {
		if (txtContacto == null) {
			txtContacto = new JTextField();
			txtContacto.setBounds(544, 236, 116, 22);
			txtContacto.setColumns(10);
		}
		return txtContacto;
	}

	private JLabel getLblInfoContacto() {
		if (lblInfoContacto == null) {
			lblInfoContacto = new JLabel("Info. contacto:");
			lblInfoContacto.setDisplayedMnemonic('I');
			lblInfoContacto.setLabelFor(getTxtContacto());
			lblInfoContacto.setBounds(544, 210, 95, 16);
		}
		return lblInfoContacto;
	}

	private JTextField getTxtUbicacion() {
		if (txtUbicacion == null) {
			txtUbicacion = new JTextField();
			txtUbicacion.setBounds(418, 236, 116, 22);
			txtUbicacion.setColumns(10);
		}
		return txtUbicacion;
	}

	private JLabel getLblUbic() {
		if (lblUbic == null) {
			lblUbic = new JLabel("Ubicaci\u00F3n:");
			lblUbic.setDisplayedMnemonic('U');
			lblUbic.setLabelFor(getTxtUbicacion());
			lblUbic.setBounds(424, 210, 71, 16);
		}
		return lblUbic;
	}

	private JLabel getLblInicio() {
		if (lblInicio == null) {
			lblInicio = new JLabel("Hora inicio (HH/mm):");
			lblInicio.setDisplayedMnemonic('H');
			lblInicio.setBounds(139, 316, 124, 14);
		}
		return lblInicio;
	}

	private JLabel getLblFin() {
		if (lblFin == null) {
			lblFin = new JLabel("Hora fin (HH/mm):");
			lblFin.setDisplayedMnemonic('O');
			lblFin.setLabelFor(lblFin);
			lblFin.setBounds(150, 355, 113, 14);
		}
		return lblFin;
	}

	private JSpinner getSpHoraEntrada() {
		if (spHoraEntrada == null) {
			spHoraEntrada = new JSpinner();
			spHoraEntrada.setEnabled(false);
			spHoraEntrada.setModel(new SpinnerDateModel(new Date(), null, null, Calendar.HOUR_OF_DAY));
			spHoraEntrada.setEditor(new JSpinner.DateEditor(spHoraEntrada, "HH:mm"));
			spHoraEntrada.setBounds(284, 316, 108, 22);
		}
		return spHoraEntrada;
	}

	private JSpinner getSpHoraSalida() {
		if (spHoraSalida == null) {
			spHoraSalida = new JSpinner();
			spHoraSalida.setEnabled(false);
			spHoraSalida.setModel(new SpinnerDateModel(new Date(), null, null, Calendar.HOUR_OF_DAY));
			spHoraSalida.setEditor(new JSpinner.DateEditor(spHoraSalida, "HH:mm"));
			spHoraSalida.setBounds(284, 351, 108, 22);
		}
		return spHoraSalida;
	}

	private JSpinner getSpDia() {
		if (spDia == null) {
			spDia = new JSpinner();
			spDia.setEnabled(false);
			spDia.setModel(new SpinnerDateModel(new Date(), null, null, Calendar.DAY_OF_YEAR));
			spDia.setEditor(new JSpinner.DateEditor(spDia, "dd-MM-yyyy"));
			spDia.setBounds(188, 381, 86, 17);
		}
		return spDia;
	}

	private JLabel getLblDia() {
		if (lblDia == null) {
			lblDia = new JLabel("D\u00EDa:");
			lblDia.setBounds(73, 380, 44, 21);
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
			chBoxDefinirHorario.setBounds(73, 292, 97, 23);
		}
		return chBoxDefinirHorario;
	}

	private void habilitarCamposHorario(boolean selected) {
		getSpDia().setEnabled(selected);
		getSpHoraEntrada().setEnabled(selected);
		getSpHoraSalida().setEnabled(selected);
	}

	private JTextField getTextField() {
		if (textField == null) {
			textField = new JTextField();
			textField.setBounds(63, 11, 86, 20);
			textField.setColumns(10);
		}
		return textField;
	}
}
