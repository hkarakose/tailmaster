package tailmaster.gui.configuration;

import javax.swing.*;
import java.awt.*;

/**
 * User: Halil KARAKOSE
 * Date: Jan 14, 2009
 * Time: 00:29:53
 */
public class LogFileConfigurationForm extends JPanel {
	private JLabel logNameLabel;
	private JTextField logNameTextField;
	private JLabel serverIdLabel;
	private JTextField serverIdTextField;
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
		serverIdTextField = new JTextField();
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
		add(serverIdTextField, gridBagConstraints);

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

	public JTextField getServerIdTextField() {
		return serverIdTextField;
	}

	public JTextField getFilePathTextField() {
		return filePathTextField;
	}
}
