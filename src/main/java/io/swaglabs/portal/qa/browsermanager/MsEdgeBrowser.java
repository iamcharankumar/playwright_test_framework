package io.swaglabs.portal.qa.browsermanager;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;
import io.swaglabs.portal.qa.constants.WebPortalConstants;

public class MsEdgeBrowser implements IBrowser {
    @Override
    public BrowserContext createSession(Playwright playwright, boolean isHeadless) {
        return playwright.chromium().launch(new BrowserType.LaunchOptions()
                        .setHeadless(isHeadless).setChannel(BrowserName.MS_EDGE.getBrowserType()))
                .newContext(new Browser.NewContextOptions()
                        .setViewportSize(WebPortalConstants.SCREEN_WIDTH, WebPortalConstants.SCREEN_HEIGHT)
                        .setTimezoneId(WebPortalConstants.TIME_ZONE));
    }
}