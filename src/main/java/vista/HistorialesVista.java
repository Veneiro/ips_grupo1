package vista;

import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import lombok.Getter;

@SuppressWarnings("serial")
@Getter
public class HistorialesVista extends JDialog {

	private JLabel lblHistorial;
	private JButton btnSalir;
	private JButton btnModificar;
	private JScrollPane scrollPaneTabla;
	private JTable table;
	private JScrollPane scrollPaneTabla_1;
	private JScrollPane scrollPaneTabla_2;
	private JTable tableDiagnosticos;
	private JTable tablePrescriciones;
	
	public HistorialesVista() {
		setTitle("iHospital : Historial");
		inicializar();
	}
	
	public void inicializar() {
		setBounds(new Rectangle(300, 300, 1100, 500));
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		
		lblHistorial = new JLabel("Historial");
		lblHistorial.setBounds(0, 0, 986, 22);
		lblHistorial.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblHistorial.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(lblHistorial);
		
		btnSalir = new JButton("Salir");
		btnSalir.setBounds(0, 442, 1076, 21);
		getContentPane().add(btnSalir);
		
		btnModificar = new JButton("Modificar\r\nDiagnostico");
		btnModificar.setHorizontalAlignment(SwingConstants.RIGHT);
		btnModificar.setBounds(911, 22, 165, 419);
		getContentPane().add(btnModificar);
		
		scrollPaneTabla = new JScrollPane();
		scrollPaneTabla.setBounds(0, 22, 911, 158);
		getContentPane().add(scrollPaneTabla);
		
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
		
		scrollPaneTabla_1 = new JScrollPane();
		scrollPaneTabla_1.setBounds(0, 180, 911, 124);
		getContentPane().add(scrollPaneTabla_1);
		
		tableDiagnosticos = new JTable();
		scrollPaneTabla_1.setViewportView(tableDiagnosticos);
		
		scrollPaneTabla_2 = new JScrollPane();
		scrollPaneTabla_2.setBounds(0, 303, 911, 138);
		getContentPane().add(scrollPaneTabla_2);
		
		tablePrescriciones = new JTable();
		scrollPaneTabla_2.setViewportView(tablePrescriciones);
	}
}
