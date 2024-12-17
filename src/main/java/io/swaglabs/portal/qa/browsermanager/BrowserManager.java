package io.swaglabs.portal.qa.browsermanager;

import com.microsoft.playwright.Page;
import io.swaglabs.portal.qa.constants.WebPortalConstants;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Slf4j
public class BrowserManager implements IBrowserManager<Page> {

    @Override
    public Page getBrowserPage() {
        String browserName = System.getProperty(WebPortalConstants.BROWSER);
        String runMode = System.getProperty(WebPortalConstants.RUN_MODE);
        BrowserFactory browserFactory = new BrowserFactory(browserName);
        if (runMode.equalsIgnoreCase("headless"))
            return browserFactory.createHeadlessBrowserSession().createWebBrowserPage();
        return browserFactory.createLocalBrowserSession().createWebBrowserPage();
    }

    @Override
    public void destroyBrowserPage(Page page) {
        Objects.requireNonNull(page, "Playwright Browser is null!");
        page.close();
    }
}