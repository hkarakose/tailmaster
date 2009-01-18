package tailmaster.gui.listener;

import tailmaster.model.LogFile;
import tailmaster.model.Server;
import tailmaster.dao.ServerDao;
import tailmaster.gui.LogWatcherFrame;
import tailmaster.gui.LogDisplayPanel;
import tailmaster.TailCommand;
import tailmaster.TailExecutor;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * User: Halil KARAKOSE
 * Date: Jan 18, 2009
 * Time: 6:24:04 PM
 */
public class DisplayLogFileListener implements ActionListener {
	private LogFile logFile;

	public DisplayLogFileListener(LogFile logFile) {
		this.logFile = logFile;
	}

	public void actionPerformed(ActionEvent e) {
		Server server = ServerDao.getInstance().findById(logFile.getServerId());
		LogWatcherFrame gui = LogWatcherFrame.getInstance();
		JTabbedPane tabbedPane = gui.getTabbedPane();

		LogDisplayPanel slcmJbossTab = new LogDisplayPanel();
		tabbedPane.addTab(logFile.getAlias(), null, slcmJbossTab, server.getHostname());
		TailCommand command = new TailCommand(server, logFile, slcmJbossTab.getLogTextArea());
		TailExecutor tailExecutor = new TailExecutor(command);
		tailExecutor.start();
	}
}
