package io.swaglabs.portal.qa.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.swaglabs.portal.qa.constants.WebLocatorConstants;

public class SwagLabsCheckoutCompletePage extends SwagLabsBasePage {

    public SwagLabsCheckoutCompletePage(Page basePage) {
        super(basePage);
    }

    public String getThankYouText() {
        return getPageLocator(WebLocatorConstants.THANK_YOU_TEXT).textContent();
    }

    public String getOrderCompleteText() {
        return getPageLocator(WebLocatorConstants.COMPLETE_TEXT).textContent();
    }

    public boolean isBackHomeButtonClicked() {
        Locator backHomeButton = getPageLocator(WebLocatorConstants.BACK_TO_HOME_BUTTON);
        if (backHomeButton.isEnabled()) {
            backHomeButton.click();
            return true;
        }
        return false;
    }
}