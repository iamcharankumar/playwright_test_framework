package io.swaglabs.portal.qa.commons;

import com.microsoft.playwright.Page;
import io.swaglabs.portal.qa.browsermanager.BrowserManager;
import io.swaglabs.portal.qa.browsermanager.ThreadSafeBrowserFactory;
import io.swaglabs.portal.qa.utils.PerformanceUtils;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

@Slf4j
public abstract class WebBaseTest {

    protected static ThreadLocal<Page> page = new ThreadLocal<>();
    private static BrowserManager browserManager;

    @BeforeSuite(alwaysRun = true)
    public void setUp() {
        browserManager = new BrowserManager();
        log.info("Browser Manager has been initiated.");
    }

    @BeforeMethod(alwaysRun = true)
    public void init() {
        page.set(browserManager.getBrowserPage());
        log.info("Browser has been set.");
        PerformanceUtils.evaluatePageLoadTime(page.get());
        PerformanceUtils.evaluateDomContentLoadTime(page.get());
    }

    @AfterMethod(alwaysRun = true)
    public void destroy() {
        browserManager.destroyBrowserPage(page.get());
        page.get().close();
        page.remove();
        ThreadSafeBrowserFactory.cleanup();
        log.info("Browser has been destroyed.");
    }
}