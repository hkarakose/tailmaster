package tailmaster.gui.configuration;

import tailmaster.dao.ServerDao;
import tailmaster.model.Server;
import tailmaster.util.JComboBoxUtils;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * User: Halil KARAKOSE
 * Date: Jan 14, 2009
 * Time: 00:29:53
 */
public class LogFileConfigurationForm extends JPanel {
	private JLabel logNameLabel;
	private JTextField logNameTextField;
	private JLabel serverIdLabel;
	private JComboBox serverIdComboBox;
	private JLabel filePathLabel;
	private JTextField filePathTextField;


	public LogFileConfigurationForm() {
		initComponents();
	}

	private void initComponents() {
		GridBagConstraints gridBagConstraints;

		logNameLabel = new JLabel();
		logNameTextField = new JTextField();
		serverIdLabel = new JLabel();
		String[] servers = JComboBoxUtils.getServerArray();
		serverIdComboBox = new JComboBox(servers);
		filePathLabel = new JLabel();
		filePathTextField = new JTextField();

		setLayout(new  GridBagLayout());

		logNameLabel.setText("Log Alias:");
		gridBagConstraints = new  GridBagConstraints();
		gridBagConstraints.ipadx = 2;
		gridBagConstraints.ipady = 2;
		gridBagConstraints.insets = new  Insets(2, 2, 2, 2);
		add(logNameLabel, gridBagConstraints);
		gridBagConstraints = new  GridBagConstraints();
		gridBagConstraints.fill =  GridBagConstraints.HORIZONTAL;
		gridBagConstraints.ipadx = 2;
		gridBagConstraints.ipady = 2;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.insets = new  Insets(2, 2, 2, 2);
		add(logNameTextField, gridBagConstraints);

		serverIdLabel.setText("Server:");
		gridBagConstraints = new  GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.insets = new  Insets(2, 2, 2, 2);
		add(serverIdLabel, gridBagConstraints);
		gridBagConstraints = new  GridBagConstraints();
		gridBagConstraints.fill =  GridBagConstraints.HORIZONTAL;
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.insets = new  Insets(2, 2, 2, 2);
		add(serverIdComboBox, gridBagConstraints);

		filePathLabel.setText("File Path:");
		gridBagConstraints = new  GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.insets = new  Insets(2, 2, 2, 2);
		add(filePathLabel, gridBagConstraints);
		gridBagConstraints = new  GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.fill =  GridBagConstraints.HORIZONTAL;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.insets = new  Insets(2, 2, 2, 2);
		add(filePathTextField, gridBagConstraints);
	}
	
	public JTextField getLogNameTextField() {
		return logNameTextField;
	}

	public JComboBox getServerIdComboBox() {
		return serverIdComboBox;
	}

	public JTextField getFilePathTextField() {
		return filePathTextField;
	}
}