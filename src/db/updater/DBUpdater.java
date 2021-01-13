package db.updater;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class DBUpdater {
    public abstract void update(Connection connection, String updateSQL) throws DBUpdateException;

    public abstract void update(PreparedStatement statement) throws DBUpdateException;
}
