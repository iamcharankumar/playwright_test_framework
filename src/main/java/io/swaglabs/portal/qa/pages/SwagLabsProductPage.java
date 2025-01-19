package io.swaglabs.portal.qa.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public final class SwagLabsProductPage extends SwagLabsBasePage {

    public SwagLabsProductPage(Page basePage) {
        super(basePage);
    }

    public String getProductNameText() {
        String productNameText = locators.getPageLocator(".inventory_details_name.large_size").textContent();
        validateNonEmptyText(productNameText, "Product Name text is empty!");
        return productNameText;
    }

    public String getProductPriceText() {
        String productPriceText = locators.getPageLocator(".inventory_details_price").textContent();
        validateNonEmptyText(productPriceText, "Product Price Text is empty!");
        return productPriceText;
    }

    public String getProductDescription(String productDescription) {
        String productDescText = locators.getByText(productDescription).textContent();
        validateNonEmptyText(productDescText, "Product Description Text is empty!");
        return productDescText;
    }

    public boolean isProductAddedToCart() {
        Locator addToCartButton = locators.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Add to cart"));
        clickElement(addToCartButton);
        return true;
    }

    public boolean isShoppingCartClicked() {
        Locator shoppingCart = locators.getPageLocator(".shopping_cart_link");
        clickElement(shoppingCart);
        shoppingCart.click();
        return true;
    }
}