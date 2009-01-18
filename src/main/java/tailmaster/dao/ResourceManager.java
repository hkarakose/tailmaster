package tailmaster.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * User: Halil KARAKOSE
 * Date: 15.Oca.2009
 * Time: 10:35:00
 */
public class ResourceManager {
    public static Connection getConnection() {
        String strUrl = "jdbc:derby:LOGWATCHER;create=true";
        try {
            return DriverManager.getConnection(strUrl);
        } catch (SQLException e) {
            throw new RuntimeException("Unable to connect embedded database.");
        }
    }

}
