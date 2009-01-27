package tailmaster.util;

import tailmaster.dao.ResourceManager;
import tailmaster.TailMaster;

import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * User: Halil KARAKOSE
 * Date: 15.01.2009
 * Time: 10:31:06
 */
public class ConfigurationManager {
	public static void init() throws SQLException {
		setDBSystemDir();
		createTablesIfNecessary();
	}

	private static void setDBSystemDir() {
		// Decide on the db system directory: <userhome>/.addressbook/
		String userHomeDir = System.getProperty("user.home", ".");
		String seperator = "\\";
		String systemDir = userHomeDir + seperator + "." + Constants.PROGRAM_NAME;
		System.out.println("systemDir = " + systemDir);

		// Set the db system directory.
		System.setProperty("derby.system.home", systemDir);
	}

	private static boolean createTablesIfNecessary() throws SQLException {
		System.out.println("creating tables if necessary");
		boolean bCreatedTables = false;
		Connection connection = ResourceManager.getConnection();
		Statement statement = null;
		try {
			statement = connection.createStatement();
			String serverCreate = "CREATE table SERVER(" +
					"ID INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), " +
					"SERVERALIAS VARCHAR(50), " +
					"HOST VARCHAR(50), " +
					"USERNAME VARCHAR(50) NOT NULL, " +
					"PASSWORD VARCHAR(50) NOT NULL)";
			statement.execute(serverCreate);

			String logFileCreate = "CREATE TABLE LOGFILE(" +
					"ID INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), " +
					"SERVERID INTEGER NOT NULL, " +
					"ALIAS VARCHAR(50) NOT NULL, " +
					"FILELOCATIONTYPE INTEGER NOT NULL, " +
					"FILEDESTINATION VARCHAR(100) NOT NULL)";
			statement.execute(logFileCreate);

			String commandTableCreate = "CREATE TABLE COMMAND( " +
					"COMMAND_ID INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), " +
					"COMMAND_NAME VARCHAR(100) NOT NULL, " +
					"COMMAND_TEXT VARCHAR(200) NOT NULL, " +
					"SERVER_ID INTEGER)";
			statement.execute(commandTableCreate);

			String propertyTableCreate = "CREATE TABLE PROPERTY(" +
					"PROPERTY_ID INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), " +
					"PROPERTY_NAME VARCHAR(100) NOT NULL, " +
					"PROPERTY_VALUE VARCHAR(100) NOT NULL)";
			statement.execute(propertyTableCreate);

			bCreatedTables = true;
		} catch (SQLException ex) {
			if (!ex.getSQLState().equalsIgnoreCase("X0Y32")) {
				JOptionPane.showMessageDialog(TailMaster.getGUI(), ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				throw ex;
			} else {
				//table already exists, no need to do anything
			}
		}

		return bCreatedTables;
	}
}
