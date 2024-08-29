package io.swaglabs.portal.qa.listeners;

import com.microsoft.playwright.Page;
import io.swaglabs.portal.qa.commons.WebBaseTest;
import io.swaglabs.portal.qa.constants.WebPortalConstants;
import lombok.extern.slf4j.Slf4j;
import org.testng.*;

import java.io.File;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;

@Slf4j
public class WebTestListeners extends WebBaseTest implements ISuiteListener, ITestListener, IRetryAnalyzer {

    private Instant startDate;
    private static final int MAX_RETRY = 0;
    private int retryCount = 0;

    @Override
    public void onStart(ISuite suite) {
        startDate = Instant.now();
        log.info("Web Test Suite {} started executing at {}.", suite.getName(), startDate);
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
        String browserName = System.getProperty(WebPortalConstants.BROWSER);
        String runMode = System.getProperty(WebPortalConstants.RUN_MODE);
        String testData = "";
        if (testResult.getParameters().length > 0)
            testData = String.valueOf(testResult.getParameters()[0]);
        if (testResult.isSuccess()) {
            String passFilePath = WebPortalConstants.SCREENSHOTS_DIRECTORY + WebPortalConstants.PASS + File.separator
                    + browserName + "_" + runMode + "_" + WebPortalConstants.PASS_PREFIX
                    + testResult.getName() + "_" + testData + "_" + new Date() + WebPortalConstants.IMAGE_FORMAT;
            page.get().screenshot(new Page.ScreenshotOptions().setPath(Paths.get(passFilePath)));
            log.info("Success scenario has been captured. PASSED Screenshot has been placed in the location {}", passFilePath);
        } else {
            String failFilePath = WebPortalConstants.SCREENSHOTS_DIRECTORY + WebPortalConstants.FAIL + File.separator
                    + browserName + "_" + runMode + "_" + WebPortalConstants.FAIL_PREFIX
                    + testResult.getName() + testData + "_" + new Date() + WebPortalConstants.IMAGE_FORMAT;
            page.get().screenshot(new Page.ScreenshotOptions().setPath(Paths.get(failFilePath)));
            log.info("Failed scenario has been captured. FAILED Screenshot has been placed in the location {}", failFilePath);
        }
    }

    @Override
    public boolean retry(ITestResult result) {
        if (!result.isSuccess() && retryCount < MAX_RETRY) {
            log.info("Retrying test for {} time(s) for the test method {} with test status {}.", retryCount + 1,
                    result.getName(), getTestStatusName(result.getStatus()));
            retryCount++;
            return true;
        }
        log.info("Retrying for the test method {} is exhausted.", result.getName());
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
