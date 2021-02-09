package command;

import context.ResponseContext;

public class ShowManagementCommand extends AbstractCommand {
    @Override
    public ResponseContext execute(ResponseContext responseContext) throws CommandException {

        responseContext.setTarget("admin/productmanagement");
        return responseContext;
    }
}
