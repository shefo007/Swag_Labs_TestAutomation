package com.saucedemo.swagLabs.Features;

import com.saucedemo.swagLabs.basetest.BaseTest;
import com.saucedemo.swagLabs.listeners.TestNGListeners;
import com.saucedemo.swagLabs.pages.ProductsPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utils.JsonUtils;

@Listeners(TestNGListeners.class)
public class AddToCartTests extends BaseTest {

    private static final JsonUtils LOGIN_DATA = new JsonUtils("LoginJsonData");
    private static final JsonUtils PRODUCTS_DATA = new JsonUtils("ProductsJsonData");
    private ProductsPage productsPage;

    @Test(priority = 1)
    public void ATC_01_VerifyAddItemToCart() {

        productsPage.addSpecificProductToCart(PRODUCTS_DATA.getJsonData("Item2.Name"));

        boolean check1 = productsPage.isProductAddedToCart(PRODUCTS_DATA.getJsonData("Item2.Name"));
        boolean check2 = productsPage.isNotificationVisibleOnCart();
        String check3 = productsPage.getNotificationTxtOnCartIcon();

        Assert.assertTrue(check1);
        Assert.assertTrue(check2);
        Assert.assertEquals(check3, "1");
    }

    @Test(priority = 2)
    public void ATC_02_VerifyAddTwoItemsToCart() {

        productsPage.addSpecificProductToCart(PRODUCTS_DATA.getJsonData("Item2.Name"));
        productsPage.addSpecificProductToCart(PRODUCTS_DATA.getJsonData("Item3.Name"));

        boolean check1 = productsPage.isProductAddedToCart(PRODUCTS_DATA.getJsonData("Item2.Name"));
        boolean check2 = productsPage.isProductAddedToCart(PRODUCTS_DATA.getJsonData("Item3.Name"));
        boolean check3 = productsPage.isNotificationVisibleOnCart();
        String check4 = productsPage.getNotificationTxtOnCartIcon();

        Assert.assertTrue(check1);
        Assert.assertTrue(check2);
        Assert.assertTrue(check3);
        Assert.assertEquals(check4, "2");
    }

    @Test(priority = 3)
    public void ATC_03_VerifyRemoveItemFromCartFromProductsPage() {

        productsPage.addSpecificProductToCart(PRODUCTS_DATA.getJsonData("Item2.Name"));
        boolean check1 = productsPage.isProductAddedToCart(PRODUCTS_DATA.getJsonData("Item2.Name"));
        boolean check2 = productsPage.isNotificationVisibleOnCart();
        String check3 = productsPage.getNotificationTxtOnCartIcon();

        Assert.assertTrue(check1);
        Assert.assertTrue(check2);
        Assert.assertEquals(check3, "1");

        productsPage.removeSpecificProductFromCart(PRODUCTS_DATA.getJsonData("Item2.Name"));
        boolean check4 = productsPage.isProductRemovedFromCart(PRODUCTS_DATA.getJsonData("Item2.Name"));
        boolean check5 = productsPage.isNotificationVisibleOnCart();

        Assert.assertTrue(check4);
        Assert.assertFalse(check5);

    }

    @Test(priority = 4)
    public void ATC_04_VerifyAddTwoItemsToCartAndRemoveOneItemFromCartFromProductsPage() {

        productsPage.addSpecificProductToCart(PRODUCTS_DATA.getJsonData("Item2.Name"));
        productsPage.addSpecificProductToCart(PRODUCTS_DATA.getJsonData("Item3.Name"));
        boolean check1 = productsPage.isProductAddedToCart(PRODUCTS_DATA.getJsonData("Item2.Name"));
        boolean check2 = productsPage.isProductAddedToCart(PRODUCTS_DATA.getJsonData("Item3.Name"));
        boolean check3 = productsPage.isNotificationVisibleOnCart();
        String check4 = productsPage.getNotificationTxtOnCartIcon();

        Assert.assertTrue(check1);
        Assert.assertTrue(check2);
        Assert.assertTrue(check3);
        Assert.assertEquals(check4, "2");

        productsPage.removeSpecificProductFromCart(PRODUCTS_DATA.getJsonData("Item2.Name"));
        boolean check5 = productsPage.isProductRemovedFromCart(PRODUCTS_DATA.getJsonData("Item2.Name"));
        boolean check6 = productsPage.isNotificationVisibleOnCart();
        String check7 = productsPage.getNotificationTxtOnCartIcon();

        Assert.assertTrue(check5);
        Assert.assertTrue(check6);
        Assert.assertEquals(check7, "1");
    }

    @BeforeMethod
    public synchronized void validLogin() {
        loginPage.typeUsername(LOGIN_DATA.getJsonData("validUsername"));
        loginPage.typePassword(LOGIN_DATA.getJsonData("validPassword"));
        productsPage = loginPage.clickLoginValid();
    }
}
