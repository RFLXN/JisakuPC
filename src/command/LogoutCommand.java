package command;

import context.ResponseContext;

public class LogoutCommand extends AbstractCommand {
    @Override
    public ResponseContext execute(ResponseContext responseContext) throws CommandException {
        getRequestContext().clearSession();

        responseContext.setTarget("index");
        return responseContext;
    }
}
