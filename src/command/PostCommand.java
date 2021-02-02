package command;

import java.util.ArrayList;
import java.util.List;

import bean.Post;
import context.ResponseContext;
import db.dao.DAOException;
import db.dao.factory.AbstractDaoFactory;
import db.dao.post.PostDao;

public class PostCommand extends AbstractCommand {
    @Override
    public ResponseContext execute(ResponseContext responseContext) throws CommandException {
        List<Post> posts = new ArrayList<>();

        try {
            AbstractDaoFactory daoFactory = AbstractDaoFactory.getFactory();
            PostDao dao = daoFactory.getPostsDao();
            posts = dao.getAllPosts();
        } catch (DAOException e) {
            throw new CommandException(e);
        }

        responseContext.setResult(posts);

        responseContext.setTarget("showpost");
        //responseContext.setTarget("postbuild");
        //本来は下になるがいちいち作成するのがめんどくさい為上のルートにしている

        return responseContext;
    }
}
