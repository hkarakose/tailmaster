package tailmaster.gui;

import tailmaster.model.Server;
import tailmaster.dao.ServerDao;
import tailmaster.util.SshUtils;
import tailmaster.SessionRegistry;
import tailmaster.commons.gui.CloseButtonTabbedPane;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import com.sshtools.j2ssh.SshClient;
import com.sshtools.j2ssh.session.SessionChannelClient;

/**
 * User: Halil KARAKOSE
 * Date: 07.Þub.2009
 * Time: 19:33:36
 */
public class ServerChooserDialog extends javax.swing.JDialog {
    private JLabel serverLabel;
    private javax.swing.JComboBox serverComboBox;
    private javax.swing.JButton connectButton;

    public ServerChooserDialog(java.awt.Frame parent, boolean modal) {
        super(parent, "Choose Server", modal);
        initComponents();

        setLocationRelativeTo(parent);
        setResizable(false);
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        serverLabel = new JLabel("Server: ");
        ArrayList<Server> list = ServerDao.getInstance().findAllSortedByServerName();
        serverComboBox = new javax.swing.JComboBox(list.toArray());
        connectButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 0);
        getContentPane().add(serverLabel, gridBagConstraints);

        serverComboBox.setRenderer(new ServerComboBoxRenderer());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.8;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 10, 10);
        getContentPane().add(serverComboBox, gridBagConstraints);

        connectButton.setText("Connect");
        connectButton.addActionListener(new ConnectButtonListener(serverComboBox));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 5, 0);
        getContentPane().add(connectButton, gridBagConstraints);

        pack();
    }

    private static class ConnectButtonListener implements ActionListener {
        private JComboBox serverComboBox;

        public ConnectButtonListener(JComboBox serverComboBox) {
            this.serverComboBox = serverComboBox;
        }

        public void actionPerformed(ActionEvent e) {
            Server server = (Server) serverComboBox.getSelectedItem();
            TailMasterFrame gui = TailMasterFrame.getInstance();

            SshClient client = SshUtils.connect(server);
            SessionChannelClient channel = SshUtils.openSshChannel(client);

            if (!SshUtils.requestPseudoTerminal(channel)) {
                JOptionPane.showMessageDialog(gui, "Terminal cannot be started", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (!SshUtils.startShell(channel)) {
                JOptionPane.showMessageDialog(gui, "Shell cannot be started", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            TerminalPanel terminalPanel = new TerminalPanel(channel);

			long connectionId = System.currentTimeMillis();
            SessionRegistry.put(connectionId, client, channel);
            CloseButtonTabbedPane tabbedPane = gui.getTabbedPane();
            tabbedPane.addTab(connectionId, "Ssh Client", null, terminalPanel, terminalPanel.getToolTipText());
            serverComboBox.getRootPane().getParent().setVisible(false);
        }
    }
}
