package tailmaster.gui;

/**
 * User: Halil KARAKOSE
 * Date: 14.01.2009
 * Time: 12:17:21
 */
public class LogDisplayPanel extends TailMasterPanel {
	//TODO tailmasterpanel yaz ve bu class'i tailmasterpanel'den extend et.

    public LogDisplayPanel() {
		textArea = new TailMasterTextArea();
		scroller.setViewportView(textArea);
    }
}
