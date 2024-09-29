package io.swaglabs.portal.qa.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.swaglabs.portal.qa.exceptions.SwagLabsException;

public class SwagLabsCheckoutPage extends SwagLabsBasePage {

    public SwagLabsCheckoutPage(Page basePage) {
        super(basePage);
    }

    public boolean isCheckoutInformationEntered(String firstName, String lastName, String postalCode) {
        Locator firstNameInputBox = locators.getPageLocator("#first-name");
        if (!firstNameInputBox.isVisible())
            throw new SwagLabsException("First Name Input Box not visible!");
        firstNameInputBox.clear();
        firstNameInputBox.fill(firstName);
        Locator lastNameInputBox = locators.getPageLocator("#last-name");
        if (!lastNameInputBox.isVisible())
            throw new SwagLabsException("Last Name Input Box not visible!");
        lastNameInputBox.clear();
        lastNameInputBox.fill(lastName);
        Locator postalCodeInputBox = locators.getPageLocator("#postal-code");
        if (!postalCodeInputBox.isVisible())
            throw new SwagLabsException("Postal Code Input Box not visible!");
        postalCodeInputBox.clear();
        postalCodeInputBox.fill(postalCode);
        Locator continueButton = locators.getPageLocator("#continue");
        if (!continueButton.isVisible() || !continueButton.isEnabled())
            throw new SwagLabsException("Continue Button is not visible or enabled!");
        continueButton.click();
        return true;
    }
}