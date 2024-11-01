package io.swaglabs.portal.qa.utils;

import java.util.Objects;
import java.util.Properties;

public class WebConfigLoader {

    private static WebConfigLoader instance;
    private final Properties PROPERTIES;
    private static final String WEB_CONFIG_FILE = "./src/main/resources/webconfig.properties";

    private WebConfigLoader() {
        PROPERTIES = PropertiesUtils.loadProperties(WEB_CONFIG_FILE);
    }

    public static WebConfigLoader getInstance() {
        if (instance == null) {
            synchronized (WebConfigLoader.class) {
                if (instance == null)
                    instance = new WebConfigLoader();
            }
        }
        return instance;
    }

    public String getSwagLabsUrl() {
        return getPropertyValue("swaglabs.url");
    }

    public String getSwagLabsUserName() {
        return getPropertyValue("swaglabs.username");
    }

    public String getSwagLabsPassword() {
        return getPropertyValue("swaglabs.password");
    }

    private String getPropertyValue(String propertyKey) {
        String propertyValue = PROPERTIES.getProperty(propertyKey);
        Objects.requireNonNull(propertyKey, "Property Value for Property Key: " + propertyKey + " is empty!");
        return propertyValue;
    }
}