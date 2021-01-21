package command;

import java.util.ArrayList;
import java.util.List;

import bean.Product;
import context.ResponseContext;
import db.dao.DAOException;
import db.dao.factory.AbstractDaoFactory;
import db.dao.product.ProductDao;

public class SearchProductsCommand extends AbstractCommand {
    @Override
    public ResponseContext execute(ResponseContext responseContext) throws CommandException {
        List<Product> products = new ArrayList<Product>();

        String productName = getRequestContext().getParameter("moji")[0];

        try {
            AbstractDaoFactory daoFactory = AbstractDaoFactory.getFactory();
            ProductDao dao = daoFactory.getProductsDao();

            String[] sort = getRequestContext().getParameter("sort-by-cost");

            if(sort == null || sort[0].equals("")) {
                products = dao.getSearchProducts(productName);
            } else if(sort[0].equals("asc")) {
                products = dao.getASCSearchProducts(productName);
            } else if(sort[0].equals("desc")) {
                products = dao.getDESCSearchProducts(productName);
            }

        } catch (DAOException e) {
            throw new CommandException(e);
        }

        responseContext.setResult(products);
        responseContext.setTarget("showproducts");

        return responseContext;
    }
}
