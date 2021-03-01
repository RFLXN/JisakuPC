package command;

import bean.Build;
import bean.StackedProduct;
import context.ResponseContext;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class GetJSONSessionBuildCommand extends AbstractCommand {
    @Override
    public ResponseContext execute(ResponseContext responseContext) throws CommandException {

        try {
            Build build = (Build) getRequestContext().getSessionAttribute("build");
            if (build == null) {
                responseContext.setResult(new JSONObject());
                return responseContext;
            }

            String buildName = build.getBuildName();
            String buildNo = build.getBuildNo();
            String totalPrice = build.getTotalPrice();
            String userNo = build.getUserNo();
            List<StackedProduct> productList = build.getStackedProducts();

            JSONObject object = new JSONObject();

            if (buildName != null && !buildName.equals("")) {
                object.put("buildName", buildName);
            }
            if (buildNo != null && !buildNo.equals("")) {
                object.put("buildNo", buildNo);
            }
            if (totalPrice != null && !totalPrice.equals("")) {
                object.put("totalPrice", totalPrice);
            }
            if (userNo != null && !userNo.equals("")) {
                object.put("userNo", userNo);
            }
            JSONArray productArray = new JSONArray();
            for (StackedProduct product : productList) {
                JSONObject productObject = new JSONObject();
                productObject.put("stack", product.getStack());
                productObject.put("productName", product.getProduct().getName());
                productObject.put("productNo", product.getProduct().getNo());
                productObject.put("productType", product.getProduct().getType());
                productObject.put("price", product.getProduct().getPrice());
                productObject.put("brand", product.getProduct().getBrand());

                try {
                    productObject.put("spec", new JSONObject(product.getProduct().getSpec()));
                } catch (JSONException ignored) {
                }

                productArray.put(productObject);
            }

            object.put("products", productArray);

            responseContext.setResult(object);

        } catch (ClassCastException | NullPointerException e) {
            throw new CommandException(e);
        }

        return responseContext;
    }
}
