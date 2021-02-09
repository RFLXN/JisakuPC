package command;

import java.util.ArrayList;
import java.util.List;

import bean.Post;
import context.ResponseContext;
import db.dao.DAOException;
import db.dao.factory.AbstractDaoFactory;
import db.dao.post.PostDao;

public class PostBuildCommand extends AbstractCommand {
    @Override
    public ResponseContext execute(ResponseContext responseContext) throws CommandException {
        List<Post> posts = new ArrayList<>();

        try {
            AbstractDaoFactory daoFactory = AbstractDaoFactory.getFactory();
            PostDao dao = daoFactory.getPostsDao();
            String title = getRequestContext().getParameter("title")[0];
            String description = getRequestContext().getParameter("description")[0];
            String buildno = getRequestContext().getParameter("buildno")[0];
            String userno = getRequestContext().getParameter("userno")[0];
            dao.insertPostBuildProducts(title,description,buildno,userno);

        } catch (DAOException e) {
            throw new CommandException(e);
        }

        responseContext.setResult(posts);
        responseContext.setTarget("buildpostcomplete");

        return responseContext;
    }
}