package io.swaglabs.portal.qa.commons;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.swaglabs.portal.qa.exceptions.WebPageException;
import io.swaglabs.portal.qa.locators.Locators;

public abstract class WebBasePage {

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

    protected void clickElement(Locator locator, String errorMessage) {
        if (!locator.isVisible() || !locator.isEnabled())
            throw new WebPageException(errorMessage);
        locator.click();
    }
}