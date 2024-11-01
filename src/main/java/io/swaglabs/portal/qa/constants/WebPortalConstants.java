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
    public static final String RUN_MODE = "runmode";
    public static final String BROWSER = "browser";

    // JAVASCRIPT COMMANDS
    public static final String PAGE_LOAD_TIME_JS_SCRIPT = "() => JSON.parse(JSON.stringify(window.performance.timing))";
    public static final String NAVIGATION_START = "navigationStart";
    public static final String LOAD_EVENT_END = "loadEventEnd";
    public static final String DOM_CONTENT_LOADED_EVENT_END = "domContentLoadedEventEnd";
}