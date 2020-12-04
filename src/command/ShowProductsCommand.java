package command;

import bean.Product;
import context.ResponseContext;
import db.dao.DAOException;
import db.dao.factory.AbstractDaoFactory;
import db.dao.product.ProductDao;

import java.util.ArrayList;
import java.util.List;

public class ShowProductsCommand extends AbstractCommand {
    @Override
    public ResponseContext execute(ResponseContext responseContext) throws CommandException {
        List<Product> products = new ArrayList<>();

        try {
            AbstractDaoFactory daoFactory = AbstractDaoFactory.getFactory();
            ProductDao dao = daoFactory.getProductsDao();
            products = dao.getAllProducts();
        } catch (DAOException e) {
            throw new CommandException(e.getMessage(), e);
        }

        responseContext.setResult(products);
        responseContext.setTarget("showproducts");

        return responseContext;
    }
}
