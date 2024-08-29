package io.swaglabs.portal.qa.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class WebLocatorConstants {

    // LOGIN PAGE LOCATORS
    public static final String LOGIN_LOGO_TEXT = "Swag Labs";
    public static final String ACCEPTED_USER_NAME_LIST = "#login_credentials";
    public static final String ALL_USER_PASSWORDS = ".login_password";
    public static final String USER_NAME_INPUT = "#user-name";
    public static final String PASSWORD_INPUT = "#password";
    public static final String LOGIN_BUTTON = "#login-button";

    // HOME PAGE LOCATORS
    public static final String SWAG_LABS_APP_LOGO_TEXT = "Swag Labs";
    public static final String HOME_PAGE_HEADER_TEXT = ".app_logo";
    public static final String PRODUCTS_TEXT = "Products";
    public static final String SHOPPING_CART_BUTTON = ".shopping_cart_link";
    public static final String HAMBURGER_MENU_BUTTON = ".bm-burger-button";
    public static final String HAMBURGER_MENU_LIST = ".bm-menu";
    public static final String INVENTORY_ITEM_NAME = ".inventory_item_name";
    public static final String ADD_TO_CART_BACKPACK = "#add-to-cart-sauce-labs-backpack";
    public static final String ADD_TO_CART_BIKE_LIGHT = "#add-to-cart-sauce-labs-bike-light";
    public static final String ADD_TO_CART_BOLT_T_SHIRT = "#add-to-cart-sauce-labs-bolt-t-shirt";
    public static final String ADD_TO_CART_FLEECE_JACKET = "#add-to-cart-sauce-labs-fleece-jacket";
    public static final String ADD_TO_CART_ONE_SIZE = "#add-to-cart-sauce-labs-onesie";
    public static final String ADD_TO_CART_RED_T_SHIRT = "button[id='add-to-cart-test.allthethings()-t-shirt-(red)']";
    public static final String ALL_ADD_TO_CART_BUTTON = ".btn.btn_primary.btn_small.btn_inventory";
    public static final String SHOPPING_CART_BADGE = ".shopping_cart_badge";

    // PRODUCTS PAGE
    public static final String PRODUCT_NAME = ".inventory_details_name.large_size";
    public static final String PRODUCT_PRICE = ".inventory_details_price";
    public static final String ADD_PRODUCT_TO_CART = "#add-to-cart";

    // CART PAGE LOCATORS
    public static final String CHECKOUT_BUTTON = "#checkout";

    // CHECKOUT PAGE LOCATORS
    public static final String FIRST_NAME_TEXT_BOX = "#first-name";
    public static final String LAST_NAME_TEXT_BOX = "#last-name";
    public static final String POSTAL_CODE_TEXT_BOX = "#postal-code";
    public static final String CONTINUE_BUTTON = "#continue";
    public static final String FINISH_BUTTON = "#finish";

    // CHECKOUT COMPLETE PAGE LOCATORS
    public static final String THANK_YOU_TEXT = ".complete-header";
    public static final String COMPLETE_TEXT = ".complete-text";
    public static final String BACK_TO_HOME_BUTTON = "#back-to-products";
}