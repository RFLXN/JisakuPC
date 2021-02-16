package command;

import bean.Build;
import bean.Product;
import context.ResponseContext;
import db.dao.DAOException;
import db.dao.factory.AbstractDaoFactory;
import db.dao.product.ProductDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class AddPartsToBuildCommand extends AbstractCommand {
    @Override
    public ResponseContext execute(ResponseContext responseContext) throws CommandException {
        HttpSession session = ((HttpServletRequest) getRequestContext().getRequest()).getSession();
        String productNo = getRequestContext().getParameter("productNo")[0];

        try {
            ProductDao dao = AbstractDaoFactory.getFactory().getProductsDao();
            Product product = dao.getProduct(productNo);

            if (session.getAttribute("build") == null | session.getAttribute("build").equals("")) {
                ArrayList<Product> products = new ArrayList<Product>();
                products.add(product);
                Build build = new Build();
                build.setProducts(products);
                session.setAttribute("build", build);
            } else {
                Build build = (Build) session.getAttribute("build");
                List<Product> products = build.getProducts();
                products.add(product);
                session.setAttribute("build", build);
            }
        } catch (DAOException e) {
            throw new CommandException(e);
        }

        responseContext.setTarget("build");
        return responseContext;
    }
}
