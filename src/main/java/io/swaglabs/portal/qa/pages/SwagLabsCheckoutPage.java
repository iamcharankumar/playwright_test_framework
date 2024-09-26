package io.swaglabs.portal.qa.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.swaglabs.portal.qa.exceptions.SwagLabsException;

public class SwagLabsCheckoutPage extends SwagLabsBasePage {

    public SwagLabsCheckoutPage(Page basePage) {
        super(basePage);
    }

    private static final String FIRST_NAME_TEXT_BOX = "#first-name";
    private static final String LAST_NAME_TEXT_BOX = "#last-name";
    private static final String POSTAL_CODE_TEXT_BOX = "#postal-code";
    private static final String CONTINUE_BUTTON = "#continue";

    public boolean isCheckoutInformationEntered(String firstName, String lastName, String postalCode) {
        Locator firstNameInputBox = locators.getPageLocator(FIRST_NAME_TEXT_BOX);
        if (!firstNameInputBox.isVisible())
            throw new SwagLabsException("First Name Input Box not visible!");
        firstNameInputBox.click();
        firstNameInputBox.clear();
        firstNameInputBox.fill(firstName);
        Locator lastNameInputBox = locators.getPageLocator(LAST_NAME_TEXT_BOX);
        if (!lastNameInputBox.isVisible())
            throw new SwagLabsException("Last Name Input Box not visible!");
        lastNameInputBox.click();
        lastNameInputBox.clear();
        lastNameInputBox.fill(lastName);
        Locator postalCodeInputBox = locators.getPageLocator(POSTAL_CODE_TEXT_BOX);
        if (!postalCodeInputBox.isVisible())
            throw new SwagLabsException("Postal Code Input Box not visible!");
        postalCodeInputBox.click();
        postalCodeInputBox.clear();
        postalCodeInputBox.fill(postalCode);
        Locator continueButton = locators.getPageLocator(CONTINUE_BUTTON);
        if (!continueButton.isVisible() || !continueButton.isEnabled())
            throw new SwagLabsException("Continue Button is not visible or enabled!");
        continueButton.click();
        return true;
    }
}