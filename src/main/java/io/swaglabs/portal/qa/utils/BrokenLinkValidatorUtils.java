package io.swaglabs.portal.qa.utils;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

/**
 * Utility class to validate all <a href> links on a web page using Playwright and RestAssured.
 * Collects all anchor tags, resolves their full URLs, checks for broken links (4xx/5xx), and logs the results.
 * Usage:
 * BrokenLinkValidatorUtils.validateAllLinks(page, "MethodNameXYZ");
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BrokenLinkValidatorUtils {

    /**
     * Extracts and validates all unique anchor tags (<a href>) on the page.
     *
     * @param page       The Playwright page instance.
     * @param methodName Name of the test method (for logging context).
     */
    public static void validateAllLinks(Page page, String methodName) {
        Objects.requireNonNull(page, "Playwright page cannot be null!");
        Set<String> uniqueLinks = extractUniqueLinks(page);
        log.info("Total unique links found in method [{}]: {}", methodName, uniqueLinks.size());
        ExecutorService executor = Executors.newFixedThreadPool(10); // Tune this based on infra
        List<Future<String>> futures = uniqueLinks.stream()
                .map(link -> executor.submit(() -> {
                    String resolvedUrl = resolveAbsoluteUrl(page.url(), link);
                    try {
                        Response response = RestAssured.given()
                                .relaxedHTTPSValidation()
                                .when()
                                .head(resolvedUrl);
                        int statusCode = response.getStatusCode();
                        if (statusCode >= HttpStatus.SC_BAD_REQUEST) {
                            log.info("BROKEN LINK: {} --> HTTP {}", resolvedUrl, statusCode);
                            return resolvedUrl + " --> HTTP " + statusCode;
                        } else {
                            log.debug("Valid Link: {} --> HTTP {}", resolvedUrl, statusCode);
                            return null;
                        }
                    } catch (Exception e) {
                        log.info("BROKEN LINK: {} --> Exception: {}", resolvedUrl, e.getMessage());
                        return resolvedUrl + " --> Exception: " + e.getMessage();
                    }
                }))
                .toList();
        List<String> brokenLinks = futures.stream()
                .map(future -> {
                    try {
                        return future.get(); // blocking until complete
                    } catch (Exception e) {
                        String errMsg = "Future execution failed --> " + e.getMessage();
                        log.info(errMsg);
                        return errMsg;
                    }
                })
                .filter(Objects::nonNull)
                .toList();
        executor.shutdown();
        if (brokenLinks.isEmpty()) {
            log.info("No broken links found on page: {}", page.url());
        } else {
            log.info("Total broken links found on page [{}]: {}", page.url(), brokenLinks.size());
            brokenLinks.forEach(link -> log.info("Broken --> {}", link));
        }
    }


    /**
     * Extracts all unique href values from <a> tags on the page.
     *
     * @param page Playwright Page
     * @return Set of unique link hrefs
     */
    private static Set<String> extractUniqueLinks(Page page) {
        Locator anchors = page.locator("a[href]");
        return anchors.all().stream()
                .map(element -> element.getAttribute("href"))
                .filter(Objects::nonNull)
                .map(String::trim)
                .filter(href -> !href.isEmpty())
                .filter(href -> !href.startsWith("javascript:"))
                .filter(href -> !href.startsWith("#"))
                .collect(Collectors.toSet());
    }

    /**
     * Resolves relative URLs against the base page URL.
     *
     * @param baseUrl   The current page URL
     * @param hrefValue The raw href
     * @return Absolute URL
     */
    private static String resolveAbsoluteUrl(String baseUrl, String hrefValue) {
        if (hrefValue.startsWith("http")) {
            return hrefValue;
        }
        if (hrefValue.startsWith("/")) {
            return baseUrl.replaceAll("(?<=https?://[^/]+).*", "") + hrefValue;
        }
        // Fallback for relative paths
        return baseUrl + "/" + hrefValue;
    }
}