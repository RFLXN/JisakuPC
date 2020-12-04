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

        Properties properties = new Properties();

        try {
            properties.load(new FileInputStream("src/resources/mysql.properties"));

            info.setHost(properties.getProperty("host"));
            info.setUserName(properties.getProperty("username"));
            info.setPassword(properties.getProperty("password"));
        } catch (IOException e) {
            throw new DAOException("Failed to Load MySQL Properties File", e);
        }

        return info;
    }

}
