package io.swaglabs.portal.qa.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.swaglabs.portal.qa.constants.WebLocatorConstants;

public class SwagLabsCheckoutPage extends SwagLabsBasePage {

    public SwagLabsCheckoutPage(Page basePage) {
        super(basePage);
    }

    public boolean isCheckoutInformationEntered(String firstName, String lastName, String postalCode) {
        getPageLocator(WebLocatorConstants.FIRST_NAME_TEXT_BOX).click();
        basePage.keyboard().type(firstName);
        getPageLocator(WebLocatorConstants.LAST_NAME_TEXT_BOX).click();
        basePage.keyboard().type(lastName);
        getPageLocator(WebLocatorConstants.POSTAL_CODE_TEXT_BOX).click();
        basePage.keyboard().type(postalCode);
        Locator continueButton = getPageLocator(WebLocatorConstants.CONTINUE_BUTTON);
        if (continueButton.isEnabled()) {
            continueButton.click();
            return true;
        }
        return false;
    }
}