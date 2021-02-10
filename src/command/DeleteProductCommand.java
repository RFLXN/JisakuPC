package command;

import bean.Product;
import context.ResponseContext;
import db.dao.DAOException;
import db.dao.factory.AbstractDaoFactory;
import db.dao.product.ProductDao;

public class DeleteProductCommand extends AbstractCommand {
    @Override
    public ResponseContext execute(ResponseContext responseContext) throws CommandException {
        String productNo = getRequestContext().getParameter("productNo")[0];

        try {
            ProductDao dao = AbstractDaoFactory.getFactory().getProductsDao();

            Product product = new Product();

            dao.deleteProduct(productNo);
        } catch (DAOException e) {
            throw new CommandException(e);
        }

        responseContext.setTarget("admin/productmanagement");

        return responseContext;
    }
}
