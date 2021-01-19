package db.connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnector implements DBConnector {
    private static final MySQLConnector instance;

    static {
        instance = new MySQLConnector();
    }

    private MySQLConnector() {
    }

    public static DBConnector getInstance() {
        return instance;
    }

    @Override
    public Connection getConnection(String host, String username, String password)
            throws DBConnectException {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(host, username, password);
            connection.setAutoCommit(false);

            System.out.println("Connection Complete!");
        } catch (ClassNotFoundException e) {
            throw new DBConnectException("Failed to Load JDBC Driver", e);
        } catch (SQLException e) {
            throw new DBConnectException(e.getMessage(), e);
        }

        return connection;
    }
}
