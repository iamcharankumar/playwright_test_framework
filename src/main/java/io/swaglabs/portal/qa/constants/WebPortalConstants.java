package io.swaglabs.portal.qa.constants;

import io.swaglabs.portal.qa.utils.WebConfigLoader;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class WebPortalConstants {

    // SWAG LABS CREDENTIALS
    public static final WebConfigLoader WEB_CONFIG_LOADER = WebConfigLoader.getInstance();
    public static final String USERNAME = WEB_CONFIG_LOADER.getSwagLabsUserName();
    public static final String PASSWORD = WEB_CONFIG_LOADER.getSwagLabsPassword();
    public static final String SWAG_LABS_URL = WEB_CONFIG_LOADER.getSwagLabsUrl();

    // DYNAMIC BROWSER & ENVIRONMENT CHOICE
    public static final String RUN_MODE = System.getProperty("runmode");
    public static final String BROWSER = System.getProperty("browser");

    public static final int SCREEN_WIDTH = 1920;
    public static final int SCREEN_HEIGHT = 1080;

    public static final String SCREENSHOT_FILE_LOCATION = "./src/test/resources/screenshots/";
    public static final String IMAGE_FORMAT = ".png";
}