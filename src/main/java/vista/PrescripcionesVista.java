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
    private JTextField textField_Cantidad;
    private JTextField textField_Intervalo;
    private JTextField textField_Duracion;
    private JButton btnNewButton_1;
    private JTextPane indicacionesTextPane;
    private JLabel lblNewLabel;
    private JLabel lblIntervalo;
    private JLabel lblDuracin;

    @Setter
    private NoEditableTableModel modeloTablaPrescripciones;

    private JCheckBox chckbxMedicamento;
    private JButton btnAsignar;
    private JLabel lblNombre;
    private JTextField textFieldNombre;

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

	modeloTablaPrescripciones = new NoEditableTableModel(
		new String[] { "Nombre", "Indicaciones", "Cantidad", "Intervalo", "Duración" }, 0);
	table = new JTable(modeloTablaPrescripciones);
	table.setFillsViewportHeight(true);
	table.setBorder(new LineBorder(new Color(0, 0, 0)));
	table.setModel(new DefaultTableModel(new Object[][] {}, new String[] {}));
	table.setBounds(10, 30, 100, 186);

	indicacionesTextPane = new JTextPane();
	indicacionesTextPane.setBounds(419, 193, 404, 123);
	contentPane.add(indicacionesTextPane);

	JLabel lblNewLabel_1 = new JLabel("Indicaciones");
	lblNewLabel_1.setBounds(419, 168, 80, 14);
	contentPane.add(lblNewLabel_1);

	JScrollPane scrollPane = new JScrollPane();
	scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
	scrollPane.setBounds(10, 11, 399, 305);
	scrollPane.setViewportView(getTable());
	contentPane.add(scrollPane);

	lblNewLabel = new JLabel("Cantidad: ");
	lblNewLabel.setBounds(429, 86, 61, 14);
	contentPane.add(lblNewLabel);

	lblIntervalo = new JLabel("Intervalo: ");
	lblIntervalo.setBounds(429, 111, 61, 14);
	contentPane.add(lblIntervalo);

	lblDuracin = new JLabel("Duraci\u00F3n: ");
	lblDuracin.setBounds(429, 136, 61, 14);
	contentPane.add(lblDuracin);

	textField_Cantidad = new JTextField();
	textField_Cantidad.setEnabled(false);
	textField_Cantidad.setColumns(10);
	textField_Cantidad.setBounds(554, 80, 260, 20);
	contentPane.add(textField_Cantidad);

	textField_Intervalo = new JTextField();
	textField_Intervalo.setEnabled(false);
	textField_Intervalo.setColumns(10);
	textField_Intervalo.setBounds(554, 105, 260, 20);
	contentPane.add(textField_Intervalo);

	textField_Duracion = new JTextField();
	textField_Duracion.setEnabled(false);
	textField_Duracion.setColumns(10);
	textField_Duracion.setBounds(554, 130, 260, 20);
	contentPane.add(textField_Duracion);

	btnNewButton_1 = new JButton("Crear");
	btnNewButton_1.setBounds(734, 327, 90, 23);
	contentPane.add(btnNewButton_1);

	chckbxMedicamento = new JCheckBox("Medicamento");
	chckbxMedicamento.setBounds(419, 48, 117, 23);
	contentPane.add(chckbxMedicamento);

	btnAsignar = new JButton("Asignar");
	btnAsignar.setBounds(320, 327, 89, 23);
	contentPane.add(btnAsignar);

	lblNombre = new JLabel("Nombre: ");
	lblNombre.setBounds(419, 12, 61, 14);
	contentPane.add(lblNombre);

	textFieldNombre = new JTextField();
	textFieldNombre.setBounds(554, 9, 260, 23);
	contentPane.add(textFieldNombre);
	textFieldNombre.setColumns(10);
    }
}
