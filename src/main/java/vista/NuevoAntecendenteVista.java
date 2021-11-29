package vista;

import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import lombok.Getter;

@Getter
public class NuevoAntecendenteVista extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textAntecedente;
	private JTextField textFecha;
	private JButton btnAceptar;
	private JLabel lblInformacion;
	private JTextField textInformacion;
	
	public NuevoAntecendenteVista() {
		getContentPane().setLayout(null);
		setBounds(new Rectangle(300, 300, 300, 200));
		setResizable(false);
		setLocationRelativeTo(null);
		setTitle("Nuevo antecedente");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		JLabel lblTitulo = new JLabel("Nuevo Antecedente");
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTitulo.setBounds(10, 10, 123, 13);
		getContentPane().add(lblTitulo);
		
		JLabel lblNewLabel = new JLabel("Antecedente:");
		lblNewLabel.setBounds(42, 33, 76, 13);
		getContentPane().add(lblNewLabel);
		
		textAntecedente = new JTextField();
		textAntecedente.setBounds(128, 30, 96, 19);
		getContentPane().add(textAntecedente);
		textAntecedente.setColumns(10);
		
		textFecha = new JTextField();
		textFecha.setBounds(128, 59, 96, 19);
		getContentPane().add(textFecha);
		textFecha.setColumns(10);
		
		JLabel lblHora = new JLabel("Fecha:");
		lblHora.setBounds(42, 62, 45, 13);
		getContentPane().add(lblHora);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(97, 118, 85, 21);
		getContentPane().add(btnAceptar);
		
		lblInformacion = new JLabel("Informaci\u00F3n:");
		lblInformacion.setBounds(42, 91, 76, 13);
		getContentPane().add(lblInformacion);
		
		textInformacion = new JTextField();
		textInformacion.setBounds(128, 88, 96, 19);
		getContentPane().add(textInformacion);
		textInformacion.setColumns(10);
		
	}
}
