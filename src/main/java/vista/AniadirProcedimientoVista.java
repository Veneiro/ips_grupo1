package vista;

import java.awt.Rectangle;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import lombok.Getter;

@Getter
public class AniadirProcedimientoVista extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textHora;
	private JTextField textFecha;
	private JComboBox<String> comboBox;
	private JButton btnAceptar;
	
	public AniadirProcedimientoVista() {
		getContentPane().setLayout(null);
		setBounds(new Rectangle(300, 300, 450, 250));
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("ihospital : Modificar Lista Procedimientos");
		
		
		JLabel lblProcedimiento = new JLabel("Procedimiento");
		lblProcedimiento.setBounds(38, 25, 83, 13);
		getContentPane().add(lblProcedimiento);
		
		JLabel lblNewLabel = new JLabel("Nombre Procedimiento:");
		lblNewLabel.setBounds(89, 48, 108, 13);
		getContentPane().add(lblNewLabel);
		
		JLabel lblFecha = new JLabel("Fecha:");
		lblFecha.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFecha.setBounds(89, 71, 108, 13);
		getContentPane().add(lblFecha);
		
		JLabel lblHora = new JLabel("Hora:");
		lblHora.setHorizontalAlignment(SwingConstants.RIGHT);
		lblHora.setBounds(152, 94, 45, 13);
		getContentPane().add(lblHora);
		
		textHora = new JTextField();
		textHora.setBounds(212, 91, 129, 19);
		getContentPane().add(textHora);
		textHora.setColumns(10);
		
		textFecha = new JTextField();
		textFecha.setBounds(212, 68, 129, 19);
		getContentPane().add(textFecha);
		textFecha.setColumns(10);
		
		comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Radiograf\u00EDa", "Resonancia", "Electrocardiograma", "TAC", "Auscultaci\u00F3n", "Ecograf\u00EDa"}));
		comboBox.setBounds(212, 44, 129, 21);
		getContentPane().add(comboBox);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(152, 159, 108, 31);
		getContentPane().add(btnAceptar);
		
	}
}
