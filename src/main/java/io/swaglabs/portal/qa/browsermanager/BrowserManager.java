package io.swaglabs.portal.qa.browsermanager;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import io.swaglabs.portal.qa.constants.WebPortalConstants;

import java.util.Objects;

public class BrowserManager implements IBrowserManager<Page> {

    @Override
    public Page getBrowserPage(Playwright playwright) {
        String browserName = System.getProperty(WebPortalConstants.BROWSER);
        String runMode = System.getProperty(WebPortalConstants.RUN_MODE);
        BrowserFactory browserFactory = new BrowserFactory(browserName);
        boolean isHeadless = runMode.equalsIgnoreCase("headless");
        return browserFactory.createBrowserSession(playwright, isHeadless).newPage();
    }

    @Override
    public void destroyBrowserPage(Page page, Browser browser) {
        Objects.requireNonNull(page, "Playwright Browser is null!");
        page.close();
        Objects.requireNonNull(browser, "Playwright Browser is null!");
        browser.close();
    }
}