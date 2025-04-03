package com.saucedemo.swagLabs.Features;

import com.saucedemo.swagLabs.basetest.BaseTest;
import com.saucedemo.swagLabs.pages.CartPage;
import com.saucedemo.swagLabs.pages.CheckoutPage;
import com.saucedemo.swagLabs.pages.ProductsPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.JsonUtils;

public class CheckoutTest extends BaseTest {

    private static final JsonUtils LOGIN_DATA = new JsonUtils("LoginJsonData");
    private static final JsonUtils PRODUCTS_DATA = new JsonUtils("ProductsJsonData");
    private static final JsonUtils CHECKOUT_INFO_DATA = new JsonUtils("CheckoutInfoData");
    private ProductsPage productsPage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;

    @Test
    public void TC_01_VerifyCheckoutWithValidData() {

        productsPage.addSpecificProductToCart(PRODUCTS_DATA.getJsonData("Item3.Name"));
        cartPage = productsPage.clickCartIcon();
        checkoutPage = cartPage.clickCheckoutBtn();
        Assert.assertTrue(checkoutPage.isCheckoutPageVisible());

        checkoutPage.fillInfo(CHECKOUT_INFO_DATA.getJsonData("firstName"),
                CHECKOUT_INFO_DATA.getJsonData("lastName"),
                CHECKOUT_INFO_DATA.getJsonData("zipCode"));

        checkoutPage.clickContinue().clickFinish();

        String orderThkMsg = checkoutPage.getOrderConfirmationMsg();

        Assert.assertTrue(checkoutPage.isOrderCompleted());
        Assert.assertEquals(orderThkMsg, CHECKOUT_INFO_DATA.getJsonData("orderConfMsg"));
    }

    @Test
    public void TC_02_VerifyCheckoutWithEmptyFirstName() {

        productsPage.addSpecificProductToCart(PRODUCTS_DATA.getJsonData("Item3.Name"));
        cartPage = productsPage.clickCartIcon();
        checkoutPage = cartPage.clickCheckoutBtn();
        Assert.assertTrue(checkoutPage.isCheckoutPageVisible());

        checkoutPage.fillInfo(CHECKOUT_INFO_DATA.getJsonData("emptyFirstName"),
                CHECKOUT_INFO_DATA.getJsonData("lastName"),
                CHECKOUT_INFO_DATA.getJsonData("zipCode"));

        checkoutPage.clickContinue();
        String errMsg = checkoutPage.getErrorMsg();
        Assert.assertEquals(errMsg, CHECKOUT_INFO_DATA.getJsonData("err:firstNameReq"));
    }

    @Test
    public void TC_03_VerifyCheckoutWithEmptyLastName() {

        productsPage.addSpecificProductToCart(PRODUCTS_DATA.getJsonData("Item3.Name"));
        cartPage = productsPage.clickCartIcon();
        checkoutPage = cartPage.clickCheckoutBtn();
        Assert.assertTrue(checkoutPage.isCheckoutPageVisible());

        checkoutPage.fillInfo(CHECKOUT_INFO_DATA.getJsonData("firstName"),
                CHECKOUT_INFO_DATA.getJsonData("emptyLastName"),
                CHECKOUT_INFO_DATA.getJsonData("zipCode"));

        checkoutPage.clickContinue();
        String errMsg = checkoutPage.getErrorMsg();
        Assert.assertEquals(errMsg, CHECKOUT_INFO_DATA.getJsonData("err:lastNameReq"));
    }

    @Test
    public void TC_04_VerifyCheckoutWithEmptyZipCode() {

        productsPage.addSpecificProductToCart(PRODUCTS_DATA.getJsonData("Item3.Name"));
        cartPage = productsPage.clickCartIcon();
        checkoutPage = cartPage.clickCheckoutBtn();
        Assert.assertTrue(checkoutPage.isCheckoutPageVisible());

        checkoutPage.fillInfo(CHECKOUT_INFO_DATA.getJsonData("firstName"),
                CHECKOUT_INFO_DATA.getJsonData("lastName"),
                CHECKOUT_INFO_DATA.getJsonData("emptyZipCode"));

        checkoutPage.clickContinue();
        String errMsg = checkoutPage.getErrorMsg();
        Assert.assertEquals(errMsg, CHECKOUT_INFO_DATA.getJsonData("err:zipCodeReq"));
    }

    @BeforeMethod
    public synchronized void validLogin() {
        loginPage.typeUsername(LOGIN_DATA.getJsonData("validUsername"));
        loginPage.typePassword(LOGIN_DATA.getJsonData("validPassword"));
        productsPage = loginPage.clickLoginValid();
    }
}
