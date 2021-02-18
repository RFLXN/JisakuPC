package command;

import bean.Build;
import context.ResponseContext;
import db.dao.DAOException;
import db.dao.build.BuildDao;
import db.dao.factory.AbstractDaoFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SelectBuildCommand extends AbstractCommand {
    @Override
    public ResponseContext execute(ResponseContext responseContext) throws CommandException {
        String buildNo = getRequestContext().getParameter("buildName")[0];
        HttpSession session = ((HttpServletRequest)(getRequestContext().getRequest())).getSession();
        responseContext.setTarget("addbuild");

        if(buildNo == null || buildNo.equals("")) {
            return responseContext;
        }

        try {
            BuildDao dao = AbstractDaoFactory.getFactory().getBuildDao();
            Build build = dao.getBuild(buildNo);

            session.setAttribute("build", build);
        } catch (DAOException e) {
            throw new CommandException(e);
        }

        return responseContext;
    }
}
