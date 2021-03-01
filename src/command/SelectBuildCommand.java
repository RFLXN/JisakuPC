package command;

import bean.Build;
import context.ResponseContext;
import db.dao.DAOException;
import db.dao.build.BuildDao;
import db.dao.factory.AbstractDaoFactory;

public class SelectBuildCommand extends AbstractCommand {
    @Override
    public ResponseContext execute(ResponseContext responseContext) throws CommandException {
        String buildNo = getRequestContext().getParameter("buildNo")[0];
        responseContext.setTarget("addbuild");

        if (buildNo == null || buildNo.equals("")) {
            return responseContext;
        }

        if (buildNo.equals("new")) {
            getRequestContext().setSessionAttribute("build", null);
        } else {
            try {
                BuildDao dao = AbstractDaoFactory.getFactory().getBuildDao();
                Build build = dao.getBuild(buildNo);

                getRequestContext().setSessionAttribute("build", build);
            } catch (DAOException e) {
                throw new CommandException(e);
            }
        }

        return responseContext;
    }
}
