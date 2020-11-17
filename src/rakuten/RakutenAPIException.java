package rakuten;

public class RakutenAPIException extends Exception {
    public RakutenAPIException(String message, Exception e) {
        super(message, e);
    }

    public RakutenAPIException(String message) {
        super(message);
    }
}
