package io.swaglabs.portal.qa.browsermanager;

import io.swaglabs.portal.qa.constants.BrowserName;


public class BrowserFactory {

    private final BrowserName BROWSER;

    public BrowserFactory(String browserName) {
        BROWSER = BrowserName.fromString(browserName);
    }

    public IBrowser createLocalBrowserSession() {
        return switch (BROWSER) {
            case CHROME -> new ChromeBrowser(false);
            case MS_EDGE -> new MsEdgeBrowser(false);
            case FIREFOX -> new FirefoxBrowser(false);
            case WEBKIT -> new WebkitBrowser(false);
        };
    }

    public IBrowser createHeadlessBrowserSession() {
        return switch (BROWSER) {
            case CHROME -> new ChromeBrowser(true);
            case MS_EDGE -> new MsEdgeBrowser(true);
            case FIREFOX -> new FirefoxBrowser(true);
            case WEBKIT -> new WebkitBrowser(true);
        };
    }
}