package tailmaster.gui.configuration;

import tailmaster.util.Utils;
import tailmaster.gui.listener.AddLogFileConfigurationListener;
import tailmaster.gui.listener.DeleteLogFileConfigurationListener;

import javax.swing.*;

/**
 * User: Halil KARAKOSE
 * Date: Jan 16, 2009
 * Time: 10:37:19 AM
 */
public class LogFileConfigurationDialog extends JDialog{
	private JButton saveButton;
	private JButton updateButton;
	private JButton deleteButton;
	private JPanel buttonPanel;
	private LogFileConfigurationForm logFileForm;
	private LogFileConfigurationTablePanel logFileTablePanel;


	public LogFileConfigurationDialog(JRootPane rootPane) {
		initComponents();
		setTitle("Log File Configuration");
        setModal(true);
        setSize(350,500);
        setLocationRelativeTo(rootPane);
        setVisible(true);
	}

	private void initComponents() {
		java.awt.GridBagConstraints gridBagConstraints;

		logFileTablePanel = new LogFileConfigurationTablePanel(Utils.getLogFileList());

		logFileForm = new LogFileConfigurationForm();
		buttonPanel = new JPanel();
		saveButton = new JButton();
		updateButton = new JButton();
		deleteButton = new JButton();

		setLayout(new java.awt.GridBagLayout());
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 1.0;
		add(logFileTablePanel, gridBagConstraints);
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
		add(logFileForm, gridBagConstraints);

		saveButton.setText("Save");
		saveButton.addActionListener(new AddLogFileConfigurationListener(logFileForm, logFileTablePanel.getLogFileTable()));
		buttonPanel.add(saveButton);

		updateButton.setText("Update");
		buttonPanel.add(updateButton);

		deleteButton.setText("Delete");
		JTable logFileTable = logFileTablePanel.getLogFileTable();
		deleteButton.addActionListener(new DeleteLogFileConfigurationListener(logFileTable));
		buttonPanel.add(deleteButton);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		add(buttonPanel, gridBagConstraints);
	}
}
