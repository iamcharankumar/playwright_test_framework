package io.swaglabs.portal.qa.browsermanager;

import com.microsoft.playwright.Page;

public interface IBrowser {

    Page createWebBrowserPage();
}
