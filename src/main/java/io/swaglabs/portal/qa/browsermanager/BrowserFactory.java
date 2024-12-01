package io.swaglabs.portal.qa.browsermanager;

import com.microsoft.playwright.*;
import io.swaglabs.portal.qa.constants.BrowserName;

import java.util.Collections;
import java.util.Objects;


public class BrowserFactory {

    private final BrowserName BROWSER;
    private static final String MAXIMIZE_WINDOW = "--start-maximized";

    public BrowserFactory(String browserName) {
        BROWSER = BrowserName.fromString(browserName);
    }

    public Page createLocalBrowserSession(Playwright playwright) {
        return switch (BROWSER) {
            case FIREFOX -> getBrowserContext(playwright.firefox().launch(new BrowserType.LaunchOptions()
                    .setHeadless(false).setArgs(Collections.singletonList(MAXIMIZE_WINDOW)))).newPage();
            case WEBKIT -> getBrowserContext(playwright.webkit().launch(new BrowserType.LaunchOptions()
                    .setHeadless(false).setArgs(Collections.singletonList(MAXIMIZE_WINDOW)))).newPage();
            default -> getBrowserContext(playwright.chromium().launch(new BrowserType.LaunchOptions()
                    .setChannel(BROWSER.getBrowserType())
                    .setHeadless(false).setArgs(Collections.singletonList(MAXIMIZE_WINDOW)))).newPage();
        };
    }

    public Page createHeadlessBrowserSession(Playwright playwright) {
        return switch (BROWSER) {
            case FIREFOX -> getBrowserContext(playwright.firefox().launch(new BrowserType.LaunchOptions()
                    .setArgs(Collections.singletonList(MAXIMIZE_WINDOW)))).newPage();
            case WEBKIT -> getBrowserContext(playwright.webkit().launch(new BrowserType.LaunchOptions()
                    .setArgs(Collections.singletonList(MAXIMIZE_WINDOW)))).newPage();
            default -> getBrowserContext(playwright.chromium().launch(new BrowserType.LaunchOptions()
                    .setChannel(BROWSER.getBrowserType())
                    .setHeadless(true).setArgs(Collections.singletonList(MAXIMIZE_WINDOW)))).newPage();
        };
    }

    private BrowserContext getBrowserContext(Browser browser) {
        Objects.requireNonNull(browser, "Playwright Browser is null in Browser Factory!");
        return browser.newContext(new Browser.NewContextOptions().setViewportSize(null));
    }
}