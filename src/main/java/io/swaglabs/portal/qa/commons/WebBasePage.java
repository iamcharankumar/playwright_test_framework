package io.swaglabs.portal.qa.commons;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.swaglabs.portal.qa.constants.WebPortalConstants;
import io.swaglabs.portal.qa.exceptions.WebPageException;
import io.swaglabs.portal.qa.listeners.WebTestListeners;
import io.swaglabs.portal.qa.locators.Locators;
import io.swaglabs.portal.qa.screenshotsmanager.ElementScreenshotStrategy;
import io.swaglabs.portal.qa.screenshotsmanager.ScreenshotContext;
import org.testng.ITestResult;
import org.testng.Reporter;

import java.util.Objects;
import java.util.function.Consumer;

public abstract class WebBasePage {

    private static final String ELEMENT_SCREENSHOT_FILE_LOCATION = WebPortalConstants.SCREENSHOT_FILE_LOCATION + "/elements/"
            + WebPortalConstants.BROWSER + "_" + WebPortalConstants.RUN_MODE + "_Element_";
    protected Page basePage;
    protected Locators locators;

    protected WebBasePage(Page basePage) {
        this.basePage = basePage;
        this.locators = new Locators(basePage);
    }

    protected void validateAction(boolean condition, String errorMessage) {
        if (!condition)
            throw new WebPageException(errorMessage);
    }

    protected void validateNonEmptyText(String text, String errorMessage) {
        if (text.isEmpty())
            throw new WebPageException(errorMessage);
    }

    protected void clickElement(Locator locator) {
        locator.click();
    }

    protected void fillText(Locator locator, String textContent) {
        locator.clear();
        locator.fill(textContent);
    }

    protected String getTextContent(String selector) {
        return extractText(locators.getPageLocator(selector), "Text content is empty for the locator: " + selector);
    }

    protected String getTextContent(Locator locator) {
        return extractText(locator, "Text content is empty for the locator: " + locator);
    }

    private String extractText(Locator locator, String errorMessage) {
        Objects.requireNonNull(locator, errorMessage);
        return locator.textContent().trim();
    }

    protected void takeElementScreenshot(Locator locator, String fileName) {
        ScreenshotContext screenshotContext = new ScreenshotContext(new ElementScreenshotStrategy(locator));
        screenshotContext.captureScreenshot(basePage, ELEMENT_SCREENSHOT_FILE_LOCATION + fileName + WebPortalConstants.IMAGE_FORMAT);
    }
    
    protected void switchToNewTabAndRun(Page newTabPage, Consumer<WebBasePage> actionsInNewTab) {
        Page originalPage = this.basePage;
        Locators originalLocators = this.locators;
        try {
            // Update to new tab context
            this.basePage = newTabPage;
            this.locators = new Locators(newTabPage);
            WebTestListeners.setPage(newTabPage);
            actionsInNewTab.accept(this); // Perform operations in new tab
        } catch (Exception testException) {
            // Capture screenshot of the new tab before any cleanup
            if (newTabPage != null && !newTabPage.isClosed()) {
                ITestResult currentTestResult = Reporter.getCurrentTestResult();
                if (currentTestResult != null) {
                    currentTestResult.setStatus(ITestResult.FAILURE);
                    currentTestResult.setThrowable(testException);
                    WebTestListeners listeners = new WebTestListeners();
                    listeners.onTestFailure(currentTestResult);
                }
            }
            throw testException; // Re-throw to maintain original behavior
        } finally {
            if (newTabPage != null && !newTabPage.isClosed()) {
                newTabPage.close(); // Always close the new tab
            }
            WebTestListeners.setPage(originalPage);
            // Revert to the original page and locator
            this.basePage = originalPage;
            this.locators = originalLocators;
        }
    }
}