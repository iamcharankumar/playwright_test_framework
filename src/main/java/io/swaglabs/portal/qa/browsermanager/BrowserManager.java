package io.swaglabs.portal.qa.browsermanager;

import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import io.swaglabs.portal.qa.constants.WebPortalConstants;

import java.util.Objects;

public class BrowserManager implements IBrowserManager<Page> {

    @Override
    public Page getBrowserPage(Playwright playwright) {
        String browserName = WebPortalConstants.BROWSER;
        String runMode = WebPortalConstants.RUN_MODE;
        boolean isHeadless = runMode.equals("headless");
        BrowserContext browserContext = new BrowserFactory().createBrowser(browserName).createSession(playwright, isHeadless);
        Objects.requireNonNull(browserContext, "Playwright Browser Context is null!");
        return browserContext.newPage();
    }

    @Override
    public void destroyBrowserPage(Page page) {
        Objects.requireNonNull(page, "Playwright Browser is null!");
        page.close();
    }
}