package tailmaster.gui.configuration;

import tailmaster.gui.listener.AddServerConfigurationListener;
import tailmaster.gui.listener.DeleteServerConfigurationListener;
import tailmaster.util.Utils;

import javax.swing.*;

/**
 * User: Halil KARAKOSE
 * Date: 15.Oca.2009
 * Time: 11:57:07
 */
public class ServerConfigurationDialog extends JDialog {
    private JButton saveButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JPanel buttonPanel;
    private ServerFormPanel serverFormPanel;
    private ServerTablePanel serverTablePanel;

    public ServerConfigurationDialog(JRootPane rootPane) {
        initComponents();
        setTitle("Server Configuration");
        setModal(true);
        setSize(350,500);
        setLocationRelativeTo(rootPane);
        setVisible(true);
    }

    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        serverTablePanel = new ServerTablePanel(Utils.getServerList());

        serverFormPanel = new ServerFormPanel();
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
        add(serverTablePanel, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        add(serverFormPanel, gridBagConstraints);

        saveButton.setText("Save");
        saveButton.addActionListener(new AddServerConfigurationListener(serverFormPanel, serverTablePanel.getServerTable()));
        buttonPanel.add(saveButton);

        updateButton.setText("Update");
        buttonPanel.add(updateButton);

        deleteButton.setText("Delete");
        JTable serverTable = serverTablePanel.getServerTable();
        deleteButton.addActionListener(new DeleteServerConfigurationListener(serverTable));
        buttonPanel.add(deleteButton);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        add(buttonPanel, gridBagConstraints);
    }
}