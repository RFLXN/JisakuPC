import bean.Product;
import db.dao.product.MySQLProductDao;

import java.util.List;

public class Test {
    public static void main(String[] args) {
        MySQLProductDao dao = new MySQLProductDao();

        try {
            List<Product> list = dao.getAllProducts();

            for(Product p : list) {
                System.out.println(p.getName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
