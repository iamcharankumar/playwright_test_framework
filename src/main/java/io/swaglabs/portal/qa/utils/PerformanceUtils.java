package io.swaglabs.portal.qa.utils;

import com.microsoft.playwright.Page;
import io.swaglabs.portal.qa.constants.WebPortalConstants;
import io.swaglabs.portal.qa.exceptions.UtilsException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PerformanceUtils {

    public static void evaluatePageLoadTime(Page page) {
        Map<String, Object> pageLoadMetrics = getPerformanceMetrics(page, WebPortalConstants.PAGE_LOAD_TIME_JS_SCRIPT);
        long navigationStart = ((Number) pageLoadMetrics.get(WebPortalConstants.NAVIGATION_START)).longValue();
        long loadEventEnd = ((Number) pageLoadMetrics.get(WebPortalConstants.LOAD_EVENT_END)).longValue();
        long pageLoadTime = loadEventEnd - navigationStart;
        log.info("Page Load Time: {}", pageLoadTime);
    }

    public static void evaluateDomContentLoadTime(Page page) {
        Map<String, Object> domContentLoadMetrics = getPerformanceMetrics(page, WebPortalConstants.PAGE_LOAD_TIME_JS_SCRIPT);
        long navigationStart = ((Number) domContentLoadMetrics.get(WebPortalConstants.NAVIGATION_START)).longValue();
        long domContentLoadedEventEnd = ((Number) domContentLoadMetrics.get(WebPortalConstants.DOM_CONTENT_LOADED_EVENT_END)).longValue();
        long domContentLoadTime = domContentLoadedEventEnd - navigationStart;
        log.info("DOM Content Loaded Time: {}", domContentLoadTime);
    }

    private static Map<String, Object> getPerformanceMetrics(Page page, String javaScriptCommand) {
        if (page == null || javaScriptCommand == null)
            throw new UtilsException("Either Playwright Page or Javascript Command is null!");
        return (Map<String, Object>) page.evaluate(javaScriptCommand);
    }
}