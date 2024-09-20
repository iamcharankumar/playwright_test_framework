package io.swaglabs.portal.qa.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class SwagLabsCheckoutPage extends SwagLabsBasePage {

    public SwagLabsCheckoutPage(Page basePage) {
        super(basePage);
    }

    private static final String FIRST_NAME_TEXT_BOX = "#first-name";
    private static final String LAST_NAME_TEXT_BOX = "#last-name";
    private static final String POSTAL_CODE_TEXT_BOX = "#postal-code";
    private static final String CONTINUE_BUTTON = "#continue";

    public boolean isCheckoutInformationEntered(String firstName, String lastName, String postalCode) {
        locators.getPageLocator(FIRST_NAME_TEXT_BOX).click();
        basePage.keyboard().type(firstName);
        locators.getPageLocator(LAST_NAME_TEXT_BOX).click();
        basePage.keyboard().type(lastName);
        locators.getPageLocator(POSTAL_CODE_TEXT_BOX).click();
        basePage.keyboard().type(postalCode);
        Locator continueButton = locators.getPageLocator(CONTINUE_BUTTON);
        if (continueButton.isEnabled()) {
            continueButton.click();
            return true;
        }
        return false;
    }
}