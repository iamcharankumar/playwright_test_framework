package io.swaglabs.portal.qa.commons;

import com.microsoft.playwright.Page;
import io.swaglabs.portal.qa.locators.Locators;

public abstract class WebBasePage {

    protected Page basePage;
    protected Locators locators;

    protected WebBasePage(Page basePage) {
        this.basePage = basePage;
        this.locators = new Locators(basePage);
    }
}