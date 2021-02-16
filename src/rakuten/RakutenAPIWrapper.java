package rakuten;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class RakutenAPIWrapper {
    private final String applicationId;
    private final String itemSearchUrl;
    private final String productSearchUrl;

    public RakutenAPIWrapper() throws RakutenAPIException {
        RakutenPropertyLoader loader = RakutenPropertyLoader.getInstance();

        try {
            applicationId = loader.getApiInfo("applicationId");
            itemSearchUrl = loader.getApiInfo("searchItemUrl");
            productSearchUrl = loader.getApiInfo("searchProductUrl");
        } catch (IllegalArgumentException e) {
            throw new RakutenAPIException(e);
        }

        if (applicationId.equals("") || applicationId == null
                || itemSearchUrl.equals("") || itemSearchUrl == null
                || productSearchUrl.equals("") || productSearchUrl == null) {
            throw new RakutenAPIException("Failed to Load Rakuten API Information");
        }
    }

    /**
     * Search Option: String[] Type
     * [
     * "genre:GENRE_ID",       // Property Loader -> getApiParameter("GENRE_NAME")
     * "productName:PRODUCT_NAME",
     * "productId:PRODUCT_ID"
     * ]
     */
    public JSONObject searchItem(String[] option) throws RakutenAPIException {
        String rawData = "";
        try {
            String url = getItemSearchUrl(option);

            System.out.println(url);

            RequestSender sender = new RequestSender(url);

            rawData = sender.get();
        } catch (UnsupportedEncodingException e) {
            throw new RakutenAPIException(e);
        }

        return new JSONObject(rawData);
    }

    public JSONObject searchProduct(String[] option) throws RakutenAPIException {
        String rawData = "";
        try {
            String url = getProductSearchUrl(option);

            System.out.println(url);

            RequestSender sender = new RequestSender(url);

            rawData = sender.get();
        } catch (UnsupportedEncodingException e) {
            throw new RakutenAPIException(e);
        }

        return new JSONObject(rawData);
    }

    private String getItemSearchUrl(String[] option) throws UnsupportedEncodingException {
        return getSearchUrl(itemSearchUrl, option);
    }

    private String getProductSearchUrl(String[] option) throws UnsupportedEncodingException {
        return getSearchUrl(productSearchUrl, option);
    }

    private String getSearchUrl(String baseUrl, String[] option) throws UnsupportedEncodingException {
        String genreId = "";
        String productName = "";
        String productId = "";
        String page = "";

        StringBuilder url = new StringBuilder(baseUrl);
        for (String o : option) {
            if (o.startsWith("genreId:")) {
                genreId = o.replaceFirst("genreId:", "");
            }
            if (o.startsWith("productName:")) {
                productName = o.replaceFirst("productName:", "");
            }
            if (o.startsWith("productId:")) {
                productId = o.replaceFirst("productId:", "");
            }
            if (o.startsWith("page:")) {
                page = o.replaceFirst("page:", "");
            }
        }

        url.append("format=json&formatVersion=2&field=1&applicationId=").append(applicationId);

        if (!genreId.equals("")) {
            url.append("&genreId=").append(genreId);
        }
        if (!productId.equals("")) {
            url.append("&productId=").append(productId);
        }
        if (!productName.equals("")) {
            productName = URLEncoder.encode(productName, "UTF-8");
            url.append("&keyword=").append(productName);
        }
        if (!page.equals("")) {
            url.append("&page=").append(page);
        }

        return url.toString();
    }
}
