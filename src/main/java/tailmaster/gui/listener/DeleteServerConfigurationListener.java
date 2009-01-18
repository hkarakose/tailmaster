package tailmaster.gui.listener;

import tailmaster.dao.ServerDao;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

/**
 * User: Halil KARAKOSE
 * Date: 15.01.2009
 * Time: 12:56:34
 */
public class DeleteServerConfigurationListener implements ActionListener {
    private JTable table;

    public DeleteServerConfigurationListener(JTable serverTablePanel) {
        this.table = serverTablePanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int selectedRow = table.getSelectedRow();
        String serverId = (String) table.getModel().getValueAt(selectedRow, 0);
        ServerDao dao = ServerDao.getInstance();
        try {
            dao.delete(Integer.parseInt(serverId));
            DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
            tableModel.removeRow(selectedRow);
        } catch (SQLException e1) {
            e1.printStackTrace();
            JOptionPane.showMessageDialog(table.getRootPane(), "Unable to delete server data", "Error", JOptionPane.ERROR);
        }
    }
}
