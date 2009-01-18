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
        LogWatcherFrame gui = new LogWatcherFrame();
		JTabbedPane tabbedPane = gui.getTabbedPane();

		Server slcmJboss = new Server("slcm-server", "159.107.240.179", "slcm", "slcm");
		LogFile logFile = new LogFile("slcm-server-log", "/opt/slcm/log/jboss/server.log");
		LogDisplayPanel slcmJbossTab = new LogDisplayPanel();
		tabbedPane.addTab(logFile.getAlias(), null, slcmJbossTab, logFile.getAlias());
		TailCommand command = new TailCommand(slcmJboss, logFile, slcmJbossTab.getLogTextArea());
		TailExecutor tailExecutor = new TailExecutor(command);
		tailExecutor.start();

		Server vrc = new Server("vrc-server", "159.107.241.201", "root", "blekinge");
		LogFile vrcResultArrayHandlerLog = new LogFile("/tmp/resultArrayHandler.0", "/tmp/resultArrayHandler.0");
		LogDisplayPanel resultArrayHandlerTab = new LogDisplayPanel();
		tabbedPane.addTab(vrcResultArrayHandlerLog.getAlias(), null, resultArrayHandlerTab, vrcResultArrayHandlerLog.getAlias());
		TailCommand resultArrayHandlerCommand = new TailCommand(vrc, vrcResultArrayHandlerLog, resultArrayHandlerTab.getLogTextArea());
		TailExecutor resultArrayHandlerExecutor = new TailExecutor(resultArrayHandlerCommand);
		resultArrayHandlerExecutor.start();
	}
}
