package io.swaglabs.portal.qa.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class SwagLabsCheckoutCompletePage extends SwagLabsBasePage {

    public SwagLabsCheckoutCompletePage(Page basePage) {
        super(basePage);
    }

    public String getThankYouText() {
        return locators.getPageLocator(".complete-header").textContent();
    }

    public String getOrderCompleteText() {
        return locators.getPageLocator(".complete-text").textContent();
    }

    public boolean isBackHomeButtonClicked() {
        Locator backHomeButton = locators.getPageLocator("#back-to-products");
        if (backHomeButton.isEnabled()) {
            backHomeButton.click();
            return true;
        }
        return false;
    }
}