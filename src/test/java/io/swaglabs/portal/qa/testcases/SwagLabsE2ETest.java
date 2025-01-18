package io.swaglabs.portal.qa.testcases;

import io.swaglabs.portal.qa.constants.TestGroups;
import io.swaglabs.portal.qa.dataprovider.SwagLabsDataProvider;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.Test;

@Slf4j
public final class SwagLabsE2ETest extends SwagLabsTestBase {

    @Test(description = "User Journey: To complete the purchase of one product.", dataProvider = "purchase-one-product",
            dataProviderClass = SwagLabsDataProvider.class, groups = {TestGroups.SWAG_LABS_UNIT})
    public void testOneProductPurchase(String productName, String price, String productDescription) {
        verifyProductSelection(productName);
        verifyProductDetails(productName, price, productDescription);
        completePurchase();
        log.info("Purchase of one products tested successfully!");
    }

    @Test(description = "User Journey: To complete the purchase of all products.", dataProvider = "purchase-product",
            dataProviderClass = SwagLabsDataProvider.class, groups = {TestGroups.SWAG_LABS_E2E})
    public void testProductPurchase(String productName, String price, String productDescription) {
        verifyProductSelection(productName);
        verifyProductDetails(productName, price, productDescription);
        completePurchase();
        log.info("Purchase of all products tested successfully!");
    }

    private void verifyProductSelection(String productName) {
        boolean isProductChosen = SWAG_LABS_PORTAL.get().HOME_PAGE.isProductSelected(productName);
        Assert.assertTrue(isProductChosen, String.format("Product '%s' in Home Page Not Chosen!", productName));
    }

    private void verifyProductDetails(String productName, String price, String description) {
        String actualProductNameText = SWAG_LABS_PORTAL.get().PRODUCT_PAGE.getProductNameText();
        Assert.assertEquals(actualProductNameText, productName, "Product Name Mismatched in Product Page!");
        String actualProductPriceText = SWAG_LABS_PORTAL.get().PRODUCT_PAGE.getProductPriceText();
        Assert.assertEquals(actualProductPriceText, price, "Product Price Mismatched in Product Page!");
        String actualProductDescriptionText = SWAG_LABS_PORTAL.get().PRODUCT_PAGE.getProductDescription(description);
        Assert.assertEquals(actualProductDescriptionText, description, "Product Description Mismatched in Product Page");
    }

    private void completePurchase() {
        Assert.assertTrue(SWAG_LABS_PORTAL.get().PRODUCT_PAGE.isProductAddedToCart(), "Product in Product Page Not Added To Cart!");
        Assert.assertTrue(SWAG_LABS_PORTAL.get().PRODUCT_PAGE.isShoppingCartClicked(), "Shopping Cart Link Not Clicked!");
        Assert.assertTrue(SWAG_LABS_PORTAL.get().CART_PAGE.isCheckoutButtonClicked(), "Checkout Button in Cart Page Not Clicked!");
        Assert.assertTrue(SWAG_LABS_PORTAL.get().CHECKOUT_PAGE.isCheckoutInformationEntered("Test", "User", "123456"), "Checkout Page Information Not Entered!");
        Assert.assertTrue(SWAG_LABS_PORTAL.get().CHECKOUT_OVERVIEW_PAGE.isFinishButtonClicked(), "Finish Button in Checkout Overview Page Not Clicked");
        String actualThankYouText = SWAG_LABS_PORTAL.get().CHECKOUT_COMPLETE_PAGE.getThankYouText();
        Assert.assertEquals(actualThankYouText, "Thank you for your order!", "Thank You Text in Checkout Complete Page Mismatched!");
        String actualOrderCompleteText = SWAG_LABS_PORTAL.get().CHECKOUT_COMPLETE_PAGE.getOrderCompleteText();
        Assert.assertEquals(actualOrderCompleteText, "Your order has been dispatched, and will arrive just as fast as the pony can get there!", "Order Complete Text in Checkout Complete Page Mismatched!");
        Assert.assertTrue(SWAG_LABS_PORTAL.get().CHECKOUT_COMPLETE_PAGE.isBackHomeButtonClicked(), "Back Home Button Not clicked");
    }
}