package command;

import bean.Product;
import context.ResponseContext;
import db.dao.DAOException;
import db.dao.factory.AbstractDaoFactory;
import db.dao.product.ProductDao;

public class ShowSpecificProductCommand extends AbstractCommand {
    @Override
    public ResponseContext execute(ResponseContext responseContext) throws CommandException {
        String productNo = getRequestContext().getParameter("pid")[0];

        System.out.println(productNo);

        try {
            AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
            ProductDao dao = factory.getProductsDao();

            Product product = dao.getProduct(productNo);

            responseContext.setResult(product);
        } catch (DAOException | NumberFormatException e) {
            throw new CommandException(e);
        }

        responseContext.setTarget("productspec");

        return responseContext;
    }
}
