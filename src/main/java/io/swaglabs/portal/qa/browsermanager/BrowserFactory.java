package io.swaglabs.portal.qa.browsermanager;

import io.swaglabs.portal.qa.constants.BrowserName;

public class BrowserFactory {

    public IBrowser createBrowser(String browserName) {
        return switch (BrowserName.fromString(browserName)) {
            case CHROME -> new ChromeBrowser();
            case FIREFOX -> new FirefoxBrowser();
            case WEBKIT -> new WebkitBrowser();
            case MS_EDGE -> new MsEdgeBrowser();
        };
    }
}