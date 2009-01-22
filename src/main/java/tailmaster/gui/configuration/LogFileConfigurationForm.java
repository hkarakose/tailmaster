package tailmaster.gui.configuration;

import tailmaster.util.JComboBoxUtils;
import tailmaster.model.LocationType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

/**
 * User: Halil KARAKOSE
 * Date: Jan 14, 2009
 * Time: 00:29:53
 */
public class LogFileConfigurationForm extends JPanel {
	private JLabel logNameLabel;
	private JTextField logNameTextField;
	private JLabel locationLabel;
	private JComboBox locationComboBox;
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
		locationLabel = new JLabel();
		locationComboBox = getLocationTypeComboBoxItems();
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

		locationLabel.setText("File Location:");
		gridBagConstraints = new  GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.insets = new  Insets(2, 2, 2, 2);
		add(locationLabel, gridBagConstraints);
		gridBagConstraints = new  GridBagConstraints();
		gridBagConstraints.fill =  GridBagConstraints.HORIZONTAL;
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.insets = new  Insets(2, 2, 2, 2);
		add(locationComboBox, gridBagConstraints);

		serverIdLabel.setText("Server:");
		gridBagConstraints = new  GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.insets = new  Insets(2, 2, 2, 2);
		add(serverIdLabel, gridBagConstraints);
		gridBagConstraints = new  GridBagConstraints();
		gridBagConstraints.fill =  GridBagConstraints.HORIZONTAL;
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.insets = new  Insets(2, 2, 2, 2);
		add(serverIdComboBox, gridBagConstraints);

		filePathLabel.setText("File Path:");
		gridBagConstraints = new  GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 3;
		gridBagConstraints.insets = new  Insets(2, 2, 2, 2);
		add(filePathLabel, gridBagConstraints);
		gridBagConstraints = new  GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 3;
		gridBagConstraints.fill =  GridBagConstraints.HORIZONTAL;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.insets = new  Insets(2, 2, 2, 2);
		add(filePathTextField, gridBagConstraints);
	}

	private JComboBox getLocationTypeComboBoxItems() {
		JComboBox comboBox = new JComboBox();
		for (LocationType type : LocationType.values()) {
			comboBox.addItem(type.name());
		}
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				String selectedItem = (String) e.getItem();
				if (LocationType.valueOf(selectedItem) == LocationType.LOCAL) {
					serverIdComboBox.setEnabled(false);
				} else {
					serverIdComboBox.setEnabled(true);
				}
			}
		});
		return comboBox;
	}

	public JTextField getLogNameTextField() {
		return logNameTextField;
	}

	public JComboBox getLocationComboBox() {
		return locationComboBox;
	}

	public JComboBox getServerIdComboBox() {
		return serverIdComboBox;
	}

	public JTextField getFilePathTextField() {
		return filePathTextField;
	}
}