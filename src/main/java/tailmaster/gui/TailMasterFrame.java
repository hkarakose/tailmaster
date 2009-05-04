package tailmaster.gui;

import tailmaster.commons.gui.CloseButtonTabbedPane;
import tailmaster.gui.menu.TailMasterMenuBar;

import javax.swing.*;
import java.awt.*;

/**
 * User: Halil KARAKOSE
 * Date: Jan 14, 2009
 * Time: 4:35:13 PM
 */
public class TailMasterFrame extends JFrame {
    private JToolBar toolBar;
	private JPanel tabbedPaneContainer;
	private CloseButtonTabbedPane tabbedPane;
	private static TailMasterFrame instance;

	public static TailMasterFrame getInstance() {
		if (instance == null) instance = new TailMasterFrame();
		return instance;
	}

	private TailMasterFrame() throws HeadlessException {
		JFrame.setDefaultLookAndFeelDecorated(false);
		setUndecorated(false);
		setTitle("TailMaster");
		setLayout(new BorderLayout());
		setSize(800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Image image = new ImageIcon(getClass().getClassLoader().getResource("armour.png")).getImage();
		setIconImage(image);

		setJMenuBar(new TailMasterMenuBar());

		toolBar = new TailMasterToolBar();
		add(toolBar, BorderLayout.PAGE_START);

		tabbedPane = new CloseButtonTabbedPane();
		tabbedPaneContainer = new JPanel(new BorderLayout());
		tabbedPaneContainer.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		tabbedPaneContainer.add(tabbedPane, SwingConstants.CENTER);
		add(tabbedPaneContainer, SwingConstants.CENTER);
	}

    public JToolBar getToolBar() {
        return toolBar;
    }

    public CloseButtonTabbedPane getTabbedPane() {
		return tabbedPane;
	}
}

