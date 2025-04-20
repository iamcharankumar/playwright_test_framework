package io.swaglabs.portal.qa.listeners;

import com.microsoft.playwright.Page;
import io.swaglabs.portal.qa.constants.WebPortalConstants;
import io.swaglabs.portal.qa.screenshotsmanager.FullPageScreenshotStrategy;
import io.swaglabs.portal.qa.screenshotsmanager.ScreenshotContext;
import lombok.extern.slf4j.Slf4j;
import org.testng.*;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;

@Slf4j
public class WebTestListeners implements ISuiteListener, ITestListener, IRetryAnalyzer {

    private int retryCount = 0;
    private Instant startDate;

    private static final ThreadLocal<Page> PAGE = new ThreadLocal<>();

    public static void setPage(Page page) {
        PAGE.set(page);
    }

    @Override
    public void onStart(ISuite suite) {
        String browserName = System.getProperty(WebPortalConstants.BROWSER);
        String runMode = System.getProperty(WebPortalConstants.RUN_MODE);
        startDate = Instant.now();
        log.info("Web Test Suite {} started executing at {}.", suite.getName(), startDate);
        log.info("Browser: {} | Run Mode: {}", browserName, runMode);
    }

    @Override
    public void onFinish(ITestContext context) {
        Instant endDate = Instant.now();
        Duration timeElapsed = Duration.between(startDate, endDate);
        log.info("Sauce Labs Test Suite finished executing in {} seconds.", timeElapsed.getSeconds());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        takeScreenshot(result);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        takeScreenshot(result);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        takeScreenshot(result);
    }

    private void takeScreenshot(ITestResult testResult) {
        Page currentPage = PAGE.get();
        if (currentPage == null || currentPage.isClosed()) {
            log.error("Page object is null or already closed. Cannot take screenshot.");
            return;
        }
        String testName = testResult.getName();
        String statusPrefix = testResult.isSuccess() ? "PASS_" : "FAIL_";
        String directory = testResult.isSuccess() ? "/passed_screenshots/" : "/failed_screenshots/";
        String testData = (testResult.getParameters().length > 0) ? String.valueOf(testResult.getParameters()[0]) : "No_Params";
        String filePath = String.format("%s%s%s_%s_%s%s_%s_%s%s", "./src/test/resources/screenshots",
                directory, System.getProperty(WebPortalConstants.BROWSER), System.getProperty(WebPortalConstants.RUN_MODE),
                statusPrefix, testName, testData, new Date(), ".png").replaceAll(":", "\\:");
        ScreenshotContext screenshotContext = new ScreenshotContext(new FullPageScreenshotStrategy());
        screenshotContext.captureScreenshot(currentPage, filePath);
    }

    @Override
    public boolean retry(ITestResult result) {
        int maxRetry = 1;
        if (!result.isSuccess() && retryCount < maxRetry) {
            log.error("Retrying test for {} time(s) for the test method {} with test status {}.",
                    retryCount + 1, result.getName(), getTestStatusName(result.getStatus()));
            retryCount++;
            return true;
        }
        log.error("Retrying for the test method {} is exhausted.", result.getName());
        return false;
    }

    private String getTestStatusName(int status) {
        return switch (status) {
            case 1 -> "SUCCESS";
            case 2 -> "FAILED";
            case 3 -> "SKIP";
            default -> null;
        };
    }
}