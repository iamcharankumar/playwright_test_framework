package io.swaglabs.portal.qa.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.swaglabs.portal.qa.constants.WebLocatorConstants;

public class SwagLabsCheckoutOverviewPage extends SwagLabsBasePage {

    public SwagLabsCheckoutOverviewPage(Page basePage) {
        super(basePage);
    }

    public boolean isFinishButtonClicked() {
        Locator finishButton = getPageLocator(WebLocatorConstants.FINISH_BUTTON);
        if (finishButton.isEnabled()) {
            finishButton.click();
            return true;
        }
        return false;
    }
}