package controlador;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

import modelo.VacunaModelo;
import util.SwingUtil;
import vista.NuevaVacunaVista;

public class NuevaVacunaControlador {

	
	private int idPaciente;
	private VacunaModelo vm;
	private HistorialControlador hc;
	private NuevaVacunaVista nvv;
	
	public NuevaVacunaControlador(int idPaciente, HistorialControlador hc) {
		this.hc = hc;
		this.idPaciente = idPaciente;
		this.nvv = new NuevaVacunaVista();
		this.vm  = new VacunaModelo();
		
		inicializarVista();
	}

	private void inicializarVista() {
		nvv.setVisible(true);
	}
	
	public void inicializar() {
		nvv.getTextHora().setText(
				String.valueOf(LocalDateTime.now().getHour()) + ":" + String.valueOf(LocalDateTime.now().getMinute()));
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		Date date = new Date(System.currentTimeMillis());
		nvv.getTextFecha().setText(formatter.format(date));
		nvv.getBtnAceptar().addActionListener(e -> SwingUtil.exceptionWrapper(() -> insertarVacuna()));
	}

	private void insertarVacuna() {
		String hora = nvv.getTextHora().getText();
		String vacuna = nvv.getTextVacuna().getText();
		String fecha = nvv.getTextFecha().getText();
		
		
		vm.addVacuna(idPaciente, fecha, hora, vacuna);
		hc.cargarHistorial(idPaciente);
		nvv.setVisible(false);
	}
}
