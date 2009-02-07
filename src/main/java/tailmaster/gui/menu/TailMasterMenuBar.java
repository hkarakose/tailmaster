package tailmaster.gui.menu;

import tailmaster.gui.configuration.ServerConfigurationDialog;
import tailmaster.gui.configuration.LogFileConfigurationDialog;
import tailmaster.gui.listener.DisplayLogFileListener;
import tailmaster.gui.ServerChooserDialog;
import tailmaster.gui.TailMasterFrame;
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
    JMenu file, help, view, tools;
    JMenuItem addServerMenuItem, addLogFileMenuItem, addCommandMenuItem, exitMenuItem, aboutMenuItem, sshClient;

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
                dialog.setVisible(true);
            }
        });
        file.add(addServerMenuItem);

        addLogFileMenuItem = new JMenuItem("Configure Log Files");
        addLogFileMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LogFileConfigurationDialog dialog = new LogFileConfigurationDialog(me.getRootPane());
                dialog.setVisible(true);
            }
        });
        file.add(addLogFileMenuItem);

        addCommandMenuItem = new JMenuItem("Configure Commands");
        addCommandMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //TODO not implemented
            }
        });
        file.add(addCommandMenuItem);

        file.addSeparator();

        exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.setMnemonic('E');
        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//				SessionRegistry.disconnect();
                System.exit(0);
            }
        });
        file.add(exitMenuItem);

        view = new JMenu("View");
        view.setMnemonic('V');
        view.addMenuListener(new ViewMenuListener());
        add(view);

        tools = new JMenu("Tools");
        tools.setMnemonic('T');
        add(tools);

        sshClient = new JMenuItem("SSH Client");
        sshClient.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ServerChooserDialog dialog = new ServerChooserDialog(TailMasterFrame.getInstance(), true);
                dialog.setVisible(true);
            }
        });
        tools.add(sshClient);

        help = new JMenu("Help");
        help.setMnemonic('H');
        aboutMenuItem = new JMenuItem("About");
        aboutMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Developed by Halil Karakose, 2009\nEmail: halilkarakose@gmail.com");
            }
        });
        help.add(aboutMenuItem);

        add(help);
    }

    private class ViewMenuListener implements MenuListener {
        public void menuSelected(MenuEvent e) {
            view.removeAll();

            ArrayList<LogFile> logFileList = LogFileDao.getInstance().findAllSortedByLogName();
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
    }
}
