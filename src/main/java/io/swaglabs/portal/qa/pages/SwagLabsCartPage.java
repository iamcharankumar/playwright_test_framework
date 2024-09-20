package io.swaglabs.portal.qa.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class SwagLabsCartPage extends SwagLabsBasePage {

    public SwagLabsCartPage(Page basePage) {
        super(basePage);
    }

    private static final String CHECKOUT_BUTTON = "#checkout";

    public boolean isCheckoutButtonClicked() {
        Locator checkoutButton = locators.getPageLocator(CHECKOUT_BUTTON);
        if (checkoutButton.isEnabled()) {
            checkoutButton.click();
            return true;
        }
        return false;
    }
}