package command;

import bean.Product;
import context.ResponseContext;
import db.dao.DAOException;
import db.dao.factory.AbstractDaoFactory;
import db.dao.product.ProductDao;
import org.json.JSONObject;

public class UpdateJSONProductPriceCommand extends AbstractCommand {
    @Override
    public ResponseContext execute(ResponseContext responseContext) throws CommandException {
        JSONObject result = new JSONObject();
        result.put("isUpdated", false);
        try {
            String pid = getRequestContext().getParameter("productId")[0];
            String price = getRequestContext().getParameter("price")[0];
            AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
            ProductDao dao = factory.getProductsDao();

            Product product = dao.getProduct(pid);
            product.setPrice(price);
            dao.updateProduct(product);

            result.remove("isUpdated");
            result.put("isUpdated", true);
        } catch (DAOException e) {
            throw new CommandException(e);
        }
        responseContext.setResult(result);

        return responseContext;
    }
}


