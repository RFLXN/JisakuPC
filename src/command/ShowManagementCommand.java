package command;

import bean.UserFlag;
import context.ResponseContext;

public class ShowManagementCommand extends AbstractCommand {
    @Override
    public ResponseContext execute(ResponseContext responseContext) throws CommandException {
        UserFlag userFlag = (UserFlag) getRequestContext().getSessionAttribute("loginFlag");

        if (userFlag != null && userFlag.isAdmin()) {
            responseContext.setTarget("admin/productmanagement");
        } else {
            responseContext.setTarget("index");
        }

        return responseContext;
    }
}
