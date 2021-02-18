package db.dao.product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
import db.updater.DBUpdateException;
import db.updater.MySQLUpdater;

public class MySQLProductDao implements ProductDao {
    private Connection connection;

    @Override
    public void addProduct(Product product) throws DAOException {
        connection = getConnection();

        try {
            String sql = "INSERT INTO product_table (product_name, product_price, product_spec, " +
                    "product_brand, product_type) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, product.getName());
            statement.setInt(2, Integer.parseInt(product.getPrice()));
            statement.setString(3, product.getSpec());
            statement.setString(4, product.getBrand());
            statement.setString(5, product.getType());

            update(statement);

            DBCloser.close(connection);
        } catch (SQLException | DBCloseException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void deleteProduct(String pid) throws DAOException {
        String sql = "DELETE FROM product_table WHERE product_no = ?";
        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            statement.setString(1, pid);
            update(statement);

            DBCloser.close(connection);
        } catch (SQLException | DBCloseException e) {
            throw new DAOException(e.getMessage(), e);
        }
    }

    @Override
    public void updateProduct(Product product) throws DAOException {
        connection = getConnection();

        try {
            String sql = "UPDATE product_table " +
                    "SET product_name = (?), product_price = (?), " +
                    "product_spec = (?), product_brand = (?), product_type = (?) " +
                    "WHERE product_no = (?)";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, product.getName());
            statement.setInt(2, Integer.parseInt(product.getPrice()));
            statement.setString(3, product.getSpec());
            statement.setString(4, product.getBrand());
            statement.setString(5, product.getType());
            statement.setInt(6, Integer.parseInt(product.getNo()));

            update(statement);

            DBCloser.close(connection);
        } catch (SQLException | DBCloseException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public Product getProduct(String pid) throws DAOException {
        Product product = new Product();
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
            if (connection != null) {
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
            if (connection != null) {
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
    public List<Product> getASCSearchProducts(String moji) throws DAOException {
        ArrayList<Product> products = new ArrayList<>();

        String sql = "SELECT  * FROM product_table WHERE product_name LIKE ? ORDER BY product_price ASC";

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
    public List<Product> getDESCSearchProducts(String moji) throws DAOException {
        ArrayList<Product> products = new ArrayList<>();

        String sql = "SELECT  * FROM product_table WHERE product_name LIKE ? ORDER BY product_price DESC";

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
            if (connection != null) {
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
    public List<Product> getPartsSearchProducts(String moji) throws DAOException {
        ArrayList<Product> products = new ArrayList<>();
        System.out.println(moji);
        String sql = "SELECT  * FROM product_table WHERE product_type = ?";
        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            statement.setString(1, "%" + moji);

            statement.setString(1, moji);


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
    public List<Product> getWordSearchProducts(String word) throws DAOException {
        ArrayList<Product> products = new ArrayList<>();
        System.out.println(word);
        String sql = "SELECT  * FROM product_table WHERE product_name LIKE ?";
        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            statement.setString(1, "%" + word + "%");

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
    public List<Product> getSpecSearchProducts(String word) throws DAOException {
        ArrayList<Product> products = new ArrayList<>();
        System.out.println(word);
        String sql = "SELECT * FROM product_table WHERE product_spec LIKE ?";
        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            statement.setString(1, "%" + word + "%");

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
    public List<Product> getSpecialSearchProducts(String ddr,String clock) throws DAOException {
        ArrayList<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM product_table WHERE product_spec LIKE ? AND product_spec LIKE ?";
        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            statement.setString(1, "%" + ddr + "%");
            statement.setString(2, "%" + clock + "%");

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
    public List<Product> getVolumeSearchProducts(String under,String over) throws DAOException {
        ArrayList<Product> products = new ArrayList<>();

        String sql = " select * from product_table WHERE JSON_UNQUOTE(JSON_EXTRACT(product_spec,'$.volume')) BETWEEN ? AND ? ";
        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            statement.setInt(1,Integer.parseInt(under));
            statement.setInt(2,Integer.parseInt(over));

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
    public List<Product> getWSizeSearchProducts(String under,String over) throws DAOException {
        ArrayList<Product> products = new ArrayList<>();

        String sql = " select * from product_table WHERE JSON_UNQUOTE(JSON_EXTRACT(product_spec,'$.W')) BETWEEN ? AND ? ";
        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            statement.setInt(1,Integer.parseInt(under));
            statement.setInt(2,Integer.parseInt(over));

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
    public List<Product> getSizeSearchProducts(String size) throws DAOException {
        ArrayList<Product> products = new ArrayList<>();

        String sql = " select * from product_table WHERE JSON_UNQUOTE(JSON_EXTRACT(product_spec,'$.size')) = ?";
        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            statement.setDouble(1,Double.parseDouble(size));

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

    private Connection getConnection() throws DAOException {
        MySQLDaoFactory factory = (MySQLDaoFactory) MySQLDaoFactory.getInstance();
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

    private void update(PreparedStatement statement) throws DAOException {
        try {
            MySQLUpdater updater = new MySQLUpdater();
            updater.update(statement);
        } catch (DBUpdateException e) {
            throw new DAOException(e.getMessage(), e);
        }
    }

    public List<Product> getAddBuildProducts(String name, String spec, String brand, String type) throws DAOException {
        ArrayList<Product> products = new ArrayList<>();
        Product product = new Product();
        connection = getConnection();

        try {
            String sql = "SELECT * FROM product_table WHERE product_no = (?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, spec);
            statement.setString(3, brand);
            statement.setString(4, type);
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
            if (connection != null) {
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
}
