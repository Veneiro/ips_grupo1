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
import javax.swing.ListSelectionModel;

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
	private JPanel panel_1;
	private JButton btnModificar;
	
	public ListaCalendarioCitasAdminVista() {
		setTitle("iHospital : Lista Citas Administrador");
		inicializar();
	}
	
	public void inicializar() {
		setBounds(new Rectangle(300, 300, 800, 400));
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
		
		panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.EAST);
		
		btnHistorial = new JButton("Historial");
		panel_1.add(btnHistorial);
		
		btnModificar = new JButton("Modificar");
		panel_1.add(btnModificar);
		
		scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		tableCitas = new JTable();
		tableCitas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(tableCitas);
	}
}
