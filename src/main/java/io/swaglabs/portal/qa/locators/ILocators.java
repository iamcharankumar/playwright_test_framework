package io.swaglabs.portal.qa.locators;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public interface ILocators<T> {

    public T getPageLocator(String webLocator);

    public T getByText(String webLocatorText);

    public T getByLabel(String labelText);

    public T getByRole(AriaRole role, Page.GetByRoleOptions options);
}