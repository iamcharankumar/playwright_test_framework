package io.swaglabs.portal.qa.browsermanager;

import com.microsoft.playwright.*;
import io.swaglabs.portal.qa.constants.BrowserName;
import io.swaglabs.portal.qa.exceptions.SwagLabsException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BrowserFactory {

    protected String browserName;

    public Page createLocalBrowserSession(Playwright playwright) {
        BrowserName browser = BrowserName.fromString(browserName);
        return switch (browser) {
            case FIREFOX -> getBrowserContext(playwright.firefox().launch(new BrowserType.LaunchOptions()
                    .setChannel(browser.getBrowserName()).setHeadless(false))).newPage();
            case WEBKIT -> getBrowserContext(playwright.webkit().launch(new BrowserType.LaunchOptions()
                    .setChannel(browser.getBrowserName()).setHeadless(false))).newPage();
            default -> getBrowserContext(playwright.chromium().launch(new BrowserType.LaunchOptions()
                    .setChannel(browser.getBrowserName()).setHeadless(false))).newPage();
        };
    }

    public Page createHeadlessBrowserSession(Playwright playwright) {
        BrowserName browser = BrowserName.fromString(browserName);
        return switch (browser) {
            case FIREFOX -> getBrowserContext(playwright.firefox().launch()).newPage();
            case MS_EDGE -> getBrowserContext(playwright.chromium().launch(new BrowserType.LaunchOptions()
                    .setChannel(browser.getBrowserName()).setHeadless(true))).newPage();
            case WEBKIT -> getBrowserContext(playwright.webkit().launch()).newPage();
            default -> getBrowserContext(playwright.chromium().launch()).newPage();
        };
    }

    private BrowserContext getBrowserContext(Browser browser) {
        if (browser == null)
            throw new SwagLabsException("Playwright Browser is null in Browser Factory!");
        return browser.newContext(new Browser.NewContextOptions().setViewportSize(null));
    }
}