package tailmaster.gui;

import tailmaster.gui.menu.TailMasterMenuBar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.net.MalformedURLException;
import java.io.File;

/**
 * User: Halil KARAKOSE
 * Date: Jan 14, 2009
 * Time: 4:35:13 PM
 */
public class TailMasterFrame extends JFrame {
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

		JToolBar toolBar = initializeToolBar();
		add(toolBar, BorderLayout.PAGE_START);

		tabbedPane = new CloseButtonTabbedPane();
		tabbedPaneContainer = new JPanel(new BorderLayout());
		tabbedPaneContainer.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		tabbedPaneContainer.add(tabbedPane, SwingConstants.CENTER);
		add(tabbedPaneContainer, SwingConstants.CENTER);
	}

	private JToolBar initializeToolBar() {
		JToolBar bar = new JToolBar("Still draggable");
		JButton pauseButton = new JButton("Pause");
		pauseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CloseButtonTabbedPane tabbedPane1 = getTabbedPane();
				Component[] tabs = tabbedPane1.getComponents();
				for (Component tab : tabs) {
					if (tab instanceof LogDisplayPanel && tab.isShowing()) {
						System.out.println(tab + " is visible");
						LogDisplayPanel panel = (LogDisplayPanel) tab;
						panel.setPlaying(!panel.isPlaying());
					}
				}
			}
		});
		bar.add(pauseButton);
	    return bar;
	}

	public CloseButtonTabbedPane getTabbedPane() {
		return tabbedPane;
	}
	protected static Image createFDImage() {
		//Create a 16x16 pixel image.
		BufferedImage bi = new BufferedImage(16, 16, BufferedImage.TYPE_INT_RGB);

		//Draw into it.
		Graphics g = bi.getGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 15, 15);
		g.setColor(Color.RED);
		g.fillOval(5, 3, 6, 6);

		//Clean up.
		g.dispose();

		//Return it.
		return bi;
	}

}

