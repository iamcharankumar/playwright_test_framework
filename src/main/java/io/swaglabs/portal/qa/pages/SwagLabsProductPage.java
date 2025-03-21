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
        return getTextContent(locators.getPageLocator(".inventory_details_price"));
    }

    public String getProductDescription(String productDescription) {
        return getTextContent(locators.getByText(productDescription));
    }

    public boolean isProductAddedToCart() {
        Locator addToCartButton = locators.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Add to cart"));
        clickElement(addToCartButton);
        return true;
    }

    public boolean isShoppingCartClicked() {
        Locator shoppingCart = locators.getPageLocator(".shopping_cart_link");
        clickElement(shoppingCart);
        return true;
    }
}