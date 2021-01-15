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
import java.sql.PreparedStatement;
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
        connection = getConnection();

        try {
            String sql = "SELECT * FROM product_table WHERE product_no = (?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, pid);
            ResultSet resultSet = query(statement);

            resultSet.next();
            product.setNo(resultSet.getString("product_no"));
            product.setName(resultSet.getString("product_name"));
            product.setPrice(resultSet.getString("product_price"));
            product.setSpec(resultSet.getString("product_spec"));
            product.setBrand(resultSet.getString("product_brand"));
            product.setType(resultSet.getString("product_type"));

            DBCloser.close(connection);
        } catch (SQLException | DBCloseException e) {
            if(connection != null) {
                try {
                    DBCloser.close(connection);
                } catch (DBCloseException ce) {
                    throw new DAOException(ce.getMessage(), ce);
                }
            }
            throw new DAOException(e.getMessage(), e);
        }

        return product;
    }

    @Override
    public List<Product> getProductsByType(String type) throws DAOException {
        ArrayList<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM product_table WHERE type = (?)";
        connection = getConnection();

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, type);

            ResultSet resultSet = query(statement);

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

            DBCloser.close(connection);
        } catch (SQLException | DBCloseException e) {
            if(connection != null) {
                try {
                    DBCloser.close(connection);
                } catch (DBCloseException ce) {
                    throw new DAOException(ce.getMessage(), ce);
                }
            }

            throw new DAOException(e.getMessage(), e);
        }

        return products;
    }

    @Override
    public List<Product> getSearchProducts(String moji) throws DAOException {
        ArrayList<Product> products = new ArrayList<>();

        String sql = "SELECT  * FROM product_table WHERE product_name LIKE ?";

        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            statement.setString(1, "%" + moji + "%");

            ResultSet resultSet = query(statement);

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

            DBCloser.close(connection);
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

            DBCloser.close(connection);
        } catch (SQLException | DBCloseException e) {
            if(connection != null) {
                try {
                    DBCloser.close(connection);
                } catch (DBCloseException ce) {
                    throw new DAOException(ce.getMessage(), ce);
                }
            }
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
            MySQLSelector selector = new MySQLSelector();
            resultSet = selector.select(connection, querySQL);
        } catch (DBSelectException e) {
            throw new DAOException(e.getMessage(), e);
        }

        return resultSet;
    }

    private ResultSet query(PreparedStatement statement) throws DAOException {
        ResultSet resultSet = null;

        try {
            MySQLSelector selector = new MySQLSelector();
            resultSet = selector.select(statement);
        } catch (DBSelectException e) {
            throw new DAOException(e.getMessage(), e);
        }

        return resultSet;
    }

}
