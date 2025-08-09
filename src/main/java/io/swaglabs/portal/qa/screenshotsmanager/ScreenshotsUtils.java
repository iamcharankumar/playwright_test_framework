package io.swaglabs.portal.qa.screenshotsmanager;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.swaglabs.portal.qa.constants.WebPortalConstants;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ScreenshotsUtils {

    private static final String ELEMENT_SCREENSHOT_FILE_LOCATION = WebPortalConstants.SCREENSHOT_FILE_LOCATION
            + "/elements/" + WebPortalConstants.BROWSER + "_" + WebPortalConstants.RUN_MODE + "_Element_";
    private static ScreenshotsUtils instance;

    public static ScreenshotsUtils getInstance() {
        if (instance == null) {
            synchronized (ScreenshotsUtils.class) {
                if (instance == null) {
                    instance = new ScreenshotsUtils();
                }
            }
        }
        return instance;
    }

    public ScreenshotContext getFullPageScreenshotContext() {
        return new ScreenshotContext(new FullPageScreenshotStrategy());
    }

    public ScreenshotContext getElementScreenshotContext(Locator locator) {
        Objects.requireNonNull(locator, "Locator cannot be null for taking element screenshot");
        return new ScreenshotContext(new ElementScreenshotStrategy(locator));
    }

    public void takeElementScreenshot(Page page, Locator locator, String fileName) {
        getElementScreenshotContext(locator).captureScreenshot(page,
                ELEMENT_SCREENSHOT_FILE_LOCATION + fileName + WebPortalConstants.IMAGE_FORMAT);
    }
}