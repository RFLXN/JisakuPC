package db.dao.factory;

import bean.DBConnectionInfo;
import db.connector.DBConnector;
import db.connector.MySQLConnector;
import db.dao.DAOException;
import db.dao.product.MySQLProductDao;
import db.dao.product.ProductDao;
import db.dao.user.MySQLUserDao;
import db.dao.user.UserDao;

import java.util.ResourceBundle;

public class MySQLDaoFactory extends DBDaoFactory {
    private static final MySQLDaoFactory instance;

    static {
        instance = new MySQLDaoFactory();
    }

    private MySQLDaoFactory() {
    }

    public static AbstractDaoFactory getInstance() {
        return instance;
    }

    @Override
    public ProductDao getProductsDao() {
        return new MySQLProductDao();
    }

    @Override
    public UserDao getUserDao() {
        return new MySQLUserDao();
    }

    @Override
    public DBConnector getConnector() {
        return MySQLConnector.getInstance();
    }

    @Override
    public DBConnectionInfo getConnectionInfo() throws DAOException {
        DBConnectionInfo info = new DBConnectionInfo();

        ResourceBundle bundle = ResourceBundle.getBundle("resources.mysql");

        info.setHost(bundle.getString("host"));
        info.setUserName(bundle.getString("username"));
        info.setPassword(bundle.getString("password"));


        return info;
    }

}
