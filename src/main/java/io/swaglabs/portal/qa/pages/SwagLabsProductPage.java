package io.swaglabs.portal.qa.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
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
        Locator addToCartButton = locators.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Add to cart"));
        if (!addToCartButton.isEnabled())
            throw new SwagLabsException("Product is not added to the shopping cart!");
        addToCartButton.click();
        return true;
    }

    public boolean isShoppingCartClicked() {
        Locator shoppingCart = locators.getPageLocator(".shopping_cart_link");
        if (!shoppingCart.isEnabled())
            throw new SwagLabsException("Shopping Card is not clicked!");
        shoppingCart.click();
        return true;
    }
}