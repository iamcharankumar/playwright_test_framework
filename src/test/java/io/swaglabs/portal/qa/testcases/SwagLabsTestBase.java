package io.swaglabs.portal.qa.testcases;

import io.swaglabs.portal.qa.commons.WebBaseTest;
import io.swaglabs.portal.qa.exceptions.WebPageException;
import io.swaglabs.portal.qa.module.SwagLabsPortal;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;

@Slf4j
public sealed class SwagLabsTestBase extends WebBaseTest permits SwagLabsE2ETest, SwagLabsHomePageTest, SwagLabsLoginPageTest {

    protected static final ThreadLocal<SwagLabsPortal> SWAG_LABS_PORTAL = new ThreadLocal<>();

    @BeforeMethod(alwaysRun = true)
    public void setUpSwagLabsPortal(Method method) {
        final long threadId = Thread.currentThread().getId();
        log.info("Thread {} starting method {}", threadId, method.getName());
        try {
            // 1. Initialize portal (thread-safe via ThreadLocal)
            SWAG_LABS_PORTAL.set(new SwagLabsPortal(PAGE.get()));
            // 2. Visit and login
            SWAG_LABS_PORTAL.get().visit();
            boolean isLoggedIn = SWAG_LABS_PORTAL.get().LOGIN_PAGE.isLoginSuccess();
            if (!isLoggedIn)
                throw new WebPageException("Swags Labs Portal Not Logged In!");
        } catch (Exception e) {
            log.error("Initialization failed for thread {}", threadId, e);
            cleanUpResources();
            throw e;
        }
    }

    @AfterMethod(alwaysRun = true)
    public void tearDownSwagLabsPortal(Method method) {
        final long threadId = Thread.currentThread().getId();
        log.info("Thread {} finishing method {}", threadId, method.getName());
        SWAG_LABS_PORTAL.remove();
    }

    private void cleanUpResources() {
        try {
            if (SWAG_LABS_PORTAL.get() != null)
                // Simple cleanup - just remove the ThreadLocal
                SWAG_LABS_PORTAL.remove();
        } catch (Exception e) {
            log.error("Thread {} cleanup error", Thread.currentThread().getId(), e);
        }
    }
}