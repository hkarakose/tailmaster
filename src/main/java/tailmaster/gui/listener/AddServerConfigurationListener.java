package tailmaster.gui.listener;

import tailmaster.dao.ServerDao;
import tailmaster.model.Server;
import tailmaster.gui.configuration.ServerConfigurationForm;
import tailmaster.util.JTableUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

/**
 * User: Halil KARAKOSE
 * Date: 15.01.2009
 * Time: 12:26:59
 */
public class AddServerConfigurationListener implements ActionListener {
    private ServerConfigurationForm serverConfigurationForm;
    private JTable serverTable;

	public AddServerConfigurationListener(ServerConfigurationForm serverConfigurationForm, JTable serverTable) {
        super();
        this.serverConfigurationForm = serverConfigurationForm;
        this.serverTable = serverTable;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ServerDao serverDao = ServerDao.getInstance();
        try {
            String alias = serverConfigurationForm.getAliasTextField().getText();
            String host = serverConfigurationForm.getHostTextField().getText();
            String username = serverConfigurationForm.getUsernameTextField().getText();
            String password = serverConfigurationForm.getPasswordTextField().getText();
            serverDao.insert(new Server(alias, host, username, password));

            DefaultTableModel tableModel = (DefaultTableModel) serverTable.getModel();
            tableModel.setDataVector(JTableUtils.getServerList(), JTableUtils.getServerTableColumnTypes());
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(serverConfigurationForm.getRootPane(), "Unable to save", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
