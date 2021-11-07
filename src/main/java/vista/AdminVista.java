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
import controlador.ListaCalendarioCitasAdminControlador;
import controlador.ListarJornadasControlador;
import logic.Admin;
import logic.CrearCitas;
import modelo.CitaModelo;
import modelo.PacienteModelo;

public class AdminVista {

    private JFrame frmIhospitalMain;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
	EventQueue.invokeLater(new Runnable() {
	    public void run() {
		try {
		    AdminVista window = new AdminVista();
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
    public AdminVista() {
	initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
	frmIhospitalMain = new JFrame();
	frmIhospitalMain.setTitle("iHospital : Main Window");
	frmIhospitalMain.setBounds(100, 100, 453, 306);
	frmIhospitalMain.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	frmIhospitalMain.getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
	frmIhospitalMain.setLocationRelativeTo(null);
	JLabel lblNewLabel = new JLabel("Administrativo");
	lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
	lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
	frmIhospitalMain.getContentPane().add(lblNewLabel);

	JButton btnCita_1 = new JButton("Crear cita");
	btnCita_1.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent arg0) {
		CitaVista dialog = new CitaVista(new Admin(), new CrearCitas());
		dialog.setLocationRelativeTo(null);
		dialog.visible(true);
	    }
	});
	frmIhospitalMain.getContentPane().add(btnCita_1);

	JButton btnAsignarJornada = new JButton("Asignar jornadas");
	btnAsignarJornada.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		JornadasControlador controller = new JornadasControlador();
		controller.inicializar();
	    }
	});

	JButton btnListarJornadas = new JButton("Listar jornadas");
	btnListarJornadas.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		ListarJornadasControlador controller = new ListarJornadasControlador();
		controller.inicializar();
	    }
	});
	frmIhospitalMain.getContentPane().add(btnListarJornadas);

	frmIhospitalMain.getContentPane().add(btnAsignarJornada);
	
	JButton btnComprobarCitas = new JButton("Comprobar Citas Creadas");
	btnComprobarCitas.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			
		}
	});
	frmIhospitalMain.getContentPane().add(btnComprobarCitas);

	frmIhospitalMain.getContentPane().add(btnAsignarJornada);

	JButton btnCalendarioCitas = new JButton("Calendario de citas");
	btnCalendarioCitas.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		ListaCalendarioCitasAdminControlador controller = new ListaCalendarioCitasAdminControlador(
			new ListaCalendarioCitasAdminVista(), new CitaModelo(), new PacienteModelo());
		controller.inicializar();
	    }
	});
	frmIhospitalMain.getContentPane().add(btnCalendarioCitas);
    }
}
