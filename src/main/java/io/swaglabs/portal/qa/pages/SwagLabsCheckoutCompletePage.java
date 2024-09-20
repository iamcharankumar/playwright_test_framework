package io.swaglabs.portal.qa.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class SwagLabsCheckoutCompletePage extends SwagLabsBasePage {

    public SwagLabsCheckoutCompletePage(Page basePage) {
        super(basePage);
    }

    private static final String THANK_YOU_TEXT = ".complete-header";
    private static final String COMPLETE_TEXT = ".complete-text";
    private static final String BACK_TO_HOME_BUTTON = "#back-to-products";

    public String getThankYouText() {
        return locators.getPageLocator(THANK_YOU_TEXT).textContent();
    }

    public String getOrderCompleteText() {
        return locators.getPageLocator(COMPLETE_TEXT).textContent();
    }

    public boolean isBackHomeButtonClicked() {
        Locator backHomeButton = locators.getPageLocator(BACK_TO_HOME_BUTTON);
        if (backHomeButton.isEnabled()) {
            backHomeButton.click();
            return true;
        }
        return false;
    }
}