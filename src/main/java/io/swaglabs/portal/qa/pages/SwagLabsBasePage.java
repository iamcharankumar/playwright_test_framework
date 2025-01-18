package io.swaglabs.portal.qa.pages;

import com.microsoft.playwright.Page;
import io.swaglabs.portal.qa.commons.WebBasePage;

public sealed class SwagLabsBasePage extends WebBasePage permits SwagLabsCartPage,
        SwagLabsCheckoutCompletePage, SwagLabsCheckoutOverviewPage, SwagLabsCheckoutPage,
        SwagLabsHomePage, SwagLabsLoginPage, SwagLabsProductPage {

    public SwagLabsBasePage(Page basePage) {
        super(basePage);
    }
}