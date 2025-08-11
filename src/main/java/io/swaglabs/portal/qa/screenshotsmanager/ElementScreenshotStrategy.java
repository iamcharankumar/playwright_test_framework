package io.swaglabs.portal.qa.screenshotsmanager;

import com.microsoft.playwright.Locator;
import lombok.RequiredArgsConstructor;

import java.nio.file.Paths;

@RequiredArgsConstructor
public class ElementScreenshotStrategy implements ScreenshotStrategy {

    private final Locator LOCATOR;

    @Override
    public void capture(String filePath) {
        LOCATOR.screenshot(new Locator.ScreenshotOptions().setPath(Paths.get(filePath)));
    }
}