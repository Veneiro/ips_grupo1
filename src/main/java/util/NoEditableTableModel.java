package util;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class NoEditableTableModel extends DefaultTableModel implements TableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoEditableTableModel(Object[] Columnas, int filas) {
		super(Columnas, filas);
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}
}
