package io.swaglabs.portal.qa.constants;

import com.microsoft.playwright.BrowserType;
import io.swaglabs.portal.qa.utils.ScreenshotsUtils;
import io.swaglabs.portal.qa.utils.WebConfigLoader;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

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
    public static final String TIME_ZONE = "Asia/Kolkata";

    // Web Browser resolution
    public static final int SCREEN_WIDTH = 1920;
    public static final int SCREEN_HEIGHT = 1080;

    public static final String SCREENSHOT_FILE_LOCATION = "./target/screenshots/";
    public static final String IMAGE_FORMAT = ".png";

    // Browser Type Launch Options Config
    public static final BrowserType.LaunchOptions BROWSER_LAUNCH_OPTIONS = new BrowserType.LaunchOptions();

    // SCREENSHOT STRATEGIES
    public static final ScreenshotsUtils SCREENSHOTS_UTILS = ScreenshotsUtils.getInstance();
}