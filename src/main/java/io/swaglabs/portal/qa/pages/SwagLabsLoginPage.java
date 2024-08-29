package io.swaglabs.portal.qa.pages;

import com.microsoft.playwright.Page;
import io.swaglabs.portal.qa.constants.WebLocatorConstants;
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
        String logoText = getByText(WebLocatorConstants.LOGIN_LOGO_TEXT).textContent();
        if (logoText.isEmpty())
            throw new SwagLabsException("Logo Text is Empty on the Swag Labs Login Page!");
        return logoText;
    }

    public String getAcceptedUserNames() {
        String acceptedUserNames = String.join("",
                getPageLocator(WebLocatorConstants.ACCEPTED_USER_NAME_LIST).allTextContents());
        if (acceptedUserNames.isEmpty())
            throw new SwagLabsException("Accepted User Name List is empty!");
        return acceptedUserNames;
    }

    public String getAllUserPasswords() {
        String allUserPasswords = String.join("",
                getPageLocator(WebLocatorConstants.ALL_USER_PASSWORDS).allTextContents());
        if (allUserPasswords.isEmpty())
            throw new SwagLabsException("All User Passwords is empty!");
        return allUserPasswords;
    }

    public boolean isLoginSuccess() {
        WebConfigLoader configLoader = WebConfigLoader.getInstance();
        String userName = configLoader.getSwagLabsUserName();
        String password = configLoader.getSwagLabsPassword();
        getPageLocator(WebLocatorConstants.USER_NAME_INPUT).click();
        basePage.keyboard().type(userName);
        getPageLocator(WebLocatorConstants.PASSWORD_INPUT).click();
        basePage.keyboard().type(password);
        getPageLocator(WebLocatorConstants.LOGIN_BUTTON).click();
        String appLogoText = getPageLocator(WebLocatorConstants.HOME_PAGE_HEADER_TEXT).textContent();
        if (appLogoText.isEmpty())
            throw new SwagLabsException("Login Failed for the username: " + userName);
        return appLogoText.equalsIgnoreCase(WebLocatorConstants.SWAG_LABS_APP_LOGO_TEXT);
    }
}