package db.selector;

import java.sql.Connection;
import java.sql.ResultSet;

public abstract class DBSelector {
    private Connection connection;

    public DBSelector(Connection connection) {
        this.connection = connection;
    }

    protected Connection getConnection() {
        return connection;
    }

    public abstract ResultSet select(String querySQL) throws DBSelectException;
}
