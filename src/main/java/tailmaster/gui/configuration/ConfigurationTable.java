package tailmaster.gui.configuration;

import javax.swing.*;

/**
 * User: Halil KARAKOSE
 * Date: Jan 16, 2009
 * Time: 10:41:58 AM
 */
public class ConfigurationTable extends JTable {
	public ConfigurationTable(Object[][] data, String[] columnList, Class[] columnTypes) {
		setModel(new ConfigurationTableModel(data, columnList, columnTypes));
	}

	public boolean isCellEditable(int rowIndex, int vColIndex) {
		return false;
	}
}
