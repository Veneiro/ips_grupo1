package vista;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import lombok.Getter;
import lombok.Setter;
import util.NoEditableTableModel;

@Getter
public class ListaJornadasVista extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTextField textFieldTrabajador;
    private JLabel lblSeleccionEmpleado;
    private JScrollPane scrollPane;
    private JPanel panelIntroducirFecha;
    private JTable table;
    private JButton btnModificar;
    private JSpinner entradaSpinner;
    private JSpinner horaEntradaSpinner;
    private JSpinner salidaSpinner;
    private JSpinner horaSalidaSpinner;
    private JCheckBox chckbxComienzo;
    private JCheckBox chckbxFin;
    private JCheckBox chckbxEntrada;
    private JCheckBox chckbxSalida;

    @Setter
    private NoEditableTableModel modeloTabla;

    public ListaJornadasVista() {
	setTitle("iHospital : Listar Jornadas");
	inicializar();
    }

    public void inicializar() {
	getContentPane().setLayout(null);
	panelIntroducirFecha = new JPanel();
	panelIntroducirFecha
		.setBorder(new TitledBorder(null, "Filtros", TitledBorder.LEADING, TitledBorder.TOP, null, null));
	panelIntroducirFecha.setBounds(10, 11, 310, 207);
	getContentPane().add(panelIntroducirFecha);
	setBounds(new Rectangle(300, 300, 800, 300));
	setLocationRelativeTo(null);
	setResizable(false);
	setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	panelIntroducirFecha.setLayout(null);

	entradaSpinner = new JSpinner();
	entradaSpinner.setEnabled(false);
	entradaSpinner.setModel(new SpinnerDateModel(Date.from(Instant.now()), null, null, Calendar.DAY_OF_YEAR));
	JSpinner.DateEditor de = new JSpinner.DateEditor(entradaSpinner, "yyyy-MM-dd");
	entradaSpinner.setEditor(de);
	entradaSpinner.setBounds(113, 70, 100, 20);
	panelIntroducirFecha.add(entradaSpinner);

	horaEntradaSpinner = new JSpinner();
	horaEntradaSpinner.setEnabled(false);
	horaEntradaSpinner.setModel(new SpinnerDateModel(Date.from(Instant.now()), null, null, Calendar.HOUR_OF_DAY));
	JSpinner.DateEditor he = new JSpinner.DateEditor(horaEntradaSpinner, "HH:mm");
	horaEntradaSpinner.setEditor(he);
	horaEntradaSpinner.setBounds(113, 127, 50, 20);
	panelIntroducirFecha.add(horaEntradaSpinner);

	lblSeleccionEmpleado = new JLabel("Trabajador: ");
	lblSeleccionEmpleado.setBounds(10, 28, 75, 14);
	lblSeleccionEmpleado.setHorizontalAlignment(SwingConstants.LEFT);
	panelIntroducirFecha.add(lblSeleccionEmpleado);

	textFieldTrabajador = new JTextField();
	textFieldTrabajador.setBounds(89, 25, 211, 20);
	panelIntroducirFecha.add(textFieldTrabajador);
	textFieldTrabajador.setColumns(10);

	salidaSpinner = new JSpinner();
	salidaSpinner.setEnabled(false);
	salidaSpinner.setModel(new SpinnerDateModel(Date.from(Instant.now()), null, null, Calendar.DAY_OF_YEAR));
	de = new JSpinner.DateEditor(salidaSpinner, "yyyy-MM-dd");
	salidaSpinner.setEditor(de);
	salidaSpinner.setBounds(113, 96, 100, 20);
	panelIntroducirFecha.add(salidaSpinner);

	horaSalidaSpinner = new JSpinner();
	horaSalidaSpinner.setEnabled(false);

	horaSalidaSpinner.setModel(new SpinnerDateModel(Date.from(Instant.now()), null, null, Calendar.HOUR_OF_DAY));
	he = new JSpinner.DateEditor(horaEntradaSpinner, "HH:mm");
	horaSalidaSpinner.setEditor(he);

	horaSalidaSpinner.setBounds(113, 153, 50, 20);
	panelIntroducirFecha.add(horaSalidaSpinner);

	chckbxComienzo = new JCheckBox("Comienzo");
	chckbxComienzo.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		if (chckbxComienzo.isSelected())
		    entradaSpinner.setEnabled(true);
		else
		    entradaSpinner.setEnabled(false);
	    }
	});
	chckbxComienzo.setBounds(10, 69, 97, 23);
	panelIntroducirFecha.add(chckbxComienzo);

	chckbxFin = new JCheckBox("Fin");
	chckbxFin.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		if (chckbxFin.isSelected())
		    salidaSpinner.setEnabled(true);
		else
		    salidaSpinner.setEnabled(false);
	    }
	});
	chckbxFin.setBounds(10, 95, 97, 23);
	panelIntroducirFecha.add(chckbxFin);

	chckbxEntrada = new JCheckBox("Entrada");
	chckbxEntrada.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		if (chckbxEntrada.isSelected())
		    horaEntradaSpinner.setEnabled(true);
		else
		    horaEntradaSpinner.setEnabled(false);
	    }
	});
	chckbxEntrada.setBounds(10, 126, 97, 23);
	panelIntroducirFecha.add(chckbxEntrada);

	chckbxSalida = new JCheckBox("Salida");
	chckbxSalida.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		if (chckbxSalida.isSelected())
		    horaSalidaSpinner.setEnabled(true);
		else
		    horaSalidaSpinner.setEnabled(false);
	    }
	});
	chckbxSalida.setBounds(10, 152, 97, 23);
	panelIntroducirFecha.add(chckbxSalida);

	scrollPane = new JScrollPane();
	scrollPane.setBounds(332, 11, 442, 207);
	getContentPane().add(scrollPane);

	btnModificar = new JButton("Modificar");
	btnModificar.setBounds(681, 227, 93, 23);
	getContentPane().add(btnModificar);

	modeloTabla = new NoEditableTableModel(new String[] { "Trabajador", "Comienzo", "Fin", "Entrada", "Salida" },
		0);
	table = new JTable(modeloTabla);
	scrollPane.setViewportView(table);
    }
}
