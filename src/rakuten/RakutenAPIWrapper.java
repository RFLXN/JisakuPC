package rakuten;

import org.json.JSONObject;
import resources.PropertiesLoader;

import java.io.IOException;

public class RakutenAPIWrapper {
    private String applicationId;
    private String searchUrl;

    public RakutenAPIWrapper() throws RakutenAPIException {
        PropertiesLoader loader = null;
        try {
            loader = new PropertiesLoader("../resources/rakuten_api_info.properties");
        } catch (IOException e) {
            throw new RakutenAPIException("Failed to Load Rakuten API Information", e);
        }

        applicationId = loader.getProperty("applicationId");
        searchUrl = loader.getProperty("searchItemUrl");

        if(applicationId.equals("") || applicationId == null
        || searchUrl.equals("") || searchUrl == null) {
            throw new RakutenAPIException("Failed to Load Rakuten API Information");
        }
    }

    public JSONObject search(String[] option) {
        String url = getSearchUrl(option);

        RequestSender sender = new RequestSender(url);

        String rawData = sender.get();
        return new JSONObject(rawData);
    }

    private String getSearchUrl(String[] option) {
        String genreId = "";
        String productName = "";
        String productId = "";

        StringBuilder url = new StringBuilder(searchUrl);
        for(String o : option) {
            if(o.startsWith("genreId:")) {
                genreId = o.replaceFirst("genreId:", "");
            }
            if(o.startsWith("productName:")) {
                productName = o.replaceFirst("productName:", "");
            }
            if(o.startsWith("productId:")) {
                productId = o.replaceFirst("productId:", "");
            }
        }

        url.append("format=json&applicationId=").append(applicationId);

        if(!genreId.equals("")) {
            url.append("&genreId=").append(genreId);
        }
        if(!productId.equals("")) {
            url.append("&productId=").append(productId);
        }
        if(!productName.equals("")) {
            url.append("&keyword=").append(productName);
        }

        return url.toString();
    }
}
