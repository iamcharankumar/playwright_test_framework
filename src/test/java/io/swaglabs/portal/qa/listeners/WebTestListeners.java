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

    private int retryCount = 0;
    private Instant startDate;

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
        String browserName = System.getProperty(WebPortalConstants.BROWSER);
        String runMode = System.getProperty(WebPortalConstants.RUN_MODE);
        String testData = (testResult.getParameters().length > 0) ? String.valueOf(testResult.getParameters()[0]) : "No_Params";
        String screenshotsDirectory = "./src/test/resources/screenshots";
        String imageFormat = ".png";
        if (testResult.isSuccess()) {
            String passLocation = "/passed_screenshots";
            String passPrefix = "PASS_";
            String passFilePath = screenshotsDirectory + passLocation + File.separator + browserName + "_"
                    + runMode + "_" + passPrefix + testResult.getName() + "_" + testData + "_" + new Date() + imageFormat;
            page.get().screenshot(new Page.ScreenshotOptions().setPath(Paths.get(passFilePath)));
        } else {
            String failLocation = "/failed_screenshots";
            String failPrefix = "FAILED_";
            String failFilePath = screenshotsDirectory + failLocation + File.separator + browserName + "_"
                    + runMode + "_" + failPrefix + testResult.getName() + testData + "_" + new Date() + imageFormat;
            page.get().screenshot(new Page.ScreenshotOptions().setPath(Paths.get(failFilePath)).setFullPage(true));
        }
    }

    @Override
    public boolean retry(ITestResult result) {
        int maxRetry = 1;
        if (!result.isSuccess() && retryCount < maxRetry) {
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