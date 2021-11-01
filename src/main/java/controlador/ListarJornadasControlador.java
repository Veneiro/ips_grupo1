package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import javax.swing.JOptionPane;

import dtos.JornadaLaboralDto;
import modelo.JornadaModelo;
import util.SwingUtil;
import vista.ListaJornadasVista;
import vista.ModificarJornadaVista;

public class ListarJornadasControlador {

    private ListaJornadasVista listaJornadasVista;
    private ModificarJornadaVista modificarVista;
    private JornadaModelo modelo_jornada;

    public ListarJornadasControlador() {
	this.listaJornadasVista = new ListaJornadasVista();
	this.modelo_jornada = new JornadaModelo();
    }

    public void inicializar() {

	listaJornadasVista.getBtnModificar().addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		String idJornada = JOptionPane.showInputDialog("Introduzca id de la jornada", "0");
		List<JornadaLaboralDto> lJ = modelo_jornada.findJornadaById(Integer.valueOf(idJornada));

		if (idJornada != null || Integer.valueOf(idJornada) >= 0 || lJ.size() < 1) {
		    modificarVista = new ModificarJornadaVista(Integer.valueOf(idJornada));

		    JornadaLaboralDto j = lJ.get(0);

		    modificarVista.getComienzoCalendar().setDate(j.getDiaComienzo());
		    modificarVista.getFinCalendar().setDate(j.getDiaFin());
		    modificarVista.getEntradaSpinner().setValue(j.getHoraEntrada());
		    modificarVista.getSalidaSpinner().setValue(j.getHoraSalida());
		    modificarVista.getLunesCheckBox().setSelected(j.isLunes());
		    modificarVista.getMartesCheckBox().setSelected(j.isMartes());
		    modificarVista.getMiercolesCheckBox().setSelected(j.isMiercoles());
		    modificarVista.getJuevesCheckBox().setSelected(j.isJueves());
		    modificarVista.getViernesCheckBox().setSelected(j.isViernes());
		    modificarVista.getSabadoCheckBox().setSelected(j.isSabado());
		    modificarVista.getDomingoCheckBox().setSelected(j.isDomingo());

		    modificarVista.getConfirmarButton()
			    .addActionListener(ex -> SwingUtil.exceptionWrapper(() -> modificarDiaLaboral()));

		    modificarVista.setVisible(true);
		} else {
		    JOptionPane.showMessageDialog(listaJornadasVista, "Id de jornada inválido.");
		}
	    }
	});

	listaJornadasVista.setVisible(true);
    }

    private void modificarDiaLaboral() {
	DateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);

	JornadaLaboralDto j;

	try {
	    j = new JornadaLaboralDto(
		    modificarVista.getModeloTabla().getValueAt(modificarVista.getTableEmpleados().getSelectedRow(), 1)
			    .toString(),
		    (dateFormat.parse(modificarVista.getComienzoCalendar().getDate().toString())),
		    (dateFormat.parse(modificarVista.getFinCalendar().getDate().toString())),
		    (dateFormat.parse(modificarVista.getHoraEntradaSpinner().getValue().toString())),
		    (dateFormat.parse(modificarVista.getHoraSalidaSpinner().getValue().toString())),
		    modificarVista.getLunesCheckBox().isSelected(), modificarVista.getMartesCheckBox().isSelected(),
		    modificarVista.getMiercolesCheckBox().isSelected(), modificarVista.getJuevesCheckBox().isSelected(),
		    modificarVista.getViernesCheckBox().isSelected(), modificarVista.getSabadoCheckBox().isSelected(),
		    modificarVista.getDomingoCheckBox().isSelected());

	    if (j.getDiaFin().before(j.getDiaComienzo())
		    || (j.getDiaComienzo().equals(j.getDiaFin()) && j.getHoraSalida().before(j.getHoraEntrada())))
		JOptionPane.showMessageDialog(modificarVista, "La fecha de comienzo debe ser anterior a la de fin.");
	    else {
		modelo_jornada.addJornada(j);
		JOptionPane.showMessageDialog(modificarVista, "Jornada actualizada correctamente.");
	    }
	} catch (ParseException e) {
	    e.printStackTrace();
	}
    }

}
