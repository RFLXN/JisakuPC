package command;

import context.ResponseContext;

public class ShowAdminCommand extends AbstractCommand {
    @Override
    public ResponseContext execute(ResponseContext responseContext) throws CommandException {
        responseContext.setTarget("admin");
        return responseContext;
    }
}
