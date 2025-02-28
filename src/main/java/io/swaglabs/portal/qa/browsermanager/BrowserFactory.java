package io.swaglabs.portal.qa.browsermanager;

import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;
import io.swaglabs.portal.qa.constants.BrowserName;

import java.util.Collections;

public class BrowserFactory {

    private final BrowserName BROWSER;
    private static final String MAXIMIZE_WINDOW = "--start-maximized";

    public BrowserFactory(String browserName) {
        BROWSER = BrowserName.fromString(browserName);
    }

    public BrowserContext createBrowserSession(Playwright playwright, boolean isHeadless) {
        BrowserType.LaunchOptions launchOptions;
        if (isHeadless)
            launchOptions = new BrowserType.LaunchOptions().setHeadless(isHeadless)
                    .setArgs(Collections.singletonList(MAXIMIZE_WINDOW))
                    .setArgs(Collections.singletonList("--headless=new"));
        else
            launchOptions = new BrowserType.LaunchOptions().setHeadless(isHeadless)
                    .setArgs(Collections.singletonList(MAXIMIZE_WINDOW));
        return switch (BROWSER) {
            case FIREFOX -> playwright.firefox().launch(launchOptions).newContext();
            case WEBKIT -> playwright.webkit().launch(launchOptions).newContext();
            default -> {
                launchOptions.setChannel(BROWSER.getBrowserType());
                yield playwright.chromium().launch(launchOptions).newContext();
            }
        };
    }
}