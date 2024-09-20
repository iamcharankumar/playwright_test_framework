package io.swaglabs.portal.qa.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.swaglabs.portal.qa.exceptions.SwagLabsException;

import java.util.List;

public class SwagLabsHomePage extends SwagLabsBasePage {

    public SwagLabsHomePage(Page basePage) {
        super(basePage);
    }

    private static final String PRODUCTS_TEXT = "Products";
    private static final String SHOPPING_CART_BUTTON = ".shopping_cart_link";
    private static final String HAMBURGER_MENU_BUTTON = ".bm-burger-button";
    private static final String HAMBURGER_MENU_LIST = ".bm-menu";
    private static final String INVENTORY_ITEM_NAME = ".inventory_item_name";
    private static final String ADD_TO_CART_BACKPACK = "#add-to-cart-sauce-labs-backpack";
    private static final String ADD_TO_CART_BIKE_LIGHT = "#add-to-cart-sauce-labs-bike-light";
    private static final String ADD_TO_CART_BOLT_T_SHIRT = "#add-to-cart-sauce-labs-bolt-t-shirt";
    private static final String ADD_TO_CART_FLEECE_JACKET = "#add-to-cart-sauce-labs-fleece-jacket";
    private static final String ADD_TO_CART_ONE_SIZE = "#add-to-cart-sauce-labs-onesie";
    private static final String ADD_TO_CART_RED_T_SHIRT = "button[id='add-to-cart-test.allthethings()-t-shirt-(red)']";
    private static final String SHOPPING_CART_BADGE = ".shopping_cart_badge";

    public String getProductHeaderText() {
        String productHeaderText = locators.getByText(PRODUCTS_TEXT).textContent();
        if (productHeaderText.isEmpty())
            throw new SwagLabsException("Products Text not found in Home Page!");
        return productHeaderText;
    }

    public boolean isShoppingCartButtonEnabled() {
        return locators.getPageLocator(SHOPPING_CART_BUTTON).isEnabled();
    }

    public boolean isHamburgerButtonEnabled() {
        return locators.getPageLocator(HAMBURGER_MENU_BUTTON).isEnabled();
    }

    public String getAllItemsText() {
        locators.getPageLocator(HAMBURGER_MENU_BUTTON).click();
        String hamburgerMenuList = String.join("", locators.getPageLocator(HAMBURGER_MENU_LIST).allTextContents());
        if (hamburgerMenuList.isEmpty())
            throw new SwagLabsException("Hamburger Menu List is empty!");
        return hamburgerMenuList;
    }

    public int addAllItemsToShoppingCart() {
        locators.getPageLocator(ADD_TO_CART_BACKPACK).click();
        locators.getPageLocator(ADD_TO_CART_BIKE_LIGHT).click();
        locators.getPageLocator(ADD_TO_CART_BOLT_T_SHIRT).click();
        locators.getPageLocator(ADD_TO_CART_FLEECE_JACKET).click();
        locators.getPageLocator(ADD_TO_CART_ONE_SIZE).click();
        locators.getPageLocator(ADD_TO_CART_RED_T_SHIRT).click();
        String shoppingCartBadgeText = locators.getPageLocator(SHOPPING_CART_BADGE).textContent();
        return Integer.parseInt(shoppingCartBadgeText);
    }

    public boolean isProductSelected(String productName) {
        List<Locator> productNameList = locators.getPageLocator(INVENTORY_ITEM_NAME).all();
        Locator product = productNameList.stream().filter(e -> e.textContent().equalsIgnoreCase(productName))
                .findFirst().orElse(null);
        if (product != null && product.isEnabled()) {
            product.click();
            return true;
        }
        return false;
    }
}