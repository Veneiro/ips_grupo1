package vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.toedter.calendar.JCalendar;

import lombok.Getter;
import lombok.Setter;
import util.NoEditableTableModel;

@Getter
public class ModificarJornadaVista extends JDialog {

    /**
     * 
     */
    private static final long serialVersionUID = 5414909378345343884L;
    private JPanel contentPane;
    private JButton confirmarButton;
    private JPanel jornadaPanel;
    private JLabel entradaLabel;
    private JLabel salidaLabel;
    private JSpinner horaEntradaSpinner;
    private JLabel lblComienzo;
    private JLabel lblFin;
    private JSpinner entradaSpinner;
    private JSpinner salidaSpinner;
    private JSpinner horaSalidaSpinner;
    private JSeparator separator;
    private JCalendar comienzoCalendar;
    private JCalendar finCalendar;
    private JPanel panel;
    private JCheckBox LunesCheckBox;
    private JCheckBox MartesCheckBox;
    private JCheckBox MiercolesCheckBox;
    private JCheckBox JuevesCheckBox;
    private JCheckBox ViernesCheckBox;
    private JCheckBox SabadoCheckBox;
    private JCheckBox DomingoCheckBox;
    @Setter
    private NoEditableTableModel modeloTabla;

    private int idJornada;

    /**
     * Create the frame.
     */
    public ModificarJornadaVista(int idJornada) {
	this.idJornada = idJornada;

	setTitle("iHospital : Modificar Jornada " + idJornada);
	setBounds(100, 100, 627, 380);
	setModal(true);
	setResizable(false);
	setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	contentPane = new JPanel();
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	setContentPane(contentPane);
	setLocationRelativeTo(null);
	contentPane.setLayout(null);

	confirmarButton = new JButton("Confirmar");
	confirmarButton.setBounds(5, 313, 604, 23);
	contentPane.add(confirmarButton);

	jornadaPanel = new JPanel();
	jornadaPanel.setBounds(5, 5, 604, 308);
	jornadaPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
	contentPane.add(jornadaPanel);
	jornadaPanel.setLayout(null);

	lblComienzo = new JLabel("Comienzo");
	lblComienzo.setFont(new Font("Tahoma", Font.BOLD, 12));
	lblComienzo.setBounds(176, 20, 69, 15);
	jornadaPanel.add(lblComienzo);

	entradaSpinner = new JSpinner();
	entradaSpinner.setModel(new SpinnerDateModel(new Date(1633644000000L), null, null, Calendar.DAY_OF_YEAR));
	JSpinner.DateEditor de = new JSpinner.DateEditor(entradaSpinner, "dd-MM-yyyy");
	entradaSpinner.setEditor(de);
	entradaSpinner.setBounds(176, 42, 100, 20);
	jornadaPanel.add(entradaSpinner);

	lblFin = new JLabel("Fin");
	lblFin.setFont(new Font("Tahoma", Font.BOLD, 12));
	lblFin.setBounds(395, 20, 36, 15);
	jornadaPanel.add(lblFin);

	salidaSpinner = new JSpinner();
	salidaSpinner.setModel(new SpinnerDateModel(new Date(1633644000000L), null, null, Calendar.DAY_OF_YEAR));
	de = new JSpinner.DateEditor(salidaSpinner, "dd-MM-yyyy");
	salidaSpinner.setEditor(de);
	salidaSpinner.setBounds(395, 42, 100, 20);
	jornadaPanel.add(salidaSpinner);

	entradaLabel = new JLabel("Entrada");
	entradaLabel.setBounds(176, 234, 48, 15);
	entradaLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
	jornadaPanel.add(entradaLabel);

	salidaLabel = new JLabel("Salida");
	salidaLabel.setBounds(395, 234, 50, 15);
	salidaLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
	jornadaPanel.add(salidaLabel);

	separator = new JSeparator();
	separator.setOrientation(SwingConstants.VERTICAL);
	separator.setBounds(158, 0, 12, 308);
	jornadaPanel.add(separator);

	panel = new JPanel();
	panel.setBounds(10, 11, 140, 286);
	jornadaPanel.add(panel);
	panel.setLayout(new GridLayout(0, 1, 0, 5));

	LunesCheckBox = new JCheckBox("Lunes");
	LunesCheckBox.setSelected(true);
	panel.add(LunesCheckBox);

	MartesCheckBox = new JCheckBox("Martes");
	MartesCheckBox.setSelected(true);
	panel.add(MartesCheckBox);

	MiercolesCheckBox = new JCheckBox("Mi\u00E9rcoles");
	MiercolesCheckBox.setSelected(true);
	panel.add(MiercolesCheckBox);

	JuevesCheckBox = new JCheckBox("Jueves");
	JuevesCheckBox.setSelected(true);
	panel.add(JuevesCheckBox);

	ViernesCheckBox = new JCheckBox("Viernes");
	ViernesCheckBox.setSelected(true);
	panel.add(ViernesCheckBox);

	SabadoCheckBox = new JCheckBox("S\u00E1bado");
	panel.add(SabadoCheckBox);

	DomingoCheckBox = new JCheckBox("Domingo");
	panel.add(DomingoCheckBox);

	comienzoCalendar = new JCalendar();
	comienzoCalendar.addPropertyChangeListener("calendar", new PropertyChangeListener() {
	    public void propertyChange(PropertyChangeEvent evt) {
		Calendar c = (Calendar) evt.getNewValue();
		entradaSpinner.setModel(new SpinnerDateModel(c.getTime(), null, null, Calendar.HOUR_OF_DAY));
		JSpinner.DateEditor de = new JSpinner.DateEditor(entradaSpinner, "dd-MM-yyyy");
		entradaSpinner.setEditor(de);
	    }
	});
	comienzoCalendar.setBounds(176, 85, 194, 129);
	comienzoCalendar.setDate((Date) entradaSpinner.getValue());
	jornadaPanel.add(comienzoCalendar);

	finCalendar = new JCalendar();
	finCalendar.addPropertyChangeListener("calendar", new PropertyChangeListener() {
	    public void propertyChange(PropertyChangeEvent evt) {
		Calendar c = (Calendar) evt.getNewValue();
		salidaSpinner.setModel(new SpinnerDateModel(c.getTime(), null, null, Calendar.HOUR_OF_DAY));
		JSpinner.DateEditor de = new JSpinner.DateEditor(salidaSpinner, "dd-MM-yyyy");
		salidaSpinner.setEditor(de);
	    }
	});
	finCalendar.setBounds(395, 85, 194, 127);
	finCalendar.setDate((Date) salidaSpinner.getValue());
	jornadaPanel.add(finCalendar);

	horaEntradaSpinner = new JSpinner();
	horaEntradaSpinner.setModel(new SpinnerDateModel(comienzoCalendar.getDate(), null, null, Calendar.HOUR_OF_DAY));
	JSpinner.DateEditor he = new JSpinner.DateEditor(horaEntradaSpinner, "HH:mm");
	horaEntradaSpinner.setEditor(he);
	horaEntradaSpinner.setBounds(176, 252, 50, 20);
	jornadaPanel.add(horaEntradaSpinner);

	horaSalidaSpinner = new JSpinner();
	horaSalidaSpinner.setModel(new SpinnerDateModel(finCalendar.getDate(), null, null, Calendar.HOUR_OF_DAY));
	he = new JSpinner.DateEditor(horaSalidaSpinner, "HH:mm");
	horaSalidaSpinner.setEditor(he);
	horaSalidaSpinner.setBounds(395, 252, 50, 20);
	jornadaPanel.add(horaSalidaSpinner);

	modeloTabla = new NoEditableTableModel(new String[] { "ID", "Nombre" }, 0);
	Object[] fila = new Object[1];
	fila[0] = "Pepe";
	modeloTabla.addRow(fila);
    }

}
