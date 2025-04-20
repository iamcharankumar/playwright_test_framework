package io.swaglabs.portal.qa.screenshotsmanager;

import com.microsoft.playwright.Page;

public interface ScreenshotStrategy {

    void capture(Page page, String filePath);
}