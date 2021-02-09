package db.dao.factory;

import java.lang.reflect.Method;
import java.util.ResourceBundle;

import db.dao.DAOException;
import db.dao.build.BuildDao;
import db.dao.post.PostDao;
import db.dao.product.ProductDao;
import db.dao.user.UserDao;

public abstract class AbstractDaoFactory {
    public static AbstractDaoFactory getFactory() throws DAOException {
        AbstractDaoFactory factory = null;

        try {
            ResourceBundle bundle = ResourceBundle.getBundle("resources.dao");
            String dao = bundle.getString("dao");
            Class<?> cls = Class.forName(dao);
            Method method = cls.getMethod("getInstance");

            factory = (AbstractDaoFactory) method.invoke(null, null);
        } catch (Exception e) {
            throw new DAOException(e.getMessage(), e);
        }

        return factory;
    }

    public abstract ProductDao getProductsDao();

    public abstract UserDao getUserDao();

    public abstract PostDao getPostsDao();

    public abstract BuildDao getBuildDao();
}
