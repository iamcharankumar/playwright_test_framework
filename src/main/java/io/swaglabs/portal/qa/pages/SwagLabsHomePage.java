package io.swaglabs.portal.qa.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import io.swaglabs.portal.qa.exceptions.SwagLabsException;

import java.util.List;

public class SwagLabsHomePage extends SwagLabsBasePage {

    public SwagLabsHomePage(Page basePage) {
        super(basePage);
    }

    public String getProductHeaderText() {
        String productHeaderText = locators.getByText("Products").textContent();
        validateNonEmptyText(productHeaderText, "Products Text not found in Home Page!");
        return productHeaderText;
    }

    public boolean isShoppingCartButtonEnabled() {
        Locator shoppingCartLink = locators.getPageLocator(".shopping_cart_link");
        validateAction(shoppingCartLink.isEnabled(), "Shopping Cart Link is not enabled!");
        return shoppingCartLink.isEnabled();
    }

    public boolean isHamburgerButtonEnabled() {
        Locator hamburgerButton = locators.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Open Menu"));
        validateAction(hamburgerButton.isEnabled(), "Hamburger Button is not enabled!");
        return hamburgerButton.isEnabled();
    }

    public String getAllItemsText() {
        Locator openMenuButton = locators.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Open Menu"));
        clickElement(openMenuButton, "Hamburger Menu Button not clicked!");
        String hamburgerMenuList = String.join("", locators.getPageLocator(".bm-menu").allTextContents());
        validateNonEmptyText(hamburgerMenuList, "Hamburger Menu List is empty!");
        return hamburgerMenuList;
    }

    public int addAllItemsToShoppingCart() {
        List<String> itemLocators = List.of("#add-to-cart-sauce-labs-backpack",
                "#add-to-cart-sauce-labs-bike-light", "#add-to-cart-sauce-labs-bolt-t-shirt",
                "#add-to-cart-sauce-labs-fleece-jacket", "#add-to-cart-sauce-labs-onesie",
                "button[id='add-to-cart-test.allthethings()-t-shirt-(red)']");
        itemLocators.forEach(item -> {
            Locator shoppingItem = locators.getPageLocator(item);
            clickElement(shoppingItem, "Shopping item: " + item + " not visible!");
        });
        Locator shoppingCartBadge = locators.getPageLocator(".shopping_cart_badge");
        validateAction(shoppingCartBadge.isVisible(), "Shopping Cart Badge is not visible!");
        return validateAndParseShoppingCartBadge(shoppingCartBadge);
    }

    private int validateAndParseShoppingCartBadge(Locator shoppingCartBadge) {
        String shoppingCartBadgeText = shoppingCartBadge.textContent();
        validateNonEmptyText(shoppingCartBadgeText, "Shopping Cart Badge text is empty!");
        validateAction(Character.isDigit(shoppingCartBadgeText.charAt(0)), "Shopping Cart Badge text is not a number!");
        return Integer.parseInt(shoppingCartBadgeText);
    }

    public boolean isProductSelected(String productName) {
        List<Locator> productNameList = locators.getPageLocator(".inventory_item_name").all();
        Locator product = productNameList.stream()
                .filter(productItem -> productItem.textContent().equalsIgnoreCase(productName))
                .findFirst().orElseThrow(() -> new SwagLabsException("Product Name not found!"));
        clickElement(product, "Product Name is not clicked!");
        return true;
    }

    public boolean isLogoutSuccess() {
        Locator openMenuButton = locators.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Open Menu"));
        clickElement(openMenuButton, "Hamburger Menu Button not visible!");
        Locator logoutButton = locators.getPageLocator("#logout_sidebar_link");
        clickElement(logoutButton, "Logout button is not clicked!");
        return basePage.title().equalsIgnoreCase("Swag Labs");
    }
}