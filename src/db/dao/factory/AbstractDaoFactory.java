package db.dao.factory;

import db.dao.DAOException;
import db.dao.product.ProductDao;

import java.io.FileInputStream;
import java.util.Properties;

public abstract class AbstractDaoFactory {
    public static AbstractDaoFactory getFactory() throws DAOException {
        AbstractDaoFactory factory = null;
        Properties properties = new Properties();

        try {
            properties.load(new FileInputStream("../../resources/dao.properties"));
            String dao = properties.getProperty("dao");
            factory = (AbstractDaoFactory) Class.forName(dao).newInstance();
        } catch (Exception e) {
            throw new DAOException(e.getMessage(), e);
        }

        return factory;
    }

    public abstract ProductDao getProductsDao();
}
