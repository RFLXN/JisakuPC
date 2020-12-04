package db.connector;

import db.DBException;

public class DBSelectException extends DBException {
    public DBSelectException() {
        super();
    }

    public DBSelectException(String msg) {
        super(msg);
    }

    public DBSelectException(Exception e) {
        super(e);
    }

    public DBSelectException(String msg, Exception e) {
        super(msg, e);
    }
}
