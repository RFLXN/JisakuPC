package command;

import bean.Product;
import context.ResponseContext;
import db.dao.DAOException;
import db.dao.factory.AbstractDaoFactory;
import db.dao.product.ProductDao;

public class AddNewProductCommand extends AbstractCommand {
    @Override
    public ResponseContext execute(ResponseContext responseContext) throws CommandException {
        String productName = getRequestContext().getParameter("productName")[0];
        String productBrand = getRequestContext().getParameter("productBrand")[0];
        String price = getRequestContext().getParameter("price")[0];
        String spec = getRequestContext().getParameter("spec")[0];
        String productType = getRequestContext().getParameter("productType")[0];

        try {
            ProductDao dao = AbstractDaoFactory.getFactory().getProductsDao();

            Product product = new Product();
            product.setName(productName);
            product.setPrice(price);
            product.setBrand(productBrand);
            product.setSpec(spec);
            product.setType(productType);

            dao.addProduct(product);
        } catch (DAOException e) {
            throw new CommandException(e);
        }

        responseContext.setTarget("admin/productmanagement");

        return responseContext;
    }
}
