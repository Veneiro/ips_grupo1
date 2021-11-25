package vista;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import controlador.EstadisticasGerenteControlador;
import controlador.EstadisticasVacunacionControlador;
import controlador.ListaCalendarioCitasControlador;
import controlador.MedicoCrearCitasControlador;
import modelo.CitaModelo;
import modelo.HistorialModelo;
import modelo.PacienteModelo;
import util.SwingUtil;

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
	frame.setBounds(100, 100, 533, 500);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.getContentPane().setLayout(null);

	JButton btnMedico = new JButton("M\u00E9dico");
	btnMedico.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		String idMedico = JOptionPane.showInputDialog(frame, "Introduzca su id");

		if (idMedico != null) {
		    ListaCalendarioCitasControlador controller = new ListaCalendarioCitasControlador(
			    new ListaCalendarioCitasVista(), new CitaModelo(), new PacienteModelo(),
			    Integer.valueOf(idMedico), new MedicoCrearCitasControlador(new CrearCitaMedicoVista()));
		    controller.inicializar();
		}

	    }
	});
	btnMedico.setBounds(10, 195, 495, 85);
	frame.getContentPane().add(btnMedico);

	JButton btnAdmin = new JButton("Administrativo");
	btnAdmin.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		AdminVista iV = new AdminVista();
		iV.main(null);
	    }
	});
	btnAdmin.setBounds(10, 54, 495, 85);
	frame.getContentPane().add(btnAdmin);
	
	JButton btnGerente = new JButton("Gerente");
	btnGerente.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			MenuGerenteVista mgv = new MenuGerenteVista();
			mgv.setVisible(true);
			mgv.getBtnEnfermedades().addActionListener(a -> SwingUtil.exceptionWrapper(() -> inicializarEstadisticasEnfermedades()));
			mgv.getBtnVacunas().addActionListener(a -> SwingUtil.exceptionWrapper(() -> inicializarEstadisticasVacunas()));
			
		}

		private void inicializarEstadisticasVacunas() {
			EstadisticasVacunacionControlador evc = new EstadisticasVacunacionControlador();
			evc.inicializar();
		}

		private void inicializarEstadisticasEnfermedades() {
			EstadisticasGerenteControlador egc = new EstadisticasGerenteControlador(new HistorialModelo()
					, new CitaModelo(), new PacienteModelo(), new EstadisticasGerenteVista());
			egc.inicilizar();
		}
	});
	btnGerente.setBounds(10, 327, 495, 85);
	frame.getContentPane().add(btnGerente);
    }
}
