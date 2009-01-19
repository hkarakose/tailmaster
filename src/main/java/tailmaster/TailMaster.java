package tailmaster;

import tailmaster.gui.TailMasterFrame;
import tailmaster.util.ConfigurationManager;

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
        TailMasterFrame gui = TailMasterFrame.getInstance();
		gui.setVisible(true);
	}
}
