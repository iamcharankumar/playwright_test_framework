package io.swaglabs.portal.qa.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import io.swaglabs.portal.qa.exceptions.WebPageException;
import io.swaglabs.portal.qa.utils.WebPageUtils;

import java.util.List;

public final class SwagLabsHomePage extends SwagLabsBasePage {

    public SwagLabsHomePage(Page basePage) {
        super(basePage);
    }

    public String getProductHeaderText() {
        return getTextContent(locators.getByText("Products"));
    }

    public boolean isShoppingCartButtonEnabled() {
        return locators.getPageLocator(".shopping_cart_link").isEnabled();
    }

    public boolean isHamburgerButtonEnabled() {
        return locators.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Open Menu")).isEnabled();
    }

    public String getAllItemsText() {
        Locator openMenuButton = locators.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Open Menu"));
        clickElement(openMenuButton);
        Locator hamburgerMenu = locators.getPageLocator(".bm-menu");
        WebPageUtils.clickUntilCondition(openMenuButton, hamburgerMenu::isVisible, 5);
        String hamburgerMenuList = String.join("", hamburgerMenu.allTextContents());
        validateNonEmptyText(hamburgerMenuList, "Hamburger Menu List is empty!");
        return hamburgerMenuList;
    }

    public int addAllItemsToShoppingCart() {
        List<String> itemLocators = List.of("#add-to-cart-sauce-labs-backpack",
                "#add-to-cart-sauce-labs-bike-light", "#add-to-cart-sauce-labs-bolt-t-shirt",
                "#add-to-cart-sauce-labs-fleece-jacket", "#add-to-cart-sauce-labs-onesie",
                "button[id='add-to-cart-test.allthethings()-t-shirt-(red)']");
        itemLocators.forEach(item -> clickElement(locators.getPageLocator(item)));
        return validateAndParseShoppingCartBadge(locators.getPageLocator(".shopping_cart_badge"));
    }

    private int validateAndParseShoppingCartBadge(Locator shoppingCartBadge) {
        String shoppingCartBadgeText = getTextContent(shoppingCartBadge);
        validateAction(Character.isDigit(shoppingCartBadgeText.charAt(0)), "Shopping Cart Badge text is not a number!");
        return Integer.parseInt(shoppingCartBadgeText);
    }

    public boolean isProductSelected(String productName) {
        Locator product = locators.getPageLocator(".inventory_item_name").all().stream()
                .filter(productItem -> getTextContent(productItem).equalsIgnoreCase(productName))
                .findFirst().orElseThrow(() -> new WebPageException("Product Name not found!"));
        clickElement(product);
        return true;
    }

    public boolean isLogoutSuccess() {
        clickElement(locators.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Open Menu")));
        clickElement(locators.getPageLocator("#logout_sidebar_link"));
        return basePage.title().equalsIgnoreCase("Swag Labs");
    }
}