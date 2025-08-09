package io.swaglabs.portal.qa.browsermanager;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;
import io.swaglabs.portal.qa.constants.WebPortalConstants;

public class ChromeBrowser implements IBrowser {

    @Override
    public BrowserContext createSession(Playwright playwright, boolean isHeadless) {
        BrowserType.LaunchOptions chromeLaunchOptions = WebPortalConstants.BROWSER_LAUNCH_OPTIONS.setHeadless(isHeadless);
        return playwright.chromium().launch(chromeLaunchOptions).newContext(new Browser.NewContextOptions()
                .setViewportSize(WebPortalConstants.SCREEN_WIDTH, WebPortalConstants.SCREEN_HEIGHT)
                .setTimezoneId(WebPortalConstants.TIME_ZONE));
    }
}