package io.swaglabs.portal.qa.pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public final class SwagLabsCartPage extends SwagLabsBasePage {

    public SwagLabsCartPage(Page basePage) {
        super(basePage);
    }

    public boolean isCheckoutButtonClicked() {
        clickElement(locators.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Checkout")));
        return true;
    }
}