package io.swaglabs.portal.qa.browsermanager;

import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;
import io.swaglabs.portal.qa.constants.WebPortalConstants;

import java.util.Collections;

public class FirefoxBrowser implements IBrowser {
    @Override
    public BrowserContext createSession(Playwright playwright) {
        return playwright.firefox().launch(new BrowserType.LaunchOptions()
                .setHeadless(true)
                .setArgs(Collections.singletonList(WebPortalConstants.MAXIMIZE_WINDOW))).newContext();
    }
}