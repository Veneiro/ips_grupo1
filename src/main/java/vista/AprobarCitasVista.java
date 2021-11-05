package vista;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JTable;

public class AprobarCitasVista extends JDialog {
	private JLabel lblCitas;
	private JTable table;

	/**
	 * Create the dialog.
	 */
	public AprobarCitasVista() {
		setBounds(100, 100, 450, 300);
		
		getContentPane().add(getLblCitas(), BorderLayout.NORTH);
		
		getContentPane().add(getTable(), BorderLayout.CENTER);

	}

	private JLabel getLblCitas() {
		if (lblCitas == null) {
			lblCitas = new JLabel("Citas pendientes de aprobaci\u00F3n");
			lblCitas.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblCitas;
	}
	private JTable getTable() {
		if (table == null) {
			table = new JTable();
		}
		return table;
	}
}
