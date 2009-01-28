package tailmaster.gui;

import tailmaster.model.TabData;
import tailmaster.TabRegistry;
import static tailmaster.util.Constants.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * User: Halil KARAKOSE
 * Date: 28.Oca.2009
 * Time: 21:28:09
 */
public class TailMasterToolBar extends JToolBar {
    private JButton pauseButton;

    public TailMasterToolBar() {
        pauseButton = new JButton(PAUSE);
        pauseButton.addActionListener(new PauseButtonListener());
        add(pauseButton);
    }

    public JButton getPauseButton() {
        return pauseButton;
    }

    class PauseButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            CloseButtonTabbedPane tabbedPane1 = TailMasterFrame.getInstance().getTabbedPane();
            Component[] tabs = tabbedPane1.getComponents();
            for (Component tab : tabs) {
                if (tab instanceof LogDisplayPanel && tab.isShowing()) {
                    LogDisplayPanel panel = (LogDisplayPanel) tab;
                    TabData data = TabRegistry.INSTANCE.getTabData(panel.getPanelId());
                    data.setPlaying(!data.isPlaying());
                    togglePauseButtonText(data.isPlaying());
                }
            }
        }
    }

    public void togglePauseButtonText(boolean playing) {
        pauseButton.setText(playing ? PAUSE : PLAY);
    }
}
