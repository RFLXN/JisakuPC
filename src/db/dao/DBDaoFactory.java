package db.dao;

import bean.DBConnectionInfo;
import db.connector.DBConnector;

public abstract class DBDaoFactory extends AbstractDaoFactory {
    public abstract DBConnector getConnector();
    public abstract DBConnectionInfo getConnectionInfo() throws DAOException;
}
