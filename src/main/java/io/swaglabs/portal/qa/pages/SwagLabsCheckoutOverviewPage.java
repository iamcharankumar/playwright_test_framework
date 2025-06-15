package io.swaglabs.portal.qa.pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public final class SwagLabsCheckoutOverviewPage extends SwagLabsBasePage {

    public SwagLabsCheckoutOverviewPage(Page basePage) {
        super(basePage);
    }

    public boolean isFinishButtonClicked() {
        clickElement(locators.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Finish")));
        return true;
    }
}