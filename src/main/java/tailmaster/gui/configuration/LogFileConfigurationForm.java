package tailmaster.gui.configuration;

import tailmaster.model.LocationType;
import tailmaster.model.Server;
import tailmaster.dao.ServerDao;
import tailmaster.gui.ServerComboBoxRenderer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.util.ArrayList;

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

    private static LogFileConfigurationForm instance;
    private int logId;


    public static LogFileConfigurationForm getInstance() {
        if (instance == null) instance = new LogFileConfigurationForm();
        return instance;
    }

	private LogFileConfigurationForm() {
		initComponents();
	}

	private void initComponents() {
		GridBagConstraints gridBagConstraints;

		logNameLabel = new JLabel();
		logNameTextField = new JTextField();
		locationLabel = new JLabel();
		locationComboBox = getLocationTypeComboBoxItems();
		serverIdLabel = new JLabel();
        ArrayList<Server> serversList = ServerDao.getInstance().findAllSortedByServerName();
        serverIdComboBox = new JComboBox(serversList.toArray());
        serverIdComboBox.setRenderer(new ServerComboBoxRenderer());
		filePathLabel = new JLabel();
		filePathTextField = new JTextField();

		setLayout(new GridBagLayout());

		logNameLabel.setText("Log Alias:");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.ipadx = 2;
		gridBagConstraints.ipady = 2;
		gridBagConstraints.insets = new Insets(2, 2, 2, 2);
		add(logNameLabel, gridBagConstraints);
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints.ipadx = 2;
		gridBagConstraints.ipady = 2;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.insets = new Insets(2, 2, 2, 2);
		add(logNameTextField, gridBagConstraints);

		locationLabel.setText("File Location:");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.insets = new Insets(2, 2, 2, 2);
		add(locationLabel, gridBagConstraints);
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.insets = new Insets(2, 2, 2, 2);
		add(locationComboBox, gridBagConstraints);

		serverIdLabel.setText("Server:");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.insets = new Insets(2, 2, 2, 2);
		add(serverIdLabel, gridBagConstraints);
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.insets = new Insets(2, 2, 2, 2);
		displayServerIdComboBoxIfAvailable();
		add(serverIdComboBox, gridBagConstraints);

		filePathLabel.setText("File Path:");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 3;
		gridBagConstraints.insets = new Insets(2, 2, 2, 2);
		add(filePathLabel, gridBagConstraints);
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 3;
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.insets = new Insets(2, 2, 2, 2);
		add(filePathTextField, gridBagConstraints);
	}

	private JComboBox getLocationTypeComboBoxItems() {
		JComboBox comboBox = new JComboBox();
		for (LocationType type : LocationType.values()) {
			comboBox.addItem(type.name());
		}
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				displayServerIdComboBoxIfAvailable();
			}

		});
		return comboBox;
	}

	private void displayServerIdComboBoxIfAvailable() {
		String selectedItem = (String) locationComboBox.getSelectedItem();
		if (LocationType.valueOf(selectedItem) == LocationType.LOCAL) {
			serverIdComboBox.setEnabled(false);
		} else {
			serverIdComboBox.setEnabled(true);
		}
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

    public int getLogId() {
        return logId;
    }

    public void setLogId(int logId) {
        this.logId = logId;
    }
}