package io.swaglabs.portal.qa.testcases;

import io.swaglabs.portal.qa.constants.TestGroups;
import io.swaglabs.portal.qa.dataprovider.SwagLabsDataProvider;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.Test;

@Slf4j
public class SwagLabsHomePageTest extends SwagLabsTestBase {

    @Test(description = "To verify, the home page products header text.",
            groups = {TestGroups.SWAG_LABS_SMOKE, TestGroups.SWAG_LABS_REGRESSION})
    public void testHomePageProductsText() {
        String actualProductsText = SWAG_LABS_PORTAL.get().HOME_PAGE.getProductHeaderText();
        Assert.assertEquals(actualProductsText, "Products", "Products Text Mismatched in Home Page!");
        log.info("Verified the Home Page Text: {}", actualProductsText);
    }

    @Test(description = "To verify, the shopping cart link is enabled.",
            groups = {TestGroups.SWAG_LABS_SMOKE, TestGroups.SWAG_LABS_REGRESSION})
    public void testShoppingCartLink() {
        boolean isShoppingCardEnabled = SWAG_LABS_PORTAL.get().HOME_PAGE.isShoppingCartButtonEnabled();
        Assert.assertTrue(isShoppingCardEnabled, "Shopping Card Button Disabled in Home Page!");
        log.info("Verified the enabled status: {} for shopping cart", isShoppingCardEnabled);
    }

    @Test(description = "To verify, the Hamburger Menu is enabled.",
            groups = {TestGroups.SWAG_LABS_SMOKE, TestGroups.SWAG_LABS_REGRESSION})
    public void testProductSortAtoZ() {
        boolean isHamburgerButtonEnabled = SWAG_LABS_PORTAL.get().HOME_PAGE.isHamburgerButtonEnabled();
        Assert.assertTrue(isHamburgerButtonEnabled, "Hamburger Menu Button Disabled in Home Page!");
        log.info("Verified the enabled status: {} for hamburger button", isHamburgerButtonEnabled);
    }

    @Test(description = "To verify all the burger menu item names",
            dataProvider = "hamburger-menu", dataProviderClass = SwagLabsDataProvider.class,
            groups = {TestGroups.SWAG_LABS_SMOKE, TestGroups.SWAG_LABS_REGRESSION})
    public void testMenuAllItemsName(String allHamburgerMenuItems) {
        String actualMenuAllItemsText = SWAG_LABS_PORTAL.get().HOME_PAGE.getAllItemsText();
        Assert.assertEquals(actualMenuAllItemsText, allHamburgerMenuItems, "Hamburger Menu All Items Text Mismatched!");
        log.info("Verified all the menu items {}.", actualMenuAllItemsText);
    }

    @Test(description = "To verify, the size of the shopping cart after adding all the products in Home Page.",
            groups = {TestGroups.SWAG_LABS_REGRESSION})
    public void testShoppingCartSize() {
        int shoppingCartSize = SWAG_LABS_PORTAL.get().HOME_PAGE.addAllItemsToShoppingCart();
        Assert.assertEquals(shoppingCartSize, 6, "Shopping Cart Size Mismatched!");
        log.info("Verified the Shopping Cart Size: {}", shoppingCartSize);
    }
}