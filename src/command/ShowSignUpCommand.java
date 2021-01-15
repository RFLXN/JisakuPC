package command;

import context.ResponseContext;

public class ShowSignUpCommand extends AbstractCommand {
    @Override
    public ResponseContext execute(ResponseContext responseContext) throws CommandException {
        responseContext.setTarget("signup");

        return responseContext;
    }
}
