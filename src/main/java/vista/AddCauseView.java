package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.Arrays;

import javax.swing.AbstractListModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controlador.PacienteControlador;
import dtos.CauseDto;
import modelo.CauseModel;

@SuppressWarnings({ "serial", "rawtypes", "unused", "unchecked" })
public class AddCauseView extends JDialog {
	private JPanel contentPane;
	private JButton btnSave;
	private JPanel panel;
	private JPanel panel_1;
	private JPanel panel_2;
	private JPanel panel_5;
	private JPanel panel_6;
	private JLabel lblNewLabel;
	private JSpinner spHour;
	private JSpinner spMin;
	private JLabel lblPoints;
	private JComboBox cbDay;
	private JComboBox cbMonth;
	private JComboBox cbYear;
	private JLabel lblHour;
	private JLabel lblDate;
	private JTextPane txtAddiObs;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_1_1;
	private JLabel lblNewLabel_1_1_1;
	private JPanel panel_3;
	private JList listCauses;
	private JPanel panel_4;
	private JButton btnNonExistence;
	private JPanel panel_7;
	private JLabel lblUsualCauses;
	private JLabel lblNewLabel_1_2;
	private JLabel lblNewLabel_1_1_2;
	private JButton btnGoBack;
	private CauseDto cdto = new CauseDto();
	private CauseModel cmo = new CauseModel();
	private NonExistenceView nev;
	private JScrollPane scrollPane;
	LocalDateTime ldt = LocalDateTime.now();

	/**
	 * Create the frame.
	 */
	public AddCauseView() {
		setTitle("iHospital : A\u00F1adir Causa");
		setBounds(100, 100, 500, 340);
		setModal(true);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getBtnGoBack(), BorderLayout.NORTH);
		contentPane.add(getBtnSave(), BorderLayout.SOUTH);
		contentPane.add(getPanel(), BorderLayout.CENTER);
	}

	public JButton getBtnSave() {
		if (btnSave == null) {
			btnSave = new JButton("Guardar Informaci\u00F3n de la Cita");
			btnSave.setBackground(Color.GREEN);
		}
		return btnSave;
	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setLayout(new GridLayout(3, 0, 0, 0));
			panel.add(getPanel_3_1());
			panel.add(getPanel_1());
			panel.add(getPanel_2());
		}
		return panel;
	}

	private JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			panel_1.setLayout(new GridLayout(0, 2, 0, 0));
			panel_1.add(getPanel_5());
			panel_1.add(getPanel_6());
		}
		return panel_1;
	}

	private JPanel getPanel_2() {
		if (panel_2 == null) {
			panel_2 = new JPanel();
			panel_2.setLayout(null);
			panel_2.add(getLblNewLabel());
			panel_2.add(getTxtAddiObs());
		}
		return panel_2;
	}

	private JPanel getPanel_5() {
		if (panel_5 == null) {
			panel_5 = new JPanel();
			panel_5.setLayout(null);
			panel_5.add(getSpHour());
			panel_5.add(getSpMin());
			panel_5.add(getLblPoints());
			panel_5.add(getLblHour());
			panel_5.add(getLblNewLabel_1_2());
			panel_5.add(getLblNewLabel_1_1_2());
		}
		return panel_5;
	}

	private JPanel getPanel_6() {
		if (panel_6 == null) {
			panel_6 = new JPanel();
			panel_6.setLayout(null);
			panel_6.add(getCbDay());
			panel_6.add(getCbMonth());
			panel_6.add(getCbYear());
			panel_6.add(getLblDate());
			panel_6.add(getLblNewLabel_1());
			panel_6.add(getLblNewLabel_1_1());
			panel_6.add(getLblNewLabel_1_1_1());
		}
		return panel_6;
	}

	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("Observaciones Adicionales:");
			lblNewLabel.setBounds(10, 2, 454, 14);
		}
		return lblNewLabel;
	}

	public JSpinner getSpHour() {
		if (spHour == null) {
			spHour = new JSpinner();
			spHour.setValue(ldt.getHour());
			spHour.setBounds(67, 54, 49, 20);
		}
		return spHour;
	}

	public JSpinner getSpMin() {
		if (spMin == null) {
			spMin = new JSpinner();
			spMin.setValue(ldt.getMinute());
			spMin.setBounds(131, 55, 49, 20);
		}
		return spMin;
	}

	private JLabel getLblPoints() {
		if (lblPoints == null) {
			lblPoints = new JLabel(":");
			lblPoints.setBounds(123, 64, 46, 14);
		}
		return lblPoints;
	}

	public JComboBox getCbDay() {
		if (cbDay == null) {
			cbDay = new JComboBox();
			cbDay.setBounds(23, 53, 41, 22);
			DefaultComboBoxModel dcb = new DefaultComboBoxModel();
			for (int i = 1; i <= 31; i++) {
				dcb.addElement(i);
			}
			cbDay.setModel(dcb);
			cbDay.setSelectedItem(ldt.getDayOfMonth());
		}
		return cbDay;
	}

	public JComboBox getCbMonth() {
		if (cbMonth == null) {
			cbMonth = new JComboBox();
			cbMonth.setBounds(85, 53, 46, 22);
			DefaultComboBoxModel dcb = new DefaultComboBoxModel();
			for (int i = 1; i <= 12; i++) {
				dcb.addElement(i);
			}
			cbMonth.setModel(dcb);
			cbMonth.setSelectedItem(ldt.getMonthValue());
		}
		return cbMonth;
	}

	public JComboBox getCbYear() {
		if (cbYear == null) {
			cbYear = new JComboBox();
			cbYear.setBounds(152, 53, 55, 22);
			DefaultComboBoxModel dcb = new DefaultComboBoxModel();
			for (int i = 1900; i <= 2021; i++) {
				dcb.addElement(i);
			}
			cbYear.setModel(dcb);
			cbYear.setSelectedItem(ldt.getYear());
		}
		return cbYear;
	}

	private JLabel getLblHour() {
		if (lblHour == null) {
			lblHour = new JLabel("Hora");
			lblHour.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblHour.setHorizontalAlignment(SwingConstants.CENTER);
			lblHour.setBounds(35, 11, 174, 28);
		}
		return lblHour;
	}

	private JLabel getLblDate() {
		if (lblDate == null) {
			lblDate = new JLabel("Fecha");
			lblDate.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblDate.setHorizontalAlignment(SwingConstants.CENTER);
			lblDate.setBounds(23, 11, 184, 28);
		}
		return lblDate;
	}

	public JTextPane getTxtAddiObs() {
		if (txtAddiObs == null) {
			txtAddiObs = new JTextPane();
			txtAddiObs.setBounds(10, 23, 454, 58);
		}
		return txtAddiObs;
	}

	private JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("D\u00EDa");
			lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_1.setBounds(23, 38, 41, 14);
		}
		return lblNewLabel_1;
	}

	private JLabel getLblNewLabel_1_1() {
		if (lblNewLabel_1_1 == null) {
			lblNewLabel_1_1 = new JLabel("Mes");
			lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_1_1.setBounds(85, 38, 46, 14);
		}
		return lblNewLabel_1_1;
	}

	private JLabel getLblNewLabel_1_1_1() {
		if (lblNewLabel_1_1_1 == null) {
			lblNewLabel_1_1_1 = new JLabel("A\u00F1o");
			lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_1_1_1.setBounds(152, 38, 55, 14);
		}
		return lblNewLabel_1_1_1;
	}

	private JPanel getPanel_3_1() {
		if (panel_3 == null) {
			panel_3 = new JPanel();
			panel_3.setLayout(new GridLayout(0, 2, 0, 0));
			panel_3.add(getPanel_7());
			panel_3.add(getPanel_4());
		}
		return panel_3;
	}

	public JList getListCauses() {
		if (listCauses == null) {
			listCauses = new JList();
			
			DefaultListModel<String> model = new DefaultListModel<>();
			model.addAll(Arrays.asList("LUMBAGIA",
					"INFECCI\u00D3N GASTROINTESTINAL", "DOLOR DE CABEZA",
					"PROBLEMA EMOCIONAL", "INFECCI\u00D3N RESPIRATORIA",
					"LESI\u00D3N DERMATOL\u00D3GICA",
					"HIPERTENSI\u00D3N ARTERIAL", "DIABETES MELLITUS",
					"DIAGN\u00D3STICO DE EMBARAZO", "LESI\u00D3N DEPORTIVA",
					"PROBLEMAS SEXUALES", "ESTRE\u00D1IMIENTO",
					"ARDOR DE EST\u00D3MAGO", "DOLOR TOR\u00C1CICO",
					"INFECCI\u00D3N URINARIA", "OSTEOARTROSIS", "INSOMNIO",
					"ANSIEDAD Y/O DEPRESI\u00D3N", "CA\u00CDDA"));
			
			listCauses.setModel(model);
		}
		return listCauses;
	}

	private JPanel getPanel_4() {
		if (panel_4 == null) {
			panel_4 = new JPanel();
			panel_4.setLayout(null);
			panel_4.add(getBtnNonExistence());
		}
		return panel_4;
	}

	public JButton getBtnNonExistence() {
		if (btnNonExistence == null) {
			btnNonExistence = new JButton("A\u00F1adir Causa no Existente");
			btnNonExistence.setBackground(Color.YELLOW);
			btnNonExistence.setBounds(10, 29, 217, 23);
		}
		return btnNonExistence;
	}

	private JPanel getPanel_7() {
		if (panel_7 == null) {
			panel_7 = new JPanel();
			panel_7.setLayout(null);
			panel_7.add(getLblUsualCauses());
			panel_7.add(getScrollPane());
		}
		return panel_7;
	}

	private JLabel getLblUsualCauses() {
		if (lblUsualCauses == null) {
			lblUsualCauses = new JLabel("Causas Comunes");
			lblUsualCauses.setBounds(10, 0, 237, 20);
		}
		return lblUsualCauses;
	}

	private JLabel getLblNewLabel_1_2() {
		if (lblNewLabel_1_2 == null) {
			lblNewLabel_1_2 = new JLabel("Hora");
			lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_1_2.setBounds(67, 38, 49, 14);
		}
		return lblNewLabel_1_2;
	}

	private JLabel getLblNewLabel_1_1_2() {
		if (lblNewLabel_1_1_2 == null) {
			lblNewLabel_1_1_2 = new JLabel("Min");
			lblNewLabel_1_1_2.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_1_1_2.setBounds(133, 38, 49, 14);
		}
		return lblNewLabel_1_1_2;
	}

	private JButton getBtnGoBack() {
		if (btnGoBack == null) {
			btnGoBack = new JButton("Volver");
			btnGoBack.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
				}
			});
			btnGoBack.setBackground(Color.RED);
		}
		return btnGoBack;
	}

	public void setNev(NonExistenceView ne) {
		this.nev = ne;
	}

	public Dialog getNev() {
		return this.nev;
	}

	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(0, 22, 237, 59);
			scrollPane.setViewportView(getListCauses());
		}
		return scrollPane;
	}
}
