package db.updater;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLUpdater extends DBUpdater {
    @Override
    public void update(Connection connection, String updateSQL) throws DBUpdateException {
        Statement statement = null;

        try {
            statement = connection.createStatement();
            statement.executeUpdate(updateSQL);
        } catch (SQLException e) {
            try {
                connection.rollback();
                throw new DBUpdateException(e.getMessage(), e);
            } catch (SQLException re) {
                throw new DBUpdateException(re.getMessage(), re);
            }
        } finally {
            if(statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    throw new DBUpdateException(e.getMessage(), e);
                }
            }
            if(connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new DBUpdateException(e.getMessage(), e);
                }
            }
        }
    }

    @Override
    public void update(PreparedStatement statement) throws DBUpdateException {
        try {
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DBUpdateException(e.getMessage(), e);
        }
    }
}
