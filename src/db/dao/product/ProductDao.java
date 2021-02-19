package db.dao.product;

import java.util.List;

import bean.Product;
import db.dao.DAOException;

public interface ProductDao {
    void addProduct(Product product) throws DAOException;

    void deleteProduct(String pid) throws DAOException;

    void updateProduct(Product product) throws DAOException;

    Product getProduct(String pid) throws DAOException;

    List<Product> getProductsByType(String type, int... page) throws DAOException;

    List<Product> getAllProducts() throws DAOException;

    List<Product> getSearchProducts(String moji, int... page) throws DAOException;

    List<Product> getASCSearchProducts(String moji, int... page) throws DAOException;

    List<Product> getDESCSearchProducts(String moji, int... page) throws DAOException;

    List<Product> getPartsSearchProducts(String moji, int... page) throws DAOException;

    List<Product> getAddBuildProducts(String name, String spec, String brand, String type) throws DAOException;

    List<Product> getWordSearchProducts(String word, int... page) throws DAOException;

    List<Product> getSpecSearchProducts(String word, int... page) throws DAOException;

    List<Product> getSpecialSearchProducts(String ddr,String clock, int... page) throws DAOException;

    List<Product> getVolumeSearchProducts(String under,String over, int... page) throws DAOException;

    List<Product> getWSizeSearchProducts(String under,String over, int... page) throws DAOException;

    List<Product> getSizeSearchProducts(String size, int... page) throws DAOException;

}
