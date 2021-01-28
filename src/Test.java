import bean.RakutenProduct;
import org.json.JSONObject;
import rakuten.ProductJSONParser;
import rakuten.RakutenAPIWrapper;
import resources.csv.CsvDbInserter;

import java.util.List;

public class Test {
    public static void main(String[] args) {
        try {
            CsvDbInserter inserter = new CsvDbInserter();
            inserter.insertAll();

            /*RakutenAPIWrapper api = new RakutenAPIWrapper();
            JSONObject result = api.search(new String[]{"productName:AMD EPYC 7551P"});
            ProductJSONParser parser = new ProductJSONParser(result);
            List<RakutenProduct> productList = parser.getProductList();
            for(RakutenProduct product : productList) {
                System.out.println(product.getProductName() + " : " + product.getAveragePrice());
            }*/
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
