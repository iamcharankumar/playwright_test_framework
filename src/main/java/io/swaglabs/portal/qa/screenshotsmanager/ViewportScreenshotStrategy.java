package io.swaglabs.portal.qa.screenshotsmanager;

import com.microsoft.playwright.Page;
import lombok.RequiredArgsConstructor;

import java.nio.file.Paths;

@RequiredArgsConstructor
public class ViewportScreenshotStrategy implements ScreenshotStrategy {

    private final Page PAGE;

    @Override
    public void capture(String filePath) {
        PAGE.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(filePath)).setFullPage(false));
    }
}