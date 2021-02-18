package db.dao.build;

import bean.Build;
import db.dao.DAOException;

import java.util.List;

public interface BuildDao {
    String addBuild(String userNo, String buildName) throws DAOException;

    List<Build> getUserBuilds(String userNo) throws DAOException;

    Build getBuild(String buildNo) throws DAOException;

    Build getBuildByName(String buildName) throws DAOException;

    void deleteBuild(String buildNo) throws DAOException;

    void updateBuild(Build build) throws DAOException;
}
