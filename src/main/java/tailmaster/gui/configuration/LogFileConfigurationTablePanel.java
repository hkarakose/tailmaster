package tailmaster.gui.configuration;

import tailmaster.gui.configuration.ConfigurationTable;
import tailmaster.util.JTableUtils;

import javax.swing.*;

/**
 * User: Halil KARAKOSE
 * Date: 15.01.2009
 * Time: 11:30:18
 */
public class LogFileConfigurationTablePanel extends JPanel {
	private ConfigurationTable logFileTable;
	private JScrollPane logFileTableScroller;

	public LogFileConfigurationTablePanel(Object[][] data) {
		initComponents(data);
	}

	private void initComponents(Object[][] data) {
        String[] columnList = JTableUtils.getLogFileColumnHeaders();
        logFileTable = new ConfigurationTable(data, columnList, JTableUtils.getLogFileColumnTypes());

		logFileTable.setColumnSelectionAllowed(true);
		logFileTable.getTableHeader().setReorderingAllowed(false);
		logFileTable.setRowSelectionAllowed(true);
		logFileTable.setColumnSelectionAllowed(false);
		logFileTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		logFileTableScroller = new JScrollPane();
		logFileTableScroller.setViewportView(logFileTable);

		GroupLayout layout = new GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(logFileTableScroller, GroupLayout.DEFAULT_SIZE, 410, Short.MAX_VALUE));
		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(logFileTableScroller, GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE));
	}

    public JTable getLogFileTable() {
		return logFileTable;
	}
}