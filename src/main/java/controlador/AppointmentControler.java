package controlador;

import javax.swing.DefaultListModel;

import dtos.CauseDto;
import modelo.CauseModel;
import util.SwingUtil;
import vista.AddCauseView;
import vista.NonExistenceView;

public class AppointmentControler {

	private AddCauseView vista_causa;
	private CauseModel modelo_causa;
	private NonExistenceView ne;

	public AppointmentControler(CauseModel causaModel, AddCauseView causeView,
			NonExistenceView nonExistenceView) {
		this.vista_causa = causeView;
		this.modelo_causa = causaModel;
		this.ne = nonExistenceView;
	}

	public void initialize() {
		vista_causa.getBtnSave().addActionListener(
				e -> SwingUtil.exceptionWrapper(() -> insertToDB()));
		vista_causa.getBtnNonExistence().addActionListener(
				e -> SwingUtil.exceptionWrapper(() -> addNewCause()));
		vista_causa.setLocationRelativeTo(null);
		vista_causa.setVisible(true);
	}

	public void showNonExistence() {
		ne.setLocationRelativeTo(null);
		ne.setVisible(true);
	}

	@SuppressWarnings("unchecked")
	public void addNewCause() {
		showNonExistence();

		@SuppressWarnings("rawtypes")
		DefaultListModel d = (DefaultListModel) vista_causa.getListCauses()
				.getModel();
		d.addElement(ne.getCause());
	}

	public void insertToDB() {
		CauseDto cdto = new CauseDto();
		String obs = vista_causa.getTxtAddiObs().getText();
		cdto.setOBSERVATIONS(obs);

		cdto.setNAME(vista_causa.getListCauses().getSelectedValue().toString());
		String hour = vista_causa.getSpHour().getValue().toString();
		String min = vista_causa.getSpMin().getValue().toString();
		String HOUR = hour + " : " + min;
		cdto.setHOUR(HOUR);

		String day = vista_causa.getCbDay().getSelectedItem().toString();
		String month = vista_causa.getCbMonth().getSelectedItem().toString();
		String year = vista_causa.getCbYear().getSelectedItem().toString();
		String DATE = day + " / " + month + " / " + year;
		cdto.setDATE(DATE);
		modelo_causa.insertCause(cdto);
		vista_causa.setVisible(false);
	}
}
