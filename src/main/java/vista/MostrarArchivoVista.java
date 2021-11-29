package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.GridLayout;

public class MostrarArchivoVista extends JDialog {

	private static final long serialVersionUID = -1349575877007315928L;
	private final JPanel contentPanel = new JPanel();
	
	private JLabel lblImage;

	/**
	 * Create the dialog.
	 */
	public MostrarArchivoVista() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(0, 1, 0, 0));
		contentPanel.add(getLblImage());
	}
	
	public JLabel getLblImage() {
		if(lblImage == null) {
			lblImage = new JLabel("");
		}
		return lblImage;
	}

}
