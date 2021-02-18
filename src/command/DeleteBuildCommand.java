package command;

import bean.UserFlag;
import context.ResponseContext;
import db.dao.DAOException;
import db.dao.build.BuildDao;
import db.dao.factory.AbstractDaoFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class DeleteBuildCommand extends AbstractCommand {
    @Override
    public ResponseContext execute(ResponseContext responseContext) throws CommandException {
        String buildNo = getRequestContext().getParameter("buildNo")[0];
        try {
            BuildDao dao = AbstractDaoFactory.getFactory().getBuildDao();
            dao.deleteBuild(buildNo);
            UserFlag user = (UserFlag)(((HttpServletRequest)(getRequestContext().getRequest())).getSession()).getAttribute("loginFlag");
            responseContext.setResult(dao.getUserBuilds(user.getUserNo()));
        } catch (DAOException e) {
            throw new CommandException(e);
        }
        responseContext.setTarget("mypage");
        return responseContext;
    }
}
