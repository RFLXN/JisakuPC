package command;

import bean.Build;
import bean.UserFlag;
import context.ResponseContext;
import db.dao.DAOException;
import db.dao.factory.AbstractDaoFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ShowMyPageCommand extends AbstractCommand {
    @Override
    public ResponseContext execute(ResponseContext responseContext) throws CommandException {
        String userNo = ((UserFlag)((HttpServletRequest) getRequestContext().getRequest())
                .getSession().getAttribute("loginFlag")).getUserNo();

        try {
            List<Build> buildList = AbstractDaoFactory.getFactory().getBuildDao().getUserBuilds(userNo);
            responseContext.setResult(buildList);
        } catch (DAOException e) {
            throw new CommandException(e);
        }

        responseContext.setTarget("mypage");
        return responseContext;
    }
}
