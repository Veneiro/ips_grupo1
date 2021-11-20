package vista;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import lombok.Getter;

@Getter
public class EDOVista extends JDialog {
    public EDOVista() {
	setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	setResizable(false);
	setModal(true);
	setBounds(0, 0, 450, 300);

	contentPane = new JPanel();
	setContentPane(contentPane);
	contentPane.setLayout(null);

	lblEDO = new JLabel("Enfermedad de Declaraci\u00F3n Obligatioria");
	lblEDO.setBounds(10, 11, 417, 17);
	lblEDO.setHorizontalAlignment(SwingConstants.CENTER);
	lblEDO.setFont(new Font("Tahoma", Font.BOLD, 14));
	contentPane.add(lblEDO);

	separator = new JSeparator();
	separator.setBounds(20, 39, 400, 2);
	contentPane.add(separator);

	lblNombre = new JLabel("Nombre: ");
	lblNombre.setBounds(30, 55, 50, 14);
	lblNombre.setFont(new Font("Tahoma", Font.BOLD, 11));
	contentPane.add(lblNombre);

	textFieldNombre = new JTextField();
	textFieldNombre.setBounds(110, 52, 301, 20);
	textFieldNombre.setColumns(10);
	contentPane.add(textFieldNombre);

	lblObservaciones = new JLabel("Observaciones");
	lblObservaciones.setBounds(30, 99, 83, 14);
	lblObservaciones.setFont(new Font("Tahoma", Font.BOLD, 11));
	contentPane.add(lblObservaciones);

	btnAceptar = new JButton("Aceptar");
	btnAceptar.setBounds(356, 227, 71, 23);
	btnAceptar.setEnabled(false);
	contentPane.add(btnAceptar);

	scrollPane = new JScrollPane();
	scrollPane.setBounds(30, 124, 381, 92);
	scrollPane.setViewportBorder(new LineBorder(new Color(0, 0, 0)));
	contentPane.add(scrollPane);

	textPaneObservaciones = new JTextPane();
	scrollPane.setViewportView(textPaneObservaciones);
    }

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JLabel lblEDO;
    private JSeparator separator;
    private JLabel lblNombre;
    private JTextField textFieldNombre;
    private JLabel lblObservaciones;
    private JButton btnAceptar;
    private JScrollPane scrollPane;
    private JTextPane textPaneObservaciones;
}
