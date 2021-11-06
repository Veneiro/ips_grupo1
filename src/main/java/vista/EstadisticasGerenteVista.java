package vista;

import java.awt.Font;
import java.awt.Label;
import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import lombok.Getter;

@SuppressWarnings("serial")
@Getter
public class EstadisticasGerenteVista extends JFrame {
	private JTextField textDesde;
	private JTextField textHasta;
	private JLabel lblIncidencia;
	private JLabel lblEstadisticas;
	private JLabel lblDesde;
	private JLabel lblHasta;
	private JButton btnCalcular;
	private JLabel lblMasComun;
	private JLabel lblNumeroPacientes;
	private Label lblResultadoMasComun;
	private Label lblResultadoPorcentaje;
	private Label lblResultadoNumero;
	private JScrollPane scrollPane;
	private JTable tableEstadisticas;
	
	public EstadisticasGerenteVista() {
		getContentPane().setLayout(null);
		setBounds(new Rectangle(300, 300, 600, 400));
		setResizable(false);
		setLocationRelativeTo(null);
		setTitle("iHospital : Estadisticas");
		
		lblEstadisticas = new JLabel("Estadisticas:");
		lblEstadisticas.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEstadisticas.setBounds(10, 74, 96, 22);
		getContentPane().add(lblEstadisticas);
		
		lblDesde = new JLabel("Desde:");
		lblDesde.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDesde.setBounds(10, 11, 83, 22);
		getContentPane().add(lblDesde);
		
		lblHasta = new JLabel("Hasta:");
		lblHasta.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblHasta.setBounds(239, 10, 70, 22);
		getContentPane().add(lblHasta);
		
		textDesde = new JTextField();
		textDesde.setBounds(103, 15, 96, 19);
		getContentPane().add(textDesde);
		textDesde.setColumns(10);
		
		textHasta = new JTextField();
		textHasta.setColumns(10);
		textHasta.setBounds(319, 14, 96, 19);
		getContentPane().add(textHasta);
		
		btnCalcular = new JButton("Calcular");
		btnCalcular.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnCalcular.setBounds(319, 43, 95, 21);
		getContentPane().add(btnCalcular);
		
		lblMasComun = new JLabel("Enfermedad mas comun:");
		lblMasComun.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMasComun.setBounds(61, 106, 267, 13);
		getContentPane().add(lblMasComun);
		
		lblIncidencia = new JLabel("% de incidencia en el hospital");
		lblIncidencia.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblIncidencia.setBounds(61, 129, 267, 13);
		getContentPane().add(lblIncidencia);
		
		lblNumeroPacientes = new JLabel("Numero de pacientes con la enfermedad:");
		lblNumeroPacientes.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNumeroPacientes.setBounds(61, 152, 267, 13);
		getContentPane().add(lblNumeroPacientes);
		
		lblResultadoMasComun = new Label("");
		lblResultadoMasComun.setBounds(356, 98, 59, 21);
		getContentPane().add(lblResultadoMasComun);
		
		lblResultadoPorcentaje = new Label("");
		lblResultadoPorcentaje.setBounds(356, 121, 96, 21);
		getContentPane().add(lblResultadoPorcentaje);
		
		lblResultadoNumero = new Label("");
		lblResultadoNumero.setBounds(356, 144, 59, 21);
		getContentPane().add(lblResultadoNumero);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 211, 568, 152);
		getContentPane().add(scrollPane);
		
		tableEstadisticas = new JTable();
		tableEstadisticas.setModel(new DefaultTableModel(
			new Object[][] {
				{},
				{},
				{},
			},
			new String[] {
			}
		));
		scrollPane.setViewportView(tableEstadisticas);
	}
}
