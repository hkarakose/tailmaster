package tailmaster.gui.listener;

import tailmaster.dao.ServerDao;
import tailmaster.model.Server;
import tailmaster.gui.configuration.ServerFormPanel;
import tailmaster.util.Utils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

/**
 * User: Halil KARAKOSE
 * Date: 15.Oca.2009
 * Time: 12:26:59
 */
public class AddServerConfigurationListener implements ActionListener {
    private ServerFormPanel serverFormPanel;
    private JTable serverTable;

    public AddServerConfigurationListener(ServerFormPanel serverFormPanel, JTable serverTable) {
        super();
        this.serverFormPanel = serverFormPanel;
        this.serverTable = serverTable;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ServerDao serverDao = ServerDao.getInstance();
        try {
            String alias = serverFormPanel.getAliasTextField().getText();
            String host = serverFormPanel.getHostTextField().getText();
            String username = serverFormPanel.getUsernameTextField().getText();
            String password = serverFormPanel.getPasswordTextField().getText();
            serverDao.insert(new Server(alias, host, username, password));

            DefaultTableModel tableModel = (DefaultTableModel) serverTable.getModel();
            tableModel.setDataVector(Utils.getServerList(), Utils.getServerTableColumnTypes());
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(serverFormPanel.getRootPane(), "Unable to save", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
