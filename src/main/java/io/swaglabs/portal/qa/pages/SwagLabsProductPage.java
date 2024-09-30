package io.swaglabs.portal.qa.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.swaglabs.portal.qa.exceptions.SwagLabsException;

public class SwagLabsProductPage extends SwagLabsBasePage {

    public SwagLabsProductPage(Page basePage) {
        super(basePage);
    }

    public String getProductNameText() {
        String productNameText = locators.getPageLocator(".inventory_details_name.large_size").textContent();
        if (productNameText.isEmpty())
            throw new SwagLabsException("Product Name text is empty!");
        return productNameText;
    }

    public String getProductPriceText() {
        String productPriceText = locators.getPageLocator(".inventory_details_price").textContent();
        if (productPriceText.isEmpty())
            throw new SwagLabsException("Product Price Text is empty!");
        return productPriceText;
    }

    public String getProductDescription(String productDescription) {
        String productDescText = locators.getByText(productDescription).textContent();
        if (productDescText.isEmpty())
            throw new SwagLabsException("Product Description Text is empty!");
        return productDescText;
    }

    public boolean isProductAddedToCart() {
        Locator addToCartButton = locators.getPageLocator("#add-to-cart");
        if (addToCartButton.isEnabled()) {
            addToCartButton.click();
            return true;
        }
        return false;
    }

    public boolean isShoppingCartClicked() {
        Locator shoppingCart = locators.getPageLocator(".shopping_cart_link");
        if (shoppingCart.isEnabled()) {
            shoppingCart.click();
            return true;
        }
        return false;
    }
}