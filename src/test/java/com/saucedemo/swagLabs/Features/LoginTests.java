package com.saucedemo.swagLabs.Features;

import com.saucedemo.swagLabs.basetest.BaseTest;
import com.saucedemo.swagLabs.listeners.TestNGListeners;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.saucedemo.swagLabs.pages.ProductsPage;
import utils.JsonUtils;

@Listeners(TestNGListeners.class)
public class LoginTests extends BaseTest {

    @Test(groups = "TestLogin")
    public void LG_01verifyLoginWithValidUsernameAndPassword() {

        loginPage.typeUsername(LOGIN_DATA.getJsonData("validUsername"));
        loginPage.typePassword(LOGIN_DATA.getJsonData("validPassword"));
        ProductsPage productsPage = loginPage.clickLoginValid();

        boolean actualResult = productsPage.isProductsListVisible();
        Assert.assertTrue(actualResult);
    }

    @Test(groups = "TestLogin")
    public void LG_02verifyLoginWithAnInvalidUsername() {

        loginPage.typeUsername(LOGIN_DATA.getJsonData("inValidUsername"));
        loginPage.typePassword(LOGIN_DATA.getJsonData("validPassword"));
        loginPage.clickLoginInvalid();

        String actualResult = loginPage.getErrorMessage();
        Assert.assertEquals(actualResult, LOGIN_DATA.getJsonData("invalidUsernameOrPassword"));
    }

    @Test(groups = "TestLogin")
    public void LG_03LoginWithAnInvalidPassword() {

        loginPage.typeUsername(LOGIN_DATA.getJsonData("validUsername"));
        loginPage.typePassword(LOGIN_DATA.getJsonData("invalidPassword"));
        loginPage.clickLoginInvalid();

        String actualResult = loginPage.getErrorMessage();
        Assert.assertEquals(actualResult, LOGIN_DATA.getJsonData("invalidUsernameOrPassword"));
    }

    @Test(groups = "TestLogin")
    public void LG_04verifyLoginWithBlankUsernameField() {

        loginPage.typeUsername(LOGIN_DATA.getJsonData("emptyUsername"));
        loginPage.typePassword(LOGIN_DATA.getJsonData("validPassword"));
        loginPage.clickLoginInvalid();

        String actualResult = loginPage.getErrorMessage();
        Assert.assertEquals(actualResult, LOGIN_DATA.getJsonData("usernameRequired"));
    }

    @Test(groups = "TestLogin")
    public void LG_05verifyLoginWithBlankPasswordField() {

        loginPage.typeUsername(LOGIN_DATA.getJsonData("validUsername"));
        loginPage.typePassword(LOGIN_DATA.getJsonData("emptyPassword"));
        loginPage.clickLoginInvalid();

        String actualResult = loginPage.getErrorMessage();
        Assert.assertEquals(actualResult, LOGIN_DATA.getJsonData("passwordRequired"));
    }

    @Test(groups = "TestLogin")
    public void LG_06verifyPasswordIsMasked() {

        loginPage.typePassword(LOGIN_DATA.getJsonData("validPassword"));

        boolean actualResult = loginPage.checkPasswordMasked();
        Assert.assertTrue(actualResult);
    }

    @Test(groups = "TestLogin")
    public void LG_07verifyLoginWithValidCredentialsAndPressEnterKey() {

        loginPage.typeUsername(LOGIN_DATA.getJsonData("validUsername"));
        loginPage.typePassword(LOGIN_DATA.getJsonData("validPassword"));
        ProductsPage productsPage = loginPage.pressEnterKeyValidCredentials();

        boolean actualResult = productsPage.isProductsListVisible();
        Assert.assertTrue(actualResult);
    }

    @Test(groups = "TestLogin")
    public void LG_08verifyLoginWithInvalidCredentialsAndPressEnterKey() {

        loginPage.typeUsername(LOGIN_DATA.getJsonData("inValidUsername"));
        loginPage.typePassword(LOGIN_DATA.getJsonData("invalidPassword"));
        loginPage.pressEnterKeyInvalidCredentials();

        String actualResult = loginPage.getErrorMessage();
        Assert.assertEquals(actualResult, LOGIN_DATA.getJsonData("invalidUsernameOrPassword"));
    }

    @Test(groups = "TestLogin")
    public void LG_09verifyUserLogout() {

        loginPage.typeUsername(LOGIN_DATA.getJsonData("validUsername"));
        loginPage.typePassword(LOGIN_DATA.getJsonData("validPassword"));
        ProductsPage productsPage = loginPage.clickLoginValid();
        productsPage.clickBurgerMenuBtn();
        loginPage = productsPage.clickLogout();

        boolean actualResult = loginPage.loginFormVisibility();
        Assert.assertTrue(actualResult);
    }
}
