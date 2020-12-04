package db.dao;

import db.DBException;

public class DAOException extends DBException {
    public DAOException() {
        super();
    }

    public DAOException(String msg) {
        super(msg);
    }

    public DAOException(Exception e) {
        super(e);
    }

    public DAOException(String msg, Exception e) {
        super(msg, e);
    }
}
