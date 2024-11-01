package io.swaglabs.portal.qa.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.swaglabs.portal.qa.constants.KeyboardEvents;
import io.swaglabs.portal.qa.constants.WebPortalConstants;

public class SwagLabsLoginPage extends SwagLabsBasePage {

    public SwagLabsLoginPage(Page basePage) {
        super(basePage);
    }

    public String getSwagLabsPageTitle() {
        String pageTitle = basePage.title();
        validateNonEmptyText(pageTitle, "Swag Labs Page Title is Empty!");
        return pageTitle;
    }

    public String getSwagLabsLogoText() {
        String logoText = locators.getByText("Swag Labs").textContent();
        validateNonEmptyText(logoText, "Logo Text is Empty on the Swag Labs Login Page!");
        return logoText;
    }

    public String getAcceptedUserNames() {
        String acceptedUserNames = String.join("",
                locators.getPageLocator("#login_credentials").allTextContents());
        validateNonEmptyText(acceptedUserNames, "Accepted User Name List is empty!");
        return acceptedUserNames;
    }

    public String getAllUserPasswords() {
        String allUserPasswords = String.join("",
                locators.getPageLocator(".login_password").allTextContents());
        validateNonEmptyText(allUserPasswords, "All User Passwords is empty!");
        return allUserPasswords;
    }

    public boolean isUserNameEntered(String userName) {
        Locator userNameInputBox = locators.getByPlaceholder("Username");
        if (!userNameInputBox.isVisible())
            return false;
        userNameInputBox.clear();
        userNameInputBox.fill(userName);
        return true;
    }

    public boolean isPasswordEntered(String password) {
        Locator passwordInputBox = locators.getByPlaceholder("Password");
        if (!passwordInputBox.isVisible())
            return false;
        passwordInputBox.clear();
        passwordInputBox.fill(password);
        return true;
    }

    public boolean isHomePageLanded() {
        Locator appLogo = locators.getPageLocator(".app_logo");
        return appLogo.isVisible() || appLogo.textContent().equalsIgnoreCase("Swag Labs");
    }

    public boolean isLoginSuccess() {
        validateAction(isUserNameEntered(WebPortalConstants.USERNAME), "User Name not entered!");
        validateAction(isPasswordEntered(WebPortalConstants.PASSWORD), "Password not entered!");
        Locator loginButton = locators.getByText("Login");
        validateAction(loginButton.isVisible(), "Login Button not clicked!");
        loginButton.press(KeyboardEvents.ENTER.getDescription());
        validateAction(isHomePageLanded(), "Login Failed for the username: " + WebPortalConstants.USERNAME);
        return true;
    }
}