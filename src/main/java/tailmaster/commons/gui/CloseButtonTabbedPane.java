package tailmaster.commons.gui;

import tailmaster.SessionRegistry;
import tailmaster.TabRegistry;
import tailmaster.gui.LogDisplayPanel;

import javax.swing.*;
import javax.swing.plaf.metal.MetalIconFactory;
import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

/**
 * User: Halil KARAKOSE
 * Date: Jan 18, 2009
 * Time: 8:43:25 PM
 */
public class CloseButtonTabbedPane extends JTabbedPane {
    public CloseButtonTabbedPane() {
    }

    @Override
    public void addTab(String title, Icon icon, Component component, String tip) {
        addTab(0, title, icon, component, tip);
    }

    @Override
    public void addTab(String title, Icon icon, Component component) {
        addTab(title, icon, component, null);
    }

    @Override
    public void addTab(String title, Component component) {
        addTab(title, null, component);
    }

    public void addTab(long connectionId, String title, Icon icon, Component component, String tip) {
        super.addTab(title, icon, component, tip);
        int count = this.getTabCount() - 1;
        setTabComponentAt(count, new CloseButtonTab(connectionId, component, title, icon));
    }

    public class CloseButtonTab extends JPanel {
        private Component tab;

        public CloseButtonTab(final long connectionId, Component tab, String title, Icon icon) {
            this.tab = tab;
            setOpaque(false);
            FlowLayout flowLayout = new FlowLayout(FlowLayout.CENTER, 3, 3);
            setLayout(flowLayout);
            setVisible(true);

            JLabel jLabel = new JLabel(title);
            jLabel.setIcon(icon);
            add(jLabel);
            JButton button = new JButton(MetalIconFactory.getInternalFrameCloseIcon(16));
            button.setMargin(new Insets(0, 0, 0, 0));
            button.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
            button.addMouseListener(new MouseListener() {
                public void mouseClicked(MouseEvent e) {
                    SessionRegistry.disconnect(connectionId);
                    JButton button = (JButton) e.getSource();
                    CloseButtonTab buttonTab = (CloseButtonTab) button.getParent();
                    LogDisplayPanel panel = (LogDisplayPanel) buttonTab.getTab();
                    TabRegistry.INSTANCE.removeTab(panel.getPanelId());
                    JTabbedPane tabbedPane = (JTabbedPane) panel.getParent();
                    tabbedPane.remove(panel);
                }

                public void mousePressed(MouseEvent e) {
                }

                public void mouseReleased(MouseEvent e) {
                }

                public void mouseEntered(MouseEvent e) {
                    JButton button = (JButton) e.getSource();
                    button.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
                }

                public void mouseExited(MouseEvent e) {
                    JButton button = (JButton) e.getSource();
                    button.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
                }
            });
            add(button);
        }

        public Component getTab() {
            return tab;
        }
    }
}
