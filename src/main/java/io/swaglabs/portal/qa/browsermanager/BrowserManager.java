package io.swaglabs.portal.qa.browsermanager;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import io.swaglabs.portal.qa.constants.WebPortalConstants;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BrowserManager implements IBrowserManager<Page> {

    @Override
    public Page getBrowserPage(Playwright playwright) {
        String browserName = System.getProperty(WebPortalConstants.BROWSER);
        String runMode = System.getProperty(WebPortalConstants.RUN_MODE);
        BrowserFactory browserFactory = new BrowserFactory(browserName);
        log.info("Browser: {} | Run Mode: {}", browserName, runMode);
        if (runMode.equalsIgnoreCase("headless"))
            return browserFactory.createHeadlessBrowserSession(playwright);
        return browserFactory.createLocalBrowserSession(playwright);
    }

    @Override
    public void destroyBrowserPage(Page page) {
        if (page != null)
            page.close();
        else
            log.error("Playwright Browser is null!");
    }
}