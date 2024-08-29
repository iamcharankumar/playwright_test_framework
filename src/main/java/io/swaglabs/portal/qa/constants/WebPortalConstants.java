package io.swaglabs.portal.qa.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class WebPortalConstants {

    // WEB BROWSER PROPERTIES FILE
    public static final String CURRENT_DIRECTORY = System.getProperty("user.dir");
    public static final String WEB_CONFIG_FILE = CURRENT_DIRECTORY + "/src/main/resources/webconfig.properties";

    // WEB BROWSER TYPES
    public static final String FIREFOX = "firefox";
    public static final String MS_EDGE = "msedge";

    // WEB BROWSER WINDOW SIZE
    public static final int SCREEN_WIDTH = 1920;
    public static final int SCREEN_HEIGHT = 1080;

    // SCREENSHOT LOCATIONS
    public static final String SCREENSHOTS_DIRECTORY = CURRENT_DIRECTORY + "/src/test/resources/screenshots";
    public static final String PASS = "/passed_screenshots";
    public static final String PASS_PREFIX = "PASS_";
    public static final String FAIL = "/failed_screenshots";
    public static final String FAIL_PREFIX = "FAILED_";
    public static final String IMAGE_FORMAT = ".png";

    // DYNAMIC BROWSER & ENVIRONMENT CHOICE
    public static final String RUN_MODE = "runmode";
    public static final String BROWSER = "browser";
}