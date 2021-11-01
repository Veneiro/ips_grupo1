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

@Getter
public class ListaJornadasVista extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTextField textFieldFecha;
    private JLabel lblSeleccionEmpleado;
    private JScrollPane scrollPaneCitas;
    private JPanel panelIntroducirFecha;
    private JTable table;
    private JButton btnModificar;
    @Setter
    private ModificarJornadaVista mJ;

    public ListaJornadasVista() {
	setTitle("iHospital : Listar Jornadas");
	inicializar();
    }

    public void inicializar() {
	getContentPane().setLayout(null);
	panelIntroducirFecha = new JPanel();
	panelIntroducirFecha.setBounds(0, 0, 454, 33);
	getContentPane().add(panelIntroducirFecha);
	setBounds(new Rectangle(300, 300, 470, 305));
	setLocationRelativeTo(null);
	setResizable(false);
	setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	panelIntroducirFecha.setLayout(null);

	lblSeleccionEmpleado = new JLabel("B\u00FAsqueda: ");
	lblSeleccionEmpleado.setBounds(10, 9, 66, 14);
	lblSeleccionEmpleado.setHorizontalAlignment(SwingConstants.LEFT);
	panelIntroducirFecha.add(lblSeleccionEmpleado);

	textFieldFecha = new JTextField();
	textFieldFecha.setBounds(77, 6, 367, 20);
	panelIntroducirFecha.add(textFieldFecha);
	textFieldFecha.setColumns(10);

	scrollPaneCitas = new JScrollPane();
	scrollPaneCitas.setBounds(0, 33, 454, 204);
	getContentPane().add(scrollPaneCitas);

	table = new JTable();
	table.setModel(new DefaultTableModel(new Object[][] {},
		new String[] { "ID", "Empleado", "Comienzo", "Fin", "Entrada", "Salida" }));
	scrollPaneCitas.setViewportView(table);

	btnModificar = new JButton("Modificar");
	btnModificar.setBounds(0, 235, 454, 31);
	getContentPane().add(btnModificar);

    }
}
