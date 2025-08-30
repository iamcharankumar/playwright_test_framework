package io.swaglabs.portal.qa.browsermanager;

import io.swaglabs.portal.qa.constants.WebPortalConstants;

public class BrowserFactory {

    public IBrowser createBrowser() {
        return switch (BrowserName.fromConfigValue(WebPortalConstants.BROWSER)) {
            case CHROME -> new ChromeBrowser();
            case MS_EDGE -> new MsEdgeBrowser();
            case FIREFOX -> new FirefoxBrowser();
            case WEBKIT -> new WebkitBrowser();
        };
    }
}