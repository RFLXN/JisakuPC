package db.connector;

import java.sql.Connection;
import java.sql.SQLException;

public interface DBConnector {
    Connection getConnection(String host, String username, String password) throws SQLException;
}
