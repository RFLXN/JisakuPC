package rakuten;

import bean.RakutenProduct;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ProductJSONParser {
    private final List<RakutenProduct> data;

    public ProductJSONParser(JSONObject jsonData) {
        JSONObject root = jsonData;
        JSONArray products = root.getJSONArray("Items");
        ArrayList<RakutenProduct> productList = new ArrayList<>();
        data = new ArrayList<>();

        for(Object p : products) {
            JSONObject product = (JSONObject) p;
            RakutenProduct productBean = new RakutenProduct();
            productBean.setProductName(product.getString("productName"));
            productBean.setProductId(product.getString("productId"));
            productBean.setGenreId(product.getString("genreId"));
            productBean.setMaxPrice(product.getInt("maxPrice"));
            productBean.setMinPrice(product.getInt("minPrice"));
            productBean.setAveragePrice(product.getInt("averagePrice"));
            productBean.setProductUrlPc(product.getString("productUrlPC"));
            productBean.setProductUrlMobile(product.getString("productUrlMobile"));
            productBean.setSmallImageUrl(product.getString("smallImageUrl"));
            productBean.setMediumImageUrl(product.getString("mediumImageUrl"));

            data.add(productBean);
        }
    }

    public List<RakutenProduct> getProductList() {
        return data;
    }

    public RakutenProduct getProduct(int index) {
        return data.get(index);
    }
}
