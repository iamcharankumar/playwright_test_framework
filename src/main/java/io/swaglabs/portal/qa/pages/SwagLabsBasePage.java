package io.swaglabs.portal.qa.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.swaglabs.portal.qa.commons.WebBasePage;
import io.swaglabs.portal.qa.exceptions.SwagLabsException;

public class SwagLabsBasePage extends WebBasePage {

    public SwagLabsBasePage(Page basePage) {
        super(basePage);
    }

    public Locator getPageLocator(String webLocator) {
        if (webLocator.isEmpty())
            throw new SwagLabsException("Web Locator is Empty!");
        return basePage.locator(webLocator);
    }

    public Locator getByText(String webLocatorText) {
        if (webLocatorText.isEmpty())
            throw new SwagLabsException("Web Locator Text is Empty!");
        return basePage.getByText(webLocatorText);
    }
}