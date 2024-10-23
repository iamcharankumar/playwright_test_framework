package io.swaglabs.portal.qa.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.swaglabs.portal.qa.constants.KeyboardEvents;

public class SwagLabsCheckoutPage extends SwagLabsBasePage {

    public SwagLabsCheckoutPage(Page basePage) {
        super(basePage);
    }

    public boolean isFirstNameEntered(String firstName) {
        Locator firstNameInputBox = locators.getByPlaceholder("First Name");
        if (!firstNameInputBox.isVisible())
            return false;
        firstNameInputBox.clear();
        firstNameInputBox.fill(firstName);
        return true;
    }

    public boolean isLastNameEntered(String lastName) {
        Locator lastNameInputBox = locators.getByPlaceholder("Last Name");
        if (!lastNameInputBox.isVisible())
            return false;
        lastNameInputBox.clear();
        lastNameInputBox.fill(lastName);
        return true;
    }

    public boolean isPostalCodeEntered(String postalCode) {
        Locator postalCodeInputBox = locators.getByPlaceholder("Zip/Postal Code");
        if (!postalCodeInputBox.isVisible())
            return false;
        postalCodeInputBox.clear();
        postalCodeInputBox.fill(postalCode);
        return true;
    }

    public boolean isContinueButtonClicked() {
        Locator continueButton = locators.getByText("Continue");
        if (!continueButton.isEnabled())
            return false;
        continueButton.press(KeyboardEvents.ENTER.getDescription());
        return true;
    }

    public boolean isCheckoutInformationEntered(String firstName, String lastName, String postalCode) {
        validateAction(isFirstNameEntered(firstName), "First Name Input Box not visible!");
        validateAction(isLastNameEntered(lastName), "Last Name Input Box not visible!");
        validateAction(isPostalCodeEntered(postalCode), "Postal Code Input Box not visible!");
        validateAction(isContinueButtonClicked(), "Continue Button is not visible or enabled!");
        return true;
    }
}