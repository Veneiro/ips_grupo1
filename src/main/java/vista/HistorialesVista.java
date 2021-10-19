package vista;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

import lombok.Getter;

@SuppressWarnings("serial")
@Getter
public class HistorialesVista extends JDialog {

	private JLabel lblHistorial;
	private JTextPane txtpnHistorial;
	private JButton btnSalir;
	
	public HistorialesVista() {
		setTitle("iHospital : Historial");
		inicializar();
	}
	
	public void inicializar() {
		getContentPane().setLayout(new BorderLayout(0, 0));
		setBounds(new Rectangle(300, 300, 280, 450));
		setResizable(false);
		setModal(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		lblHistorial = new JLabel("Historial");
		lblHistorial.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblHistorial.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(lblHistorial, BorderLayout.NORTH);
		
		txtpnHistorial = new JTextPane();
		txtpnHistorial.setEditable(false);
		getContentPane().add(txtpnHistorial, BorderLayout.CENTER);
		
		btnSalir = new JButton("Salir");
		getContentPane().add(btnSalir, BorderLayout.SOUTH);
	}
}
