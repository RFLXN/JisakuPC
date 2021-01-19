package db.dao.factory;

import bean.DBConnectionInfo;
import db.connector.DBConnector;
import db.dao.DAOException;

public abstract class DBDaoFactory extends AbstractDaoFactory {
    public abstract DBConnector getConnector();

    public abstract DBConnectionInfo getConnectionInfo() throws DAOException;
}
