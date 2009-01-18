package tailmaster.gui.configuration;

import tailmaster.util.Utils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

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
