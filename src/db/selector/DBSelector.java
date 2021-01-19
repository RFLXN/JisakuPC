package db.selector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public abstract class DBSelector {
    public abstract ResultSet select(Connection connection, String querySQL) throws DBSelectException;

    public abstract ResultSet select(PreparedStatement statement) throws DBSelectException;
}
