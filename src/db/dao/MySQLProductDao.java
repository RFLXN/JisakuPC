package db.dao;

import bean.DBConnectionInfo;
import bean.Product;
import db.connector.DBConnectException;
import db.connector.DBConnector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class MySQLProductDao implements ProductDao {
    @Override
    public void addProduct(Product product) throws DAOException {

    }

    @Override
    public Product getProduct(String pid) throws DAOException {
        return null;
    }

    @Override
    public List<Product> getProductsByType(String type) throws DAOException {
        return null;
    }

    @Override
    public List<Product> getAllProducts() throws DAOException {
        return null;
    }

    private Connection getConnection() throws DAOException {
        MySQLDaoFactory factory = (MySQLDaoFactory)MySQLDaoFactory.getInstance();
        DBConnector connector = factory.getConnector();
        DBConnectionInfo info = factory.getConnectionInfo();

        Connection connection = null;
        try {
            connection = connector.getConnection(info.getHost()
                    , info.getUserName(), info.getPassword());
        } catch (DBConnectException e) {
            throw new DAOException(e.getMessage(), e);
        }
        return connection;
    }

    private ResultSet query(String querySQL) throws DAOException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        return resultSet;
    }
}
