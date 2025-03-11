package io.swaglabs.portal.qa.browsermanager;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;
import io.swaglabs.portal.qa.constants.WebPortalConstants;

import java.util.Collections;

public class WebkitBrowser implements IBrowser {
    @Override
    public BrowserContext createSession(Playwright playwright, boolean isHeadless) {
        return playwright.webkit().launch(new BrowserType.LaunchOptions()
                        .setHeadless(isHeadless)
                        .setArgs(Collections.singletonList(WebPortalConstants.MAXIMIZE_WINDOW)))
                .newContext(new Browser.NewContextOptions().setViewportSize(null));
    }
}