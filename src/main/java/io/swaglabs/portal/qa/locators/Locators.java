package io.swaglabs.portal.qa.locators;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

import java.util.Objects;

public class Locators implements ILocators<Locator> {

    private final Page PAGE;

    public Locators(Page page) {
        this.PAGE = page;
    }

    @Override
    public Locator getPageLocator(String webLocator) {
        Objects.requireNonNull(webLocator, "Web Locator cannot be null or empty!");
        return PAGE.locator(webLocator);
    }

    @Override
    public Locator getByText(String webLocatorText) {
        Objects.requireNonNull(webLocatorText, "Web Locator Text cannot be null or empty!");
        return PAGE.getByText(webLocatorText);
    }

    @Override
    public Locator getByLabel(String labelText) {
        Objects.requireNonNull(labelText, "Label Text cannot be null or empty!");
        return PAGE.getByLabel(labelText);
    }

    @Override
    public Locator getByRole(AriaRole role, Page.GetByRoleOptions options) {
        Objects.requireNonNull(role, "Role cannot be null!");
        Objects.requireNonNull(options, "Options cannot be null!");
        return PAGE.getByRole(role, options);
    }

    @Override
    public Locator getByPlaceholder(String placeholderText) {
        Objects.requireNonNull(placeholderText, "Placeholder text cannot be null or empty!");
        return PAGE.getByPlaceholder(placeholderText);
    }
}