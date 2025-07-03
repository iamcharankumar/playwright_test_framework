package io.swaglabs.portal.qa.waits;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

public class PlaywrightWait implements IPlaywrightWait {

    private final Page PAGE;

    public PlaywrightWait(Page page) {
        this.PAGE = page;
    }

    @Override
    public void waitForElementToBeVisible(String locator) {
        PAGE.waitForSelector(locator, new Page.WaitForSelectorOptions().setState(WaitForSelectorState.VISIBLE));
    }

    @Override
    public void waitForElementToBeHidden(String locator) {
        PAGE.waitForSelector(locator, new Page.WaitForSelectorOptions().setState(WaitForSelectorState.HIDDEN));
    }

    @Override
    public void waitForElementToBeAttached(String locator) {
        PAGE.waitForSelector(locator, new Page.WaitForSelectorOptions().setState(WaitForSelectorState.ATTACHED));
    }

    @Override
    public void waitForElementToBeDetached(String locator) {
        PAGE.waitForSelector(locator, new Page.WaitForSelectorOptions().setState(WaitForSelectorState.DETACHED));
    }
}