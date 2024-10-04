package io.swaglabs.portal.qa.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.swaglabs.portal.qa.constants.KeyboardEvents;
import io.swaglabs.portal.qa.exceptions.SwagLabsException;
import io.swaglabs.portal.qa.utils.WebConfigLoader;

public class SwagLabsLoginPage extends SwagLabsBasePage {

    public SwagLabsLoginPage(Page basePage) {
        super(basePage);
    }

    public String getSwagLabsPageTitle() {
        String pageTitle = basePage.title();
        if (pageTitle.isEmpty())
            throw new SwagLabsException("Swag Labs Page Title is Empty!");
        return pageTitle;
    }

    public String getSwagLabsLogoText() {
        String logoText = locators.getByText("Swag Labs").textContent();
        if (logoText.isEmpty())
            throw new SwagLabsException("Logo Text is Empty on the Swag Labs Login Page!");
        return logoText;
    }

    public String getAcceptedUserNames() {
        String acceptedUserNames = String.join("",
                locators.getPageLocator("#login_credentials").allTextContents());
        if (acceptedUserNames.isEmpty())
            throw new SwagLabsException("Accepted User Name List is empty!");
        return acceptedUserNames;
    }

    public String getAllUserPasswords() {
        String allUserPasswords = String.join("",
                locators.getPageLocator(".login_password").allTextContents());
        if (allUserPasswords.isEmpty())
            throw new SwagLabsException("All User Passwords is empty!");
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
        WebConfigLoader configLoader = WebConfigLoader.getInstance();
        String userName = configLoader.getSwagLabsUserName();
        String password = configLoader.getSwagLabsPassword();
        if (!isUserNameEntered(userName))
            throw new SwagLabsException("User Name not entered!");
        if (!isPasswordEntered(password))
            throw new SwagLabsException("Password not entered!");
        Locator loginButton = locators.getByText("Login");
        if (!loginButton.isVisible())
            throw new SwagLabsException("Login Button not clicked!");
        loginButton.press(KeyboardEvents.ENTER.getDescription());
        if (!isHomePageLanded())
            throw new SwagLabsException("Login Failed for the username: " + userName);
        return true;
    }
}