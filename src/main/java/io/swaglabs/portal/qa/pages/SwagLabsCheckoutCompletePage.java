package io.swaglabs.portal.qa.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.swaglabs.portal.qa.exceptions.SwagLabsException;

public class SwagLabsCheckoutCompletePage extends SwagLabsBasePage {

    public SwagLabsCheckoutCompletePage(Page basePage) {
        super(basePage);
    }

    public String getThankYouText() {
        Locator thankYouLocator = locators.getPageLocator(".complete-header");
        if (!thankYouLocator.isVisible())
            throw new SwagLabsException("Thank you text not visible!");
        return thankYouLocator.textContent();
    }

    public String getOrderCompleteText() {
        Locator orderCompleteLocator = locators.getPageLocator(".complete-text");
        if (!orderCompleteLocator.isVisible())
            throw new SwagLabsException("Order Complete Text not visible!");
        return orderCompleteLocator.textContent();
    }

    public boolean isBackHomeButtonClicked() {
        Locator backHomeButton = locators.getPageLocator("#back-to-products");
        if (!backHomeButton.isEnabled())
            throw new SwagLabsException("Back Home Button not clicked!");
        backHomeButton.click();
        return true;
    }
}