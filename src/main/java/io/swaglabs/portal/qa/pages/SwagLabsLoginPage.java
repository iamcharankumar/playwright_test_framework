package io.swaglabs.portal.qa.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.swaglabs.portal.qa.constants.KeyboardEvents;
import io.swaglabs.portal.qa.constants.WebPortalConstants;

public final class SwagLabsLoginPage extends SwagLabsBasePage {

    public SwagLabsLoginPage(Page basePage) {
        super(basePage);
    }

    public String getSwagLabsPageTitle() {
        String pageTitle = basePage.title();
        validateNonEmptyText(pageTitle, "Swag Labs Page Title is Empty!");
        return pageTitle;
    }

    public String getSwagLabsLogoText() {
        return getTextContent(locators.getByText("Swag Labs"));
    }

    public String getAcceptedUserNames() {
        String acceptedUserNames = String.join("", locators.getPageLocator("#login_credentials").allTextContents());
        validateNonEmptyText(acceptedUserNames, "Accepted User Name List is empty!");
        return acceptedUserNames;
    }

    public String getAllUserPasswords() {
        String allUserPasswords = String.join("", locators.getPageLocator(".login_password").allTextContents());
        validateNonEmptyText(allUserPasswords, "All User Passwords is empty!");
        return allUserPasswords;
    }

    public boolean isUserNameEntered(String userName) {
        Locator userNameInputBox = locators.getByPlaceholder("Username");
        fillText(userNameInputBox, userName);
        return true;
    }

    public boolean isPasswordEntered(String password) {
        Locator passwordInputBox = locators.getByPlaceholder("Password");
        fillText(passwordInputBox, password);
        return true;
    }

    public boolean isHomePageLanded() {
        return getTextContent(".app_logo").equalsIgnoreCase("Swag Labs");
    }

    public boolean isLoginSuccess() {
        validateAction(isUserNameEntered(WebPortalConstants.USERNAME), "User Name not entered!");
        validateAction(isPasswordEntered(WebPortalConstants.PASSWORD), "Password not entered!");
        Locator loginButton = locators.getByText("Login");
        loginButton.press(KeyboardEvents.ENTER.getDescription());
        validateAction(isHomePageLanded(), "Login Failed for the username: " + WebPortalConstants.USERNAME);
        return true;
    }
}