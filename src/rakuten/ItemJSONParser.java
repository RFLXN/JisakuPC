package rakuten;

import bean.RakutenItem;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ItemJSONParser {
    private final List<RakutenItem> data;

    public ItemJSONParser(JSONObject jsonData) {
        JSONObject root = jsonData;
        JSONArray products = root.getJSONArray("Items");
        data = new ArrayList<>();

        for (Object p : products) {
            JSONObject product = (JSONObject) p;
            RakutenItem item = new RakutenItem();
            item.setGenreId(product.getString("genreId"));
            item.setItemCode(product.getString("itemCode"));
            item.setItemName(product.getString("itemName"));
            item.setItemUrl(product.getString("itemUrl"));

            JSONArray array = product.getJSONArray("mediumImageUrls");
            String[] strArr = new String[array.length()];
            for (int i = 0; i < array.length(); i++) {
                strArr[i] = array.getString(i);
            }
            item.setMediumImageUrls(strArr);

            array = product.getJSONArray("smallImageUrls");
            strArr = new String[array.length()];
            for (int i = 0; i < array.length(); i++) {
                strArr[i] = array.getString(i);
            }
            item.setSmallImageUrls(strArr);

            item.setPrice(product.getInt("itemPrice"));
            item.setShopName(product.getString("shopName"));


            data.add(item);
        }
    }

    public List<RakutenItem> getItemList() {
        return data;
    }

    public RakutenItem getItem(int index) {
        return data.get(index);
    }
}
