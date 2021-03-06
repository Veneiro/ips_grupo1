package vista;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.FlowLayout;

public class AprobarCitasVista extends JDialog {

	private static final long serialVersionUID = 4214030988752432070L;
	private JLabel lblCitas;
	private JTable table;
	private JScrollPane scrollPane;
	private JButton btnConfirmar;
	private JPanel panel;
	private JButton btnBack;
	private JButton btnModificar;
	private JButton btnHelp;

	/**
	 * Create the dialog.
	 */
	public AprobarCitasVista() {
		setBounds(100, 100, 864, 546);

		getContentPane().add(getLblCitas(), BorderLayout.NORTH);

		getScrollPane().setViewportView(getTable());
		getContentPane().add(getScrollPane(), BorderLayout.CENTER);
		getContentPane().add(getPanel(), BorderLayout.SOUTH);
	}

	private JLabel getLblCitas() {
		if (lblCitas == null) {
			lblCitas = new JLabel("Citas pendientes de aprobaci\u00F3n");
			lblCitas.setForeground(Color.RED);
			lblCitas.setFont(new Font("Tahoma", Font.BOLD, 18));
			lblCitas.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblCitas;
	}

	public JTable getTable() {
		if (table == null) {
			table = new JTable();
			table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "ID Cita", "Hora Inicio", "Hora fin",
					"Ubicaci?n", "Nombre Paciente", "Nombre M?dico", "Contacto M?dico", "Aprobada" }));
		}
		return table;
	}

	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
		}
		return scrollPane;
	}

	public JButton getBtnConfirmar() {
		if (btnConfirmar == null) {
			btnConfirmar = new JButton("Confirmar");
			btnConfirmar.setMnemonic('C');
			btnConfirmar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		}
		return btnConfirmar;
	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setLayout(new FlowLayout(FlowLayout.TRAILING, 5, 5));
			panel.add(getBtnHelp());
			panel.add(getBtnModificar());
			panel.add(getBtnBack());
			panel.add(getBtnConfirmar());
		}
		return panel;
	}

	public JButton getBtnBack() {
		if (btnBack == null) {
			btnBack = new JButton("Atr\u00E1s");
			btnBack.setFont(new Font("Tahoma", Font.PLAIN, 14));
		}
		return btnBack;
	}
	public JButton getBtnModificar() {
		if (btnModificar == null) {
			btnModificar = new JButton("Modificar Cita");
			btnModificar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		}
		return btnModificar;
	}
	public JButton getBtnHelp() {
		if (btnHelp == null) {
			btnHelp = new JButton("Help");
			btnHelp.setFont(new Font("Tahoma", Font.PLAIN, 14));
		}
		return btnHelp;
	}
}
