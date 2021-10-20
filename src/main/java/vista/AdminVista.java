package vista;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import controlador.JornadasControlador;
import controlador.ListaCalendarioCitasControlador;
import controlador.ListaCitasAcudioControlador;
import controlador.ListaCitasAdminControlador;
import controlador.PacienteControlador;
import controlador.PrescripcionesControlador;
import logic.Admin;
import logic.CrearCitas;
import modelo.CitaModelo;
import modelo.PacienteModelo;

public class InicioVista {

	private JFrame frmIhospitalMain;
	private JButton btnCita;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InicioVista window = new InicioVista();
					window.frmIhospitalMain.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public InicioVista() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmIhospitalMain = new JFrame();
		frmIhospitalMain.setTitle("iHospital : Main Window");
		frmIhospitalMain.setBounds(100, 100, 453, 306);
		frmIhospitalMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmIhospitalMain.getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
		frmIhospitalMain.setLocationRelativeTo(null);
		JLabel lblNewLabel = new JLabel("iHospital");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		frmIhospitalMain.getContentPane().add(lblNewLabel);

		JButton btnCitasAdmin = new JButton("Lista Citas Admin");
		btnCitasAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ListaCitasAdminControlador controller = new ListaCitasAdminControlador(new ListaCitasAdminVista(),
						new CitaModelo(), new PacienteModelo());
				controller.inicializar();
			}
		});

		JButton btnCita_1 = new JButton("Crear cita");
		btnCita_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CitaVista dialog = new CitaVista(new Admin(), new CrearCitas());
				dialog.setLocationRelativeTo(null);
				dialog.visible(true);
			}
		});
		frmIhospitalMain.getContentPane().add(btnCita_1);
		frmIhospitalMain.getContentPane().add(getBtnCita());

		JButton btnAsignarJornada = new JButton("Asignar jornadas");
		btnAsignarJornada.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JornadasControlador controller = new JornadasControlador();
				controller.inicializar();
			}
		});

		frmIhospitalMain.getContentPane().add(btnAsignarJornada);

		JButton btnCalendarioCitas = new JButton("Calendario de citas");
		btnCalendarioCitas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListaCalendarioCitasControlador controller = new ListaCalendarioCitasControlador(
						new ListaCalendarioCitasVista(), new CitaModelo(), new PacienteModelo(), 1);
				controller.inicializar();
			}
		});
		frmIhospitalMain.getContentPane().add(btnCalendarioCitas);

		JButton btnAsignarPrescripcion = new JButton("Asignar prescripci\u00F3n");
		btnAsignarPrescripcion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PrescripcionesControlador pC = new PrescripcionesControlador();
				pC.inicializar();
			}
		});
		frmIhospitalMain.getContentPane().add(btnAsignarPrescripcion);
		frmIhospitalMain.getContentPane().add(btnCitasAdmin);

		JButton btnCitasMedico = new JButton("Lista de Citas Medico");
		btnCitasMedico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListaCitasAcudioControlador controller = new ListaCitasAcudioControlador(new CitaModelo(),
						new ListaCitasAcudioVista(), new PacienteModelo(), 1);
				controller.inicializar();
			}
		});
		frmIhospitalMain.getContentPane().add(btnCitasMedico);
	}

	private JButton getBtnCita() {
		if (btnCita == null) {
			btnCita = new JButton("Controlar Cita");
			btnCita.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					PacienteControlador controller = new PacienteControlador(new PacienteModelo(),
							new AppointmentView());
					controller.initialize();
				}
			});
		}
		return btnCita;
	}
}
