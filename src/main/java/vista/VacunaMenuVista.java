package vista;

import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;

import lombok.Getter;

@Getter
public class VacunaMenuVista extends JDialog {

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	
	private JButton btnModificar;
	private JButton btnNueva;
	
	
	public VacunaMenuVista() {
		setBounds(new Rectangle(300, 300, 200, 150));
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JLabel lblSelecicion = new JLabel("Seleccione una Opcion:");
		lblSelecicion.setBounds(10, 10, 134, 20);
		getContentPane().add(lblSelecicion);
		
		btnModificar = new JButton("Modificar Vacuna");
		btnModificar.setBounds(10, 40, 166, 21);
		getContentPane().add(btnModificar);
		
		btnNueva = new JButton("Nueva Vacuna");
		btnNueva.setBounds(10, 71, 166, 21);
		getContentPane().add(btnNueva);
		
	}
}
