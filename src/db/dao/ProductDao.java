package db.dao;

import bean.Product;
import java.util.List;

public interface ProductDao {
    void addProduct(Product product) throws DAOException;
    Product getProduct(String pid) throws DAOException;
    List<Product> getProductsByType(String type) throws DAOException;
    List<Product> getAllProducts() throws DAOException;
}
