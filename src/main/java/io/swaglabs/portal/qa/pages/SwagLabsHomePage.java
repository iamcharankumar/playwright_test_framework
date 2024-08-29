package io.swaglabs.portal.qa.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.swaglabs.portal.qa.constants.WebLocatorConstants;
import io.swaglabs.portal.qa.exceptions.SwagLabsException;

import java.util.List;

public class SwagLabsHomePage extends SwagLabsBasePage {

    public SwagLabsHomePage(Page basePage) {
        super(basePage);
    }

    public String getProductHeaderText() {
        String productHeaderText = getByText(WebLocatorConstants.PRODUCTS_TEXT).textContent();
        if (productHeaderText.isEmpty())
            throw new SwagLabsException("Products Text not found in Home Page!");
        return productHeaderText;
    }

    public boolean isShoppingCartButtonEnabled() {
        return getPageLocator(WebLocatorConstants.SHOPPING_CART_BUTTON).isEnabled();
    }

    public boolean isHamburgerButtonEnabled() {
        return getPageLocator(WebLocatorConstants.HAMBURGER_MENU_BUTTON).isEnabled();
    }

    public String getAllItemsText() {
        getPageLocator(WebLocatorConstants.HAMBURGER_MENU_BUTTON).click();
        String hamburgerMenuList = String.join("",
                getPageLocator(WebLocatorConstants.HAMBURGER_MENU_LIST).allTextContents());
        if (hamburgerMenuList.isEmpty())
            throw new SwagLabsException("Hamburger Menu List is empty!");
        return hamburgerMenuList;
    }

    public int addAllItemsToShoppingCart() {
        getPageLocator(WebLocatorConstants.ADD_TO_CART_BACKPACK).click();
        getPageLocator(WebLocatorConstants.ADD_TO_CART_BIKE_LIGHT).click();
        getPageLocator(WebLocatorConstants.ADD_TO_CART_BOLT_T_SHIRT).click();
        getPageLocator(WebLocatorConstants.ADD_TO_CART_FLEECE_JACKET).click();
        getPageLocator(WebLocatorConstants.ADD_TO_CART_ONE_SIZE).click();
        getPageLocator(WebLocatorConstants.ADD_TO_CART_RED_T_SHIRT).click();
        String shoppingCartBadgeText = getPageLocator(WebLocatorConstants.SHOPPING_CART_BADGE).textContent();
        return Integer.parseInt(shoppingCartBadgeText);
    }

    public boolean isProductSelected(String productName) {
        List<Locator> productNameList = getPageLocator(WebLocatorConstants.INVENTORY_ITEM_NAME).all();
        Locator product = productNameList.stream().filter(e -> e.textContent().equalsIgnoreCase(productName))
                .findFirst().orElse(null);
        if (product != null && product.isEnabled()) {
            product.click();
            return true;
        }
        return false;
    }
}