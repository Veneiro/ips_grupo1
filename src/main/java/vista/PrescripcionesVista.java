package vista;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JCheckBox;
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
import lombok.Setter;
import util.NoEditableTableModel;

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
    private JScrollPane scrollPanePacientes;
    private JTable tablePacientes;

    @Setter
    private NoEditableTableModel modeloTablaPrescripciones;

    @Setter
    private NoEditableTableModel modeloTablaPacientes;
    private JCheckBox chckbxNewCheckBox;

    /**
     * Create the frame.
     */
    public PrescripcionesVista() {
	setTitle("iHospital : Preinscripci\u00F3n");
	setResizable(false);
	setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	setModal(true);
	setBounds(100, 100, 850, 400);
	contentPane = new JPanel();
	contentPane.setBackground(new Color(211, 211, 211));
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	setContentPane(contentPane);
	contentPane.setLayout(null);

	modeloTablaPrescripciones = new NoEditableTableModel(new String[] { "Nombre" }, 0);
	table = new JTable(modeloTablaPrescripciones);
	table.setFillsViewportHeight(true);
	table.setBorder(new LineBorder(new Color(0, 0, 0)));
	table.setModel(new DefaultTableModel(new Object[][] {}, new String[] {}));
	table.setBounds(10, 30, 100, 186);

	indicacionesTextPane = new JTextPane();
	indicacionesTextPane.setBounds(419, 166, 404, 150);
	contentPane.add(indicacionesTextPane);

	JLabel lblNewLabel_1 = new JLabel("Indicaciones");
	lblNewLabel_1.setBounds(419, 141, 80, 14);
	contentPane.add(lblNewLabel_1);

	textField_Paciente = new JTextField();
	textField_Paciente.setBounds(170, 15, 220, 20);
	contentPane.add(textField_Paciente);
	textField_Paciente.setColumns(10);

	JScrollPane scrollPane = new JScrollPane();
	scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
	scrollPane.setBounds(10, 11, 150, 339);
	scrollPane.setViewportView(getTable());
	contentPane.add(scrollPane);

	lblNewLabel = new JLabel("Cantidad: ");
	lblNewLabel.setBounds(429, 52, 61, 14);
	contentPane.add(lblNewLabel);

	lblIntervalo = new JLabel("Intervalo: ");
	lblIntervalo.setBounds(429, 77, 61, 14);
	contentPane.add(lblIntervalo);

	lblDuracin = new JLabel("Duraci\u00F3n: ");
	lblDuracin.setBounds(429, 102, 61, 14);
	contentPane.add(lblDuracin);

	textField_Cantidad = new JTextField();
	textField_Cantidad.setColumns(10);
	textField_Cantidad.setBounds(554, 46, 260, 20);
	contentPane.add(textField_Cantidad);

	textField_Intervalo = new JTextField();
	textField_Intervalo.setColumns(10);
	textField_Intervalo.setBounds(554, 71, 260, 20);
	contentPane.add(textField_Intervalo);

	textField_Duracion = new JTextField();
	textField_Duracion.setColumns(10);
	textField_Duracion.setBounds(554, 96, 260, 20);
	contentPane.add(textField_Duracion);

	btnNewButton_1 = new JButton("Confirmar");
	btnNewButton_1.setBounds(704, 327, 120, 23);
	contentPane.add(btnNewButton_1);

	scrollPanePacientes = new JScrollPane();
	scrollPanePacientes.setBounds(170, 46, 220, 304);
	contentPane.add(scrollPanePacientes);

	modeloTablaPacientes = new NoEditableTableModel(new String[] { "Nombre", "Apellido", "Contacto" }, 0);
	tablePacientes = new JTable(modeloTablaPacientes);
	tablePacientes.setBounds(0, 0, 1, 1);
	contentPane.add(tablePacientes);

	chckbxNewCheckBox = new JCheckBox("Medicamento");
	chckbxNewCheckBox.setBounds(419, 14, 97, 23);
	contentPane.add(chckbxNewCheckBox);
    }
}
