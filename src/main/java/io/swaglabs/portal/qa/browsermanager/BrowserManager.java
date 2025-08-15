package io.swaglabs.portal.qa.browsermanager;

import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import io.swaglabs.portal.qa.constants.WebPortalConstants;

import java.util.Objects;

public class BrowserManager implements IBrowserManager<Page> {

    @Override
    public Page getBrowserPage(Playwright playwright) {
        Objects.requireNonNull(playwright, "Playwright instance is null in Browser Manager!");
        boolean isHeadless = "headless".equals(WebPortalConstants.RUN_MODE);
        BrowserContext browserContext = new BrowserFactory().createBrowser().createSession(playwright, isHeadless);
        Objects.requireNonNull(browserContext, "Playwright Browser Context is null!");
        return browserContext.newPage();
    }

    @Override
    public void destroyBrowserPage(Page page) {
        Objects.requireNonNull(page, "Playwright Browser is null!");
        page.close();
    }
}