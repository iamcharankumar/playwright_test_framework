package io.swaglabs.portal.qa.utils;

import io.swaglabs.portal.qa.constants.WebPortalConstants;
import io.swaglabs.portal.qa.exceptions.UtilsException;

import java.util.Properties;

public class WebConfigLoader {

    private static WebConfigLoader instance;
    private final Properties PROPERTIES;

    private WebConfigLoader() {
        PROPERTIES = PropertiesUtils.loadProperties(WebPortalConstants.WEB_CONFIG_FILE);
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
        String swagLabsUrl = PROPERTIES.getProperty("swaglabs.url");
        if (!swagLabsUrl.isEmpty())
            return swagLabsUrl;
        else
            throw new UtilsException("Swag Labs URL is empty!");
    }

    public String getSwagLabsUserName() {
        String swagLabsUserName = PROPERTIES.getProperty("swaglabs.username");
        if (!swagLabsUserName.isEmpty())
            return swagLabsUserName;
        else
            throw new UtilsException("Swag Labs username is empty!");
    }

    public String getSwagLabsPassword() {
        String swagLabsPassword = PROPERTIES.getProperty("swaglabs.password");
        if (!swagLabsPassword.isEmpty())
            return swagLabsPassword;
        else
            throw new UtilsException("Swag Labs password is empty!");
    }
}