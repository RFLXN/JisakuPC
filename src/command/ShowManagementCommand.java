package command;

import javax.servlet.http.HttpServletRequest;

import bean.UserFlag;
import context.ResponseContext;

public class ShowManagementCommand extends AbstractCommand {
    @Override
    public ResponseContext execute(ResponseContext responseContext) throws CommandException {
        boolean userNo = ((UserFlag) ((HttpServletRequest) getRequestContext().getRequest())
                .getSession().getAttribute("loginFlag")).isAdmin();
        if(userNo == true) {
            responseContext.setTarget("admin/productmanagement");
        } else {
        	responseContext.setTarget("/JisakuPC/");
        }
        return responseContext;
    }
}
