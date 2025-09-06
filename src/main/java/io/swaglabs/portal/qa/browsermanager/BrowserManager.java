package io.swaglabs.portal.qa.browsermanager;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import io.swaglabs.portal.qa.constants.WebPortalConstants;
import io.swaglabs.portal.qa.exceptions.WebUtilsException;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Slf4j
public class BrowserManager implements IBrowserManager<Page> {

    private static final ThreadLocal<BrowserContext> BROWSER_CONTEXT = new ThreadLocal<>();
    private static final ThreadLocal<Browser> BROWSER = new ThreadLocal<>();

    @Override
    public Page getBrowserPage(Playwright playwright) {
        try {
            Objects.requireNonNull(playwright, "Playwright instance is null in Browser Manager!");
            boolean isHeadless = "headless".equals(WebPortalConstants.RUN_MODE);
            BrowserContext browserContext = new BrowserFactory().createBrowser().createSession(playwright, isHeadless);
            BROWSER_CONTEXT.set(browserContext);
            if (browserContext.browser() != null)
                BROWSER.set(browserContext.browser());
            return browserContext.newPage();
        } catch (Exception e) {
            log.error("Browser initialization failed.");
            cleanUp();
            throw new WebUtilsException("Failed to create browser page: " + e);
        }
    }

    @Override
    public void destroyBrowserPage(Page page) {
        try {
            if (page != null)
                page.close();
        } finally {
            cleanUp();
        }
    }

    private void cleanUp() {
        BrowserContext browserContext = BROWSER_CONTEXT.get();
        if (browserContext != null) {
            browserContext.close();
            BROWSER_CONTEXT.remove();
        }
        Browser browser = BROWSER.get();
        if (browser != null) {
            browser.close();
            BROWSER.remove();
        }
    }
}