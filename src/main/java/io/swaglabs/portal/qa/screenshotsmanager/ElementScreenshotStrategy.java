package io.swaglabs.portal.qa.screenshotsmanager;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import lombok.AllArgsConstructor;

import java.nio.file.Paths;

@AllArgsConstructor
public class ElementScreenshotStrategy implements ScreenshotStrategy {

    private final Locator locator;

    @Override
    public void capture(Page page, String filePath) {
        locator.screenshot(new Locator.ScreenshotOptions().setPath(Paths.get(filePath)));
    }
}