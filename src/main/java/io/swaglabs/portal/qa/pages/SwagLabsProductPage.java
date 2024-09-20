package io.swaglabs.portal.qa.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.swaglabs.portal.qa.exceptions.SwagLabsException;

public class SwagLabsProductPage extends SwagLabsBasePage {

    public SwagLabsProductPage(Page basePage) {
        super(basePage);
    }

    private static final String PRODUCT_NAME = ".inventory_details_name.large_size";
    private static final String PRODUCT_PRICE = ".inventory_details_price";
    private static final String ADD_PRODUCT_TO_CART = "#add-to-cart";
    private static final String SHOPPING_CART_BUTTON = ".shopping_cart_link";

    public String getProductNameText() {
        String productNameText = locators.getPageLocator(PRODUCT_NAME).textContent();
        if (productNameText.isEmpty())
            throw new SwagLabsException("Product Name text is empty!");
        return productNameText;
    }

    public String getProductPriceText() {
        String productPriceText = locators.getPageLocator(PRODUCT_PRICE).textContent();
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
        Locator addToCartButton = locators.getPageLocator(ADD_PRODUCT_TO_CART);
        if (addToCartButton.isEnabled()) {
            addToCartButton.click();
            return true;
        }
        return false;
    }

    public boolean isShoppingCartClicked() {
        Locator shoppingCart = locators.getPageLocator(SHOPPING_CART_BUTTON);
        if (shoppingCart.isEnabled()) {
            shoppingCart.click();
            return true;
        }
        return false;
    }
}