package vista;

import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import lombok.Getter;
import lombok.Setter;
import util.NoEditableTableModel;

@Getter
public class ListaJornadasVista extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTextField textFieldBuscar;
    private JLabel lblSeleccionEmpleado;
    private JScrollPane scrollPane;
    private JPanel panelIntroducirFecha;
    private JTable table;
    private JButton btnModificar;

    @Setter
    private NoEditableTableModel modeloTabla;

    public ListaJornadasVista() {
	setTitle("iHospital : Listar Jornadas");
	inicializar();
    }

    public void inicializar() {
	getContentPane().setLayout(null);
	panelIntroducirFecha = new JPanel();
	panelIntroducirFecha.setBounds(10, 11, 310, 207);
	getContentPane().add(panelIntroducirFecha);
	setBounds(new Rectangle(300, 300, 470, 305));
	setLocationRelativeTo(null);
	setResizable(false);
	setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	panelIntroducirFecha.setLayout(null);

	lblSeleccionEmpleado = new JLabel("Trabajador: ");
	lblSeleccionEmpleado.setBounds(10, 9, 66, 14);
	lblSeleccionEmpleado.setHorizontalAlignment(SwingConstants.LEFT);
	panelIntroducirFecha.add(lblSeleccionEmpleado);

	textFieldBuscar = new JTextField();
	textFieldBuscar.setBounds(77, 6, 223, 20);
	panelIntroducirFecha.add(textFieldBuscar);
	textFieldBuscar.setColumns(10);

	scrollPane = new JScrollPane();
	scrollPane.setBounds(332, 11, 452, 207);
	getContentPane().add(scrollPane);

	table = new JTable();
	table.setModel(new DefaultTableModel(new Object[][] {},
		new String[] { "ID", "Empleado", "Comienzo", "Fin", "Entrada", "Salida" }));
	scrollPane.setViewportView(table);

	btnModificar = new JButton("Modificar");
	btnModificar.setBounds(681, 227, 93, 23);
	getContentPane().add(btnModificar);

	modeloTabla = new NoEditableTableModel(new String[] { "Trabajador", "Comienzo", "Fin", "Entrada", "Salida" },
		0);
	table = new JTable(modeloTabla);
	scrollPane.setViewportView(table);
    }
}
