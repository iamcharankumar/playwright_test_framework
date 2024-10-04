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
        if (productHeaderText.isEmpty())
            throw new SwagLabsException("Products Text not found in Home Page!");
        return productHeaderText;
    }

    public boolean isShoppingCartButtonEnabled() {
        return locators.getPageLocator(".shopping_cart_link").isEnabled();
    }

    public boolean isHamburgerButtonEnabled() {
        return locators.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Open Menu")).isEnabled();
    }

    public String getAllItemsText() {
        Locator openMenuButton = locators.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Open Menu"));
        if (!openMenuButton.isVisible())
            throw new SwagLabsException("Hamburger Menu Button not visible!");
        openMenuButton.click();
        String hamburgerMenuList = String.join("", locators.getPageLocator(".bm-menu").allTextContents());
        if (hamburgerMenuList.isEmpty())
            throw new SwagLabsException("Hamburger Menu List is empty!");
        return hamburgerMenuList;
    }

    public int addAllItemsToShoppingCart() {
        List<String> itemLocators = List.of("#add-to-cart-sauce-labs-backpack",
                "#add-to-cart-sauce-labs-bike-light", "#add-to-cart-sauce-labs-bolt-t-shirt",
                "#add-to-cart-sauce-labs-fleece-jacket", "#add-to-cart-sauce-labs-onesie",
                "button[id='add-to-cart-test.allthethings()-t-shirt-(red)']");
        itemLocators.forEach(item -> {
            Locator shoppingItem = locators.getPageLocator(item);
            if (!shoppingItem.isVisible())
                throw new SwagLabsException("Shopping item: " + item + " not visible!");
            shoppingItem.click();
        });
        Locator shoppingCartBadge = locators.getPageLocator(".shopping_cart_badge");
        if (!shoppingCartBadge.isVisible())
            throw new SwagLabsException("Shopping Cart Badge is not visible!");
        return validateAndParseShoppingCartBadge(shoppingCartBadge);
    }

    private int validateAndParseShoppingCartBadge(Locator shoppingCartBadge) throws SwagLabsException {
        String shoppingCartBadgeText = shoppingCartBadge.textContent();
        if (shoppingCartBadgeText.isEmpty())
            throw new SwagLabsException("Shopping Cart Badge text is empty!");
        if (!Character.isDigit(shoppingCartBadgeText.charAt(0)))
            throw new SwagLabsException("Shopping Cart Badge text is not a number!");
        return Integer.parseInt(shoppingCartBadgeText);
    }

    public boolean isProductSelected(String productName) {
        List<Locator> productNameList = locators.getPageLocator(".inventory_item_name").all();
        Locator product = productNameList.stream()
                .filter(productItem -> productItem.textContent().equalsIgnoreCase(productName))
                .findFirst().orElseThrow(() -> new SwagLabsException("Product Name not found!"));
        if (!product.isEnabled())
            throw new SwagLabsException("Product is not enabled!");
        product.click();
        return true;
    }
}