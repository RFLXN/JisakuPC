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
        JSONArray products = root.getJSONArray("Products");
        data = new ArrayList<>();

        for (Object p : products) {
            JSONObject product = (JSONObject) p;
            RakutenProduct rakutenProduct = new RakutenProduct();

            rakutenProduct.setGenreId(product.getString("genreId"));
            rakutenProduct.setProductName(product.getString("productName"));
            rakutenProduct.setProductId(product.getString("productId"));
            rakutenProduct.setProductUrlPc(product.getString("productUrlPC"));
            rakutenProduct.setProductUrlMobile(product.getString("productUrlMobile"));
            rakutenProduct.setMediumImageUrl(product.getString("mediumImageUrl"));
            rakutenProduct.setSmallImageUrl(product.getString("smallImageUrl"));
            rakutenProduct.setMaxPrice(product.getInt("maxPrice"));
            rakutenProduct.setMinPrice(product.getInt("minPrice"));
            rakutenProduct.setAveragePrice(product.getInt("averagePrice"));

            data.add(rakutenProduct);
        }
    }

    public List<RakutenProduct> getItemList() {
        return data;
    }

    public RakutenProduct getItem(int index) {
        return data.get(index);
    }
}
