package io.swaglabs.portal.qa.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class SwagLabsCheckoutCompletePage extends SwagLabsBasePage {

    public SwagLabsCheckoutCompletePage(Page basePage) {
        super(basePage);
    }

    public String getThankYouText() {
        Locator thankYouLocator = locators.getPageLocator(".complete-header");
        validateAction(thankYouLocator.isVisible(), "Thank you text not visible!");
        return thankYouLocator.textContent();
    }

    public String getOrderCompleteText() {
        Locator orderCompleteLocator = locators.getPageLocator(".complete-text");
        validateAction(orderCompleteLocator.isVisible(), "Order Complete Text not visible!");
        return orderCompleteLocator.textContent();
    }

    public boolean isBackHomeButtonClicked() {
        Locator backHomeButton = locators.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Back Home"));
        validateAction(backHomeButton.isEnabled(), "Back Home Button not clicked!");
        backHomeButton.click();
        return true;
    }
}