package io.swaglabs.portal.qa.commons;

import com.microsoft.playwright.Page;

public abstract class WebBasePage {

    protected Page basePage;

    protected WebBasePage(Page basePage) {
        this.basePage = basePage;
    }
}
