package controlador;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import dtos.EnfermeroDto;
import dtos.JornadaLaboralDto;
import dtos.MedicoDto;
import modelo.EnfermeroModelo;
import modelo.JornadaModelo;
import modelo.MedicoModelo;
import util.NoEditableTableModel;
import util.SwingUtil;
import vista.AsignarJornadaVista;

public class JornadasControlador {

    private AsignarJornadaVista vista_jornadas;
    private EnfermeroModelo modelo_enfermero;
    private MedicoModelo modelo_medico;
    private JornadaModelo modelo_jornada;

    private Map<Integer, String> mapTable = new HashMap<>();

    public JornadasControlador() {
	this.modelo_medico = new MedicoModelo();
	this.modelo_enfermero = new EnfermeroModelo();
	this.modelo_jornada = new JornadaModelo();
	this.vista_jornadas = new AsignarJornadaVista();
    }

    public void inicializar() {
	cargarListaEmpleados();
	vista_jornadas.getTipoEmpleadoComboBox()
		.addActionListener(e -> SwingUtil.exceptionWrapper(() -> cargarListaEmpleados()));

	vista_jornadas.getBuscarTextField().getDocument().addDocumentListener(new DocumentListener() {

	    @Override
	    public void removeUpdate(DocumentEvent e) {
		cargarListaEmpleados();
	    }

	    @Override
	    public void insertUpdate(DocumentEvent e) {
		cargarListaEmpleados();
	    }

	    @Override
	    public void changedUpdate(DocumentEvent e) {
		cargarListaEmpleados();
	    }
	});

	vista_jornadas.getEntradaSpinner().addChangeListener(e -> SwingUtil.exceptionWrapper(() -> vista_jornadas
		.getComienzoCalendar().setDate((Date) vista_jornadas.getEntradaSpinner().getValue())));

	vista_jornadas.getSalidaSpinner().addChangeListener(e -> SwingUtil.exceptionWrapper(
		() -> vista_jornadas.getFinCalendar().setDate((Date) vista_jornadas.getSalidaSpinner().getValue())));

	vista_jornadas.getAnadirButton().addActionListener(e -> SwingUtil.exceptionWrapper(() -> addDiaLaboral()));

	vista_jornadas.getTableEmpleados().getSelectionModel().addListSelectionListener(new ListSelectionListener() {

	    @Override
	    public void valueChanged(ListSelectionEvent e) {
		checkConfirmButton();
	    }
	});

	vista_jornadas.setVisible(true);
    }

    private void cargarListaEmpleados() {
	int fila = 0;

	vista_jornadas.setModeloTabla(new NoEditableTableModel(new String[] { "Nombre" }, 0));

	if (vista_jornadas.getBuscarTextField().getText().trim().isEmpty()) {
	    if (vista_jornadas.getTipoEmpleadoComboBox().getSelectedIndex() == 0) {
		mapTable = new HashMap<>();
		vista_jornadas.setModeloTabla(new NoEditableTableModel(new String[] { "Nombre", "Especialidad" }, 0));
		for (MedicoDto m : modelo_medico.getListaMedicos()) {
		    mapTable.put(fila, m.getNombre());
		    vista_jornadas.getModeloTabla().addRow(new Object[] { m.getNombre(), m.getEspecialidad() });
		}
	    }
	    if (vista_jornadas.getTipoEmpleadoComboBox().getSelectedIndex() == 1) {
		mapTable = new HashMap<>();
		for (EnfermeroDto e : modelo_enfermero.getListaEnfermeros()) {
		    mapTable.put(fila, e.getNombre());
		    vista_jornadas.getModeloTabla().addRow(new Object[] { e.getNombre() });
		}
	    }
	} else {
	    if (vista_jornadas.getTipoEmpleadoComboBox().getSelectedIndex() == 0) {
		vista_jornadas.setModeloTabla(new NoEditableTableModel(new String[] { "Nombre", "Especialidad" }, 0));
		mapTable = new HashMap<>();
		for (MedicoDto m : modelo_medico
			.getListaMedicosBySearch(vista_jornadas.getBuscarTextField().getText())) {
		    mapTable.put(fila, m.getNombre());
		    vista_jornadas.getModeloTabla().addRow(new Object[] { m.getNombre(), m.getEspecialidad() });
		}
	    }
	    if (vista_jornadas.getTipoEmpleadoComboBox().getSelectedIndex() == 1) {
		mapTable = new HashMap<>();
		for (EnfermeroDto e : modelo_enfermero
			.getListaEnfermerosBySearch(vista_jornadas.getBuscarTextField().getText())) {
		    mapTable.put(fila, e.getNombre());
		    vista_jornadas.getModeloTabla().addRow(new Object[] { e.getNombre() });
		}
	    }
	}

	vista_jornadas.getTableEmpleados().setModel(vista_jornadas.getModeloTabla());

	checkConfirmButton();
    }

    private void checkConfirmButton() {
	if (vista_jornadas.getModeloTabla().getRowCount() == 0
		|| vista_jornadas.getTableEmpleados().getSelectionModel().isSelectionEmpty())
	    vista_jornadas.getAnadirButton().setEnabled(false);
	else
	    vista_jornadas.getAnadirButton().setEnabled(true);
    }

    private void addDiaLaboral() {
	DateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);

	JornadaLaboralDto j;

	try {
	    j = new JornadaLaboralDto(mapTable.get(vista_jornadas.getTableEmpleados().getSelectedColumn()),
		    (dateFormat.parse(vista_jornadas.getComienzoCalendar().getDate().toString())),
		    (dateFormat.parse(vista_jornadas.getFinCalendar().getDate().toString())),
		    (dateFormat.parse(vista_jornadas.getHoraEntradaSpinner().getValue().toString())),
		    (dateFormat.parse(vista_jornadas.getHoraSalidaSpinner().getValue().toString())),
		    vista_jornadas.getLunesCheckBox().isSelected(), vista_jornadas.getMartesCheckBox().isSelected(),
		    vista_jornadas.getMiercolesCheckBox().isSelected(), vista_jornadas.getJuevesCheckBox().isSelected(),
		    vista_jornadas.getViernesCheckBox().isSelected(), vista_jornadas.getSabadoCheckBox().isSelected(),
		    vista_jornadas.getDomingoCheckBox().isSelected());

	    if (j.getDia_fin().before(j.getDia_comienzo())
		    || (j.getDia_comienzo().equals(j.getDia_fin()) && j.getHora_salida().before(j.getHora_entrada())))
		JOptionPane.showMessageDialog(vista_jornadas, "La fecha de comienzo debe ser anterior a la de fin.");
	    else {
		modelo_jornada.addJornada(j);
		JOptionPane.showMessageDialog(vista_jornadas, "Jornada añadida correctamente.");
	    }
	} catch (ParseException e) {
	    e.printStackTrace();
	}
    }

}
