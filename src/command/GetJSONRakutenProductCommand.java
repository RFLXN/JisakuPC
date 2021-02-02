package command;

import bean.Product;
import bean.RakutenItem;
import bean.RakutenProduct;
import context.ResponseContext;
import db.dao.DAOException;
import db.dao.factory.AbstractDaoFactory;
import db.dao.product.ProductDao;
import org.json.JSONObject;
import rakuten.*;

import java.util.List;

public class GetJSONRakutenProductCommand extends AbstractCommand {
    @Override
    public ResponseContext execute(ResponseContext responseContext) throws CommandException {
        JSONObject result = new JSONObject();
        try {
            String pid = getRequestContext().getParameter("productId")[0];
            AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
            ProductDao dao = factory.getProductsDao();

            Product product = dao.getProduct(pid);

            String brandName = product.getBrand();
            String productName = product.getName();
            String type = product.getType();
            String genreId = RakutenPropertyLoader.getInstance().getApiParameter("genre-" + type);

            String searchString = productName.replace(brandName + " ", "")
                    .replace(" ", "");

            RakutenAPIWrapper api = new RakutenAPIWrapper();
            try {
                result = searchProduct(searchString, api, genreId);
            } catch (RakutenAPIException e) {
                try {
                    result = searchItem(searchString, api,
                            productName.replace(brandName + " ", ""), genreId);
                } catch (RakutenAPIException ee) {
                    throw new CommandException(ee);
                }
            }

        } catch (DAOException | RakutenAPIException e) {
            throw new CommandException(e);
        }
        responseContext.setResult(result);

        return responseContext;
    }

    private JSONObject searchItem(String productName, RakutenAPIWrapper api, String originProductName, String type) throws RakutenAPIException {
        JSONObject itemSearchResult = null;
        ItemJSONParser parser = null;
        JSONObject result = new JSONObject();
        try {
            itemSearchResult = api.searchItem(new String[]{
                    "productName:" + productName,
                    "genre:" + type});
            parser = new ItemJSONParser(itemSearchResult);
        } catch (Exception e) {
            throw new RakutenAPIException(e);
        }
        if (parser.getItemList().size() < 1) {
            throw new RakutenAPIException("no result");
        }

        List<RakutenItem> itemList = parser.getItemList();

        for (RakutenItem item : itemList) {
            if (item.getItemName().toLowerCase().contains(originProductName.toLowerCase())) {
                result.put("productName", item.getItemName());
                result.put("genreId", item.getGenreId());
                result.put("productUrl", item.getItemUrl());
                result.put("mediumImageUrl", item.getMediumImageUrls());
                result.put("smallImageUrl", item.getSmallImageUrls());
                result.put("minPrice", item.getPrice());
                result.put("rakutenItemCode", item.getItemCode());

                break;
            }
        }

        return result;
    }

    private JSONObject searchProduct(String productName, RakutenAPIWrapper api, String type) throws RakutenAPIException {
        JSONObject productObject = null;
        ProductJSONParser parser = null;
        JSONObject result = new JSONObject();
        try {
            productObject = api.searchProduct(new String[]{
                    "productName:" + productName,
                    "genre:" + type});
            parser = new ProductJSONParser(productObject);
        } catch (Exception e) {
            throw new RakutenAPIException(e);
        }
        if (parser.getItemList().size() < 1) {
            throw new RakutenAPIException("no result");
        }

        RakutenProduct rakutenProduct = parser.getItem(0);

        result.put("minPrice", rakutenProduct.getMinPrice());
        result.put("maxPrice", rakutenProduct.getMaxPrice());
        result.put("averagePrice", rakutenProduct.getAveragePrice());
        result.put("genreId", rakutenProduct.getGenreId());
        result.put("productName", rakutenProduct.getProductName());
        result.put("mediumImageUrl", rakutenProduct.getMediumImageUrl());
        result.put("smallImageUrl", rakutenProduct.getSmallImageUrl());
        result.put("productUrl", rakutenProduct.getProductUrlPc());
        result.put("productUrlMobile", rakutenProduct.getProductUrlMobile());
        result.put("rakutenProductId", rakutenProduct.getProductId());

        return result;
    }
}
