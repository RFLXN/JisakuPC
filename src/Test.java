import bean.Product;
import db.dao.factory.AbstractDaoFactory;
import org.json.JSONArray;
import org.json.JSONObject;

public class Test {
    public static void main(String[] args) {
        try {
            Product product = AbstractDaoFactory.getFactory().getProductsDao().getProduct("1");
            String spec = product.getSpec();

            System.out.println(spec);


            //JSONデータを持っているStringでJSONObjectをnewする
            JSONObject object = new JSONObject(spec);

            // ルートオブジェクトからキーがKEYであるデータを持ってくる
            object.getString("KEY");

            JSONArray array = object.getJSONArray("ARRAY_KEY");

            for (Object obj : array) {
                JSONObject jsonObj = (JSONObject) obj;
                jsonObj.getString("asdasdads");
            }


        } catch (Exception e) {

        }
    }

}
