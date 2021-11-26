package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
import dtos.RegistroDto;
import dtos.TrabajadorDto;
import modelo.EnfermeroModelo;
import modelo.JornadaModelo;
import modelo.MedicoModelo;
import modelo.RegistroModelo;
import modelo.TrabajadorModelo;
import records.JornadaLaboralRecord;
import records.RecordAssembler;
import util.NoEditableTableModel;
import util.SwingUtil;
import vista.ListaJornadasVista;
import vista.ModificarJornadaVista;

public class ListarJornadasControlador {

    private ListaJornadasVista listaJornadasVista;
    private ModificarJornadaVista modificarVista;
    private JornadaModelo modelo_jornada;
    private TrabajadorModelo modelo_trabajador;
    private JornadaLaboralDto jornada;

    private Map<Integer, Integer> mapTable = new HashMap<>();
    private int idJornada = -1;

    public ListarJornadasControlador() {
	this.listaJornadasVista = new ListaJornadasVista();
	this.modelo_jornada = new JornadaModelo();
	this.modelo_trabajador = new TrabajadorModelo();
    }

    public void inicializar() {

	cargarListaJornadas();

	listaJornadasVista.getTextFieldTrabajador().getDocument().addDocumentListener(new DocumentListener() {

	    @Override
	    public void removeUpdate(DocumentEvent e) {
		cargarListaJornadas();
	    }

	    @Override
	    public void insertUpdate(DocumentEvent e) {
		cargarListaJornadas();
	    }

	    @Override
	    public void changedUpdate(DocumentEvent e) {
		cargarListaJornadas();
	    }
	});

	listaJornadasVista.getChckbxComienzo().addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		cargarListaJornadas();
	    }
	});

	listaJornadasVista.getChckbxFin().addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		cargarListaJornadas();
	    }
	});

	listaJornadasVista.getChckbxEntrada().addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		cargarListaJornadas();
	    }
	});

	listaJornadasVista.getChckbxSalida().addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		cargarListaJornadas();
	    }
	});

	listaJornadasVista.getTable().getSelectionModel().addListSelectionListener(new ListSelectionListener() {

	    @Override
	    public void valueChanged(ListSelectionEvent e) {
		checkConfirmButton();
	    }
	});

	listaJornadasVista.getBtnModificar().addActionListener(new ActionListener() {

	    public void actionPerformed(ActionEvent e) {
		idJornada = mapTable.get(listaJornadasVista.getTable().getSelectedRow());

		List<JornadaLaboralRecord> lJ = modelo_jornada.findJornadaById(Integer.valueOf(idJornada));

		modificarVista = new ModificarJornadaVista(idJornada);

		jornada = RecordAssembler.toDto(lJ.get(0));

		modificarVista.getComienzoCalendar().setDate(jornada.getDia_comienzo());
		modificarVista.getEntradaSpinner().setValue(jornada.getDia_comienzo());
		modificarVista.getFinCalendar().setDate(jornada.getDia_fin());
		modificarVista.getSalidaSpinner().setValue(jornada.getDia_fin());
		modificarVista.getHoraEntradaSpinner().setValue(jornada.getHora_entrada());
		modificarVista.getHoraSalidaSpinner().setValue(jornada.getHora_salida());
		modificarVista.getLunesCheckBox().setSelected(jornada.isLunes());
		modificarVista.getMartesCheckBox().setSelected(jornada.isMartes());
		modificarVista.getMiercolesCheckBox().setSelected(jornada.isMiercoles());
		modificarVista.getJuevesCheckBox().setSelected(jornada.isJueves());
		modificarVista.getViernesCheckBox().setSelected(jornada.isViernes());
		modificarVista.getSabadoCheckBox().setSelected(jornada.isSabado());
		modificarVista.getDomingoCheckBox().setSelected(jornada.isDomingo());

		modificarVista.getConfirmarButton()
			.addActionListener(ex -> SwingUtil.exceptionWrapper(() -> modificarDiaLaboral()));

		modificarVista.getEntradaSpinner()
			.addChangeListener(ex -> SwingUtil.exceptionWrapper(() -> modificarVista.getComienzoCalendar()
				.setDate((Date) modificarVista.getEntradaSpinner().getValue())));

		modificarVista.getSalidaSpinner()
			.addChangeListener(ex -> SwingUtil.exceptionWrapper(() -> modificarVista.getFinCalendar()
				.setDate((Date) modificarVista.getSalidaSpinner().getValue())));

		modificarVista.setVisible(true);

	    }
	});

	RegistroModelo.addRegistro(new RegistroDto("Administrativo", "Consulta lista de jornadas"));

	listaJornadasVista.setVisible(true);
    }

    private void cargarListaJornadas() {
	List<JornadaLaboralRecord> lJ = new ArrayList<>();

	List<JornadaLaboralRecord> aux = new ArrayList<>();

	listaJornadasVista.setModeloTabla(
		new NoEditableTableModel(new String[] { "Trabajador", "Comienzo", "Fin", "Entrada", "Salida" }, 0));

	if (listaJornadasVista.getTextFieldTrabajador().getText().trim().isEmpty()) {
	    lJ = modelo_jornada.findAll();

	} else {

	    lJ = modelo_jornada.findByName(listaJornadasVista.getTextFieldTrabajador().getText());

	}

	if (listaJornadasVista.getChckbxComienzo().isSelected()) {
	    for (JornadaLaboralRecord j : modelo_jornada
		    .findJornadaByComienzo((Date) listaJornadasVista.getEntradaSpinner().getValue())) {
		if (lJ.contains(j))
		    aux.add(j);
	    }
	    lJ = aux;
	    aux = new ArrayList<>();
	}

	if (listaJornadasVista.getChckbxFin().isSelected()) {
	    for (JornadaLaboralRecord j : modelo_jornada
		    .findJornadaByFin((Date) listaJornadasVista.getSalidaSpinner().getValue())) {
		if (lJ.contains(j))
		    aux.add(j);
	    }
	    lJ = aux;
	    aux = new ArrayList<>();
	}

	if (listaJornadasVista.getChckbxEntrada().isSelected()) {
	    for (JornadaLaboralRecord j : modelo_jornada
		    .findJornadaByEntrada((Date) listaJornadasVista.getHoraEntradaSpinner().getValue())) {
		if (lJ.contains(j))
		    aux.add(j);
	    }
	    lJ = aux;
	    aux = new ArrayList<>();
	}

	if (listaJornadasVista.getChckbxSalida().isSelected()) {
	    for (JornadaLaboralRecord j : modelo_jornada
		    .findJornadaBySalida((Date) listaJornadasVista.getHoraSalidaSpinner().getValue())) {
		if (lJ.contains(j))
		    aux.add(j);
	    }
	    lJ = aux;
	    aux = new ArrayList<>();
	}

	int fila = 0;
	for (JornadaLaboralRecord j : lJ) {
	    mapTable.put(fila, j.getId());

	    String nombreTrabajador = "";
	    TrabajadorDto t = modelo_trabajador.findById(j.getId_trabajador()).get(0);

	    if (t.getCategoria().equals("MEDICO")) {
		MedicoDto m = new MedicoModelo().getListaMedicosById(t.getId_medico()).get(0);
		nombreTrabajador = m.getNombre();
	    } else if (t.getCategoria().equals("ENFERMERO")) {
		EnfermeroDto m = new EnfermeroModelo().findById(t.getId_enfermero()).get(0);
		nombreTrabajador = m.getNombre();
	    }

	    listaJornadasVista.getModeloTabla().addRow(new Object[] { nombreTrabajador, j.getDia_comienzo(),
		    j.getDia_fin(), j.getHora_entrada(), j.getHora_salida() });

	    fila++;
	}

	listaJornadasVista.getTable().setModel(listaJornadasVista.getModeloTabla());

	checkConfirmButton();
    }

    private void checkConfirmButton() {
	if (listaJornadasVista.getModeloTabla().getRowCount() == 0
		|| listaJornadasVista.getTable().getSelectionModel().isSelectionEmpty())
	    listaJornadasVista.getBtnModificar().setEnabled(false);
	else
	    listaJornadasVista.getBtnModificar().setEnabled(true);
    }

    private void modificarDiaLaboral() {
	DateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);

	JornadaLaboralDto j;

	try {
	    j = new JornadaLaboralDto(jornada.getId_trabajador(),
		    (dateFormat.parse(modificarVista.getComienzoCalendar().getDate().toString())),
		    (dateFormat.parse(modificarVista.getFinCalendar().getDate().toString())),
		    (dateFormat.parse(modificarVista.getHoraEntradaSpinner().getValue().toString())),
		    (dateFormat.parse(modificarVista.getHoraSalidaSpinner().getValue().toString())),
		    modificarVista.getLunesCheckBox().isSelected(), modificarVista.getMartesCheckBox().isSelected(),
		    modificarVista.getMiercolesCheckBox().isSelected(), modificarVista.getJuevesCheckBox().isSelected(),
		    modificarVista.getViernesCheckBox().isSelected(), modificarVista.getSabadoCheckBox().isSelected(),
		    modificarVista.getDomingoCheckBox().isSelected());

	    if (j.getDia_fin().before(j.getDia_comienzo())
		    || (j.getDia_comienzo().equals(j.getDia_fin()) && j.getHora_salida().before(j.getHora_entrada())))
		JOptionPane.showMessageDialog(modificarVista, "La fecha de comienzo debe ser anterior a la de fin.");
	    else {
		RegistroModelo.addRegistro(new RegistroDto("Administrativo", "Actualiza jornada" + idJornada));

		modelo_jornada.updateJornada(idJornada, j);
		JOptionPane.showMessageDialog(modificarVista, "Jornada actualizada correctamente.");
	    }
	} catch (ParseException e) {
	    e.printStackTrace();
	}
    }

}
