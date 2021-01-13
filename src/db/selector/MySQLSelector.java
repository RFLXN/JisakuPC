package db.selector;

import java.sql.*;

public class MySQLSelector extends DBSelector {
    @Override
    public ResultSet select(Connection connection, String querySQL) throws DBSelectException {
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(querySQL);
        } catch (SQLException e) {
            throw new DBSelectException(e.getMessage(), e);
        }

        return resultSet;
    }

    @Override
    public ResultSet select(PreparedStatement statement) throws DBSelectException {
        ResultSet resultSet = null;

        try {
            resultSet = statement.executeQuery();
        } catch (SQLException e) {
            throw new DBSelectException(e.getMessage(), e);
        }

        return resultSet;
    }
}
