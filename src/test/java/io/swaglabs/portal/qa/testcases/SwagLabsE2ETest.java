package io.swaglabs.portal.qa.testcases;

import io.swaglabs.portal.qa.constants.TestGroups;
import io.swaglabs.portal.qa.dataprovider.SwagLabsDataProvider;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.Test;

@Slf4j
public class SwagLabsE2ETest extends SwagLabsTestBase {

    @Test(description = "User Journey: To complete the purchase of all products.", dataProvider = "purchase-product",
            dataProviderClass = SwagLabsDataProvider.class, groups = {TestGroups.SWAG_LABS_E2E})
    public void testProductPurchase(String productName, String price, String productDescription) {
        boolean isProductChosen = SWAG_LABS_PORTAL.get().HOME_PAGE.isProductSelected(productName);
        Assert.assertTrue(isProductChosen, "Product in Home Page Not Chosen!");
        log.info("Verified the choice of product: {}", productName);
        String actualProductNameText = SWAG_LABS_PORTAL.get().PRODUCT_PAGE.getProductNameText();
        Assert.assertEquals(actualProductNameText, productName, "Product Name Mismatched in Product Page!");
        log.info("Verified the product name: {}", actualProductNameText);
        String actualProductPriceText = SWAG_LABS_PORTAL.get().PRODUCT_PAGE.getProductPriceText();
        Assert.assertEquals(actualProductPriceText, price, "Product Price Mismatched in Product Page!");
        log.info("Verified the Product Price : {} for the Product Name: {}", actualProductPriceText, productName);
        String actualProductDescriptionText = SWAG_LABS_PORTAL.get().PRODUCT_PAGE.getProductDescription(productDescription);
        Assert.assertEquals(actualProductDescriptionText, productDescription,
                "Product Description Mismatched in Product Page");
        log.info("Verified the Product Description: {} for the product: {}", actualProductDescriptionText, productName);
        boolean isProductAdded = SWAG_LABS_PORTAL.get().PRODUCT_PAGE.isProductAddedToCart();
        Assert.assertTrue(isProductAdded, "Product in Product Page Not Added To Cart!");
        log.info("Verified the Product Added to cart: {}", isProductAdded);
        boolean isShoppingCartLinkClicked = SWAG_LABS_PORTAL.get().PRODUCT_PAGE.isShoppingCartClicked();
        Assert.assertTrue(isShoppingCartLinkClicked, "Shopping Cart Link Not Clicked!");
        log.info("Verified the Shopping cart button clicked: {}", isProductAdded);
        boolean isCheckoutButtonClicked = SWAG_LABS_PORTAL.get().CART_PAGE.isCheckoutButtonClicked();
        Assert.assertTrue(isCheckoutButtonClicked, "Checkout Button in Cart Page Not Clicked!");
        log.info("Verified the Checkout button clicked: {}", isCheckoutButtonClicked);
        boolean isCheckoutInfoEntered = SWAG_LABS_PORTAL.get().CHECKOUT_PAGE.isCheckoutInformationEntered("Test",
                "User", "123456");
        Assert.assertTrue(isCheckoutInfoEntered, "Checkout Page Information Not Entered!");
        log.info("Verified the Checkout information entered: {}", isCheckoutInfoEntered);
        boolean isCheckoutOverviewFinishButtonClicked = SWAG_LABS_PORTAL.get().CHECKOUT_OVERVIEW_PAGE.isFinishButtonClicked();
        Assert.assertTrue(isCheckoutOverviewFinishButtonClicked, "Finish Button in Checkout Overview Page Not Clicked");
        log.info("Verified the Finish Button Clicked: {}", isCheckoutOverviewFinishButtonClicked);
        String actualThankYouText = SWAG_LABS_PORTAL.get().CHECKOUT_COMPLETE_PAGE.getThankYouText();
        Assert.assertEquals(actualThankYouText, "Thank you for your order!",
                "Thank You Text in Checkout Complete Page Mismatched!");
        log.info("Verified the Thank You Text {} for the Product: {}", actualThankYouText, productName);
        String actualOrderCompleteText = SWAG_LABS_PORTAL.get().CHECKOUT_COMPLETE_PAGE.getOrderCompleteText();
        Assert.assertEquals(actualOrderCompleteText, "Your order has been dispatched, and will arrive just as fast as the pony can get there!",
                "Order Complete Text in Checkout Complete Page Mismatched!");
        log.info("Verified the Order Complete Text {} for the Product: {}", actualOrderCompleteText, productName);
        boolean isBackButtonClicked = SWAG_LABS_PORTAL.get().CHECKOUT_COMPLETE_PAGE.isBackHomeButtonClicked();
        Assert.assertTrue(isBackButtonClicked, "Back Home Button in Checkout Complete Page Not Clicked!");
        log.info("Verified the Back to Home Button Clicked: {}", isBackButtonClicked);
    }
}