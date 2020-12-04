package db.updater;

import java.sql.Connection;

public abstract class DBUpdater {
    private Connection connection;

    public DBUpdater(Connection connection) {
        this.connection = connection;
    }

    protected Connection getConnection() {
        return connection;
    }

    public abstract void update(String updateSQL) throws DBUpdateException;

    public abstract void update(String updateSQL, String[][] data) throws DBUpdateException;
}
