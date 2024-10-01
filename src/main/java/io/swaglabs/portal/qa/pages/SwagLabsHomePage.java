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
        Locator backpack = locators.getPageLocator("#add-to-cart-sauce-labs-backpack");
        if (!backpack.isVisible())
            throw new SwagLabsException("Backpack not added to cart!");
        backpack.click();
        Locator bikeLight = locators.getPageLocator("#add-to-cart-sauce-labs-bike-light");
        if (!bikeLight.isVisible())
            throw new SwagLabsException("Bike Light not added to cart!");
        bikeLight.click();
        Locator boltTshirt = locators.getPageLocator("#add-to-cart-sauce-labs-bolt-t-shirt");
        if (!boltTshirt.isVisible())
            throw new SwagLabsException("Bolt T-shirt not added to cart!");
        boltTshirt.click();
        Locator fleeceJacket = locators.getPageLocator("#add-to-cart-sauce-labs-fleece-jacket");
        if (!fleeceJacket.isVisible())
            throw new SwagLabsException("Fleece Jacket not visible!");
        fleeceJacket.click();
        Locator onesize = locators.getPageLocator("#add-to-cart-sauce-labs-onesie");
        if (!onesize.isVisible())
            throw new SwagLabsException("One size is not visible!");
        onesize.click();
        Locator redTshirt = locators.getPageLocator("button[id='add-to-cart-test.allthethings()-t-shirt-(red)']");
        if (!redTshirt.isVisible())
            throw new SwagLabsException("Red T-shirt is not visible!");
        redTshirt.click();
        Locator shoppingCartBadge = locators.getPageLocator(".shopping_cart_badge");
        if (!shoppingCartBadge.isVisible())
            throw new SwagLabsException("Shopping Cart Badge is not visible!");
        String shoppingCartBadgeText = shoppingCartBadge.textContent();
        if (shoppingCartBadgeText.isEmpty())
            throw new SwagLabsException("Shopping Cart Badge text is empty!");
        if (!Character.isDigit(shoppingCartBadgeText.charAt(0)))
            throw new SwagLabsException("Shopping Cart Badge text is not a number!");
        return Integer.parseInt(shoppingCartBadgeText);
    }

    public boolean isShoppingCartItemClicked(String webLocatorText) {
        Locator cartItem = locators.getPageLocator(webLocatorText);
        if (!cartItem.isVisible())
            throw new SwagLabsException("Backpack not added to cart!");
        cartItem.click();
        return true;
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