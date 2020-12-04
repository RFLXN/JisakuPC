package db.updater;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLUpdater extends DBUpdater {
    public MySQLUpdater(Connection connection) {
        super(connection);
    }

    @Override
    public void update(String updateSQL) throws DBUpdateException {
        Connection connection = getConnection();
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
    public void update(String updateSQL, String[][] data) throws DBUpdateException {
        Connection connection = getConnection();
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(updateSQL);

            for(int i=0; i<data.length ; i++) {
                for(int j=0 ; j<data[i].length ; j++) {
                    statement.setString(j+1, data[i][j]);
                }
                statement.executeUpdate();
            }

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
}
