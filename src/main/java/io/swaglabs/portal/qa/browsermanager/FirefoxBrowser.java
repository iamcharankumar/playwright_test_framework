package io.swaglabs.portal.qa.browsermanager;

import com.microsoft.playwright.Page;
import io.swaglabs.portal.qa.constants.BrowserName;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FirefoxBrowser implements IBrowser {

    private boolean isHeadless;

    @Override
    public Page createWebBrowserPage() {
        ThreadSafeBrowserFactory.initialize(BrowserName.FIREFOX.getBrowserType(), isHeadless);
        return ThreadSafeBrowserFactory.getBrowser().newPage();
    }
}
