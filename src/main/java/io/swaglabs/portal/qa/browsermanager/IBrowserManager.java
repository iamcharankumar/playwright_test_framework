package io.swaglabs.portal.qa.browsermanager;

public interface IBrowserManager<T> {

    T getBrowserPage();

    void destroyBrowserPage(T t);
}
