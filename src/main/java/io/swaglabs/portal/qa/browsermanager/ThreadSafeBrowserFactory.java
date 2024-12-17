package io.swaglabs.portal.qa.browsermanager;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;
import io.swaglabs.portal.qa.constants.WebPortalConstants;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.function.Supplier;

/**
 * A utility class for creating thread-safe Playwright browsers with flexible configurations.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ThreadSafeBrowserFactory {

    private static final ThreadLocal<Playwright> PLAYWRIGHT = ThreadLocal.withInitial(Playwright::create);
    private static ThreadLocal<Browser> BROWSER;

    /**
     * Initializes the browser with the desired type and headless mode at runtime.
     *
     * @param browserType The type of browser to launch (e.g., "chromium", "firefox", "webkit").
     * @param isHeadless  Whether to launch the browser in headless mode.
     */
    public static void initialize(String browserType, boolean isHeadless) {
        BrowserType.LaunchOptions options = new BrowserType.LaunchOptions()
                .setHeadless(isHeadless)
                .setChannel(browserType)
                .setArgs(Collections.singletonList(WebPortalConstants.MAXIMIZE_WINDOW));
        switch (browserType.toLowerCase()) {
            case "chrome", "msedge":
                BROWSER = createBrowser(() -> PLAYWRIGHT.get().chromium(), options);
                break;
            case "firefox":
                BROWSER = createBrowser(() -> PLAYWRIGHT.get().firefox(), options);
                break;
            case "webkit":
                BROWSER = createBrowser(() -> PLAYWRIGHT.get().webkit(), options);
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser type: " + browserType);
        }
    }

    /**
     * Creates a thread-local browser instance for the desired browser type.
     *
     * @param browserTypeSupplier A supplier that specifies which browser type to initialize.
     * @param launchOptions       Configuration options for the browser launch.
     * @return A ThreadLocal instance of Browser.
     */
    private static ThreadLocal<Browser> createBrowser(Supplier<BrowserType> browserTypeSupplier, BrowserType.LaunchOptions launchOptions) {
        return ThreadLocal.withInitial(() -> browserTypeSupplier.get().launch(launchOptions));
    }

    /**
     * Provides the initialized browser instance.
     *
     * @return The current thread-local Browser instance.
     */
    public static Browser getBrowser() {
        if (BROWSER == null) {
            throw new IllegalStateException("Browser is not initialized. Call initialize() first.");
        }
        return BROWSER.get();
    }

    /**
     * Cleans up the resources used by the thread-local Playwright and browser instances.
     */
    public static void cleanup() {
        if (BROWSER != null && BROWSER.get() != null) {
            BROWSER.get().close();
            BROWSER.remove();
        }

        if (PLAYWRIGHT.get() != null) {
            PLAYWRIGHT.get().close();
            PLAYWRIGHT.remove();
        }
    }
}