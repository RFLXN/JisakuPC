package rakuten;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class RakutenPropertyLoader {
    private static final RakutenPropertyLoader instance;

    private RakutenPropertyLoader() {}

    static {
        instance = new RakutenPropertyLoader();
    }

    public static RakutenPropertyLoader getInstance() {
        return instance;
    }

    public String getApiInfo(String key) throws IllegalArgumentException {
        String value = null;

        try {
            value = getProperty("resources.rakuten_api_info", key);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e);
        }

        return value;
    }

    public String getApiParameter(String key) throws IllegalArgumentException {
        String value = null;

        try {
            value = getProperty("resources.rakuten_api_parameter", key);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e);
        }

        return value;
    }

    private String getProperty(String bundleName, String key) throws IllegalArgumentException {
        String value = null;
        try {
            ResourceBundle bundle = ResourceBundle.getBundle(bundleName);
            value = bundle.getString(key);
        } catch (MissingResourceException e) {
            throw new IllegalArgumentException(key + " is Not Correct Key", e);
        }
        return value;
    }
}
