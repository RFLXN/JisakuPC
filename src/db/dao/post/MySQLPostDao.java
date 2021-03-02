package db.dao.post;

import bean.DBConnectionInfo;
import bean.Post;
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

public class MySQLPostDao implements PostDao {
    private Connection connection;

    @Override
    public void insertPostBuildProducts(String title, String description, String buildno, String userno) throws DAOException {
        //ArrayList<Post> posts = new ArrayList<>();
        String sql = "INSERT INTO build_post_table (title,description,build_no,user_no) VALUES (?,?,?,?)";
        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            statement.setString(1, title);
            statement.setString(2, description);
            statement.setString(3, buildno);
            statement.setString(4, userno);
            update(statement);

            DBCloser.close(connection);
        } catch (SQLException | DBCloseException e) {
            throw new DAOException(e.getMessage(), e);
        }
    }


    @Override
    public void deletePost(String postno) throws DAOException {
        String sql = "DELETE FROM build_post_table WHERE post_no = ?";
        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            statement.setString(1, postno);
            update(statement);

            DBCloser.close(connection);
        } catch (SQLException | DBCloseException e) {
            throw new DAOException(e.getMessage(), e);
        }
    }

    @Override
    public List<Post> getShowPostProducts() throws DAOException {
        ArrayList<Post> posts = new ArrayList<>();

        String sql = "select post_no,user_no,build_no,title,description,date from build_post_table";

        ResultSet resultSet = query(sql);

        try {
            while (resultSet.next()) {
                Post post = new Post();

                post.setTitle(resultSet.getString("title"));
                post.setDescription(resultSet.getString("description"));
                post.setNo(resultSet.getString("post_no"));

                posts.add(post);
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

        return posts;
    }

    @Override
    public List<Post> getPostData(String postno) throws DAOException {
        ArrayList<Post> posts = new ArrayList<>();

        String sql = "SELECT bpt.post_no,ut.user_id,bpt.title,bpt.description,bpt.date "
                + "FROM build_post_table bpt INNER JOIN user_table ut ON bpt.user_no = ut.user_no "
                + "WHERE bpt.post_no = ?;";
        String sql1 = "SELECT pt.product_type,pt.product_no,pt.product_name,pt.product_price "
                + "FROM build_post_table bpot INNER JOIN build_table bt "
                + "INNER JOIN build_parts_table bpat INNER JOIN product_table pt "
                + "ON bpot.build_no=bt.build_no AND bt.build_no=bpat.build_no "
                + "AND bpat.product_no=pt.product_no WHERE bpot.post_no = ?;";

        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            statement.setString(1, postno);

            ResultSet resultSet = query(statement);


            PreparedStatement statement1 = connection.prepareStatement(sql1, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            statement1.setString(1, postno);

            ResultSet resultSet1 = query(statement1);

            while (resultSet.next()) {
                Post post = new Post();
                post.setUserid(resultSet.getString("user_id"));
                post.setNo(resultSet.getString("post_no"));
                post.setTitle(resultSet.getString("title"));
                post.setDescription(resultSet.getString("description"));
                post.setDate(resultSet.getString("date"));
                ArrayList<Post> list = new ArrayList<>();
                while (resultSet1.next()) {

                    Post post2 = new Post();
                    post2.setProductno(resultSet1.getString("product_no"));
                    post2.setPname(resultSet1.getString("product_name"));
                    post2.setPrice(resultSet1.getString("product_price"));
                    post2.setType(resultSet1.getString("product_type"));

                    list.add(post2);

                }
                post.setList(list);
                posts.add(post);
            }

            DBCloser.close(connection);
        } catch (SQLException | DBCloseException e) {
            throw new DAOException(e.getMessage(), e);
        }

        return posts;
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
