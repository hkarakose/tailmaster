package tailmaster.gui.configuration;

import javax.swing.table.DefaultTableModel;

/**
 * User: Halil KARAKOSE
 * Date: Jan 16, 2009
 * Time: 11:13:48 AM
 */
public class ConfigurationTableModel extends DefaultTableModel {
	Class[] types;

	public ConfigurationTableModel(Object[][] data, String[] columnList, Class[] columnTypes) {
		super(data, columnList);
		this.types = columnTypes;
	}

	public Class getColumnClass(int columnIndex) {
		return types[columnIndex];
	}

}
