package io.swaglabs.portal.qa.browsermanager;

import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;
import io.swaglabs.portal.qa.constants.WebPortalConstants;

import java.util.List;

public class ChromeBrowser implements IBrowser {

    @Override
    public BrowserContext createSession(Playwright playwright) {
        return playwright.chromium().launch(new BrowserType.LaunchOptions()
                .setHeadless(true)
                .setArgs(List.of("--headless=new", WebPortalConstants.MAXIMIZE_WINDOW))).newContext();
    }
}