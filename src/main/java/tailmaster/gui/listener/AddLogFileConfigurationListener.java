package tailmaster.gui.listener;

import tailmaster.gui.configuration.LogFileConfigurationForm;
import tailmaster.gui.configuration.ConfigurationTableModel;
import tailmaster.dao.LogFileDao;
import tailmaster.dao.ServerDao;
import tailmaster.model.LogFile;
import tailmaster.model.Server;
import tailmaster.util.JTableUtils;
import tailmaster.util.JComboBoxUtils;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.ArrayList;

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
		 LogFileDao serverDao = LogFileDao.getInstance();
        try {
            String name = logFileConfigurationForm.getLogNameTextField().getText();
			int serverId = JComboBoxUtils.getSelectedServerId(logFileConfigurationForm.getServerIdComboBox());
            String filePath = logFileConfigurationForm.getFilePathTextField().getText();
            serverDao.insert(new LogFile(serverId, name, filePath));

            ConfigurationTableModel tableModel = (ConfigurationTableModel) logFileTable.getModel();
            tableModel.setDataVector(JTableUtils.getLogFileList(), JTableUtils.getLogFileColumnTypes());
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(logFileConfigurationForm.getRootPane(), "Unable to save", "Error", JOptionPane.ERROR_MESSAGE);
        }
	}
}
