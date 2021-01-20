package command;

import context.ResponseContext;

public class ShowMyPageCommand extends AbstractCommand {
    @Override
    public ResponseContext execute(ResponseContext responseContext) throws CommandException {
        responseContext.setTarget("mypage");
        return responseContext;
    }
}
