package io.swaglabs.portal.qa.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.swaglabs.portal.qa.constants.WebLocatorConstants;

public class SwagLabsCartPage extends SwagLabsBasePage {

    public SwagLabsCartPage(Page basePage) {
        super(basePage);
    }

    public boolean isCheckoutButtonClicked() {
        Locator checkoutButton = getPageLocator(WebLocatorConstants.CHECKOUT_BUTTON);
        if (checkoutButton.isEnabled()) {
            checkoutButton.click();
            return true;
        }
        return false;
    }
}