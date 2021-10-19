package vista;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

import lombok.Getter;

@SuppressWarnings("serial")
@Getter
public class EmpleadosVista extends JDialog {

	private JFrame frame;
	private JTextPane txtPane;
	private JButton btnListarEmpleados;

	/**
	 * Create the application.
	 */
	public EmpleadosVista() {
		setTitle("iHospital : Empleados");
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setBounds(100, 100, 431, 312);
		setModal(true);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		txtPane = new JTextPane();
		txtPane.setEditable(false);
		getContentPane().add(txtPane, BorderLayout.CENTER);

		JLabel lblEmpleados = new JLabel("Empleados");
		lblEmpleados.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblEmpleados.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(lblEmpleados, BorderLayout.NORTH);

		btnListarEmpleados = new JButton("Listar");
		getContentPane().add(btnListarEmpleados, BorderLayout.SOUTH);
	}
}
