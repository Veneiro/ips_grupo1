package vista;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;

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
public class RegistroVista extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTextField textFieldBusqueda;
    private JLabel lblBusqueda;
    private JScrollPane scrollPane;
    private JPanel panelIntroducirFecha;
    private JTable table;
    private JSpinner desdeSpinner;
    private JCheckBox chckbxDesde;

    @Setter
    private NoEditableTableModel modeloTabla;

    public RegistroVista() {
	setTitle("iHospital : Registro");
	inicializar();
    }

    public void inicializar() {
	getContentPane().setLayout(null);
	panelIntroducirFecha = new JPanel();
	panelIntroducirFecha
		.setBorder(new TitledBorder(null, "Filtros", TitledBorder.LEADING, TitledBorder.TOP, null, null));
	panelIntroducirFecha.setBounds(10, 11, 310, 109);
	getContentPane().add(panelIntroducirFecha);
	setBounds(new Rectangle(300, 300, 800, 300));
	setLocationRelativeTo(null);
	setResizable(false);
	setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	panelIntroducirFecha.setLayout(null);

	desdeSpinner = new JSpinner();
	desdeSpinner.setEnabled(false);
	desdeSpinner.setModel(new SpinnerDateModel(Date.from(Instant.now()), null, null, Calendar.DAY_OF_YEAR));
	JSpinner.DateEditor de = new JSpinner.DateEditor(desdeSpinner, "yyyy-MM-dd");
	desdeSpinner.setEditor(de);
	desdeSpinner.setBounds(89, 70, 90, 20);
	panelIntroducirFecha.add(desdeSpinner);

	lblBusqueda = new JLabel("Buscar: ");
	lblBusqueda.setBounds(10, 28, 75, 14);
	lblBusqueda.setHorizontalAlignment(SwingConstants.LEFT);
	panelIntroducirFecha.add(lblBusqueda);

	textFieldBusqueda = new JTextField();
	textFieldBusqueda.setBounds(89, 25, 211, 20);
	panelIntroducirFecha.add(textFieldBusqueda);
	textFieldBusqueda.setColumns(10);

	chckbxDesde = new JCheckBox("Fecha");
	chckbxDesde.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		if (chckbxDesde.isSelected())
		    desdeSpinner.setEnabled(true);
		else
		    desdeSpinner.setEnabled(false);
	    }
	});
	chckbxDesde.setBounds(10, 69, 62, 23);
	panelIntroducirFecha.add(chckbxDesde);

	scrollPane = new JScrollPane();
	scrollPane.setBounds(332, 11, 442, 239);
	getContentPane().add(scrollPane);
	modeloTabla = new NoEditableTableModel(new String[] { "Quién", "Qué", "Fecha", "Hora" }, 0);
	table = new JTable(modeloTabla);
	scrollPane.setViewportView(table);
    }
}
