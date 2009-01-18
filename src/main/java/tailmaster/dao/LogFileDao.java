package tailmaster.dao;

import tailmaster.model.LogFile;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * User: Halil KARAKOSE
 * Date: 15.01.2009
 * Time: 09:31:49
 */
public class LogFileDao extends AbstractDao {
    private static LogFileDao instance;

    private LogFileDao() {
        super();
    }

    public static LogFileDao getInstance() {
        if (instance == null) instance = new LogFileDao();
        return instance;
    }

    public ArrayList<LogFile> findAll() {
		Statement stmt2 = null;
		try {
			stmt2 = connection.createStatement();
			ResultSet rs = stmt2.executeQuery("SELECT * FROM LOGFILE ORDER BY ALIAS ASC");
			ArrayList<LogFile> serverList = new ArrayList<LogFile>();

			while (rs.next()) {
				serverList.add(new LogFile(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4)));
			}
			return serverList;
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Unable to retrieve log file list", "Error", JOptionPane.ERROR_MESSAGE);
			return null;
		}
    }


    public LogFile findById(int id) throws SQLException {
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM LOGFILE WHERE ID = " + id);
        if (rs.next()) {
            return new LogFile(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4));
        }

        return null;
    }

    public int update(LogFile server, int id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("UPDATE LOGFILE SET SERVERID=?, ALIAS=?, FILEDESTINATION=? WHERE ID=?");

        int i = 1;
        statement.setInt(i++, server.getServerId());
        statement.setString(i++, server.getAlias());
        statement.setString(i++, server.getFileDestination());
        statement.setInt(i, id);

        return statement.executeUpdate();
    }

    public int delete(int id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM LOGFILE WHERE ID = ?");
        statement.clearParameters();
        statement.setInt(1, id);
        return statement.executeUpdate();
    }

    public void insert(LogFile server) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO LOGFILE(SERVERID, ALIAS, FILEDESTINATION) values (?,?,?)");

        int i = 1;
        statement.setInt(i++, server.getServerId());
        statement.setString(i++, server.getAlias());
        statement.setString(i++, server.getFileDestination());
        statement.executeUpdate();
    }
}
