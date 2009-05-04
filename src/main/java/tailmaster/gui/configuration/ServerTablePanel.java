package tailmaster.gui.configuration;

import tailmaster.util.JTableUtils;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * User: Halil KARAKOSE
 * Date: 15.01.2009
 * Time: 11:30:18
 */
public class ServerTablePanel extends JPanel {
    private ConfigurationTable serverTable;
    private JScrollPane serverTableScroller;

    public ServerTablePanel(Object[][] data) {
        initComponents(data);
    }

    private void initComponents(Object[][] data) {
        String[] columnList = JTableUtils.getServerColumnHeaders();
        serverTable = new ConfigurationTable(data, columnList, JTableUtils.getServerTableColumnTypes());

		serverTable.setColumnSelectionAllowed(true);
		serverTable.getTableHeader().setReorderingAllowed(false);
		serverTable.setRowSelectionAllowed(true);
		serverTable.setColumnSelectionAllowed(false);
		serverTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        serverTable.getSelectionModel().addListSelectionListener(new RowSelectionListener(serverTable));

		serverTableScroller = new JScrollPane();
		serverTableScroller.setViewportView(serverTable);

		GroupLayout layout = new GroupLayout(this);
		this.setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(serverTableScroller, GroupLayout.DEFAULT_SIZE, 410, Short.MAX_VALUE));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(serverTableScroller, GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE));
    }

    public JTable getServerTable() {
        return serverTable;
    }

    private class RowSelectionListener implements ListSelectionListener {
        private JTable table;

        public RowSelectionListener(JTable serverTable) {
            this.table = serverTable;
        }

        public void valueChanged(ListSelectionEvent e) {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) return;

            String serverId = (String) table.getModel().getValueAt(selectedRow, 0);
            String serverName = (String) table.getModel().getValueAt(selectedRow, 1);
            String host = (String) table.getModel().getValueAt(selectedRow, 2);
            String username = (String) table.getModel().getValueAt(selectedRow, 3);
            String password = (String) table.getModel().getValueAt(selectedRow, 4);

            populateServerConfigurationForm(serverId, serverName, host, username, password);
        }

        private void populateServerConfigurationForm(String serverId, String serverName, String host, String username, String password) {
            ServerConfigurationForm form = ServerConfigurationForm.getInstance();
            form.setServerId(Integer.parseInt(serverId));
            form.getAliasTextField().setText(serverName);
            form.getHostTextField().setText(host);
            form.getUsernameTextField().setText(username);
            form.getPasswordTextField().setText(password);
        }
    }
}
