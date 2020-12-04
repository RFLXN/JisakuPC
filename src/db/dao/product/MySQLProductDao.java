package db.dao.product;

import bean.DBConnectionInfo;
import bean.Product;
import db.connector.DBCloseException;
import db.connector.DBCloser;
import db.connector.DBConnectException;
import db.connector.DBConnector;
import db.dao.DAOException;
import db.dao.factory.MySQLDaoFactory;
import db.selector.DBSelectException;
import db.selector.MySQLSelector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySQLProductDao implements ProductDao {
    private Connection connection;
    @Override
    public void addProduct(Product product) throws DAOException {

    }

    @Override
    public void deleteProduct(String pid) throws DAOException {

    }

    @Override
    public void updateProduct(String pid, Product product) throws DAOException {

    }

    @Override
    public Product getProduct(String pid) throws DAOException {
        Product product = null;

        String sql = "SELECT * FROM product_table WHERE product_no = " + pid;
        ResultSet resultSet = query(sql);

        try {
            resultSet.next();
            product.setNo(resultSet.getString("product_no"));
            product.setName(resultSet.getString("product_name"));
            product.setPrice(resultSet.getString("product_price"));
            product.setSpec(resultSet.getString("product_spec"));
            product.setBrand(resultSet.getString("product_brand"));
            product.setType(resultSet.getString("product_type"));

            DBCloser.closer(connection);
        } catch (SQLException | DBCloseException e) {
            throw new DAOException(e.getMessage(), e);
        }

        return product;
    }

    @Override
    public List<Product> getProductsByType(String type) throws DAOException {
        ArrayList<Product> products = new ArrayList<>();

        String sql = "SELECT * FROM product_table WHERE type = " + type;
        ResultSet resultSet = query(sql);

        try {
            while (resultSet.next()) {
                Product product = new Product();
                product.setNo(resultSet.getString("product_no"));
                product.setName(resultSet.getString("product_name"));
                product.setPrice(resultSet.getString("product_price"));
                product.setSpec(resultSet.getString("product_spec"));
                product.setBrand(resultSet.getString("product_brand"));
                product.setType(resultSet.getString("product_type"));

                products.add(product);
            }

            DBCloser.closer(connection);
        } catch (SQLException | DBCloseException e) {
            throw new DAOException(e.getMessage(), e);
        }

        return products;
    }

    @Override
    public List<Product> getAllProducts() throws DAOException {
        ArrayList<Product> products = new ArrayList<>();

        String sql = "SELECT * FROM product_table";
        ResultSet resultSet = query(sql);

        try {
            while (resultSet.next()) {
                Product product = new Product();
                product.setNo(resultSet.getString("product_no"));
                product.setName(resultSet.getString("product_name"));
                product.setPrice(resultSet.getString("product_price"));
                product.setSpec(resultSet.getString("product_spec"));
                product.setBrand(resultSet.getString("product_brand"));
                product.setType(resultSet.getString("product_type"));

                products.add(product);
            }

            DBCloser.closer(connection);
        } catch (SQLException | DBCloseException e) {
            throw new DAOException(e.getMessage(), e);
        }

        return products;
    }

    private Connection getConnection() throws DAOException {
        MySQLDaoFactory factory = (MySQLDaoFactory)MySQLDaoFactory.getInstance();
        DBConnector connector = factory.getConnector();
        DBConnectionInfo info = factory.getConnectionInfo();

        connection = null;
        try {
            connection = connector.getConnection(info.getHost()
                    , info.getUserName(), info.getPassword());
        } catch (DBConnectException e) {
            throw new DAOException(e.getMessage(), e);
        }
        return connection;
    }

    private ResultSet query(String querySQL) throws DAOException {
        connection = getConnection();
        ResultSet resultSet = null;

        try {
            MySQLSelector selector = new MySQLSelector(connection);
            resultSet = selector.select(querySQL);
        } catch (DBSelectException e) {
            throw new DAOException(e.getMessage(), e);
        }

        return resultSet;
    }

}
