package io.swaglabs.portal.qa.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.swaglabs.portal.qa.exceptions.SwagLabsException;

public class SwagLabsCheckoutOverviewPage extends SwagLabsBasePage {

    public SwagLabsCheckoutOverviewPage(Page basePage) {
        super(basePage);
    }

    public boolean isFinishButtonClicked() {
        Locator finishButton = locators.getPageLocator("#finish");
        if (!finishButton.isEnabled())
            throw new SwagLabsException("Finish button not enabled!");
        finishButton.click();
        return true;
    }
}