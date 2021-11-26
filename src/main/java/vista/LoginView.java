package vista;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controlador.BackupDBControlador;
import controlador.LoginControlador;
import lombok.Getter;

@Getter
public class LoginView {

    private JFrame frmIhospitalLogin;
    private JButton btnAceptar;
    private JLabel lblUser;
    private JLabel lblNewLabel;
    private JTextField textFieldUser;
    private JPasswordField passwordField;
    private JButton btnForgot;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
	EventQueue.invokeLater(new Runnable() {
	    public void run() {
		try {
		    new BackupDBControlador();
		    LoginView window = new LoginView();
		    window.frmIhospitalLogin.setLocationRelativeTo(null);
		    window.frmIhospitalLogin.setVisible(true);
		} catch (Exception e) {
		    e.printStackTrace();
		}
	    }
	});
    }

    /**
     * Create the application.
     */
    public LoginView() {
	initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
	LoginControlador lC = new LoginControlador(this);

	frmIhospitalLogin = new JFrame();
	frmIhospitalLogin.setResizable(false);
	frmIhospitalLogin.setTitle("iHospital: Login");
	frmIhospitalLogin.setBounds(100, 100, 340, 170);
	frmIhospitalLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frmIhospitalLogin.getContentPane().setLayout(null);

	lblUser = new JLabel("Usuario:");
	lblUser.setBounds(24, 24, 89, 14);
	frmIhospitalLogin.getContentPane().add(lblUser);

	lblNewLabel = new JLabel("Contrase\u00F1a:");
	lblNewLabel.setBounds(24, 64, 89, 14);
	frmIhospitalLogin.getContentPane().add(lblNewLabel);

	btnAceptar = new JButton("Aceptar");
	btnAceptar.setBounds(225, 100, 89, 25);
	frmIhospitalLogin.getContentPane().add(btnAceptar);

	btnAceptar.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		lC.login();
	    }
	});

	btnForgot = new JButton("\u00BFContrase\u00F1a olvidada?");
	btnForgot.setBounds(10, 100, 175, 25);
	frmIhospitalLogin.getContentPane().add(btnForgot);
	btnForgot.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		lC.forgotPassword();
	    }
	});

	textFieldUser = new JTextField();
	textFieldUser.setBounds(112, 21, 188, 20);
	frmIhospitalLogin.getContentPane().add(textFieldUser);
	textFieldUser.setColumns(10);

	passwordField = new JPasswordField();
	passwordField.setBounds(112, 61, 188, 20);
	frmIhospitalLogin.getContentPane().add(passwordField);

    }
}
