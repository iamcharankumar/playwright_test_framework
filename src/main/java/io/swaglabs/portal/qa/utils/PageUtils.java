package io.swaglabs.portal.qa.utils;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import io.swaglabs.portal.qa.exceptions.UtilsException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PageUtils {

    public static Page getPageByText(Page basePage, String selectorsText) {
        if (basePage == null || selectorsText.isEmpty())
            throw new UtilsException("");
        return basePage.context().waitForPage(() -> basePage.getByText(selectorsText));
    }

    public static Locator locatePageElementByRole(Page newPage, AriaRole role, Page.GetByRoleOptions options) {
        if (newPage == null || role == null || options == null)
            throw new UtilsException("Unable to Locate element by Role!");
        return newPage.getByRole(role, options);
    }
}