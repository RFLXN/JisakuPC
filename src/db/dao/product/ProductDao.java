package db.dao.product;

import java.util.List;

import bean.Post;
import bean.Product;
import db.dao.DAOException;

public interface ProductDao {
    void addProduct(Product product) throws DAOException;
    void deleteProduct(String pid) throws DAOException;
    void updateProduct(String pid, Product product) throws DAOException;
    Product getProduct(String pid) throws DAOException;
    List<Product> getProductsByType(String type) throws DAOException;
    List<Product> getAllProducts() throws DAOException;
    List<Product> getSearchProducts(String moji) throws DAOException;
    List<Product> getASCSearchProducts(String moji) throws DAOException;
    List<Product> getDESCSearchProducts(String moji) throws DAOException;
    void getPostBuildProducts(String title ,String discription) throws DAOException;
    List<Post> getShowPostProducts() throws DAOException;
    List<Post> getAllPosts() throws DAOException;
    List<Product> getPartsSearchProducts(String moji) throws DAOException;
}
