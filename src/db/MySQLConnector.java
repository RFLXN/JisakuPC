package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnector implements DBConnector {
    @Override
    public Connection getConnection(String host, String username, String password) throws SQLException {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(host, username, password);
            connection.setAutoCommit(false);

            System.out.println("Connection Complete!");
        } catch (ClassNotFoundException e) {
            System.out.println("Failed to Find JDBC Driver");
        }

        return connection;
    }
}
