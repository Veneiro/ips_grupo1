package vista;

import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import lombok.Getter;

@Getter
public class AddDiagnosticoVista extends JDialog{
	public AddDiagnosticoVista() {
		setBounds(new Rectangle(300, 300, 450, 240));
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setTitle("iHospital : Nuevo Diagnostico");
		getContentPane().setLayout(null);
		
		JLabel lblNuevoDiagnostico = new JLabel("Nuevo Diagnostico:");
		lblNuevoDiagnostico.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNuevoDiagnostico.setBounds(10, 10, 163, 32);
		getContentPane().add(lblNuevoDiagnostico);
		
		JLabel lblDiagnostico = new JLabel("Diagnostico:");
		lblDiagnostico.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDiagnostico.setBounds(128, 52, 79, 27);
		getContentPane().add(lblDiagnostico);
		
		JLabel lblHora = new JLabel("Hora:");
		lblHora.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblHora.setBounds(128, 89, 79, 27);
		getContentPane().add(lblHora);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(182, 172, 85, 21);
		getContentPane().add(btnAceptar);
		
		textDiagnostico = new JTextField();
		textDiagnostico.setBounds(217, 57, 96, 19);
		getContentPane().add(textDiagnostico);
		textDiagnostico.setColumns(10);
		
		textHora = new JTextField();
		textHora.setBounds(217, 89, 96, 19);
		getContentPane().add(textHora);
		textHora.setColumns(10);
		
		JLabel lblFecha = new JLabel("Fecha:");
		lblFecha.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblFecha.setBounds(128, 126, 79, 23);
		getContentPane().add(lblFecha);
		
		textFecha = new JTextField();
		textFecha.setBounds(217, 129, 96, 19);
		getContentPane().add(textFecha);
		textFecha.setColumns(10);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textDiagnostico;
	private JTextField textHora;
	private JButton btnAceptar;
	private JTextField textFecha;
}
