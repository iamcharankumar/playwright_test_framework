package io.swaglabs.portal.qa.browsermanager;

import io.swaglabs.portal.qa.constants.WebPortalConstants;

public class BrowserFactory {

    public IBrowser createBrowser() {
        return switch (BrowserName.fromConfigValue(WebPortalConstants.BROWSER)) {
            case CHROME -> new ChromeBrowser();
            case EDGE -> new EdgeBrowser();
            case FIREFOX -> new FirefoxBrowser();
            case WEBKIT -> new WebkitBrowser();
        };
    }
}