package vista;

import javax.swing.JDialog;

import lombok.Getter;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.SwingConstants;
import javax.swing.JButton;

@Getter
public class MenuGerenteVista extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JButton btnEnfermedades;
	private JButton btnVacunas;
	
	public MenuGerenteVista() {
		getContentPane().setLayout(null);
		setBounds(new Rectangle(300, 300, 450, 200));
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("ihospital : Menu Gerente");
		
		JLabel lblNewLabel = new JLabel("Gerente");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 10, 416, 29);
		getContentPane().add(lblNewLabel);
		
		btnVacunas = new JButton("Estadisticas Vacunas");
		btnVacunas.setBounds(10, 49, 416, 29);
		getContentPane().add(btnVacunas);
		
		btnEnfermedades = new JButton("Estadisticas Enfermedades");
		btnEnfermedades.setBounds(10, 88, 416, 29);
		getContentPane().add(btnEnfermedades);
		
	}
}
