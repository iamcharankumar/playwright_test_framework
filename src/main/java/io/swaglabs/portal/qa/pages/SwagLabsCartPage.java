package io.swaglabs.portal.qa.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class SwagLabsCartPage extends SwagLabsBasePage {

    public SwagLabsCartPage(Page basePage) {
        super(basePage);
    }

    public boolean isCheckoutButtonClicked() {
        Locator checkoutButton = locators.getPageLocator("#checkout");
        if (checkoutButton.isEnabled()) {
            checkoutButton.click();
            return true;
        }
        return false;
    }
}