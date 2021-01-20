package command;

import context.ResponseContext;

public class ShowLoginCommand extends AbstractCommand {
    @Override
    public ResponseContext execute(ResponseContext responseContext) throws CommandException {
        responseContext.setTarget("login");

        return responseContext;
    }
}
