package db.dao.user;

import bean.DBConnectionInfo;
import bean.UserFlag;
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

public class MySQLUserDao implements UserDao {
    private Connection connection;

    @Override
    public void addUser(String id, String password) throws DAOException {
        connection = getConnection();
        try {
            String sql = "INSERT INTO user_table(user_id, user_pw, admin)"
                    + " VALUES (?, sha2(?, 256), ?)";
            PreparedStatement statement = getConnection().prepareStatement(sql);

            statement.setString(1, id);
            statement.setString(2, password);
            statement.setBoolean(3, false);

            update(statement);

            DBCloser.close(connection);
        } catch (SQLException | DBCloseException e) {
            throw new DAOException(e.getMessage(), e);
        }
    }

    @Override
    public void deleteUser(String userNo) throws DAOException {
        connection = getConnection();
        try {
            String sql = "DELETE FROM user_table WHERE user_no = ?";
            PreparedStatement statement = getConnection().prepareStatement(sql);

            statement.setInt(1, Integer.parseInt(userNo));

            update(statement);

            DBCloser.close(connection);
        } catch (SQLException | DBCloseException e) {
            throw new DAOException(e.getMessage(), e);
        }
    }

    @Override
    public boolean isAlreadyUsedId(String id) throws DAOException {
        connection = getConnection();
        boolean isAlreadyUsedId = true;
        try {
            String sql = "SELECT user_id FROM user_table WHERE user_id = (?)";
            PreparedStatement statement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            statement.setString(1, id);
            ResultSet resultSet = query(statement);

            resultSet.last();

            if (resultSet.getRow() < 1) {
                isAlreadyUsedId = false;
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

        return isAlreadyUsedId;
    }

    @Override
    public UserFlag getUser(String id, String password) throws DAOException {
        UserFlag userFlag = new UserFlag();

        connection = getConnection();

        try {
            String sql = "SELECT * FROM user_table WHERE user_id = (?)";
            PreparedStatement statement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            statement.setString(1, id);
            ResultSet resultSet = query(statement);

            resultSet.last();

            if (resultSet.getRow() < 1) {
                userFlag.setCorrectUser(false);
            } else {
                userFlag.setUserId(resultSet.getString("user_id"));
                userFlag.setUserNo(resultSet.getString("user_no"));
                userFlag.setAdmin(resultSet.getBoolean("admin"));

                String rawPassword = resultSet.getString("user_pw");

                sql = "SELECT sha2(?, 256) AS password";
                statement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                statement.setString(1, password);

                resultSet = query(statement);

                resultSet.last();
                userFlag.setCorrectUser(resultSet.getString("password").equals(rawPassword));
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

        return userFlag;
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

