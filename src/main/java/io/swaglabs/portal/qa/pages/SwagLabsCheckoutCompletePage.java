package io.swaglabs.portal.qa.pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public final class SwagLabsCheckoutCompletePage extends SwagLabsBasePage {

    public SwagLabsCheckoutCompletePage(Page basePage) {
        super(basePage);
    }

    public String getThankYouText() {
        return getTextContent(".complete-header");
    }

    public String getOrderCompleteText() {
        return getTextContent(".complete-text");
    }

    public boolean isBackHomeButtonClicked() {
        clickElement(locators.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Back Home")));
        return true;
    }
}