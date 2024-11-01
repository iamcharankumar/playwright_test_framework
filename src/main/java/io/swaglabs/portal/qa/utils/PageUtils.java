package io.swaglabs.portal.qa.utils;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import io.swaglabs.portal.qa.exceptions.UtilsException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PageUtils {

    public static Page getPageByText(Page basePage, String selectorsText) {
        Objects.requireNonNull(basePage, "Page cannot be null!");
        if (selectorsText.isEmpty())
            throw new UtilsException("Selector Text should not be empty!");
        return basePage.context().waitForPage(() -> basePage.getByText(selectorsText));
    }

    public static Locator locatePageElementByRole(Page newPage, AriaRole role, Page.GetByRoleOptions options) {
        Objects.requireNonNull(newPage, "Page cannot be null!");
        Objects.requireNonNull(role, "Role cannot be null!");
        Objects.requireNonNull(options, "Options cannot be null!");
        return newPage.getByRole(role, options);
    }
}