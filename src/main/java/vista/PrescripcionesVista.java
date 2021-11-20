package vista;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
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
    private JButton btnAsignar;
    private JTextPane indicacionesTextPane;
    private JLabel lblNewLabel;
    private JLabel lblIntervalo;
    private JLabel lblDuracin;

    @Setter
    private NoEditableTableModel modeloTablaPrescripciones;

    private JCheckBox chckbxMedicamento;
    private JButton btnCargar;
    private JLabel lblNombre;
    private JTextField textFieldNombre;
    private JLabel lblBuscar;
    private JTextField textFieldBuscar;
    private JSeparator separator;
    private JLabel lblNewLabel_2;

    /**
     * Create the frame.
     */
    public PrescripcionesVista() {
	setTitle("iHospital : Preinscripci\u00F3n");
	setResizable(false);
	setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	setModal(true);
	setBounds(100, 100, 650, 400);
	contentPane = new JPanel();
	contentPane.setBackground(new Color(211, 211, 211));
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	setContentPane(contentPane);
	contentPane.setLayout(null);

	modeloTablaPrescripciones = new NoEditableTableModel(new String[] { "Nombre" }, 0);
	table = new JTable(modeloTablaPrescripciones);
	table.setFillsViewportHeight(true);
	table.setBorder(null);
	table.setModel(new DefaultTableModel(new Object[][] {}, new String[] {}));
	table.setBounds(10, 30, 100, 186);

	indicacionesTextPane = new JTextPane();
	indicacionesTextPane.setBounds(219, 193, 404, 123);
	contentPane.add(indicacionesTextPane);

	JLabel lblNewLabel_1 = new JLabel("Indicaciones");
	lblNewLabel_1.setBounds(219, 168, 80, 14);
	contentPane.add(lblNewLabel_1);

	JScrollPane scrollPane = new JScrollPane();
	scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
	scrollPane.setBounds(10, 69, 171, 247);
	scrollPane.setViewportView(getTable());
	contentPane.add(scrollPane);

	lblNewLabel = new JLabel("Cantidad: ");
	lblNewLabel.setBounds(229, 86, 61, 14);
	contentPane.add(lblNewLabel);

	lblIntervalo = new JLabel("Intervalo: ");
	lblIntervalo.setBounds(229, 111, 61, 14);
	contentPane.add(lblIntervalo);

	lblDuracin = new JLabel("Duraci\u00F3n: ");
	lblDuracin.setBounds(229, 136, 61, 14);
	contentPane.add(lblDuracin);

	textField_Cantidad = new JTextField();
	textField_Cantidad.setColumns(10);
	textField_Cantidad.setBounds(354, 80, 260, 20);
	contentPane.add(textField_Cantidad);

	textField_Intervalo = new JTextField();
	textField_Intervalo.setColumns(10);
	textField_Intervalo.setBounds(354, 105, 260, 20);
	contentPane.add(textField_Intervalo);

	textField_Duracion = new JTextField();
	textField_Duracion.setColumns(10);
	textField_Duracion.setBounds(354, 130, 260, 20);
	contentPane.add(textField_Duracion);

	btnAsignar = new JButton("Asignar");
	btnAsignar.setBounds(534, 327, 90, 23);
	contentPane.add(btnAsignar);

	chckbxMedicamento = new JCheckBox("Medicamento");
	chckbxMedicamento.setBounds(219, 48, 117, 23);
	contentPane.add(chckbxMedicamento);

	btnCargar = new JButton("Cargar");
	btnCargar.setBounds(10, 327, 171, 23);
	contentPane.add(btnCargar);

	lblNombre = new JLabel("Nombre: ");
	lblNombre.setBounds(219, 12, 61, 14);
	contentPane.add(lblNombre);

	textFieldNombre = new JTextField();
	textFieldNombre.setBounds(354, 9, 260, 23);
	contentPane.add(textFieldNombre);
	textFieldNombre.setColumns(10);

	lblBuscar = new JLabel("Buscar");
	lblBuscar.setBounds(10, 12, 46, 14);
	contentPane.add(lblBuscar);

	textFieldBuscar = new JTextField();
	textFieldBuscar.setBounds(64, 9, 117, 20);
	contentPane.add(textFieldBuscar);
	textFieldBuscar.setColumns(10);

	separator = new JSeparator();
	separator.setOrientation(SwingConstants.VERTICAL);
	separator.setBounds(191, 12, 1, 338);
	contentPane.add(separator);

	lblNewLabel_2 = new JLabel("Existentes");
	lblNewLabel_2.setBounds(10, 44, 90, 14);
	contentPane.add(lblNewLabel_2);
    }
}
