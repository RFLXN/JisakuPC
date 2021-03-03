package command;

import bean.Post;
import bean.UserFlag;
import context.ResponseContext;
import db.dao.DAOException;
import db.dao.factory.AbstractDaoFactory;
import db.dao.post.PostDao;
import utility.Conversion;

import java.util.ArrayList;
import java.util.List;

public class PostBuildCommand extends AbstractCommand {
    @Override
    public ResponseContext execute(ResponseContext responseContext) throws CommandException {
        List<Post> posts = new ArrayList<>();
        Conversion cn = new Conversion();

        try {
            AbstractDaoFactory daoFactory = AbstractDaoFactory.getFactory();
            PostDao dao = daoFactory.getPostsDao();
            String title = getRequestContext().getParameter("title")[0];
            String description = Conversion.conversionText(getRequestContext().getParameter("description")[0]);
            String buildno = getRequestContext().getParameter("buildno")[0];
            UserFlag user = (UserFlag) getRequestContext().getSessionAttribute("loginFlag");
            String userno = user.getUserNo();

            dao.insertPostBuildProducts(title, description, buildno, userno);

        } catch (DAOException e) {
            throw new CommandException(e);
        }

        responseContext.setResult(posts);
        responseContext.setTarget("buildpostcomplete");

        return responseContext;
    }
}