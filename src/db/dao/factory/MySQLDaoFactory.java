package db.dao.factory;

import bean.DBConnectionInfo;
import db.connector.DBConnector;
import db.connector.MySQLConnector;
import db.dao.DAOException;
import db.dao.product.MySQLProductDao;
import db.dao.product.ProductDao;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.ResourceBundle;

public class MySQLDaoFactory extends DBDaoFactory {
    private static final MySQLDaoFactory instance;

    private MySQLDaoFactory() {}

    static {
        instance = new MySQLDaoFactory();
    }

    public static AbstractDaoFactory getInstance() {
        return instance;
    }

    @Override
    public ProductDao getProductsDao() {
        return new MySQLProductDao();
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
