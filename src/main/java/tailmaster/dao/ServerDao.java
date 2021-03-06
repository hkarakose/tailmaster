package tailmaster.dao;

import tailmaster.model.Server;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * User: Halil KARAKOSE
 * Date: 15.01.2009
 * Time: 09:31:39
 */
public class ServerDao extends AbstractDao {
    private static ServerDao instance;

    private ServerDao() {
        super();
    }

    public static ServerDao getInstance() {
        if (instance == null) instance = new ServerDao();
        return instance;
    }

    public ArrayList<Server> findAll() {
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM SERVER");
			ArrayList<Server> serverList = new ArrayList<Server>();
			while (rs.next()) {
				serverList.add(new Server(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
			}
			return serverList;
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Unable to retrieve server list", "Error", JOptionPane.ERROR_MESSAGE);
			return null;
		}
    }

    public ArrayList<Server> findAllSortedByServerName() {
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM SERVER ORDER BY SERVERALIAS ASC");
			ArrayList<Server> serverList = new ArrayList<Server>();
			while (rs.next()) {
				serverList.add(new Server(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
			}
			return serverList;
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Unable to retrieve server list", "Error", JOptionPane.ERROR_MESSAGE);
			return null;
		}
    }


    public Server findById(int id) {
		Statement stmt = null;
		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM SERVER WHERE ID = " + id);
			if (rs.next()) {
				return new Server(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Unable to get server information with id=" + id, "Error", JOptionPane.ERROR_MESSAGE);
		}
		return null;
	}

    public int update(Server server) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("UPDATE SERVER SET SERVERALIAS=?, HOST=?, USERNAME=?, PASSWORD=? WHERE ID=?");
            int i = 1;
            statement.setString(i++, server.getServerAlias());
            statement.setString(i++, server.getHostname());
            statement.setString(i++, server.getUsername());
            statement.setString(i++, server.getPassword());
            statement.setInt(i, server.getId());
            return statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Unable to update server information with id=" + server.getId(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        return 0;
    }

    public int delete(int id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM SERVER WHERE ID = ?");
        statement.clearParameters();
        statement.setInt(1, id);
        return statement.executeUpdate();
    }

    public void insert(Server server) throws SQLException {
		PreparedStatement statement = connection.prepareStatement("INSERT INTO SERVER(SERVERALIAS, HOST, USERNAME, PASSWORD) VALUES (?,?,?,?)");

		int i = 1;
		statement.setString(i++, server.getServerAlias());
		statement.setString(i++, server.getHostname());
		statement.setString(i++, server.getUsername());
		statement.setString(i, server.getPassword());
		statement.executeUpdate();
    }
}
