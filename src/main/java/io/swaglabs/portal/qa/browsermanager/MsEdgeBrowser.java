package io.swaglabs.portal.qa.browsermanager;

import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;
import io.swaglabs.portal.qa.constants.BrowserName;
import io.swaglabs.portal.qa.constants.WebPortalConstants;

import java.util.List;

public class MsEdgeBrowser implements IBrowser {
    @Override
    public BrowserContext createSession(Playwright playwright, boolean isHeadless) {
        return playwright.chromium().launch(new BrowserType.LaunchOptions()
                .setHeadless(isHeadless)
                .setArgs(isHeadless
                        ? List.of(WebPortalConstants.NEW_HEADLESS, WebPortalConstants.MAXIMIZE_WINDOW)
                        : List.of(WebPortalConstants.MAXIMIZE_WINDOW))
                .setChannel(BrowserName.MS_EDGE.getBrowserType())).newContext();
    }
}