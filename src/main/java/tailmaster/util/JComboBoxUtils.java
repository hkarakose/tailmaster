package tailmaster.util;

import tailmaster.dao.ServerDao;
import tailmaster.model.Server;

import javax.swing.*;
import java.util.ArrayList;

/**
 * User: Halil KARAKOSE
 * Date: Jan 18, 2009
 * Time: 2:07:50 PM
 */
public class JComboBoxUtils {
	public static String[] getServerArray() {
		ArrayList<Server> list = ServerDao.getInstance().findAllSortedByServerName();
		String[] servers = new String[list.size()];
		for (int i = 0; i < list.size(); i++) {
			Server server = list.get(i);
			servers[i] = server.getServerAlias() + " (" + server.getHostname() + ")";
		}
		return servers;
	}

	public static int getSelectedServerId(JComboBox serverIdComboBox) {
		int index = serverIdComboBox.getSelectedIndex();
		ArrayList<Server> serverList = ServerDao.getInstance().findAllSortedByServerName();
		return serverList.get(index).getId();
	}
}
