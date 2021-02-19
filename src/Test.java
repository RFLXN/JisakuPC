import bean.Product;
import db.dao.factory.AbstractDaoFactory;
import db.dao.product.ProductDao;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        try {
            ProductDao dao = AbstractDaoFactory.getFactory().getProductsDao();
            HashMap<String, Object> options = new HashMap<>();

            HashMap<String, String> specOptions = new HashMap<>();
            specOptions.put("tdp", "65");
            specOptions.put("socket", "Socket AM4");

            options.put("specOptions", specOptions);


            List<Product> products = dao.searchProducts(options, 0, 99);

            for(Product product: products) {
                System.out.println(product.getName() + ": " + product.getPrice() + " " + product.getSpec());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
