package io.swaglabs.portal.qa.listeners;

import com.microsoft.playwright.Page;
import io.swaglabs.portal.qa.constants.WebPortalConstants;
import lombok.extern.slf4j.Slf4j;
import org.testng.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
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
        String browserName = WebPortalConstants.BROWSER;
        String runMode = WebPortalConstants.RUN_MODE;
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
        String statusPrefix = testResult.isSuccess() ? "PASS" : "FAILED";
        String directory = testResult.isSuccess() ? "passed_screenshots" : "failed_screenshots";
        String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss").format(new Date());
        String parameter = extractSafeParameter(testResult);
        String fileName = formatScreenshotFileName(statusPrefix, testName, parameter, timestamp).replaceAll(":", "_");
        String dirPath = Paths.get(WebPortalConstants.SCREENSHOT_FILE_LOCATION + "/pages/", directory).toString();
        String filePath = Paths.get(dirPath, fileName).toString();
        try {
            Files.createDirectories(Paths.get(dirPath));
            WebPortalConstants.SCREENSHOTS_UTILS.takeFullPageScreenshotContext(currentPage).captureScreenshot(filePath);
        } catch (IOException e) {
            log.error("Screenshot failed for {}: {}", testName, e.getMessage());
        }
    }

    private String extractSafeParameter(ITestResult result) {
        int maxParamLength = 30;
        if (result.getParameters().length == 0 || result.getParameters()[0] == null) {
            return "NoParams";
        }
        String parameter = result.getParameters()[0].toString().replaceAll("[^a-zA-Z0-9-_]", "_"); // Sanitize
        return parameter.length() > maxParamLength ? parameter.substring(0, maxParamLength) : parameter;
    }

    private String formatScreenshotFileName(String prefix, String testName, String param, String timestamp) {
        int maxFileNameLength = 200;
        String fileName = String.format("%s_%s_%s_%s_%s_%s%s", WebPortalConstants.BROWSER, WebPortalConstants.RUN_MODE,
                prefix, testName, param, timestamp, WebPortalConstants.IMAGE_FORMAT);
        return fileName.length() > maxFileNameLength ? fileName.substring(0, maxFileNameLength) + WebPortalConstants.IMAGE_FORMAT : fileName;
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