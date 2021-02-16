package command;

import bean.Post;
import context.ResponseContext;
import db.dao.DAOException;
import db.dao.factory.AbstractDaoFactory;
import db.dao.post.PostDao;

import java.util.ArrayList;
import java.util.List;

public class ShowPostCommand extends AbstractCommand {
    @Override
    public ResponseContext execute(ResponseContext responseContext) throws CommandException {
        List<Post> posts = new ArrayList<>();

        try {
            AbstractDaoFactory daoFactory = AbstractDaoFactory.getFactory();
            PostDao dao = daoFactory.getPostsDao();
            String postno = getRequestContext().getParameter("postno")[0];
            System.out.println("showpostno=" + postno);
            //posts = dao.getSearchPost(postno);
            posts = dao.getPostData(postno);
        } catch (DAOException e) {
            throw new CommandException(e);
        }

        responseContext.setResult(posts);
        responseContext.setTarget("showpost");

        return responseContext;
    }
}
