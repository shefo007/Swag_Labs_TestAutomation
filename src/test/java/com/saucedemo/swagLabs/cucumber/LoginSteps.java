package com.saucedemo.swagLabs.cucumber;

import com.saucedemo.swagLabs.basetest.DriverManager;
import com.saucedemo.swagLabs.config.Configuration;
import com.saucedemo.swagLabs.testdata.LoginData;
import io.cucumber.java.en.*;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import com.saucedemo.swagLabs.pages.LoginPage;
import com.saucedemo.swagLabs.pages.ProductsPage;

import java.io.IOException;

import static com.saucedemo.swagLabs.config.ConfigurationManager.configuration;

public class LoginSteps {

    protected static Configuration configuration;

    private LoginPage loginPage;
    private ProductsPage productsPage;
    private final static LoginData LOGIN_DATA = new LoginData();

    @Given("user is on login page")
    public void user_is_in_login_page() {
        // Write code here that turns the phrase above into concrete actions
        configuration = configuration();

        DriverManager.setDriver(configuration.browser());
        DriverManager.getDriver().get(configuration.url());
        loginPage = new LoginPage(DriverManager.getDriver());
    }

    @When("user enters valid credentials")
    public void user_enters_valid_credentials() throws IOException, ParseException {
        // Write code here that turns the phrase above into concrete actions
        LOGIN_DATA.data();
        loginPage.typeUsername(LOGIN_DATA.validUsername);
        loginPage.typePassword(LOGIN_DATA.validPassword);

    }

    @When("user enters invalid credentials")
    public void user_enters_invalid_credentials() throws IOException, ParseException {
        // Write code here that turns the phrase above into concrete actions
        LOGIN_DATA.data();
        loginPage.typeUsername(LOGIN_DATA.inValidUsername);
        loginPage.typePassword(LOGIN_DATA.invalidPassword);
    }

    @When("clicks login button with valid credentials")
    public void clicks_login_button_with_valid_credentials() {
        // Write code here that turns the phrase above into concrete actions
        productsPage = loginPage.clickLoginValid();

    }

    @When("clicks login button with invalid credentials")
    public void clicks_login_button_with_invalid_credentials() {
        // Write code here that turns the phrase above into concrete actions
        loginPage.clickLoginInvalid();
    }

    @Then("user sees an error message appeared")
    public void user_sees_an_error_message_appeared() throws IOException, ParseException {
        // Write code here that turns the phrase above into concrete actions
        LOGIN_DATA.data();
        String actualResult = loginPage.getErrorMessage();
        Assert.assertEquals(actualResult, LOGIN_DATA.invalidUsernameOrPassword);

    }

    @Then("user redirects to products page")
    public void user_redirects_to_products_page() {
        // Write code here that turns the phrase above into concrete actions
        boolean actualResult = productsPage.checkProductsPageUrl();
        Assert.assertTrue(actualResult);
    }

    @Then("sees products")
    public void sees_products() {
        // Write code here that turns the phrase above into concrete actions
        boolean actualResult = productsPage.isProductsListVisible();
        Assert.assertTrue(actualResult);
    }

    @When("user click burger menu")
    public void user_click_burger_menu() {
        // Write code here that turns the phrase above into concrete actions
        productsPage.clickBurgerMenuBtn();
    }

    @When("click logout button")
    public void click_logout_button() {
        // Write code here that turns the phrase above into concrete actions
        loginPage = productsPage.clickLogout();
    }

    @Then("user redirects to login page")
    public void user_redirects_to_login_page() {
        // Write code here that turns the phrase above into concrete actions
        boolean actualResult = loginPage.loginFormVisibility();
        Assert.assertTrue(actualResult);
    }
}
