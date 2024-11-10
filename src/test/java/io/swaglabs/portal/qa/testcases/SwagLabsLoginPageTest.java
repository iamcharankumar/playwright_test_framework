package io.swaglabs.portal.qa.testcases;

import io.swaglabs.portal.qa.constants.TestGroups;
import io.swaglabs.portal.qa.dataprovider.SwagLabsDataProvider;
import io.swaglabs.portal.qa.module.SwagLabsPortal;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

@Slf4j
public class SwagLabsLoginPageTest extends SwagLabsTestBase {

    @Override
    @BeforeMethod(alwaysRun = true)
    public void setUpSwagLabsPortal(Method method) {
        log.info("Thread id in BeforeMethod for the test method : {} is {}.", method.getName(), Thread.currentThread().getId());
        SWAG_LABS_PORTAL.set(new SwagLabsPortal(page.get()));
        SWAG_LABS_PORTAL.get().visit();
    }

    @Test(description = "To verify, the login page title.", groups = {TestGroups.SWAG_LABS_SMOKE})
    public void testLoginPageTitle() {
        String actualLoginPageTitle = SWAG_LABS_PORTAL.get().LOGIN_PAGE.getSwagLabsPageTitle();
        Assert.assertEquals(actualLoginPageTitle, "Swag Labs", "Incorrect Login Page Title!");
        log.info("Verified the Login Page title: {}", actualLoginPageTitle);
    }

    @Test(description = "To verify, the login page header text.", groups = {TestGroups.SWAG_LABS_SMOKE})
    public void testLoginPageHeaderText() {
        String actualLoginPageLogoText = SWAG_LABS_PORTAL.get().LOGIN_PAGE.getSwagLabsLogoText();
        Assert.assertEquals(actualLoginPageLogoText, "Swag Labs", "Incorrect Login Page Header Text!");
        log.info("Verified the Login Page Header Text {} ", actualLoginPageLogoText);
    }

    @Test(description = "To verify, all the accepted usernames text present in the Login Page.",
            dataProvider = "accepted-usernames", dataProviderClass = SwagLabsDataProvider.class,
            groups = {TestGroups.SWAG_LABS_SMOKE})
    public void testLoginPageAllAcceptedUserNamesText(String expectedUserNames) {
        String actualUserNames = SWAG_LABS_PORTAL.get().LOGIN_PAGE.getAcceptedUserNames();
        Assert.assertEquals(actualUserNames, expectedUserNames, "Accepted User Names Mismatched!");
        log.info("Verified the Accepted User Names List: {}", actualUserNames);
    }

    @Test(description = "To verify, all the usernames' password text present in the Login Page.",
            dataProvider = "all-passwords", dataProviderClass = SwagLabsDataProvider.class,
            groups = {TestGroups.SWAG_LABS_SMOKE})
    public void testLoginPageAllUserPasswords(String expectedAllUserPasswords) {
        String actualAllUserPasswords = SWAG_LABS_PORTAL.get().LOGIN_PAGE.getAllUserPasswords();
        Assert.assertEquals(actualAllUserPasswords, expectedAllUserPasswords, "All User Passwords mismatched!");
        log.info("Verified the All User Passwords: {}", actualAllUserPasswords);
    }

    @Test(description = "To verify, the landing page as Home Page from Login Page.",
            groups = {TestGroups.SWAG_LABS_SMOKE, TestGroups.SWAG_LABS_UNIT})
    public void testSuccessfulLogin() {
        boolean isLoginSuccessful = SWAG_LABS_PORTAL.get().LOGIN_PAGE.isLoginSuccess();
        Assert.assertTrue(isLoginSuccessful, "Swag Labs Login Failed!");
        log.info("Verified the Swag Labs Successful Login status: {}", isLoginSuccessful);
    }
}