package io.swaglabs.portal.qa.module;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import io.swaglabs.portal.qa.pages.*;
import io.swaglabs.portal.qa.utils.WebConfigLoader;

public class SwagLabsPortal {

    private final Page PAGE;
    private final String SWAG_LABS_URL;
    private final String SWAG_LABS_USERNAME;
    private final String SWAG_LABS_PASSWORD;
    public final SwagLabsLoginPage LOGIN_PAGE;
    public final SwagLabsHomePage HOME_PAGE;
    public final SwagLabsProductPage PRODUCT_PAGE;
    public final SwagLabsCartPage CART_PAGE;
    public final SwagLabsCheckoutPage CHECKOUT_PAGE;
    public final SwagLabsCheckoutOverviewPage CHECKOUT_OVERVIEW_PAGE;
    public final SwagLabsCheckoutCompletePage CHECKOUT_COMPLETE_PAGE;

    public SwagLabsPortal(Page page) {
        WebConfigLoader configLoader = WebConfigLoader.getInstance();
        this.PAGE = page;
        SWAG_LABS_URL = configLoader.getSwagLabsUrl();
        SWAG_LABS_USERNAME = configLoader.getSwagLabsUserName();
        SWAG_LABS_PASSWORD = configLoader.getSwagLabsPassword();
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
        PAGE.waitForLoadState(LoadState.NETWORKIDLE);
    }
}