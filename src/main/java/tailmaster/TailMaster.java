package tailmaster;

import tailmaster.gui.TailMasterFrame;
import tailmaster.util.ConfigurationManager;

import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;

/**
 * User: Halil KARAKOSE
 * Date: Jan 14, 2009
 * Time: 4:27:53 PM
 */
public class TailMaster {
	private static JFrame GUI;

	public static void main(String args[]) throws IOException, SQLException {
        ConfigurationManager.init();
        GUI = TailMasterFrame.getInstance();
		GUI.setVisible(true);
	}

	public static JFrame getGUI() {
		return GUI;
	}
}
