package com.saucedemo.swagLabs.Features;

import com.saucedemo.swagLabs.basetest.BaseTest;
import com.saucedemo.swagLabs.listeners.TestNGListeners;
import com.saucedemo.swagLabs.pages.CartPage;
import com.saucedemo.swagLabs.pages.ProductsPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utils.JsonUtils;

@Listeners(TestNGListeners.class)
public class ShoppingCartTests extends BaseTest {

    private static final JsonUtils PRODUCTS_DATA = new JsonUtils("ProductsJsonData");
    private CartPage cartPage;

    @Test(groups = "validLogin")
    public void SC_01_VerifyItemAddedToCart() {

        productsPage.addSpecificProductToCart(PRODUCTS_DATA.getJsonData("Item2.Name"));
        cartPage = productsPage.clickCartIcon();

        boolean check1 = cartPage.checkCartPageUrl();
        boolean check2 = cartPage.isNotificationVisibleOnCart();
        String check3 = cartPage.getNotificationTxtOnCartIcon();
        String itemName = cartPage.getSpecificItemFromCart(PRODUCTS_DATA.getJsonData("Item2.Name")).getItemName();

        Assert.assertTrue(check1);
        Assert.assertTrue(check2);
        Assert.assertEquals(check3, "1");
        Assert.assertEquals(itemName, PRODUCTS_DATA.getJsonData("Item2.Name"));
    }

    @Test(groups = "validLogin")
    public void SC_02_VerifyTwoItemsAddedToCart() {

        productsPage.addSpecificProductToCart(PRODUCTS_DATA.getJsonData("Item2.Name"));
        productsPage.addSpecificProductToCart(PRODUCTS_DATA.getJsonData("Item3.Name"));
        cartPage = productsPage.clickCartIcon();

        boolean check1 = cartPage.checkCartPageUrl();
        boolean check2 = cartPage.isNotificationVisibleOnCart();
        String check3 = cartPage.getNotificationTxtOnCartIcon();
        String itemName = cartPage.getSpecificItemFromCart(PRODUCTS_DATA.getJsonData("Item2.Name")).getItemName();
        String itemName2 = cartPage.getSpecificItemFromCart(PRODUCTS_DATA.getJsonData("Item3.Name")).getItemName();

        Assert.assertTrue(check1);
        Assert.assertTrue(check2);
        Assert.assertEquals(check3, "2");
        Assert.assertEquals(itemName, PRODUCTS_DATA.getJsonData("Item2.Name"));
        Assert.assertEquals(itemName2, PRODUCTS_DATA.getJsonData("Item3.Name"));
    }

    @Test(groups = "validLogin")
    public void SC_03_VerifyRemoveItemFromCart() {

        productsPage.addSpecificProductToCart(PRODUCTS_DATA.getJsonData("Item2.Name"));
        cartPage = productsPage.clickCartIcon();

        boolean check1 = cartPage.checkCartPageUrl();
        boolean check2 = cartPage.isNotificationVisibleOnCart();
        String check3 = cartPage.getNotificationTxtOnCartIcon();

        Assert.assertTrue(check1);
        Assert.assertTrue(check2);
        Assert.assertEquals(check3, "1");

        cartPage.removeSpecificProductFromCart(PRODUCTS_DATA.getJsonData("Item2.Name"));
        boolean check4 = cartPage.isSpecificItemRemovedFromCart(PRODUCTS_DATA.getJsonData("Item2.Name"));

        Assert.assertTrue(check4);
    }

    @Test(groups = "validLogin")
    public void SC_04_VerifyAddTwoItemsToCartAndRemoveOneItemFromCart() {

        productsPage.addSpecificProductToCart(PRODUCTS_DATA.getJsonData("Item2.Name"));
        productsPage.addSpecificProductToCart(PRODUCTS_DATA.getJsonData("Item3.Name"));
        cartPage = productsPage.clickCartIcon();

        boolean check1 = cartPage.checkCartPageUrl();
        boolean check2 = cartPage.isNotificationVisibleOnCart();
        String check3 = cartPage.getNotificationTxtOnCartIcon();

        Assert.assertTrue(check1);
        Assert.assertTrue(check2);
        Assert.assertEquals(check3, "2");

        cartPage.removeSpecificProductFromCart(PRODUCTS_DATA.getJsonData("Item2.Name"));
        boolean check4 = cartPage.isSpecificItemRemovedFromCart(PRODUCTS_DATA.getJsonData("Item2.Name"));
        boolean check5 = cartPage.isNotificationVisibleOnCart();
        String itemName = cartPage.getSpecificItemFromCart(PRODUCTS_DATA.getJsonData("Item3.Name")).getItemName();

        Assert.assertTrue(check4);
        Assert.assertTrue(check5);
        Assert.assertEquals(itemName, PRODUCTS_DATA.getJsonData("Item3.Name"));
    }

    @Test(groups = "validLogin")
    public void SC_05VerifyEmptyCart() {

        cartPage = productsPage.clickCartIcon();

        boolean check1 = cartPage.checkCartPageUrl();
        boolean check2 = cartPage.isNotificationVisibleOnCart();
        boolean check3 = cartPage.isCartEmpty();

        Assert.assertTrue(check1);
        Assert.assertFalse(check2);
        Assert.assertTrue(check3);

    }

}
