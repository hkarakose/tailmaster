package tailmaster;

import tailmaster.model.LogFile;
import tailmaster.model.Server;
import tailmaster.gui.LogDisplayPanel;
import tailmaster.gui.LogWatcherFrame;
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

	public static void main(String args[]) throws IOException, SQLException {
        ConfigurationManager.init();
        LogWatcherFrame gui = LogWatcherFrame.getInstance();
	}
}
