package io.swaglabs.portal.qa.browsermanager;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;
import io.swaglabs.portal.qa.constants.BrowserName;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ChromeBrowser implements IBrowser {

    private boolean isHeadless;

    @Override
    public Page createWebBrowserPage() {
        ThreadSafeBrowserFactory.initialize(BrowserName.CHROME.getBrowserType(), isHeadless);
        Browser chromeBrowser = ThreadSafeBrowserFactory.getBrowser();
        chromeBrowser.newContext(new Browser.NewContextOptions().setScreenSize(null));
        return chromeBrowser.newPage();
    }
}
