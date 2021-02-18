package command;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import bean.Product;
import context.ResponseContext;
import db.dao.DAOException;
import db.dao.factory.AbstractDaoFactory;
import db.dao.product.ProductDao;

public class SortProductsCommand extends AbstractCommand {
    @Override
    public ResponseContext execute(ResponseContext responseContext) throws CommandException {
        List<Product> products = new ArrayList<Product>();

        //String productName = getRequestContext().getParameter("moji")[0];
        //System.out.println(productName);
        try {
            AbstractDaoFactory daoFactory = AbstractDaoFactory.getFactory();
            ProductDao dao = daoFactory.getProductsDao();

            String[] parts = getRequestContext().getParameter("parts");
            String words = getRequestContext().getParameter("words")[0];
            //wordはKEY
            String word = getRequestContext().getParameter("word")[0];

            //product_noの取得がまだできていない
            Product product = AbstractDaoFactory.getFactory().getProductsDao().getProduct("1787");
            String spec = product.getSpec();

            //JSONデータを持っているStringでJSONObjectをnewする
            JSONObject object = new JSONObject(spec);

            // ルートオブジェクトからキーがKEYであるデータを持ってくる
            if (word != null && word.length() > 1) {
                String value = object.getString(word);
                //valueにKEYの値が入っている
                System.out.println(value);
            }


            if(parts == null || parts[0].equals("")) {
                if(words.equals("Ryzen 5") || words.equals("Core i9") || words.equals("Core i7") || words.equals("B550") || words.equals("Z490") ||
                		words.equals("Noctua") || words.equals("クーラーマスター") || words.equals("Corsair")) {

                    products = dao.getWordSearchProducts(words);


                } else if(words.equals("Mini ITX") ||words.equals("MicroATX") || words.equals("ATX") || words.equals("Mini-ITX") ||
                		words.equals("SFX") || words.equals("RTX 3070") || words.equals("RTX 2070 SUPER") ||
                		words.equals("RX 5700 XT") || words.equals("M.2 (Type2280)")) {

                    products = dao.getSpecSearchProducts(words);


                } else if(words.equals("DDR4-2666")) {
                	//ddr=DDR4,clock=2666


                    products = dao.getSpecialSearchProducts("DDR4","2666");



                } else if(words.equals("480GB~512GB未満")) {

//                	select product_no,product_price,JSON_EXTRACT(product_spec,'$.volume')AS HDD
//                	FROM product_table;
                	System.out.print("sssssssssss");
                	products = dao.getVolumeSearchProducts("480","511");


                } else if(words.equals("300W~600W未満")) {
                	products = dao.getWSizeSearchProducts("300","599");
                } else if(words.equals("600W~800W未満")) {
                	products = dao.getWSizeSearchProducts("600","799");

                    //できた
                } else if(words.equals("2.5インチ")) {
                    products = dao.getSizeSearchProducts("2.5");
                } else if(words.equals("140mm角")) {
                    products = dao.getSizeSearchProducts("140");
                } else if(words.equals("120mm角")) {
                    products = dao.getSizeSearchProducts("120");
                } else {
                	System.out.println("NoSpec");
                }
            } else if(parts[0].equals("cpu")) {
                products = dao.getPartsSearchProducts("cpu");
            } else if(parts[0].equals("gpu")) {
                products = dao.getPartsSearchProducts("gpu");
            } else if(parts[0].equals("ram")) {
                products = dao.getPartsSearchProducts("ram");
            } else if(parts[0].equals("cpu_cooler")) {
                products = dao.getPartsSearchProducts("cpu_cooler");
            } else if(parts[0].equals("case")) {
                products = dao.getPartsSearchProducts("case");
            } else if(parts[0].equals("mother_board")) {
                products = dao.getPartsSearchProducts("mother_board");
            } else if(parts[0].equals("storage")) {
                products = dao.getPartsSearchProducts("storage");
            } else if(parts[0].equals("power_supply")) {
                products = dao.getPartsSearchProducts("power_supply");
            } else if(parts[0].equals("case_fan")) {
                products = dao.getPartsSearchProducts("case_fan");
            }



        } catch (DAOException e) {
            throw new CommandException(e);
        }

        responseContext.setResult(products);
        responseContext.setTarget("showproducts");

        return responseContext;
    }
}
