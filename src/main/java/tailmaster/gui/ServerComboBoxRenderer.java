package tailmaster.gui;

import tailmaster.model.Server;

import javax.swing.*;
import java.awt.*;

/**
 * User: Halil KARAKOSE
 * Date: 07.Þub.2009
 * Time: 20:27:48
 */
public class ServerComboBoxRenderer extends JLabel implements ListCellRenderer {
    public ServerComboBoxRenderer() {
        setOpaque(true);
    }

    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }

        Server server = (Server) value;
        setText(server.getServerAlias() + " (" + server.getHostname() + ")");
        return this;
    }
}

