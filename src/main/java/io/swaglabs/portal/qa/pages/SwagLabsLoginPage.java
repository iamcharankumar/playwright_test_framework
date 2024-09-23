package io.swaglabs.portal.qa.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.swaglabs.portal.qa.exceptions.SwagLabsException;
import io.swaglabs.portal.qa.utils.WebConfigLoader;

public class SwagLabsLoginPage extends SwagLabsBasePage {

    public SwagLabsLoginPage(Page basePage) {
        super(basePage);
    }

    private static final String LOGIN_LOGO_TEXT = "Swag Labs";
    private static final String ACCEPTED_USER_NAME_LIST = "#login_credentials";
    private static final String ALL_USER_PASSWORDS = ".login_password";
    private static final String USER_NAME_INPUT = "#user-name";
    private static final String PASSWORD_INPUT = "#password";
    private static final String LOGIN_BUTTON = "#login-button";
    private static final String SWAG_LABS_APP_LOGO_TEXT = "Swag Labs";
    private static final String HOME_PAGE_HEADER_TEXT = ".app_logo";

    public String getSwagLabsPageTitle() {
        String pageTitle = basePage.title();
        if (pageTitle.isEmpty())
            throw new SwagLabsException("Swag Labs Page Title is Empty!");
        return pageTitle;
    }

    public String getSwagLabsLogoText() {
        String logoText = locators.getByText(LOGIN_LOGO_TEXT).textContent();
        if (logoText.isEmpty())
            throw new SwagLabsException("Logo Text is Empty on the Swag Labs Login Page!");
        return logoText;
    }

    public String getAcceptedUserNames() {
        String acceptedUserNames = String.join("",
                locators.getPageLocator(ACCEPTED_USER_NAME_LIST).allTextContents());
        if (acceptedUserNames.isEmpty())
            throw new SwagLabsException("Accepted User Name List is empty!");
        return acceptedUserNames;
    }

    public String getAllUserPasswords() {
        String allUserPasswords = String.join("",
                locators.getPageLocator(ALL_USER_PASSWORDS).allTextContents());
        if (allUserPasswords.isEmpty())
            throw new SwagLabsException("All User Passwords is empty!");
        return allUserPasswords;
    }

    public boolean isLoginSuccess() {
        WebConfigLoader configLoader = WebConfigLoader.getInstance();
        String userName = configLoader.getSwagLabsUserName();
        String password = configLoader.getSwagLabsPassword();
        Locator userNameInputBox = locators.getPageLocator(USER_NAME_INPUT);
        userNameInputBox.click();
        userNameInputBox.clear();
        userNameInputBox.fill(userName);
        Locator passwordInputBox = locators.getPageLocator(PASSWORD_INPUT);
        passwordInputBox.click();
        passwordInputBox.clear();
        passwordInputBox.fill(password);
        locators.getPageLocator(LOGIN_BUTTON).click();
        String appLogoText = locators.getPageLocator(HOME_PAGE_HEADER_TEXT).textContent();
        if (appLogoText.isEmpty())
            throw new SwagLabsException("Login Failed for the username: " + userName);
        return appLogoText.equalsIgnoreCase(SWAG_LABS_APP_LOGO_TEXT);
    }
}