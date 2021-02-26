package command;

import bean.Product;
import bean.ProductSpecSearchOption;
import context.ResponseContext;
import db.dao.DAOException;
import db.dao.factory.AbstractDaoFactory;
import db.dao.product.ProductDao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchProductsCommand extends AbstractCommand {
    @Override
    public ResponseContext execute(ResponseContext responseContext) throws CommandException {
        List<Product> products = new ArrayList<Product>();

        String productName = null;
        String sort = null;
        String productType = null;
        int[] page = null;

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

        if (getRequestContext().getParameter("page") != null
                && (!getRequestContext().getParameter("page")[0].equals(""))) {
            String[] pBuff = getRequestContext().getParameter("page");
            page = new int[pBuff.length];
            for (int i = 0; i < pBuff.length; i++) {
                page[i] = Integer.parseInt(pBuff[i]);
            }
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

            if (page != null) {
                products = dao.searchProducts(searchOption, page);
            } else {
                products = dao.searchProducts(searchOption);
            }
        } catch (DAOException e) {
            throw new CommandException(e);
        }
        responseContext.setResult(products);
        responseContext.setTarget("showproducts");

        return responseContext;
    }
}
