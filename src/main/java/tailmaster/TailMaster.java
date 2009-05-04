package tailmaster;

import tailmaster.gui.TailMasterFrame;
import tailmaster.util.ConfigurationManager;

import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * User: Halil KARAKOSE
 * Date: Jan 14, 2009
 * Time: 4:27:53 PM
 */
public class TailMaster {
    
	public static void main(String args[]) throws IOException, SQLException {
		ConfigurationManager.init();

		Logger logger = Logger.getLogger("com.sshtools");
		logger.setLevel(Level.ALL);
		FileHandler fh = new FileHandler(ConfigurationManager.getConfigurationDirectory() + "\\tailmaster.log", true);
		fh.setFormatter(new SimpleFormatter());
		logger.addHandler(fh);

        setSystemLookAndFeel();
        JFrame gui = TailMasterFrame.getInstance();
		gui.setVisible(true);
	}

    private static void setSystemLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }
}
