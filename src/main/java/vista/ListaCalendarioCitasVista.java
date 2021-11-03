package vista;

import java.awt.BorderLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import controlador.AppointmentControler;
import lombok.Getter;
import modelo.CauseModel;

@Getter
public class ListaCalendarioCitasVista extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField textFieldFecha;
	private JLabel lblSeleccionFecha;
	private JButton btnBuscar;
	private JScrollPane scrollPaneCitas;
	private JPanel panelIntroducirFecha;
	private JTable table;
	private JButton btnGestionarCita;
	
	
	public ListaCalendarioCitasVista() {
		setTitle("iHospital : Calendario");
		inicializar();
	}
	
	public void inicializar() {
		panelIntroducirFecha = new JPanel();
		getContentPane().add(panelIntroducirFecha, BorderLayout.NORTH);
		setBounds(new Rectangle(300, 300, 470, 305));
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		
		lblSeleccionFecha = new JLabel("Introduzca una fecha:");
		lblSeleccionFecha.setHorizontalAlignment(SwingConstants.LEFT);
		panelIntroducirFecha.add(lblSeleccionFecha);
		
		textFieldFecha = new JTextField();
		panelIntroducirFecha.add(textFieldFecha);
		textFieldFecha.setColumns(10);
		
		btnBuscar = new JButton("Buscar");
		panelIntroducirFecha.add(btnBuscar);
		
		btnGestionarCita = new JButton("Gestionar Cita");
		panelIntroducirFecha.add(btnGestionarCita);
		
		scrollPaneCitas = new JScrollPane();
		getContentPane().add(scrollPaneCitas, BorderLayout.CENTER);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
					"Nombre", "Fecha", "Hora Inicio", "Hora fin",
		    		"Informacion", "Acudio"
			}
		));
		scrollPaneCitas.setViewportView(table);
		
	}
}
