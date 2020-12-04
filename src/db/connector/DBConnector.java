package db.connector;

import java.sql.Connection;

public interface DBConnector {
    Connection getConnection(String host, String username, String password) throws DBConnectException;
}
