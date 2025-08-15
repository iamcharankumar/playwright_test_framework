package io.swaglabs.portal.qa.utils;

import lombok.extern.slf4j.Slf4j;

import java.util.Objects;
import java.util.Properties;

@Slf4j
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
        String url = System.getenv("SWAGLABS_URL");
        if (url == null || url.isEmpty()) {
            log.info("Environment variable SWAGLABS_URL is not set, falling back to properties file.");
            url = getPropertyValue("swaglabs.url");
        }
        log.info("SwagLabs URL picked from github secrets.");
        return url;
    }

    public String getSwagLabsUserName() {
        String username = System.getenv("SWAGLABS_USERNAME");
        if (username == null || username.isEmpty()) {
            log.info("Environment variable SWAGLABS_USERNAME is not set, falling back to properties file.");
            username = getPropertyValue("swaglabs.username");
        }
        log.info("SwagLabs Username picked from github secrets.");
        return username;
    }

    public String getSwagLabsPassword() {
        String password = System.getenv("SWAGLABS_PASSWORD");
        if (password == null || password.isEmpty()) {
            log.info("Environment variable SWAGLABS_PASSWORD is not set, falling back to properties file.");
            password = getPropertyValue("swaglabs.password");
        }
        log.info("SwagLabs Password picked from github secrets.");
        return password;
    }

    private String getPropertyValue(String propertyKey) {
        String propertyValue = PROPERTIES.getProperty(propertyKey);
        Objects.requireNonNull(propertyKey, "Property Value for Property Key: " + propertyKey + " is empty!");
        return propertyValue;
    }
}