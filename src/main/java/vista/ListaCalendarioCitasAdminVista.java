package vista;

import java.awt.BorderLayout;
import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import lombok.Getter;

@Getter
public class ListaCalendarioCitasAdminVista extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTable tableCitas;
	private JButton btnHistorial;
	private JScrollPane scrollPane;
	private JPanel panel;
	private JLabel lblIntroduzcaUnaFecha;
	private JTextField textFecha;
	private JButton btnBuscar;
	
	public ListaCalendarioCitasAdminVista() {
		setTitle("iHospital : Lista Citas Administrador");
		inicializar();
	}
	
	public void inicializar() {
		setBounds(new Rectangle(300, 300, 470, 305));
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		
		panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);
		
		lblIntroduzcaUnaFecha = new JLabel("Introduzca una fecha:");
		panel.add(lblIntroduzcaUnaFecha);
		
		textFecha = new JTextField();
		panel.add(textFecha);
		textFecha.setColumns(10);
		
		btnBuscar = new JButton("Buscar");
		panel.add(btnBuscar);
		
		btnHistorial = new JButton("Historial");
		getContentPane().add(btnHistorial, BorderLayout.EAST);
		
		scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		tableCitas = new JTable();
		scrollPane.setViewportView(tableCitas);
	}
}
