package tailmaster.util;

import tailmaster.dao.ResourceManager;

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
        String systemDir = userHomeDir + seperator + ".addressbook";
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
                    "    ID          INTEGER NOT NULL " +
                    "                PRIMARY KEY GENERATED ALWAYS AS IDENTITY " +
                    "                (START WITH 1, INCREMENT BY 1)," +
                    "    SERVERALIAS    VARCHAR(30), " +
                    "    HOST    VARCHAR(30), " +
                    "    USERNAME   VARCHAR(30)," +
                    "    PASSWORD  VARCHAR(30))";

            statement.execute(serverCreate);

            String logFileCreate = "CREATE table LOGFILE(" +
                    "    ID          INTEGER NOT NULL " +
                    "                PRIMARY KEY GENERATED ALWAYS AS IDENTITY " +
                    "                (START WITH 1, INCREMENT BY 1)," +
                    "    SERVERID INTEGER NOT NULL, " +
                    "    ALIAS    VARCHAR(30), " +
                    "    FILEDESTINATION    VARCHAR(30))";

            statement.execute(logFileCreate);
            bCreatedTables = true;
        } catch (SQLException ex) {
            if (!ex.getSQLState().equalsIgnoreCase("X0Y32")) {
                 throw ex;
            } else {
                //table already exists, no need to do anything
            }
        }

        return bCreatedTables;
    }
}
