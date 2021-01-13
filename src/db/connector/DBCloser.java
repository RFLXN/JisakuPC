package db.connector;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class DBCloser {
    public static void close(Connection connection) throws DBCloseException {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new DBCloseException(e.getMessage(), e);
        }
    }
}
