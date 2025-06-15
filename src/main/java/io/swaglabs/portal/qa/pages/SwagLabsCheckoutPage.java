package io.swaglabs.portal.qa.pages;

import com.microsoft.playwright.Page;
import io.swaglabs.portal.qa.constants.KeyboardEvents;

public final class SwagLabsCheckoutPage extends SwagLabsBasePage {

    public SwagLabsCheckoutPage(Page basePage) {
        super(basePage);
    }

    public boolean isFirstNameEntered(String firstName) {
        fillText(locators.getByPlaceholder("First Name"), firstName);
        return true;
    }

    public boolean isLastNameEntered(String lastName) {
        fillText(locators.getByPlaceholder("Last Name"), lastName);
        return true;
    }

    public boolean isPostalCodeEntered(String postalCode) {
        fillText(locators.getByPlaceholder("Zip/Postal Code"), postalCode);
        return true;
    }

    public boolean isContinueButtonClicked() {
        locators.getByText("Continue").press(KeyboardEvents.ENTER.getDescription());
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