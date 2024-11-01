package io.swaglabs.portal.qa.module;

import com.microsoft.playwright.Page;
import io.swaglabs.portal.qa.constants.WebPortalConstants;
import io.swaglabs.portal.qa.pages.*;

public class SwagLabsPortal {

    private final Page PAGE;
    private final String SWAG_LABS_URL;
    public final SwagLabsLoginPage LOGIN_PAGE;
    public final SwagLabsHomePage HOME_PAGE;
    public final SwagLabsProductPage PRODUCT_PAGE;
    public final SwagLabsCartPage CART_PAGE;
    public final SwagLabsCheckoutPage CHECKOUT_PAGE;
    public final SwagLabsCheckoutOverviewPage CHECKOUT_OVERVIEW_PAGE;
    public final SwagLabsCheckoutCompletePage CHECKOUT_COMPLETE_PAGE;

    public SwagLabsPortal(Page page) {
        this.PAGE = page;
        SWAG_LABS_URL = WebPortalConstants.SWAG_LABS_URL;
        LOGIN_PAGE = new SwagLabsLoginPage(page);
        HOME_PAGE = new SwagLabsHomePage(page);
        PRODUCT_PAGE = new SwagLabsProductPage(page);
        CART_PAGE = new SwagLabsCartPage(page);
        CHECKOUT_PAGE = new SwagLabsCheckoutPage(page);
        CHECKOUT_OVERVIEW_PAGE = new SwagLabsCheckoutOverviewPage(page);
        CHECKOUT_COMPLETE_PAGE = new SwagLabsCheckoutCompletePage(page);
    }

    public void visit() {
        PAGE.navigate(SWAG_LABS_URL);
    }
}