import bean.Product;
import bean.UserFlag;
import db.dao.product.MySQLProductDao;
import db.dao.user.MySQLUserDao;

import java.util.List;

public class Test {
    public static void main(String[] args) {
        MySQLUserDao mySQLUserDao = new MySQLUserDao();

        try {
            mySQLUserDao.deleteUser("4");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
