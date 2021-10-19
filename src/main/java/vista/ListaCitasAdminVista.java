package vista;

import java.awt.BorderLayout;
import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import lombok.Getter;

@Getter
public class ListaCitasAdminVista extends JDialog {

	private static final long serialVersionUID = 1L;
	private JTable tableCitas;
	private JButton btnHistorial;
	private JLabel lblCitas;
	private JScrollPane scrollPane;
	
	public ListaCitasAdminVista() {
		setTitle("iHospital : Lista Citas Administrador");
		inicializar();
	}
	
	public void inicializar() {
		setBounds(new Rectangle(300, 300, 470, 305));
		setModal(true);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		lblCitas = new JLabel("Citas:");
		lblCitas.setVerticalAlignment(SwingConstants.BOTTOM);
		lblCitas.setHorizontalAlignment(SwingConstants.LEFT);
		getContentPane().add(lblCitas, BorderLayout.NORTH);
		
		btnHistorial = new JButton("Historial");
		getContentPane().add(btnHistorial, BorderLayout.EAST);
		
		scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		tableCitas = new JTable();
		scrollPane.setViewportView(tableCitas);
	}
}
