package tailmaster.gui.configuration;

import tailmaster.util.JTableUtils;
import tailmaster.gui.listener.AddLogFileConfigurationListener;
import tailmaster.gui.listener.DeleteLogFileConfigurationListener;
import tailmaster.commons.gui.ClosableJDialog;
import tailmaster.model.LocationType;
import tailmaster.model.Server;
import tailmaster.model.LogFile;
import tailmaster.dao.LogFileDao;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.*;

/**
 * User: Halil KARAKOSE
 * Date: Jan 16, 2009
 * Time: 10:37:19 AM
 */
public class LogFileConfigurationDialog extends ClosableJDialog {
    private JButton saveButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JPanel buttonPanel;
    private LogFileConfigurationForm logFileForm;
    private LogFileConfigurationTablePanel logFileTablePanel;

    public LogFileConfigurationDialog(JRootPane rootPane) {
        super((JFrame) rootPane.getParent(), "Log File Configuration", true);
        initComponents();
        setSize(400, 500);
        setLocationRelativeTo(rootPane);
    }

    private void initComponents() {
        GridBagConstraints gridBagConstraints;

        logFileTablePanel = new LogFileConfigurationTablePanel(JTableUtils.getLogFileList());

        logFileForm = LogFileConfigurationForm.getInstance();
        buttonPanel = new JPanel();
        saveButton = new JButton();
        updateButton = new JButton();
        deleteButton = new JButton();

        setLayout(new GridBagLayout());
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.anchor = GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(logFileTablePanel, gridBagConstraints);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        add(logFileForm, gridBagConstraints);

        saveButton.setText("Save");
        saveButton.addActionListener(new AddLogFileConfigurationListener(logFileForm, logFileTablePanel.getLogFileTable()));
        buttonPanel.add(saveButton);

        updateButton.setText("Update");
        updateButton.addActionListener(new UpdateLogFileActionListener(logFileTablePanel.getLogFileTable()));
        buttonPanel.add(updateButton);

        deleteButton.setText("Delete");
        JTable logFileTable = logFileTablePanel.getLogFileTable();
        deleteButton.addActionListener(new DeleteLogFileConfigurationListener(logFileTable));
        buttonPanel.add(deleteButton);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        add(buttonPanel, gridBagConstraints);
    }

    private class UpdateLogFileActionListener implements ActionListener {
        private JTable table;

        public UpdateLogFileActionListener(JTable logFileTable) {
            this.table = logFileTable;
        }

        public void actionPerformed(ActionEvent e) {
            LogFileConfigurationForm configurationForm = LogFileConfigurationForm.getInstance();
            int logId = configurationForm.getLogId();
            if (logId == 0) {
                return;
            }
            String filePath = configurationForm.getFilePathTextField().getText();
            String locationType = (String) configurationForm.getLocationComboBox().getSelectedItem();
            String logName = configurationForm.getLogNameTextField().getText();
            Server server = (Server) configurationForm.getServerIdComboBox().getSelectedItem();

            if (LocationType.valueOf(locationType) == LocationType.LOCAL) {
                LogFileDao.getInstance().update(new LogFile(0, logName, filePath), logId);
            } else {
                LogFileDao.getInstance().update(new LogFile(server.getId(), logName, filePath), logId);
            }
            ConfigurationTableModel tableModel = (ConfigurationTableModel) table.getModel();
            tableModel.setDataVector(JTableUtils.getLogFileList(), JTableUtils.getLogFileColumnHeaders());
            JOptionPane.showMessageDialog(null, "Update Successfull", "Success", JOptionPane.WARNING_MESSAGE);
        }
    }
}
