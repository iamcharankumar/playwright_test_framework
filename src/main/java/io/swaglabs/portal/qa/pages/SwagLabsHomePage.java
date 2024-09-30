package io.swaglabs.portal.qa.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
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
        return locators.getPageLocator(".bm-burger-button").isEnabled();
    }

    public String getAllItemsText() {
        locators.getPageLocator(".bm-burger-button").click();
        String hamburgerMenuList = String.join("", locators.getPageLocator(".bm-menu").allTextContents());
        if (hamburgerMenuList.isEmpty())
            throw new SwagLabsException("Hamburger Menu List is empty!");
        return hamburgerMenuList;
    }

    public int addAllItemsToShoppingCart() {
        locators.getPageLocator("#add-to-cart-sauce-labs-backpack").click();
        locators.getPageLocator("#add-to-cart-sauce-labs-bike-light").click();
        locators.getPageLocator("#add-to-cart-sauce-labs-bolt-t-shirt").click();
        locators.getPageLocator("#add-to-cart-sauce-labs-fleece-jacket").click();
        locators.getPageLocator("#add-to-cart-sauce-labs-onesie").click();
        locators.getPageLocator("button[id='add-to-cart-test.allthethings()-t-shirt-(red)']").click();
        String shoppingCartBadgeText = locators.getPageLocator(".shopping_cart_badge").textContent();
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