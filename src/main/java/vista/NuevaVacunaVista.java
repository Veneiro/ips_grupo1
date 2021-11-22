package vista;

import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import lombok.Getter;

@Getter
public class NuevaVacunaVista extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textVacuna;
	private JTextField textHora;
	private JButton btnAceptar;
	
	public NuevaVacunaVista() {
		getContentPane().setLayout(null);
		setBounds(new Rectangle(300, 300, 300, 170));
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
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
		
		textHora = new JTextField();
		textHora.setBounds(97, 59, 96, 19);
		getContentPane().add(textHora);
		textHora.setColumns(10);
		
		JLabel lblHora = new JLabel("Hora:");
		lblHora.setBounds(42, 62, 45, 13);
		getContentPane().add(lblHora);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(97, 101, 85, 21);
		getContentPane().add(btnAceptar);
		
	}
}
