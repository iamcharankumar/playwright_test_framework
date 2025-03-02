package io.swaglabs.portal.qa.browsermanager;

import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Playwright;

public interface IBrowser {

    BrowserContext createSession(Playwright playwright, boolean isHeadless);
}