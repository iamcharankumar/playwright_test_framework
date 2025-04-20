package io.swaglabs.portal.qa.screenshotsmanager;

import com.microsoft.playwright.Page;
import lombok.AllArgsConstructor;

import java.util.Objects;

@AllArgsConstructor
public class ScreenshotContext {

    private ScreenshotStrategy screenshotStrategy;

    public void captureScreenshot(Page page, String filePath) {
        Objects.requireNonNull(screenshotStrategy, "Screenshot strategy is not set.");
        screenshotStrategy.capture(page, filePath);
    }
}