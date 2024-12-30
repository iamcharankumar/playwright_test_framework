package io.swaglabs.portal.qa.browsermanager;

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
        if (runMode.equalsIgnoreCase("headless"))
            return browserFactory.createHeadlessBrowserSession(playwright);
        return browserFactory.createLocalBrowserSession(playwright);
    }

    @Override
    public void destroyBrowserPage(Page page) {
        Objects.requireNonNull(page, "Playwright Browser is null!");
        page.close();
    }
}