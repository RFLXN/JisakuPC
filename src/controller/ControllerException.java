package controller;

public class ControllerException extends Exception {
    public ControllerException() {
        super();
    }
    public ControllerException(String msg) {
        super(msg);
    }
    public ControllerException(Exception e) {
        super(e);
    }
    public ControllerException(String msg, Exception e) {
        super(msg, e);
    }
}
