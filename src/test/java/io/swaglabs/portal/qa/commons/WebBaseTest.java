package io.swaglabs.portal.qa.commons;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import io.swaglabs.portal.qa.browsermanager.BrowserManager;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

@Slf4j
public abstract class WebBaseTest {

    protected static ThreadLocal<Page> page = new ThreadLocal<>();
    protected static ThreadLocal<Playwright> playwright = new ThreadLocal<>();
    private static BrowserManager browserManager;

    @BeforeSuite(alwaysRun = true)
    public void setUp() {
        browserManager = new BrowserManager();
        log.info("Browser Manager has been initiated.");
    }

    @BeforeMethod(alwaysRun = true)
    public void init() {
        playwright.set(Playwright.create());
        page.set(browserManager.getBrowserPage(playwright.get()));
        log.info("Browser has been set.");
    }

    @AfterMethod(alwaysRun = true)
    public void destroy() {
        browserManager.destroyBrowserPage(page.get());
        page.get().close();
        page.remove();
        playwright.get().close();
        playwright.remove();
        log.info("Browser has been destroyed.");
    }
}