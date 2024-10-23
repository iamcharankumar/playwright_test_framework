package io.swaglabs.portal.qa.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class SwagLabsCartPage extends SwagLabsBasePage {

    public SwagLabsCartPage(Page basePage) {
        super(basePage);
    }

    public boolean isCheckoutButtonClicked() {
        Locator checkoutButton = locators.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Checkout"));
        validateAction(checkoutButton.isEnabled(), "Checkout Button not clicked!");
        checkoutButton.click();
        return true;
    }
}