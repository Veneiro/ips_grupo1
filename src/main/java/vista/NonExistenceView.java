package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import lombok.Getter;

@SuppressWarnings("serial")
@Getter
public class NonExistenceView extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private String cause;
	private JTextPane textPane;

	/**
	 * Create the dialog.
	 */
	public NonExistenceView() {
		setTitle("iHospital : A\u00F1adir Causa No Existente");
		setModal(true);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			textPane = new JTextPane();
			contentPanel.add(textPane);
		}
		{
			JLabel lblTitle = new JLabel("Type here the New Cause");
			lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
			contentPanel.add(lblTitle, BorderLayout.NORTH);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Add");
				okButton.setMnemonic('A');
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						cause = textPane.getText();
						setVisible(false);
					}
				});
				{
					JButton btnBack = new JButton("Back");
					btnBack.setMnemonic('B');
					btnBack.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							setVisible(false);
						}
					});
					buttonPane.add(btnBack);
				}
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}

}
