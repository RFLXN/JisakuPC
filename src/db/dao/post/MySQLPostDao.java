package db.dao.post;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

public class MySQLPostDao implements PostDao {
    private Connection connection;

    @Override
    public List<Post> getAllPosts() throws DAOException {
        ArrayList<Post> posts = new ArrayList<>();

        String sql = "SELECT * FROM build_post_table";

        ResultSet resultSet = query(sql);

        try {
        	while (resultSet.next()) {
                Post post = new Post();

                post.setNo(resultSet.getString("post_no"));
                post.setUserNo(resultSet.getString("user_no"));
                post.setBuildNo(resultSet.getString("build_no"));
                post.setTitle(resultSet.getString("title"));
                post.setDescription(resultSet.getString("description"));
                post.setDate(resultSet.getString("date"));

                posts.add(post);
            }

            DBCloser.close(connection);
        } catch (SQLException | DBCloseException e) {
            throw new DAOException(e.getMessage(), e);
        }

        return posts;
    }

    @Override
    public void insertPostBuildProducts(String title ,String description) throws DAOException {
        ArrayList<Post> posts = new ArrayList<>();
        String sql = "INSERT INTO build_post_table (title,description) VALUES (?,?)";
        try {
	        connection = getConnection();
	        PreparedStatement statement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
	        statement.setString(1,title);
	        statement.setString(2,description);
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
	        statement.setString(1,postno);
	        update(statement);

	        DBCloser.close(connection);
        } catch (SQLException | DBCloseException e) {
            throw new DAOException(e.getMessage(), e);
        }
    }

    @Override
    public List<Post> getShowPostProducts() throws DAOException {
        ArrayList<Post> posts = new ArrayList<>();

        String sql = "select post_no,user_no,build_no,title,description,date from build_post_table;\n" + "";

        ResultSet resultSet = query(sql);

        try {

            while (resultSet.next()) {
                Post post = new Post();

                post.setTitle(resultSet.getString("title"));
                post.setDescription(resultSet.getString("description"));

                posts.add(post);
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

        return posts;
    }

    @Override
    public List<Post> getSearchPost(String postno) throws DAOException {
        ArrayList<Post> posts = new ArrayList<>();

        String sql = "SELECT  * FROM build_post_table WHERE post_no = ?";

        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            statement.setString(1,postno);

            ResultSet resultSet = query(statement);

        	while (resultSet.next()) {
                Post post = new Post();

                post.setNo(resultSet.getString("post_no"));
                post.setUserNo(resultSet.getString("user_no"));
                post.setBuildNo(resultSet.getString("build_no"));
                post.setTitle(resultSet.getString("title"));
                post.setDescription(resultSet.getString("description"));
                post.setDate(resultSet.getString("date"));

                posts.add(post);
            }

            DBCloser.close(connection);
        } catch (SQLException | DBCloseException e) {
            throw new DAOException(e.getMessage(), e);
        }

        return posts;
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

    private void update(PreparedStatement statement) throws DAOException {
        try {
            MySQLUpdater updater = new MySQLUpdater();
            updater.update(statement);
        } catch (DBUpdateException e) {
            throw new DAOException(e.getMessage(), e);
        }
    }
}
