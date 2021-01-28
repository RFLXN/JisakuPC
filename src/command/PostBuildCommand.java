package command;

import java.util.ArrayList;
import java.util.List;

import bean.Post;
import context.ResponseContext;
import db.dao.DAOException;
import db.dao.factory.AbstractDaoFactory;
import db.dao.product.ProductDao;

public class PostBuildCommand extends AbstractCommand {
    @Override
    public ResponseContext execute(ResponseContext responseContext) throws CommandException {
        List<Post> posts = new ArrayList<>();

        try {
            AbstractDaoFactory daoFactory = AbstractDaoFactory.getFactory();
            ProductDao dao = daoFactory.getProductsDao();
            String title = getRequestContext().getParameter("title")[0];
            String description = getRequestContext().getParameter("description")[0];
            dao.getPostBuildProducts(title,description);
        } catch (DAOException e) {
            throw new CommandException(e);
        }

        //responseContext.setResult(posts);
        responseContext.setTarget("post");

        return responseContext;
    }
}
