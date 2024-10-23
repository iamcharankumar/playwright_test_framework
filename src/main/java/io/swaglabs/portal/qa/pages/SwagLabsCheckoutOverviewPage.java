package io.swaglabs.portal.qa.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class SwagLabsCheckoutOverviewPage extends SwagLabsBasePage {

    public SwagLabsCheckoutOverviewPage(Page basePage) {
        super(basePage);
    }

    public boolean isFinishButtonClicked() {
        Locator finishButton = locators.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Finish"));
        validateAction(finishButton.isEnabled(), "Finish button not enabled!");
        finishButton.click();
        return true;
    }
}