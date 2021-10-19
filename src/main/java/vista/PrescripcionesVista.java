package vista;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import lombok.Getter;

@Getter
public class PrescripcionesVista extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JTextField textField_Paciente;
	private JTextField textField_Cantidad;
	private JTextField textField_Intervalo;
	private JTextField textField_Duracion;
	private JButton btnNewButton_1;
	private JTextPane indicacionesTextPane;
	private JLabel lblNewLabel;
	private JLabel lblIntervalo;
	private JLabel lblDuracin;
	private JButton btnNuevoMedicamento;

	/**
	 * Create the frame.
	 */
	public PrescripcionesVista() {
		setTitle("iHospital : Preinscripci\u00F3n");
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setModal(true);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(211, 211, 211));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		table = new JTable();
		table.setFillsViewportHeight(true);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setModel(new DefaultTableModel(new Object[][] { { null },
				{ null }, { null }, { null }, { null }, { null }, { null },
				{ null }, { null }, { null }, { null }, { null }, { null },
				{ null }, { null }, { null }, { null }, { null }, { null },
				{ null }, { null }, { null }, { null }, { null }, { null }, },
				new String[] { "Medicamento" }) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] { false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.setBounds(10, 30, 100, 186);

		indicacionesTextPane = new JTextPane();
		indicacionesTextPane.setBounds(170, 212, 404, 104);
		contentPane.add(indicacionesTextPane);

		JLabel lblNewLabel_1 = new JLabel("Indicaciones");
		lblNewLabel_1.setBounds(170, 187, 80, 14);
		contentPane.add(lblNewLabel_1);

		textField_Paciente = new JTextField();
		textField_Paciente.setBounds(170, 31, 134, 20);
		contentPane.add(textField_Paciente);
		textField_Paciente.setColumns(10);

		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(314, 30, 260, 22);
		contentPane.add(comboBox);

		JLabel lblNewLabel_2 = new JLabel("Paciente");
		lblNewLabel_2.setBounds(170, 11, 100, 14);
		contentPane.add(lblNewLabel_2);

		btnNuevoMedicamento = new JButton("Nuevo");
		btnNuevoMedicamento.setBounds(10, 327, 150, 23);
		contentPane.add(btnNuevoMedicamento);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(
				ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(10, 11, 150, 305);
		scrollPane.setViewportView(getTable());
		contentPane.add(scrollPane);

		lblNewLabel = new JLabel("Cantidad: ");
		lblNewLabel.setBounds(170, 96, 61, 14);
		contentPane.add(lblNewLabel);

		lblIntervalo = new JLabel("Intervalo: ");
		lblIntervalo.setBounds(170, 121, 61, 14);
		contentPane.add(lblIntervalo);

		lblDuracin = new JLabel("Duraci\u00F3n: ");
		lblDuracin.setBounds(170, 146, 61, 14);
		contentPane.add(lblDuracin);

		textField_Cantidad = new JTextField();
		textField_Cantidad.setColumns(10);
		textField_Cantidad.setBounds(295, 90, 260, 20);
		contentPane.add(textField_Cantidad);

		textField_Intervalo = new JTextField();
		textField_Intervalo.setColumns(10);
		textField_Intervalo.setBounds(295, 115, 260, 20);
		contentPane.add(textField_Intervalo);

		textField_Duracion = new JTextField();
		textField_Duracion.setColumns(10);
		textField_Duracion.setBounds(295, 140, 260, 20);
		contentPane.add(textField_Duracion);

		btnNewButton_1 = new JButton("Confirmar");
		btnNewButton_1.setBounds(455, 327, 120, 23);
		contentPane.add(btnNewButton_1);
	}
}
