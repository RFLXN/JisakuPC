package db.dao.build;

import bean.Build;
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

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class MySQLBuildDao implements BuildDao {
    private Connection connection;

    @Override
    public String addBuild(String userNo, String buildName) throws DAOException {
        String result = null;
        try {
            connection = getConnection();
            String sql = "INSERT INTO build_table(user_no, build_name) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, Integer.parseInt(userNo));
            statement.setString(2, buildName);

            update(statement);

            sql = "SELECT build_no FROM build_table WHERE build_name = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, buildName);

            ResultSet resultSet = query(statement);

            resultSet.next();

            result = resultSet.getString(1);

            DBCloser.close(connection);
        } catch (SQLException | DBCloseException e) {
            throw new DAOException(e);
        }

        return result;
    }

    @Override
    public List<Build> getUserBuilds(String userNo) throws DAOException {
        List<Build> list = new ArrayList<>();
        try {
            connection = getConnection();
            String sql = "SELECT * FROM build_table WHERE user_no = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, Integer.parseInt(userNo));
            ResultSet resultSet = query(statement);

            Map<Integer, String> buildNoBuff = new HashMap<>();

            while (resultSet.next()) {
                buildNoBuff.put(resultSet.getInt("build_no")
                        , resultSet.getString("build_name"));
            }

            Set<Integer> b = buildNoBuff.keySet();

            for (int buildNo : b) {
                sql = "select build_no, product_table.product_no AS 'product_no', " +
                        "product_name, product_type,product_brand, product_spec, product_price " +
                        "from build_parts_table join product_table " +
                        "on build_parts_table.product_no = product_table.product_no " +
                        "where build_no = ?";

                statement = connection.prepareStatement(sql);
                statement.setInt(1, buildNo);

                resultSet = query(statement);

                Build build = new Build();
                List<Product> products = new ArrayList<>();
                while (resultSet.next()) {
                    Product product = new Product();
                    product.setNo(resultSet.getString("product_no"));
                    product.setName(resultSet.getString("product_name"));
                    product.setBrand(resultSet.getString("product_brand"));
                    product.setType(resultSet.getString("product_type"));
                    product.setPrice(resultSet.getString("product_price"));
                    product.setSpec(resultSet.getString("product_spec"));

                    products.add(product);
                }
                build.setBuildNo(Integer.toString(buildNo));
                build.setBuildName(buildNoBuff.get(buildNo));
                build.setUserNo(userNo);
                build.setProducts(products);

                list.add(build);
            }

            DBCloser.close(connection);
        } catch (SQLException | DBCloseException e) {
            throw new DAOException(e);
        }
        return list;
    }

    @Override
    public void deleteBuild(String buildNo) throws DAOException {
        connection = getConnection();

        try {
            String sql = "DELETE FROM build_parts_table WHERE build_no = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, Integer.parseInt(buildNo));
            update(statement);

            sql = "DELETE FROM build_table WHERE build_no = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, Integer.parseInt(buildNo));
            update(statement);

            DBCloser.close(connection);
        } catch (SQLException | DBCloseException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void updateBuild(Build build) throws DAOException {
        connection = getConnection();

        try {
            String sql = "UPDATE build_table SET " +
                    "build_name = ? WHERE build_no = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, build.getBuildName());
            statement.setInt(2, Integer.parseInt(build.getBuildNo()));
            update(statement);

            sql = "DELETE FROM build_parts_table WHERE build_no = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, Integer.parseInt(build.getBuildNo()));
            update(statement);

            sql = "INSERT INTO build_parts_table(build_no, product_no) " +
                    "VALUES (?, ?)";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, Integer.parseInt(build.getBuildNo()));

            for (Product part : build.getProducts()) {
                statement.setInt(2, Integer.parseInt(part.getNo()));
                update(statement);
            }

            DBCloser.close(connection);
        } catch (SQLException | DBCloseException e) {
            throw new DAOException(e);
        }
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
}
