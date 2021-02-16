package db.dao.post;

import bean.Post;
import db.dao.DAOException;

import java.util.List;

public interface PostDao {
    void insertPostBuildProducts(String title, String discription, String buildno, String userno) throws DAOException;

    List<Post> getShowPostProducts() throws DAOException;

    List<Post> getAllPosts() throws DAOException;

    void deletePost(String postno) throws DAOException;

    List<Post> getPostData(String postno) throws DAOException;
}
