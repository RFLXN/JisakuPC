package command;

public class CommandException extends Exception {
    public CommandException() {
        super();
    }
    public CommandException(String msg) {
        super(msg);
    }
    public CommandException(Exception e) {
        super(e);
    }
    public CommandException(String msg, Exception e) {
        super(msg, e);
    }
}
