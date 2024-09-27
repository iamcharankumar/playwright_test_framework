package io.swaglabs.portal.qa.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class WebPortalConstants {

    // DYNAMIC BROWSER & ENVIRONMENT CHOICE
    public static final String RUN_MODE = "runmode";
    public static final String BROWSER = "browser";
    public static final String HEADLESS = "headless";

    // JAVASCRIPT COMMANDS
    public static final String PAGE_LOAD_TIME_JS_SCRIPT = "() => JSON.parse(JSON.stringify(window.performance.timing))";
    public static final String NAVIGATION_START = "navigationStart";
    public static final String LOAD_EVENT_END = "loadEventEnd";
    public static final String DOM_CONTENT_LOADED_EVENT_END = "domContentLoadedEventEnd";
}