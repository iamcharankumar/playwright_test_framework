package io.swaglabs.portal.qa.screenshotsmanager;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ScreenshotContext {

    private final ScreenshotStrategy screenshotStrategy;

    public void captureScreenshot(String filePath) {
        screenshotStrategy.capture(filePath);
    }
}