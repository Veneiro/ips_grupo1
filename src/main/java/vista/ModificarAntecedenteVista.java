package vista;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import lombok.Getter;

@Getter
public class ModificarAntecedenteVista extends JDialog {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable tableAntecedentes;
	private JButton btnModificar;
	
	public ModificarAntecedenteVista() {
		setBounds(new Rectangle(300, 300, 502, 324));
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		btnModificar = new JButton("Modificar");
		getContentPane().add(btnModificar, BorderLayout.EAST);
		
		JLabel lblVancunas = new JLabel("Seleccione el antecedente que quiere modificar:");
		lblVancunas.setFont(new Font("Tahoma", Font.PLAIN, 14));
		getContentPane().add(lblVancunas, BorderLayout.NORTH);
		
		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		tableAntecedentes = new JTable();
		scrollPane.setViewportView(tableAntecedentes);
		
	}
}
