package command;

import bean.ProductSpecSearchOption;
import context.ResponseContext;
import db.dao.DAOException;
import db.dao.factory.AbstractDaoFactory;
import db.dao.product.ProductDao;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GetJSONSearchProductsLengthCommand extends AbstractCommand {
    @Override
    public ResponseContext execute(ResponseContext responseContext) throws CommandException {
        String productName = null;
        String sort = null;
        String productType = null;

        String[] parameterKeys = getRequestContext().getParameterKeys();

        if (getRequestContext().getParameter("productName") != null
                && (!getRequestContext().getParameter("productName")[0].equals(""))) {
            productName = getRequestContext().getParameter("productName")[0];
        }

        if (getRequestContext().getParameter("sortByCost") != null
                && (!getRequestContext().getParameter("sortByCost")[0].equals(""))) {
            sort = getRequestContext().getParameter("sortByCost")[0];
        }

        if (getRequestContext().getParameter("productType") != null
                && (!getRequestContext().getParameter("productType")[0].equals(""))) {
            productType = getRequestContext().getParameter("productType")[0];
        }

        try {
            ProductDao dao = AbstractDaoFactory.getFactory().getProductsDao();
            Map<String, Object> searchOption = new HashMap<>();

            if (productName != null) {
                searchOption.put("productName", productName);
            }

            if (sort != null) {
                searchOption.put("orderBy", sort);
            }

            if (productType != null) {
                searchOption.put("productType", productType);
            }
            ArrayList<ProductSpecSearchOption> specSearchOptions = new ArrayList<>();
            for (String optionName : parameterKeys) {
                if (!optionName.equals("productName") && !optionName.equals("sortByCost")
                        && !optionName.equals("productType") && !optionName.equals("page")
                        && !getRequestContext().getParameter(optionName)[0].equals("")) {
                    ProductSpecSearchOption specSearchOption = new ProductSpecSearchOption();
                    specSearchOption.setOptionName(optionName);
                    String value = getRequestContext().getParameter(optionName)[0];

                    String[] buff = value.split(",");
                    if (value.contains(",")) {
                        specSearchOption.setCanRange(true);
                        specSearchOption.setValue(value.split(","));
                    } else {
                        specSearchOption.setValue(new String[]{value});
                        specSearchOption.setCanRange(false);
                    }

                    try {
                        Integer.parseInt(buff[0]);
                        specSearchOption.setValueType(ProductSpecSearchOption.INT);
                    } catch (NumberFormatException e) {
                        try {
                            Double.parseDouble(buff[0]);
                            specSearchOption.setValueType(ProductSpecSearchOption.DOUBLE);
                        } catch (NumberFormatException ee) {
                            specSearchOption.setValueType(ProductSpecSearchOption.STRING);
                        }
                    }
                    specSearchOptions.add(specSearchOption);
                }
            }

            if (specSearchOptions.size() > 0) {
                searchOption.put("specOptions", specSearchOptions);
            }

            int length = dao.getSearchProductsLength(searchOption);

            JSONObject o = new JSONObject();
            o.put("length", length);

            responseContext.setResult(o);
        } catch (DAOException e) {
            throw new CommandException(e);
        }

        responseContext.setTarget("showproducts");

        return responseContext;
    }
}
