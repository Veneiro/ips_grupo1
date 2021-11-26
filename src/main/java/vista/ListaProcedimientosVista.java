package vista;

import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import lombok.Getter;
import javax.swing.SwingConstants;

@Getter
public class ListaProcedimientosVista extends JDialog{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textBuscar;
	private JTable table;
	private JButton btnBuscar;
	private JButton btnModificar;
	private JButton btnAProcedimiento;

	public ListaProcedimientosVista() {
		getContentPane().setLayout(null);
		setBounds(new Rectangle(300, 300, 750, 450));
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("ihospital : Lista de Procedimientos");
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 736, 31);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblBuscar = new JLabel("Buscar:");
		lblBuscar.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBuscar.setBounds(120, 9, 55, 13);
		panel.add(lblBuscar);
		
		textBuscar = new JTextField();
		textBuscar.setBounds(185, 6, 96, 19);
		panel.add(textBuscar);
		textBuscar.setColumns(10);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(291, 5, 96, 21);
		panel.add(btnBuscar);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(526, 31, 200, 432);
		getContentPane().add(panel_1);
		
		btnModificar = new JButton("Modificar Procedimiento");
		btnModificar.setBounds(0, 40, 200, 115);
		
		btnAProcedimiento = new JButton("A\u00F1adir Procedimiento");
		btnAProcedimiento.setBounds(0, 257, 200, 115);
		panel_1.setLayout(null);
		panel_1.add(btnModificar);
		panel_1.add(btnAProcedimiento);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 31, 516, 432);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
	}
}
