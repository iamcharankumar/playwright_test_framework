package io.swaglabs.portal.qa.commons;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import io.swaglabs.portal.qa.browsermanager.BrowserManager;
import io.swaglabs.portal.qa.cdp.CdpCommands;
import io.swaglabs.portal.qa.constants.BrowserName;
import io.swaglabs.portal.qa.constants.WebPortalConstants;
import io.swaglabs.portal.qa.utils.CdpUtils;
import io.swaglabs.portal.qa.utils.PerformanceUtils;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.lang.reflect.Method;

@Slf4j
public abstract class WebBaseTest {

    protected static ThreadLocal<Page> page = new ThreadLocal<>();
    protected static ThreadLocal<Playwright> playwright = new ThreadLocal<>();
    protected static ThreadLocal<Browser> browser = new ThreadLocal<>();
    private static BrowserManager browserManager;


    @BeforeSuite(alwaysRun = true)
    public void setUp() {
        browserManager = new BrowserManager();
        log.info("Browser Manager has been initiated.");
    }

    @BeforeMethod(alwaysRun = true)
    public void init(Method method) {
        playwright.set(Playwright.create());
        page.set(browserManager.getBrowserPage(playwright.get()));
        browser.set(page.get().context().browser());
        log.info("Browser has been set.");
        if (isChromiumBrowser()) {
            CdpUtils.initializeCdpSession(page.get());
            CdpUtils.enableCdpSession();
            CdpUtils.sendCommand(CdpCommands.NETWORK_ENABLE.getDescription());
            CdpUtils.logErrorResponses();
            CdpUtils.sendCommand(CdpCommands.CONSOLE_ENABLE.getDescription());
            CdpUtils.logConsoleErrors();
        }
        PerformanceUtils.evaluatePageLoadTime(page.get(), method.getName());
        PerformanceUtils.evaluateDomContentLoadTime(page.get(), method.getName());
    }

    @AfterMethod(alwaysRun = true)
    public void destroy() {
        if (isChromiumBrowser()) {
            CdpUtils.destroyCdpSession();
        }
        browserManager.destroyBrowserPage(page.get(), browser.get());
        page.remove();
        browser.remove();
        playwright.get().close();
        playwright.remove();
        log.info("Browser has been destroyed.");
    }

    private boolean isChromiumBrowser() {
        String browserName = System.getProperty(WebPortalConstants.BROWSER);
        return (browserName.equalsIgnoreCase(BrowserName.CHROME.getBrowserType()) || browserName.equalsIgnoreCase(BrowserName.MS_EDGE.getBrowserType()));
    }
}