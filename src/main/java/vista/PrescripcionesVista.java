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

    /**
     * Create the frame.
     */
    public PrescripcionesVista() {
	setTitle("iHospital : Preinscripci\u00F3n");
	setResizable(false);
	setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	setModal(true);
	setBounds(100, 100, 589, 400);
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
	indicacionesTextPane.setBounds(158, 193, 404, 123);
	contentPane.add(indicacionesTextPane);

	JLabel lblNewLabel_1 = new JLabel("Indicaciones");
	lblNewLabel_1.setBounds(158, 168, 80, 14);
	contentPane.add(lblNewLabel_1);

	JScrollPane scrollPane = new JScrollPane();
	scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
	scrollPane.setBounds(10, 11, 125, 305);
	scrollPane.setViewportView(getTable());
	contentPane.add(scrollPane);

	lblNewLabel = new JLabel("Cantidad: ");
	lblNewLabel.setBounds(168, 86, 61, 14);
	contentPane.add(lblNewLabel);

	lblIntervalo = new JLabel("Intervalo: ");
	lblIntervalo.setBounds(168, 111, 61, 14);
	contentPane.add(lblIntervalo);

	lblDuracin = new JLabel("Duraci\u00F3n: ");
	lblDuracin.setBounds(168, 136, 61, 14);
	contentPane.add(lblDuracin);

	textField_Cantidad = new JTextField();
	textField_Cantidad.setColumns(10);
	textField_Cantidad.setBounds(293, 80, 260, 20);
	contentPane.add(textField_Cantidad);

	textField_Intervalo = new JTextField();
	textField_Intervalo.setColumns(10);
	textField_Intervalo.setBounds(293, 105, 260, 20);
	contentPane.add(textField_Intervalo);

	textField_Duracion = new JTextField();
	textField_Duracion.setColumns(10);
	textField_Duracion.setBounds(293, 130, 260, 20);
	contentPane.add(textField_Duracion);

	btnAsignar = new JButton("Asignar");
	btnAsignar.setBounds(473, 327, 90, 23);
	contentPane.add(btnAsignar);

	chckbxMedicamento = new JCheckBox("Medicamento");
	chckbxMedicamento.setBounds(158, 48, 117, 23);
	contentPane.add(chckbxMedicamento);

	btnCargar = new JButton("Cargar");
	btnCargar.setBounds(10, 327, 125, 23);
	contentPane.add(btnCargar);

	lblNombre = new JLabel("Nombre: ");
	lblNombre.setBounds(158, 12, 61, 14);
	contentPane.add(lblNombre);

	textFieldNombre = new JTextField();
	textFieldNombre.setBounds(293, 9, 260, 23);
	contentPane.add(textFieldNombre);
	textFieldNombre.setColumns(10);
    }
}
