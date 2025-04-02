package com.saucedemo.swagLabs.cucumber;

import com.saucedemo.swagLabs.basetest.DriverManager;
import com.saucedemo.swagLabs.config.Configuration;

import io.cucumber.java.en.*;
import org.testng.Assert;
import com.saucedemo.swagLabs.pages.LoginPage;
import com.saucedemo.swagLabs.pages.ProductsPage;
import utils.JsonUtils;


import static com.saucedemo.swagLabs.config.ConfigurationManager.configuration;

public class LoginSteps {

    protected static Configuration configuration;

    private LoginPage loginPage;
    private ProductsPage productsPage;
    private static final JsonUtils LOGIN_DATA = new JsonUtils("LoginJsonData");

    @Given("user is on login page")
    public void user_is_in_login_page() {
        configuration = configuration();

        DriverManager.setDriver(configuration.browser());
        DriverManager.getDriver().get(configuration.url());
        loginPage = new LoginPage(DriverManager.getDriver());
    }

    @When("user enters valid credentials")
    public void user_enters_valid_credentials() {

        loginPage.typeUsername(LOGIN_DATA.getJsonData("validUsername"));
        loginPage.typePassword(LOGIN_DATA.getJsonData("validPassword"));

    }

    @When("user enters invalid credentials")
    public void user_enters_invalid_credentials() {

        loginPage.typeUsername(LOGIN_DATA.getJsonData("inValidUsername"));
        loginPage.typePassword(LOGIN_DATA.getJsonData("invalidPassword"));
    }

    @When("clicks login button with valid credentials")
    public void clicks_login_button_with_valid_credentials() {
        productsPage = loginPage.clickLoginValid();

    }

    @When("clicks login button with invalid credentials")
    public void clicks_login_button_with_invalid_credentials() {
        loginPage.clickLoginInvalid();
    }

    @Then("user sees an error message appeared")
    public void user_sees_an_error_message_appeared() {

        String actualResult = loginPage.getErrorMessage();
        Assert.assertEquals(actualResult, LOGIN_DATA.getJsonData("invalidUsernameOrPassword"));

    }

    @Then("user redirects to products page")
    public void user_redirects_to_products_page() {
        boolean actualResult = productsPage.checkProductsPageUrl();
        Assert.assertTrue(actualResult);
    }

    @Then("sees products")
    public void sees_products() {
        boolean actualResult = productsPage.isProductsListVisible();
        Assert.assertTrue(actualResult);
    }

    @When("user click burger menu")
    public void user_click_burger_menu() {
        productsPage.clickBurgerMenuBtn();
    }

    @When("click logout button")
    public void click_logout_button() {
        loginPage = productsPage.clickLogout();
    }

    @Then("user redirects to login page")
    public void user_redirects_to_login_page() {
        boolean actualResult = loginPage.loginFormVisibility();
        Assert.assertTrue(actualResult);
    }
}
