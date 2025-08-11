package io.swaglabs.portal.qa.screenshotsmanager;

import com.microsoft.playwright.Locator;
import lombok.RequiredArgsConstructor;

import java.nio.file.Paths;

@RequiredArgsConstructor
public class ElementScreenshotStrategy implements ScreenshotStrategy {

    private final Locator locator;

    @Override
    public void capture(String filePath) {
        locator.screenshot(new Locator.ScreenshotOptions().setPath(Paths.get(filePath)));
    }
}