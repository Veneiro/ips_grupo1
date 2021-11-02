package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.swing.JOptionPane;

import dtos.JornadaLaboralDto;
import modelo.JornadaModelo;
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
    private JornadaLaboralDto jornada;

    private String idJornada;

    public ListarJornadasControlador() {
	this.listaJornadasVista = new ListaJornadasVista();
	this.modelo_jornada = new JornadaModelo();
    }

    public void inicializar() {

	listaJornadasVista.getBtnModificar().addActionListener(new ActionListener() {

	    public void actionPerformed(ActionEvent e) {
		idJornada = JOptionPane.showInputDialog("Introduzca id de la jornada", "0");
		List<JornadaLaboralRecord> lJ = modelo_jornada.findJornadaById(Integer.valueOf(idJornada));

		if (idJornada != null || Integer.valueOf(idJornada) >= 0 || lJ.size() < 1) {
		    modificarVista = new ModificarJornadaVista(Integer.valueOf(idJornada));

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

		    modificarVista.getEntradaSpinner().addChangeListener(
			    ex -> SwingUtil.exceptionWrapper(() -> modificarVista.getComienzoCalendar()
				    .setDate((Date) modificarVista.getEntradaSpinner().getValue())));

		    modificarVista.getSalidaSpinner()
			    .addChangeListener(ex -> SwingUtil.exceptionWrapper(() -> modificarVista.getFinCalendar()
				    .setDate((Date) modificarVista.getSalidaSpinner().getValue())));

		    modificarVista.setVisible(true);
		} else {
		    JOptionPane.showMessageDialog(listaJornadasVista, "Id de jornada inválido.");
		}
	    }
	});

	listaJornadasVista.setVisible(true);
    }

    private void cargarListaJornadas() {
	listaJornadasVista.setModeloTabla(new NoEditableTableModel(new String[] { "Nombre" }, 0));

	if (listaJornadasVista.getTextFieldBuscar().getText().trim().isEmpty()) {
	    for (JornadaLaboralRecord j : modelo_jornada.findAll()) {
		listaJornadasVista.getModeloTabla().addRow(new Object[] { j.getNombre_trabajador() });
	    }
	} else {
	    for (JornadaLaboralRecord j : modelo_jornada.findAll()) {
		listaJornadasVista.getModeloTabla().addRow(new Object[] { j.getNombre_trabajador() });
	    }
	}

	listaJornadasVista.getTableJornadas().setModel(modificarVista.getModeloTabla());

	if (listaJornadasVista.getModeloTabla().getRowCount() == 0)
	    listaJornadasVista.getBtnModificar().setEnabled(false);
	else
	    listaJornadasVista.getBtnModificar().setEnabled(true);
    }

    private void modificarDiaLaboral() {
	DateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);

	JornadaLaboralDto j;

	try {
	    j = new JornadaLaboralDto(jornada.getNombre_trabajador(),
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
		modelo_jornada.updateJornada(Integer.parseInt(idJornada), j);
		JOptionPane.showMessageDialog(modificarVista, "Jornada actualizada correctamente.");
	    }
	} catch (ParseException e) {
	    e.printStackTrace();
	}
    }

}
