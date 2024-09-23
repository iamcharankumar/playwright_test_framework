package io.swaglabs.portal.qa.testcases;

import io.swaglabs.portal.qa.commons.WebBaseTest;
import io.swaglabs.portal.qa.exceptions.SwagLabsException;
import io.swaglabs.portal.qa.module.SwagLabsPortal;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;

@Slf4j
public class SwagLabsTestBase extends WebBaseTest {

    protected static final ThreadLocal<SwagLabsPortal> SWAG_LABS_PORTAL = new ThreadLocal<>();

    @BeforeMethod(alwaysRun = true)
    public void setUpSwagLabsPortal(Method method) {
        log.info("Thread id in BeforeMethod for the test method : {} is {}.", method.getName(), Thread.currentThread().getId());
        SWAG_LABS_PORTAL.set(new SwagLabsPortal(page.get()));
        SWAG_LABS_PORTAL.get().visit();
        boolean isLoggedIn = SWAG_LABS_PORTAL.get().LOGIN_PAGE.isLoginSuccess();
        if (!isLoggedIn)
            throw new SwagLabsException("Swags Labs Portal Not Logged In!");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDownSwagLabsPortal(Method method) {
        log.info("Thread id in AfterMethod for the test method :{} is {}.", method.getName(), Thread.currentThread().getId());
        SWAG_LABS_PORTAL.remove();
    }
}