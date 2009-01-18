package tailmaster.util;

import tailmaster.dao.ServerDao;
import tailmaster.dao.LogFileDao;
import tailmaster.model.Server;
import tailmaster.model.LogFile;

import javax.swing.*;
import java.util.ArrayList;
import java.sql.SQLException;

/**
 * User: Halil KARAKOSE
 * Date: 15.01.2009
 * Time: 13:26:26
 */
public class JTableUtils {
	public static Object[][] getServerList() {
		ServerDao dao = ServerDao.getInstance();
		ArrayList<Server> list = dao.findAll();
		Object[][] objects = new Object[list.size()][5];
		for (int i = 0; i < list.size(); i++) {
			Server server = list.get(i);
			objects[i] = new String[]{String.valueOf(server.getId()), server.getServerAlias(), server.getHostname(), server.getUsername(), server.getPassword()};
		}
		return objects;
	}

	public static Class[] getServerTableColumnTypes() {
		return new Class[]{String.class, String.class, String.class, String.class, String.class};
	}

	public static Object[][] getLogFileList() {
		LogFileDao dao = LogFileDao.getInstance();
		try {
			ArrayList<LogFile> list = dao.findAll();
			Object[][] objects = new Object[list.size()][5];
			for (int i = 0; i < list.size(); i++) {
				LogFile logFile = list.get(i);
				objects[i] = new String[]{String.valueOf(logFile.getId()), logFile.getAlias(), String.valueOf(logFile.getServerId()), logFile.getFileDestination()};
			}
			return objects;
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Unable to select server list", "Error", JOptionPane.ERROR);
		}

		return null;
	}

	public static Class[] getLogFileColumnTypes() {
		return new Class[]{String.class, String.class, String.class, String.class};
	}
}
