package tailmaster.gui.configuration;

import tailmaster.dao.LogFileDao;
import tailmaster.dao.ServerDao;
import tailmaster.model.LocationType;
import tailmaster.model.LogFile;
import tailmaster.model.Server;
import tailmaster.util.JTableUtils;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

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
        logFileTable.getSelectionModel().addListSelectionListener(new RowSelectionListener(logFileTable));
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

    private class RowSelectionListener implements ListSelectionListener {
        private JTable table;

        public RowSelectionListener(JTable table) {
            this.table = table;
        }

        public void valueChanged(ListSelectionEvent e) {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) return;
            
            String logId = (String) table.getModel().getValueAt(selectedRow, 0);
            String logName = (String) table.getModel().getValueAt(selectedRow, 1);
            String host = (String) table.getModel().getValueAt(selectedRow, 2);
            String filePath = (String) table.getModel().getValueAt(selectedRow, 3);

            poýpulateLogFileConfigurationForm(logId, logName, host, filePath);
        }

        private void poýpulateLogFileConfigurationForm(String logId, String logName, String host, String filePath) {
            LogFileConfigurationForm configurationForm = LogFileConfigurationForm.getInstance();
            configurationForm.setLogId(Integer.parseInt(logId));
            configurationForm.getLogNameTextField().setText(logName);
            configurationForm.getFilePathTextField().setText(filePath);

            LogFile logFile = LogFileDao.getInstance().findById(Integer.parseInt(logId));
            Server server = ServerDao.getInstance().findById(logFile.getServerId());
            JComboBox locationCombo = configurationForm.getLocationComboBox();
            JComboBox serverCombo = configurationForm.getServerIdComboBox();
            if (LocationType.LOCAL.name().equals(host)) {
                locationCombo.setSelectedItem(LocationType.LOCAL.name());
                serverCombo.setEnabled(false);
            } else {
                locationCombo.setSelectedItem(LocationType.REMOTE.name());
                serverCombo.setSelectedItem(server);
            }
        }
    }
}