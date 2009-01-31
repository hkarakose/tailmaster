package tailmaster.gui.configuration;

import tailmaster.gui.configuration.ConfigurationTable;
import tailmaster.util.JTableUtils;

import javax.swing.*;

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
}
