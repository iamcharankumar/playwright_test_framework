package io.swaglabs.portal.qa.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public final class SwagLabsProductPage extends SwagLabsBasePage {

    public SwagLabsProductPage(Page basePage) {
        super(basePage);
    }

    public String getProductNameText() {
        return getTextContent(locators.getPageLocator(".inventory_details_name.large_size"));
    }

    public String getProductPriceText() {
        Locator productPrice = locators.getPageLocator(".inventory_details_price");
        String productPriceText = getTextContent(productPrice);
        takeElementScreenshot(productPrice, productPriceText);
        return productPriceText;
    }

    public String getProductDescription(String productDescription) {
        return getTextContent(locators.getByText(productDescription));
    }

    public boolean isProductAddedToCart() {
        clickElement(locators.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Add to cart")));
        return true;
    }

    public boolean isShoppingCartClicked() {
        Locator shoppingCart = locators.getPageLocator(".shopping_cart_link");
        takeElementScreenshot(shoppingCart, "shoppingCart");
        clickElement(shoppingCart);
        return true;
    }
}