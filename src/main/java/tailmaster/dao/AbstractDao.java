package tailmaster.dao;

import java.sql.Connection;

/**
 * User: Halil KARAKOSE
 * Date: 15.01.2009
 * Time: 09:32:58
 */
public abstract class AbstractDao {
    Connection connection;

    public AbstractDao() {
        connection = ResourceManager.getConnection();
    }
}
