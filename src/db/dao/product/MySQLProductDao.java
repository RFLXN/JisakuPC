package db.dao.product;

import bean.DBConnectionInfo;
import bean.Product;
import bean.ProductSpecSearchOption;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    /**
     * パーツ検索メソッド
     *
     * @param options (key, value) ->
     *                ("productName", String), ("productType", String),
     *                ("productBrand", String), ("priceRange", int[] = [最小値, 最大値]),
     *                ("orderBy", String),
     *                ("specOptions", Map<String, String> = ["jsonKey", "jsonValue"])
     * @param page    (始まる結果の番号, 結果個数) or (結果個数)
     * @return 検索結果のリスト
     * @throws DAOException
     */
    @Override
    public List<Product> searchProducts(Map<String, Object> options, int... page) throws DAOException {
        int frontPageNum = 0;
        int backPageNum = 10;

        if (page.length == 1) {
            backPageNum = page[0];
        } else if (page.length == 2) {
            frontPageNum = page[0];
            backPageNum = page[1];
        }

        String sql = createOptionSearchSql(options);
        List<Product> products = new ArrayList<>();

        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, frontPageNum);
            statement.setInt(2, backPageNum);

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
        } catch (SQLException | DBCloseException | NumberFormatException e) {
            throw new DAOException(e);
        }

        return products;
    }

    @Override
    public int getSearchProductsLength(Map<String, Object> options) throws DAOException {
        String sql = createOptionSearchSqlForLength(options);

        int result = 0;

        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);

            ResultSet resultSet = query(statement);

            resultSet.last();
            result = resultSet.getRow();


            DBCloser.close(connection);
        } catch (SQLException | DBCloseException | NumberFormatException e) {
            throw new DAOException(e);
        }

        return result;
    }

    private String createOptionSearchSql(Map<String, Object> options) throws NumberFormatException {
        StringBuilder builder = new StringBuilder();
        builder.append("SELECT product_table.* FROM (")
                .append("SELECT * FROM product_table WHERE ");

        if (options.containsKey("productName")) {
            if (builder.toString().endsWith("WHERE ")) {
                builder.append("product_name LIKE '%").append((String) options.get("productName"))
                        .append("%'");
            } else {
                builder.append(" AND product_name LIKE '%").append((String) options.get("productName"))
                        .append("%'");
            }
        }

        if (options.containsKey("productType")) {
            if (builder.toString().endsWith("WHERE ")) {
                builder.append("product_type = '").append(options.get("productType"))
                        .append("'");
            } else {
                builder.append(" AND product_type = '").append(options.get("productType"))
                        .append("'");
            }
        }

        if (options.containsKey("priceRange")) {
            int[] range = (int[]) options.get("priceRange");
            if (builder.toString().endsWith("WHERE ")) {
                builder.append("(product_price BETWEEN ").append(range[0])
                        .append(" AND ").append(range[1]).append(")");
            } else {
                builder.append(" AND (product_price BETWEEN ").append(range[0])
                        .append(" AND ").append(range[1]).append(")");
            }
        }

        if (options.containsKey("productBrand")) {
            if (builder.toString().endsWith("WHERE ")) {
                builder.append("product_brand = '").append((String) options.get("productBrand"))
                        .append("'");
            } else {
                builder.append(" AND product_brand = '").append((String) options.get("productBrand"))
                        .append("'");
            }
        }

        if (options.containsKey("specOptions")) {
            ArrayList<ProductSpecSearchOption> specSearchOptions = (ArrayList) options.get("specOptions");

            specSearchOptions.forEach((o) -> {
                String optionName = o.getOptionName();
                if (optionName.equals("80PLUS")) {
                    optionName = "\"80PLUS\"";
                }
                int valueType = o.getValueType();
                boolean isCanRange = o.isCanRange();
                String[] value = o.getValue();

                if (valueType == ProductSpecSearchOption.STRING) {
                    String trueValue = value[0];

                    if (builder.toString().endsWith("WHERE ")) {
                        builder.append("JSON_UNQUOTE(JSON_EXTRACT(product_spec, '$.")
                                .append(optionName).append("')) = ");
                    } else {
                        builder.append(" AND JSON_UNQUOTE(JSON_EXTRACT(product_spec, '$.")
                                .append(optionName).append("')) = ");
                    }
                    builder.append("'").append(trueValue).append("'");

                } else if (valueType == ProductSpecSearchOption.INT) {
                    int trueValue = Integer.parseInt(value[0]);
                    if (isCanRange) {
                        if (builder.toString().endsWith("WHERE ")) {
                            builder.append("JSON_UNQUOTE(JSON_EXTRACT(product_spec, '$.")
                                    .append(optionName).append("')) >= ");
                        } else {
                            builder.append(" AND JSON_UNQUOTE(JSON_EXTRACT(product_spec, '$.")
                                    .append(optionName).append("')) >= ");
                        }
                        builder.append(trueValue);

                        builder.append(" AND JSON_UNQUOTE(JSON_EXTRACT(product_spec, '$.")
                                .append(optionName).append("')) <= ");
                        builder.append(Integer.parseInt(value[1]));
                    } else {
                        if (builder.toString().endsWith("WHERE ")) {
                            builder.append("JSON_UNQUOTE(JSON_EXTRACT(product_spec, '$.")
                                    .append(optionName).append("')) = ");
                        } else {
                            builder.append(" AND JSON_UNQUOTE(JSON_EXTRACT(product_spec, '$.")
                                    .append(optionName).append("')) = ");
                        }
                        builder.append(trueValue);
                    }
                } else if (valueType == ProductSpecSearchOption.DOUBLE) {
                    double trueValue = Double.parseDouble(value[0]);
                    if (isCanRange) {
                        if (builder.toString().endsWith("WHERE ")) {
                            builder.append("JSON_UNQUOTE(JSON_EXTRACT(product_spec, '$.")
                                    .append(optionName).append("')) >= ");
                        } else {
                            builder.append(" AND JSON_UNQUOTE(JSON_EXTRACT(product_spec, '$.")
                                    .append(optionName).append("')) >= ");
                        }
                        builder.append(trueValue);

                        builder.append(" AND JSON_UNQUOTE(JSON_EXTRACT(product_spec, '$.")
                                .append(optionName).append("')) <= ");
                        builder.append(Double.parseDouble(value[1]));
                    } else {
                        if (builder.toString().endsWith("WHERE ")) {
                            builder.append("JSON_UNQUOTE(JSON_EXTRACT(product_spec, '$.")
                                    .append(optionName).append("')) = ");
                        } else {
                            builder.append(" AND JSON_UNQUOTE(JSON_EXTRACT(product_spec, '$.")
                                    .append(optionName).append("')) = ");
                        }
                        builder.append(trueValue);
                    }
                }
            });
        }


        if (options.containsKey("orderBy")) {
            if (builder.toString().endsWith("WHERE ")) {
                builder.delete(builder.length() - 6, builder.length());
                builder.append("ORDER BY product_price ").append((String) options.get("orderBy"));
            } else {
                builder.append(" ORDER BY product_price ").append((String) options.get("orderBy"));
            }
        }

        if (builder.toString().endsWith("WHERE ")) {
            builder.delete(builder.length() - 7, builder.length());
        }
        builder.append(") product_table LIMIT ?, ?");

        System.out.println(builder.toString());

        return builder.toString();
    }

    private String createOptionSearchSqlForLength(Map<String, Object> options) throws NumberFormatException {
        StringBuilder builder = new StringBuilder();
        builder.append("SELECT product_table.* FROM (")
                .append("SELECT * FROM product_table WHERE ");

        if (options.containsKey("productName")) {
            if (builder.toString().endsWith("WHERE ")) {
                builder.append("product_name LIKE '%").append((String) options.get("productName"))
                        .append("%'");
            } else {
                builder.append(" AND product_name LIKE '%").append((String) options.get("productName"))
                        .append("%'");
            }
        }

        if (options.containsKey("productType")) {
            if (builder.toString().endsWith("WHERE ")) {
                builder.append("product_type = '").append(options.get("productType"))
                        .append("'");
            } else {
                builder.append(" AND product_type = '").append(options.get("productType"))
                        .append("'");
            }
        }

        if (options.containsKey("priceRange")) {
            int[] range = (int[]) options.get("priceRange");
            if (builder.toString().endsWith("WHERE ")) {
                builder.append("(product_price BETWEEN ").append(range[0])
                        .append(" AND ").append(range[1]).append(")");
            } else {
                builder.append(" AND (product_price BETWEEN ").append(range[0])
                        .append(" AND ").append(range[1]).append(")");
            }
        }

        if (options.containsKey("productBrand")) {
            if (builder.toString().endsWith("WHERE ")) {
                builder.append("product_brand = '").append((String) options.get("productBrand"))
                        .append("'");
            } else {
                builder.append(" AND product_brand = '").append((String) options.get("productBrand"))
                        .append("'");
            }
        }

        if (options.containsKey("specOptions")) {
            ArrayList<ProductSpecSearchOption> specSearchOptions = (ArrayList) options.get("specOptions");

            specSearchOptions.forEach((o) -> {
                String optionName = o.getOptionName();
                if (optionName.equals("80PLUS")) {
                    optionName = "\"80PLUS\"";
                }
                int valueType = o.getValueType();
                boolean isCanRange = o.isCanRange();
                String[] value = o.getValue();

                if (valueType == ProductSpecSearchOption.STRING) {
                    String trueValue = value[0];

                    if (builder.toString().endsWith("WHERE ")) {
                        builder.append("JSON_UNQUOTE(JSON_EXTRACT(product_spec, '$.")
                                .append(optionName).append("')) = ");
                    } else {
                        builder.append(" AND JSON_UNQUOTE(JSON_EXTRACT(product_spec, '$.")
                                .append(optionName).append("')) = ");
                    }
                    builder.append("'").append(trueValue).append("'");

                } else if (valueType == ProductSpecSearchOption.INT) {
                    int trueValue = Integer.parseInt(value[0]);
                    if (isCanRange) {
                        if (builder.toString().endsWith("WHERE ")) {
                            builder.append("JSON_UNQUOTE(JSON_EXTRACT(product_spec, '$.")
                                    .append(optionName).append("')) >= ");
                        } else {
                            builder.append(" AND JSON_UNQUOTE(JSON_EXTRACT(product_spec, '$.")
                                    .append(optionName).append("')) >= ");
                        }
                        builder.append(trueValue);

                        builder.append(" AND JSON_UNQUOTE(JSON_EXTRACT(product_spec, '$.")
                                .append(optionName).append("')) <= ");
                        builder.append(Integer.parseInt(value[1]));
                    } else {
                        if (builder.toString().endsWith("WHERE ")) {
                            builder.append("JSON_UNQUOTE(JSON_EXTRACT(product_spec, '$.")
                                    .append(optionName).append("')) = ");
                        } else {
                            builder.append(" AND JSON_UNQUOTE(JSON_EXTRACT(product_spec, '$.")
                                    .append(optionName).append("')) = ");
                        }
                        builder.append(trueValue);
                    }
                } else if (valueType == ProductSpecSearchOption.DOUBLE) {
                    double trueValue = Double.parseDouble(value[0]);
                    if (isCanRange) {
                        if (builder.toString().endsWith("WHERE ")) {
                            builder.append("JSON_UNQUOTE(JSON_EXTRACT(product_spec, '$.")
                                    .append(optionName).append("')) >= ");
                        } else {
                            builder.append(" AND JSON_UNQUOTE(JSON_EXTRACT(product_spec, '$.")
                                    .append(optionName).append("')) >= ");
                        }
                        builder.append(trueValue);

                        builder.append(" AND JSON_UNQUOTE(JSON_EXTRACT(product_spec, '$.")
                                .append(optionName).append("')) <= ");
                        builder.append(Double.parseDouble(value[1]));
                    } else {
                        if (builder.toString().endsWith("WHERE ")) {
                            builder.append("JSON_UNQUOTE(JSON_EXTRACT(product_spec, '$.")
                                    .append(optionName).append("')) = ");
                        } else {
                            builder.append(" AND JSON_UNQUOTE(JSON_EXTRACT(product_spec, '$.")
                                    .append(optionName).append("')) = ");
                        }
                        builder.append(trueValue);
                    }
                }
            });
        }


        if (options.containsKey("orderBy")) {
            if (builder.toString().endsWith("WHERE ")) {
                builder.delete(builder.length() - 6, builder.length());
                builder.append("ORDER BY product_price ").append((String) options.get("orderBy"));
            } else {
                builder.append(" ORDER BY product_price ").append((String) options.get("orderBy"));
            }
        }

        if (builder.toString().endsWith("WHERE ")) {
            builder.delete(builder.length() - 7, builder.length());
        }
        builder.append(") product_table");

        System.out.println(builder.toString());

        return builder.toString();
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
