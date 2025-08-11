package io.swaglabs.portal.qa.browsermanager;

import io.swaglabs.portal.qa.constants.WebPortalConstants;

public class BrowserFactory {

    public IBrowser createBrowser() {
        return switch (BrowserName.fromString(WebPortalConstants.BROWSER)) {
            case FIREFOX -> new FirefoxBrowser();
            case WEBKIT -> new WebkitBrowser();
            case MS_EDGE -> new MsEdgeBrowser();
            default -> new ChromeBrowser();
        };
    }
}