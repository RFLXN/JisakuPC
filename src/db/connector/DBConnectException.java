package db.connector;

import db.DBException;

public class DBConnectException extends DBException {
    public DBConnectException() {
        super();
    }

    public DBConnectException(String msg) {
        super(msg);
    }

    public DBConnectException(Exception e) {
        super(e);
    }

    public DBConnectException(String msg, Exception e) {
        super(msg, e);
    }
}
