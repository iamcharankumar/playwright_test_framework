package io.swaglabs.portal.qa.utils;

import com.microsoft.playwright.Page;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Objects;

/**
 * @author charankumar.h
 * This utility class, is designed to evaluate and log performance metrics for a web page using Playwright.
 * It leverages the browser's Performance Timing API to calculate key metrics like Page Load Time and DOM Content Loaded Time.
 */

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PerformanceUtils {

    // JAVASCRIPT COMMANDS
    private static final String PERFORMANCE_JS_SCRIPT = "() => JSON.parse(JSON.stringify(window.performance.timing))"; // A JavaScript snippet that fetches the Performance Timing API data from the browser's window.performance.timing object.
    private static final String NAVIGATION_START = "navigationStart"; // The time when navigation to the page started.
    private static final String LOAD_EVENT_END = "loadEventEnd"; //The time when the page and its resources finished loading.
    private static final String DOM_CONTENT_LOADED_EVENT_END = "domContentLoadedEventEnd"; //The time when the DOM is fully constructed and ready to be interacted with.

    /**
     * Calculates Page Load Time, which is the time difference between navigationStart and loadEventEnd.
     * Page Load Time measures how long it takes for the entire page (including resources) to be fully loaded.
     */
    public static void evaluatePageLoadTime(Page page, String methodName) {
        Map<String, Object> pageLoadMetrics = getPerformanceMetrics(page);
        long pageLoadTime = calculateLoadTime(pageLoadMetrics, LOAD_EVENT_END);
        log.info("Page Load Time for the method: {} is: {} ms.", methodName, pageLoadTime);
    }

    /**
     * Calculates DOM Content Loaded Time,
     * which is the time difference between navigationStart and domContentLoadedEventEnd.
     * This metric measures how long it takes for the DOM to be fully constructed
     * (excluding external resources like images or CSS).
     */
    public static void evaluateDomContentLoadTime(Page page, String methodName) {
        Map<String, Object> domContentLoadMetrics = getPerformanceMetrics(page);
        long domContentLoadTime = calculateLoadTime(domContentLoadMetrics, DOM_CONTENT_LOADED_EVENT_END);
        log.info("DOM Content Loaded Time for the method: {} is: {} ms.", methodName, domContentLoadTime);
    }

    @SuppressWarnings("unchecked")
    private static Map<String, Object> getPerformanceMetrics(Page page) {
        Objects.requireNonNull(page, "Page cannot be null!");
        return (Map<String, Object>) page.evaluate(PERFORMANCE_JS_SCRIPT);
    }

    private static long calculateLoadTime(Map<String, Object> metrics, String endKey) {
        Objects.requireNonNull(metrics, "Performance metrics map cannot be null!");
        long navigationStart = ((Number) metrics.getOrDefault(NAVIGATION_START, 0)).longValue();
        long loadedEventEnd = ((Number) metrics.getOrDefault(endKey, 0)).longValue();
        return loadedEventEnd - navigationStart;
    }
}