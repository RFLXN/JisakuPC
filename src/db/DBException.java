package db;

public class DBException extends Exception {
    public DBException() {
        super();
    }

    public DBException(String msg) {
        super(msg);
    }

    public DBException(Exception e) {
        super(e);
    }

    public DBException(String msg, Exception e) {
        super(msg, e);
    }
}
