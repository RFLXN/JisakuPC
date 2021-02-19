package command;

import bean.Post;
import context.ResponseContext;
import db.dao.DAOException;
import db.dao.factory.AbstractDaoFactory;
import db.dao.post.PostDao;

import java.util.List;

public class ShowPostListCommand extends AbstractCommand {
    @Override
    public ResponseContext execute(ResponseContext responseContext) throws CommandException {

        try {
            PostDao dao = AbstractDaoFactory.getFactory().getPostsDao();
            List<Post> posts = dao.getShowPostProducts();
            responseContext.setResult(posts);
        } catch (DAOException e) {
            throw new CommandException(e);
        }

        responseContext.setTarget("postlist");
        return responseContext;
    }
}
