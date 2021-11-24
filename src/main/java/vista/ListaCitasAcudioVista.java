package vista;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import lombok.Getter;

@Getter
public class ListaCitasAcudioVista extends JDialog {

	private static final long serialVersionUID = 8527067274039121448L;
	private JLabel lblCitas;
	private JButton btnAcudio;
	private JButton btnHistorial;
	private JScrollPane scrollPane;
	private JTable tableCitas;

	public ListaCitasAcudioVista() {
		setTitle("iHospital : Lista Citas Acudidas");
		initialize();
	}

	public void initialize() {
		setBounds(new Rectangle(300, 300, 470, 300));
		setModal(true);
		setResizable(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout(0, 0));

		lblCitas = new JLabel("Citas:");
		lblCitas.setFont(new Font("Tahoma", Font.PLAIN, 16));
		getContentPane().add(lblCitas, BorderLayout.NORTH);

		JPanel panelBotones = new JPanel();
		getContentPane().add(panelBotones, BorderLayout.EAST);
		panelBotones.setLayout(new GridLayout(0, 2, 0, 0));

		btnAcudio = new JButton("Acudio");
		panelBotones.add(btnAcudio);

		btnHistorial = new JButton("Historial");
		panelBotones.add(btnHistorial);

		scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);

		tableCitas = new JTable();
		scrollPane.setViewportView(tableCitas);

	}
}
