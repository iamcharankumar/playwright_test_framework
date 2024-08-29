package io.swaglabs.portal.qa.browsermanager;

import com.microsoft.playwright.*;
import io.swaglabs.portal.qa.constants.WebPortalConstants;
import io.swaglabs.portal.qa.exceptions.SwagLabsException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BrowserFactory {

    protected String browserName;

    public Page createLocalBrowserSession(Playwright playwright) {
        switch (browserName.toLowerCase()) {
            case WebPortalConstants.FIREFOX:
                return getBrowserContext(playwright.firefox().launch(new BrowserType.LaunchOptions().setChannel("firefox").setHeadless(false))).newPage();
            case WebPortalConstants.MS_EDGE:
                return getBrowserContext(playwright.chromium().launch(new BrowserType.LaunchOptions().setChannel("msedge").setHeadless(false))).newPage();
            default:
                return getBrowserContext(playwright.chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false))).newPage();
        }

    }

    public Page createHeadlessBrowserSession(Playwright playwright) {
        switch (browserName.toLowerCase()) {
            case WebPortalConstants.FIREFOX:
                return getBrowserContext(playwright.firefox().launch()).newPage();
            case WebPortalConstants.MS_EDGE:
                return getBrowserContext(playwright.chromium().launch(new BrowserType.LaunchOptions().setChannel("msedge").setHeadless(true))).newPage();
            default:
                return getBrowserContext(playwright.chromium().launch()).newPage();
        }
    }

    private BrowserContext getBrowserContext(Browser browser) {
        if (browser == null)
            throw new SwagLabsException("Playwright Browser is null in Browser Factory!");
        return browser.newContext(new Browser.NewContextOptions().setViewportSize(WebPortalConstants.SCREEN_WIDTH, WebPortalConstants.SCREEN_HEIGHT));
    }
}