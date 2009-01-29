package tailmaster.gui;

import tailmaster.model.TabData;
import tailmaster.TabRegistry;
import tailmaster.commons.gui.CloseButtonTabbedPane;

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
	private ImageIcon pauseIcon;
	private ImageIcon playIcon;

    public TailMasterToolBar() {
		pauseIcon = new ImageIcon((getClass().getClassLoader().getResource("media_pause.png")));
		pauseIcon = new ImageIcon(pauseIcon.getImage().getScaledInstance(24,24, Image.SCALE_FAST));
        pauseButton = new JButton(pauseIcon);
		pauseButton.setMargin(new Insets(0,0,0,0));
		pauseButton.addActionListener(new PauseButtonListener());
		add(pauseButton);

		playIcon = new ImageIcon(getClass().getClassLoader().getResource("media_play.png"));
		playIcon = new ImageIcon(playIcon.getImage().getScaledInstance(24,24, Image.SCALE_DEFAULT));
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
        pauseButton.setIcon(playing ? pauseIcon : playIcon);
    }
}
