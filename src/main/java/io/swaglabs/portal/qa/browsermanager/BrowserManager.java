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
        return new BrowserFactory().createBrowser(browserName).createSession(playwright).newPage();
    }

    @Override
    public void destroyBrowserPage(Page page, Browser browser) {
        Objects.requireNonNull(page, "Playwright Browser is null!");
        page.close();
        Objects.requireNonNull(browser, "Playwright Browser is null!");
        browser.close();
    }
}