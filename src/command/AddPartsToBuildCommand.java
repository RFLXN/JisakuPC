package command;

import bean.Build;
import bean.Product;
import context.ResponseContext;
import db.dao.DAOException;
import db.dao.factory.AbstractDaoFactory;
import db.dao.product.ProductDao;

import java.util.ArrayList;
import java.util.List;

public class AddPartsToBuildCommand extends AbstractCommand {
    @Override
    public ResponseContext execute(ResponseContext responseContext) throws CommandException {
        String productNo = getRequestContext().getParameter("pid")[0];

        try {
            ProductDao dao = AbstractDaoFactory.getFactory().getProductsDao();
            Product product = dao.getProduct(productNo);

            if (getRequestContext().getSessionAttribute("build") == null
                    || getRequestContext().getSessionAttribute("build").equals("")) {
                ArrayList<Product> products = new ArrayList<Product>();
                products.add(product);
                Build build = new Build();
                build.setProducts(products);
                getRequestContext().setSessionAttribute("build", build);
            } else {
                Build build = (Build) getRequestContext().getSessionAttribute("build");
                if(build.getProducts() == null || build.getProducts().size() == 0) {
                    build.setProducts(new ArrayList<>());
                    build = (Build) getRequestContext().getSessionAttribute("build");
                }
                List<Product> products = build.getProducts();
                products.add(product);
                getRequestContext().setSessionAttribute("build", build);
            }
        } catch (DAOException e) {
            throw new CommandException(e);
        }

        responseContext.setTarget("addbuild");
        return responseContext;
    }
}
