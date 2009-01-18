package tailmaster.gui;

import tailmaster.gui.menu.TailMasterMenuBar;

import javax.swing.*;
import java.awt.*;

/**
 * User: Halil KARAKOSE
 * Date: Jan 14, 2009
 * Time: 4:35:13 PM
 */
public class LogWatcherFrame extends JFrame {
    private JPanel tabbedPaneContainer;
    private JTabbedPane tabbedPane;

    public LogWatcherFrame() throws HeadlessException {
		setTitle("TailMaster");
		setLayout(new BorderLayout());
		setSize(800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
        setJMenuBar(new TailMasterMenuBar());

        tabbedPane = new JTabbedPane();
		tabbedPaneContainer = new JPanel(new BorderLayout());
        tabbedPaneContainer.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        tabbedPaneContainer.add(tabbedPane, SwingConstants.CENTER);
        add(tabbedPaneContainer, SwingConstants.CENTER);

        setVisible(true);
	}

    public JTabbedPane getTabbedPane() {
        return tabbedPane;
    }
}
