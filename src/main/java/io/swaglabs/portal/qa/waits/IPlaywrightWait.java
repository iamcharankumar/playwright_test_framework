package io.swaglabs.portal.qa.waits;

public interface IPlaywrightWait {

    void waitForElementToBeVisible(String locator);

    void waitForElementToBeHidden(String locator);

    void waitForElementToBeAttached(String locator);

    void waitForElementToBeDetached(String locator);
}