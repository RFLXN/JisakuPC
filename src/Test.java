import bean.RakutenProduct;
import org.json.JSONObject;
import rakuten.ProductJSONParser;
import rakuten.RakutenAPIWrapper;
import rakuten.RakutenPropertyLoader;
import resources.csv.CsvDbInserter;

import java.util.List;

public class Test {
    public static void main(String[] args) {
        try {
            RakutenAPIWrapper api = new RakutenAPIWrapper();
            RakutenPropertyLoader properties = RakutenPropertyLoader.getInstance();

            String cpu = properties.getApiParameter("genre-cpu");

            JSONObject result = api.search(new String[]{"productName:Ryzen 5600", "genre:" + cpu});

            System.out.println(result.toString(2));


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
