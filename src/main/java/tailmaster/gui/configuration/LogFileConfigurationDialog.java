package tailmaster.gui.configuration;

import tailmaster.util.JTableUtils;
import tailmaster.gui.listener.AddLogFileConfigurationListener;
import tailmaster.gui.listener.DeleteLogFileConfigurationListener;
import tailmaster.commons.gui.ClosableJDialog;

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

		logFileForm = new LogFileConfigurationForm();
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
		updateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Not implemented.", "Warning", JOptionPane.WARNING_MESSAGE);
			}
		});
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
}
