package db.selector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLSelector extends DBSelector {
    public MySQLSelector(Connection connection) {
        super(connection);
    }

    @Override
    public ResultSet select(String querySQL) throws DBSelectException {
        Connection connection = getConnection();
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
}
