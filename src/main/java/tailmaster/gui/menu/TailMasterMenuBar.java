package tailmaster.gui.menu;

import tailmaster.gui.configuration.ServerConfigurationDialog;
import tailmaster.gui.configuration.LogFileConfigurationDialog;
import tailmaster.gui.listener.DisplayLogFileListener;
import tailmaster.dao.LogFileDao;
import tailmaster.model.LogFile;

import javax.swing.*;
import javax.swing.event.MenuListener;
import javax.swing.event.MenuEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

/**
 * User: Halil KARAKOSE
 * Date: 15.01.2009
 * Time: 12:19:29
 */
public class TailMasterMenuBar extends JMenuBar {
    JMenu file, help, view;
    JMenuItem addServerMenuItem, addLogFileMenuItem, exitMenuItem, aboutMenuItem;

    public TailMasterMenuBar() {
        file = new JMenu("File");
        file.setMnemonic('F');
        add(file);

        final TailMasterMenuBar me = this;

        addServerMenuItem = new JMenuItem("Configure Servers");
        addServerMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ServerConfigurationDialog dialog = new ServerConfigurationDialog(me.getRootPane());
            }
        });
        file.add(addServerMenuItem);

        addLogFileMenuItem = new JMenuItem("Configure Log Files");
		addLogFileMenuItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				LogFileConfigurationDialog dialog = new LogFileConfigurationDialog(me.getRootPane()); 
			}
		});
        file.add(addLogFileMenuItem);

        file.addSeparator();

        exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.setMnemonic('E');
        exitMenuItem.addActionListener(new ActionListener() {
            //@Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        file.add(exitMenuItem);

        view = new JMenu("View");
        view.setMnemonic('V');
		view.addMenuListener(new MenuListener(){
			public void menuSelected(MenuEvent e) {
				view.removeAll();
				
				ArrayList<LogFile> logFileList = LogFileDao.getInstance().findAll();
				for (LogFile logFile : logFileList) {
					JMenuItem menuItem = new JMenuItem(logFile.getAlias() + " (" + logFile.getFileDestination() + ")");
					menuItem.addActionListener(new DisplayLogFileListener(logFile));
					view.add(menuItem);
				}
			}

			public void menuDeselected(MenuEvent e) {
			}

			public void menuCanceled(MenuEvent e) {
			}
		});
		add(view);

        help = new JMenu("Help");
        help.setMnemonic('H');
        aboutMenuItem = new JMenuItem("About");
        help.add(aboutMenuItem);

        add(help);
    }
}
