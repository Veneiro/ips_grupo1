package vista;

import javax.swing.JDialog;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;

public class NuevaVacunaVista extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textVacuna;
	private JTextField textFecha;
	private JButton btnAceptar;
	
	public NuevaVacunaVista() {
		getContentPane().setLayout(null);
		
		JLabel lblTitulo = new JLabel("Nueva Vacuna");
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTitulo.setBounds(10, 10, 123, 13);
		getContentPane().add(lblTitulo);
		
		JLabel lblNewLabel = new JLabel("Vacuna:");
		lblNewLabel.setBounds(42, 33, 45, 13);
		getContentPane().add(lblNewLabel);
		
		textVacuna = new JTextField();
		textVacuna.setBounds(97, 30, 96, 19);
		getContentPane().add(textVacuna);
		textVacuna.setColumns(10);
		
		textFecha = new JTextField();
		textFecha.setBounds(97, 59, 96, 19);
		getContentPane().add(textFecha);
		textFecha.setColumns(10);
		
		JLabel lblFecha = new JLabel("Fecha:");
		lblFecha.setBounds(42, 62, 45, 13);
		getContentPane().add(lblFecha);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(97, 101, 85, 21);
		getContentPane().add(btnAceptar);
		
	}
}
