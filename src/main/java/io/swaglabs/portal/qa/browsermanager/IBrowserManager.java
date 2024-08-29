package io.swaglabs.portal.qa.browsermanager;

import com.microsoft.playwright.Playwright;

public interface IBrowserManager<T> {
    public T getBrowserPage(Playwright playwright);

    public void destroyBrowserPage(T t);
}
