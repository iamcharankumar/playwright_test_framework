package io.swaglabs.portal.qa.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class SwagLabsCheckoutOverviewPage extends SwagLabsBasePage {

    public SwagLabsCheckoutOverviewPage(Page basePage) {
        super(basePage);
    }

    private static final String FINISH_BUTTON = "#finish";

    public boolean isFinishButtonClicked() {
        Locator finishButton = locators.getPageLocator(FINISH_BUTTON);
        if (finishButton.isEnabled()) {
            finishButton.click();
            return true;
        }
        return false;
    }
}