 package vista;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import lombok.Getter;

@SuppressWarnings("serial")
@Getter
public class HistorialesVista extends JFrame {

	private JLabel lblHistorial;
	private JButton btnSalir;
	private JButton btnModificar;
	private JScrollPane scrollPaneTabla;
	private JTable table;
	
	public HistorialesVista() {
		setTitle("iHospital : Historial");
		inicializar();
	}
	
	public void inicializar() {
		getContentPane().setLayout(new BorderLayout(0, 0));
		setBounds(new Rectangle(300, 300, 1000, 500));
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		lblHistorial = new JLabel("Historial");
		lblHistorial.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblHistorial.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(lblHistorial, BorderLayout.NORTH);
		
		btnSalir = new JButton("Salir");
		getContentPane().add(btnSalir, BorderLayout.SOUTH);
		
		btnModificar = new JButton("Modificar");
		getContentPane().add(btnModificar, BorderLayout.EAST);
		
		scrollPaneTabla = new JScrollPane();
		getContentPane().add(scrollPaneTabla, BorderLayout.CENTER);
		
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Historial"
			}
		));
		scrollPaneTabla.setViewportView(table);
	}
}
