package io.swaglabs.portal.qa.browsermanager;

import com.microsoft.playwright.Playwright;

public interface IBrowserManager<T> {

    T getBrowserPage(Playwright playwright);

    void destroyBrowserPage(T t);
}