package io.swaglabs.portal.qa.locators;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import io.swaglabs.portal.qa.exceptions.SwagLabsException;
import io.swaglabs.portal.qa.exceptions.UtilsException;

public class Locators implements ILocators<Locator> {

    private final Page PAGE;

    public Locators(Page page) {
        this.PAGE = page;
    }

    @Override
    public Locator getPageLocator(String webLocator) {
        if (webLocator.isEmpty())
            throw new SwagLabsException("Web Locator is Empty!");
        return PAGE.locator(webLocator);
    }

    @Override
    public Locator getByText(String webLocatorText) {
        if (webLocatorText.isEmpty())
            throw new SwagLabsException("Web Locator Text is Empty!");
        return PAGE.getByText(webLocatorText);
    }

    @Override
    public Locator getByLabel(String labelText) {
        if (labelText.isEmpty())
            throw new UtilsException("Label Text is empty!");
        return PAGE.getByLabel(labelText);
    }

    @Override
    public Locator getByRole(AriaRole role, Page.GetByRoleOptions options) {
        if (role == null || options == null)
            throw new UtilsException("Role or Options is null!");
        return PAGE.getByRole(role, options);
    }

    @Override
    public Locator getByPlaceholder(String placeholderText) {
        if (placeholderText.isEmpty())
            throw new UtilsException("Placeholder text is empty!");
        return PAGE.getByPlaceholder(placeholderText);
    }
}