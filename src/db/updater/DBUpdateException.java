package db.updater;

import db.DBException;

public class DBUpdateException extends DBException {
    public DBUpdateException() {
        super();
    }

    public DBUpdateException(String msg) {
        super(msg);
    }

    public DBUpdateException(Exception e) {
        super(e);
    }

    public DBUpdateException(String msg, Exception e) {
        super(msg, e);
    }
}
