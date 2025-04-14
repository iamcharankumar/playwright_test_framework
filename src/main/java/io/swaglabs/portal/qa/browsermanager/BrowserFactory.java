package io.swaglabs.portal.qa.browsermanager;

public class BrowserFactory {

    public IBrowser createBrowser(String browserName) {
        return switch (BrowserName.fromString(browserName)) {
            case FIREFOX -> new FirefoxBrowser();
            case WEBKIT -> new WebkitBrowser();
            case MS_EDGE -> new MsEdgeBrowser();
            default -> new ChromeBrowser();
        };
    }
}