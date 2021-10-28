package vista;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import controlador.ListaCalendarioCitasControlador;
import modelo.CitaModelo;
import modelo.PacienteModelo;

public class MainView {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainView window = new MainView();
					window.frame.setVisible(true);
					window.frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 170, 125);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnMedico = new JButton("M\u00E9dico");
		btnMedico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String idMedico = JOptionPane.showInputDialog(frame, "Introduzca su id");
				
				if(idMedico!=null) {
					ListaCalendarioCitasControlador controller = new ListaCalendarioCitasControlador(
							new ListaCalendarioCitasVista(), new CitaModelo(), new PacienteModelo(),
																			Integer.valueOf(idMedico));
					controller.inicializar();
				}
				
			}
		});
		btnMedico.setBounds(10, 52, 134, 23);
		frame.getContentPane().add(btnMedico);
		
		JButton btnAdmin = new JButton("Administrador");
		btnAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminVista iV = new AdminVista();
				iV.main(null);
			}
		});
		btnAdmin.setBounds(10, 11, 134, 23);
		frame.getContentPane().add(btnAdmin);
	}
}