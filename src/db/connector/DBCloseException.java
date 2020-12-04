package db.connector;

import db.DBException;

public class DBCloseException extends DBException {
    public DBCloseException() {
        super();
    }
    public DBCloseException(String msg) {
        super(msg);
    }
    public DBCloseException(Exception e) {
        super(e);
    }
    public DBCloseException(String msg, Exception e) {
        super(msg, e);
    }
}
