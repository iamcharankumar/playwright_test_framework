package io.swaglabs.portal.qa.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.swaglabs.portal.qa.constants.WebLocatorConstants;
import io.swaglabs.portal.qa.exceptions.SwagLabsException;

public class SwagLabsProductPage extends SwagLabsBasePage {

    public SwagLabsProductPage(Page basePage) {
        super(basePage);
    }

    public String getProductNameText() {
        String productNameText = getPageLocator(WebLocatorConstants.PRODUCT_NAME).textContent();
        if (productNameText.isEmpty())
            throw new SwagLabsException("Product Name text is empty!");
        return productNameText;
    }

    public String getProductPriceText() {
        String productPriceText = getPageLocator(WebLocatorConstants.PRODUCT_PRICE).textContent();
        if (productPriceText.isEmpty())
            throw new SwagLabsException("Product Price Text is empty!");
        return productPriceText;
    }

    public String getProductDescription(String productDescription) {
        String productDescText = getByText(productDescription).textContent();
        if (productDescText.isEmpty())
            throw new SwagLabsException("Product Description Text is empty!");
        return productDescText;
    }

    public boolean isProductAddedToCart() {
        Locator addToCartButton = getPageLocator(WebLocatorConstants.ADD_PRODUCT_TO_CART);
        if (addToCartButton.isEnabled()) {
            addToCartButton.click();
            return true;
        }
        return false;
    }

    public boolean isShoppingCartClicked() {
        Locator shoppingCart = getPageLocator(WebLocatorConstants.SHOPPING_CART_BUTTON);
        if (shoppingCart.isEnabled()) {
            shoppingCart.click();
            return true;
        }
        return false;
    }
}