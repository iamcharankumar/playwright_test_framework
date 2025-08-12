package io.swaglabs.portal.qa.utils;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.swaglabs.portal.qa.constants.WebPortalConstants;
import io.swaglabs.portal.qa.screenshotsmanager.ElementScreenshotStrategy;
import io.swaglabs.portal.qa.screenshotsmanager.FullPageScreenshotStrategy;
import io.swaglabs.portal.qa.screenshotsmanager.ScreenshotContext;
import io.swaglabs.portal.qa.screenshotsmanager.ViewportScreenshotStrategy;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ScreenshotsUtils {

    private static ScreenshotsUtils instance;
    private static final String ELEMENT_SCREENSHOT_FILE_LOCATION = WebPortalConstants.SCREENSHOT_FILE_LOCATION
            + "/elements/" + WebPortalConstants.BROWSER + "_" + WebPortalConstants.RUN_MODE + "_Element_";

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

    public ScreenshotContext takeFullPageScreenshotContext(Page page) {
        Objects.requireNonNull(page, "Page cannot be null.");
        return new ScreenshotContext(new FullPageScreenshotStrategy(page));
    }

    public ScreenshotContext takeViewportScreenshotContext(Page page) {
        Objects.requireNonNull(page, "Page cannot be null.");
        return new ScreenshotContext(new ViewportScreenshotStrategy(page));
    }

    public void takeElementScreenshot(Locator locator, String fileName) {
        Objects.requireNonNull(locator, "Locator cannot be null.");
        Objects.requireNonNull(fileName, "File name cannot be null.");
        String filePath = ELEMENT_SCREENSHOT_FILE_LOCATION + fileName + WebPortalConstants.IMAGE_FORMAT;
        new ScreenshotContext(new ElementScreenshotStrategy(locator)).captureScreenshot(filePath);
    }
}