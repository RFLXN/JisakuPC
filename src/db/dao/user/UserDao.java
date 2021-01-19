package db.dao.user;

import bean.UserFlag;
import db.dao.DAOException;

public interface UserDao {
    void addUser(String id, String password) throws DAOException;

    void deleteUser(String userNo) throws DAOException;

    boolean isAlreadyUsedId(String id) throws DAOException;

    UserFlag getUser(String id, String password) throws DAOException;
}
