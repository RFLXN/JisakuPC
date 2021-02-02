package db.dao.post;

import java.util.List;

import bean.Post;
import db.dao.DAOException;

public interface PostDao {
    void insertPostBuildProducts(String title ,String discription) throws DAOException;
    List<Post> getShowPostProducts() throws DAOException;
    List<Post> getAllPosts() throws DAOException;
    void deletePost(String postno) throws DAOException;
    List<Post> getSearchPost(String postno) throws DAOException;
}
