package db.dao.product;

import bean.Product;
import db.dao.DAOException;

import java.util.List;
import java.util.Map;

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

    List<Product> getSpecialSearchProducts(String ddr, String clock, int... page) throws DAOException;

    List<Product> getVolumeSearchProducts(String under, String over, int... page) throws DAOException;

    List<Product> getWSizeSearchProducts(String under, String over, int... page) throws DAOException;

    List<Product> getSizeSearchProducts(String size, int... page) throws DAOException;

    /**
     * パーツ検索メソッド
     *
     * @param options (key, value) ->
     *                ("productName", String), ("productType", String),
     *                ("productBrand", String), ("priceRange", int[] = [最小値, 最大値]),
     *                ("orderBy", String),
     *                ("specOptions", Map<String, String> = ["jsonKey", "jsonValue"])
     * @param page    (始まる結果の番号, 結果個数) or (結果個数)
     * @return 検索結果のリスト
     * @throws DAOException
     */
    List<Product> searchProducts(Map<String, Object> options, int... page) throws DAOException;

    int getSearchProductsLength(Map<String, Object> options) throws DAOException;
}
