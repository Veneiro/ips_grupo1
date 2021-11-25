package vista;

import javax.swing.JDialog;

import lombok.Getter;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;

import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

@Getter
public class EstadisticasVacunacionGerenteVista extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textDesde;
	private JTextField textHasta;
	private JTable table;
	private JLabel lblPacientesVacunadosR;
	private JLabel lblPacientesNoVacunadosR;
	private JLabel lblProcentageR;
	private JComboBox<String> comboBox;
	private JButton btnCalcular;
	
	
	public EstadisticasVacunacionGerenteVista() {
		getContentPane().setLayout(null);
		setBounds(new Rectangle(300, 300, 480, 400));
		setResizable(false);
		setLocationRelativeTo(null);
		setTitle("iHospital : Estadisticas Vacunación");
		
		JLabel lblDesde = new JLabel("Desde:");
		lblDesde.setBounds(10, 10, 45, 13);
		getContentPane().add(lblDesde);
		
		textDesde = new JTextField();
		textDesde.setBounds(65, 7, 96, 19);
		getContentPane().add(textDesde);
		textDesde.setColumns(10);
		
		JLabel lblHasta = new JLabel("Hasta:");
		lblHasta.setBounds(254, 10, 45, 13);
		getContentPane().add(lblHasta);
		
		textHasta = new JTextField();
		textHasta.setBounds(309, 7, 96, 19);
		getContentPane().add(textHasta);
		textHasta.setColumns(10);
		
		comboBox = new JComboBox<String>();
		comboBox.setBounds(132, 46, 114, 21);
		getContentPane().add(comboBox);
		
		JLabel lblVacuna = new JLabel("Vacuna:");
		lblVacuna.setBounds(62, 50, 45, 13);
		getContentPane().add(lblVacuna);
		
		btnCalcular = new JButton("Calcular");
		btnCalcular.setBounds(285, 46, 85, 21);
		getContentPane().add(btnCalcular);
		
		JLabel lblPacientesVacunados = new JLabel("Pacientes Vacunados:");
		lblPacientesVacunados.setBounds(65, 108, 143, 13);
		getContentPane().add(lblPacientesVacunados);
		
		lblPacientesVacunadosR = new JLabel("-");
		lblPacientesVacunadosR.setBounds(293, 108, 45, 13);
		getContentPane().add(lblPacientesVacunadosR);
		
		JLabel lblPacientesNoVacunados = new JLabel("Pacientes no Vacunados");
		lblPacientesNoVacunados.setBounds(65, 148, 143, 13);
		getContentPane().add(lblPacientesNoVacunados);
		
		lblPacientesNoVacunadosR = new JLabel("-");
		lblPacientesNoVacunadosR.setBounds(293, 148, 45, 13);
		getContentPane().add(lblPacientesNoVacunadosR);
		
		JLabel lblPorcentage = new JLabel("Porcentage del Hospital Vacunado:");
		lblPorcentage.setBounds(65, 184, 203, 13);
		getContentPane().add(lblPorcentage);
		
		lblProcentageR = new JLabel("-");
		lblProcentageR.setBounds(293, 184, 45, 13);
		getContentPane().add(lblProcentageR);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 237, 466, 126);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
	}
}
