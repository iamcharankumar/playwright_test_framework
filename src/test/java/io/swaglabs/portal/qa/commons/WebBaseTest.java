package io.swaglabs.portal.qa.commons;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import io.swaglabs.portal.qa.browsermanager.BrowserManager;
import io.swaglabs.portal.qa.cdp.CdpCommands;
import io.swaglabs.portal.qa.cdp.CdpEvents;
import io.swaglabs.portal.qa.cdp.CdpSessionHandler;
import io.swaglabs.portal.qa.cdp.CdpSessionHandlerImpl;
import io.swaglabs.portal.qa.constants.BrowserName;
import io.swaglabs.portal.qa.constants.WebPortalConstants;
import io.swaglabs.portal.qa.utils.PerformanceUtils;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

@Slf4j
public abstract class WebBaseTest {

    protected static ThreadLocal<Page> page = new ThreadLocal<>();
    protected static ThreadLocal<Playwright> playwright = new ThreadLocal<>();
    private static BrowserManager browserManager;
    private static CdpSessionHandler cdpSessionHandler;
    private final String BROWSER = System.getProperty(WebPortalConstants.BROWSER);

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
        log.info("Name: {}", BROWSER);
        if (BROWSER.equalsIgnoreCase(BrowserName.CHROME.getBrowserType()) ||
                BROWSER.equalsIgnoreCase(BrowserName.MS_EDGE.getBrowserType())) {
            cdpSessionHandler = new CdpSessionHandlerImpl(page.get());
            cdpSessionHandler.enableSession();
            cdpSessionHandler.sendCDPCommand(CdpCommands.NETWORK_ENABLE.getDescription());
            cdpSessionHandler.attachEventListener(CdpEvents.NETWORK_RESPONSE_RECEIVED.getDescription(), responseEvent -> {
                String responseUrl = responseEvent.getAsJsonObject("response").get("url").getAsString();
                int statusCode = Integer.parseInt(responseEvent.getAsJsonObject("response").get("status").getAsString());
                String statusText = responseEvent.getAsJsonObject("response").get("statusText").getAsString();
                String requestId = responseEvent.get("requestId").getAsString();
                if (!responseUrl.matches("") && statusCode >= 400) {
                    log.info("Response URL: {}, the Status code: {}, the Status Text: {} the for the given request ID: {}",
                            responseUrl, statusCode, statusText, requestId);
                }
            });
        }
        PerformanceUtils.evaluatePageLoadTime(page.get());
        PerformanceUtils.evaluateDomContentLoadTime(page.get());
    }

    @AfterMethod(alwaysRun = true)
    public void destroy() {
        if (BROWSER.equalsIgnoreCase(BrowserName.CHROME.getBrowserType()) ||
                BROWSER.equalsIgnoreCase(BrowserName.MS_EDGE.getBrowserType())) {
            cdpSessionHandler.detachSession();
        }
        browserManager.destroyBrowserPage(page.get());
        page.get().close();
        page.remove();
        playwright.get().close();
        playwright.remove();
        log.info("Browser has been destroyed.");
    }
}