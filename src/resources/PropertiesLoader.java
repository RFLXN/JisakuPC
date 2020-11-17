package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesLoader {
    private String path;
    private Properties properties;

    public PropertiesLoader(String path) throws IOException {
        this.path = path;
        properties = loadProperties();
    }

    private Properties loadProperties() throws IOException {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(path));
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }

        return properties;
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}
