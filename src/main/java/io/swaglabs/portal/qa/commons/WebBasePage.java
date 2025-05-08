package io.swaglabs.portal.qa.commons;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.swaglabs.portal.qa.constants.WebPortalConstants;
import io.swaglabs.portal.qa.exceptions.WebPageException;
import io.swaglabs.portal.qa.locators.Locators;
import io.swaglabs.portal.qa.screenshotsmanager.ElementScreenshotStrategy;
import io.swaglabs.portal.qa.screenshotsmanager.ScreenshotContext;

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
        String textContent = locator.textContent().trim();
        validateNonEmptyText(textContent, errorMessage);
        return textContent;
    }

    protected void takeElementScreenshot(Locator locator, String fileName) {
        ScreenshotContext screenshotContext = new ScreenshotContext(new ElementScreenshotStrategy(locator));
        screenshotContext.captureScreenshot(basePage, ELEMENT_SCREENSHOT_FILE_LOCATION + fileName + WebPortalConstants.IMAGE_FORMAT);
    }
}