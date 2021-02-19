package command;

import bean.Build;
import bean.UserFlag;
import context.ResponseContext;
import db.dao.DAOException;
import db.dao.build.BuildDao;
import db.dao.factory.AbstractDaoFactory;

import java.util.ArrayList;
import java.util.List;

public class SaveBuildCommand extends AbstractCommand {
    @Override
    public ResponseContext execute(ResponseContext responseContext) throws CommandException {
        String buildName = getRequestContext().getParameter("buildName")[0];

        if(buildName.equals("")) {
            buildName = "新しい見積り";
        }

        try {
            UserFlag flag = (UserFlag) getRequestContext().getSessionAttribute("loginFlag");
            if(flag == null || !flag.isCorrectUser()) {
                responseContext.setTarget("login");
                return responseContext;
            }

            BuildDao dao = AbstractDaoFactory.getFactory().getBuildDao();

            List<Build> builds = dao.getUserBuilds(flag.getUserNo());
            boolean isAlreadyExist = false;

            for(Build build : builds) {
                if(build.getBuildName().equals(buildName)) {
                    isAlreadyExist = true;
                    break;
                }
            }

            if(isAlreadyExist) {
                Build sessionBuild = (Build) getRequestContext().getSessionAttribute("build");
                Build build = dao.getBuildByName(buildName);

                if(sessionBuild != null && sessionBuild.getProducts() != null) {
                    build.setProducts(sessionBuild.getProducts());
                } else {
                    build.setProducts(new ArrayList<>());
                }

                dao.updateBuild(build);

            } else {
                dao.addBuild(flag.getUserNo(), buildName);
                Build build = dao.getBuildByName(buildName);
                Build sessionBuild = (Build) getRequestContext().getSessionAttribute("build");

                if(sessionBuild != null && sessionBuild.getProducts() != null) {
                    build.setProducts(sessionBuild.getProducts());
                } else {
                    build.setProducts(new ArrayList<>());
                }

                dao.updateBuild(build);
            }

            responseContext.setTarget("addbuild");
        } catch (DAOException e) {
            throw new CommandException(e);
        }

        return responseContext;
    }
}
