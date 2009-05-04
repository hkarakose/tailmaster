package tailmaster.gui.listener;

import tailmaster.dao.LogFileDao;
import tailmaster.gui.configuration.ConfigurationTableModel;
import tailmaster.gui.configuration.LogFileConfigurationForm;
import tailmaster.model.LocationType;
import tailmaster.model.LogFile;
import tailmaster.model.Server;
import tailmaster.util.JTableUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/**
 * User: Halil KARAKOSE
 * Date: Jan 16, 2009
 * Time: 11:22:09 AM
 */
public class AddLogFileConfigurationListener implements ActionListener {
	private LogFileConfigurationForm logFileConfigurationForm;
	private JTable logFileTable;

	public AddLogFileConfigurationListener(LogFileConfigurationForm serverFormPanel, JTable logFileTable) {
		this.logFileConfigurationForm = serverFormPanel;
		this.logFileTable = logFileTable;
	}

	public void actionPerformed(ActionEvent e) {
		 LogFileDao logFileDao = LogFileDao.getInstance();
        try {
            String name = logFileConfigurationForm.getLogNameTextField().getText();
			String locationTypeStr = (String) logFileConfigurationForm.getLocationComboBox().getSelectedItem();
			LocationType locationType = LocationType.valueOf(locationTypeStr);
			int serverId = findServerId(locationType);
            String filePath = logFileConfigurationForm.getFilePathTextField().getText();
            logFileDao.insert(new LogFile(locationType.getLocationTypeId(), serverId, name, filePath));

            ConfigurationTableModel tableModel = (ConfigurationTableModel) logFileTable.getModel();
            tableModel.setDataVector(JTableUtils.getLogFileList(), JTableUtils.getLogFileColumnHeaders());
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(logFileConfigurationForm.getRootPane(), "Unable to save", "Error", JOptionPane.ERROR_MESSAGE);
        }
	}

	private int findServerId(LocationType locationType) {
		if (locationType == LocationType.REMOTE) {
            JComboBox serverComboBox = logFileConfigurationForm.getServerIdComboBox();
            Server server = (Server) serverComboBox.getSelectedItem();
            return server.getId();
		} else {
			return 0; //the file is in the local computer, there is no need for a server info.
		}
	}
}
