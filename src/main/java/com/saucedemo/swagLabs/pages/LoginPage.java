package com.saucedemo.swagLabs.pages;


import com.saucedemo.swagLabs.elementActions.ElementActions;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import java.util.Objects;

import static com.saucedemo.swagLabs.elementActions.ElementActions.*;


public class LoginPage {

    private final WebDriver driver;
    private final static By usernameFieldTxt = By.id("user-name");
    private final static By passwordFieldTxt = By.id("password");
    private final static By loginBtn = By.id("login-button");
    private final static By errorMessage = By.cssSelector("h3[data-test='error']");
    private final static By loginContainer = By.id("login_button_container");

    public LoginPage(WebDriver webDriver) {
        this.driver = webDriver;
    }

    @Step("Entering username: {0}")
    public void typeUsername(String username) {
        typeTxt(driver, usernameFieldTxt, username);
    }

    @Step("Entering password: {0}")
    public void typePassword(String password) {
        typeTxt(driver, passwordFieldTxt, password);
    }

    @Step("Clicking Login Button")
    public ProductsPage clickLoginValid() {
        clickElement(driver, loginBtn);
        return new ProductsPage(driver);
    }

    @Step("Press Enter Key")
    public ProductsPage pressEnterKeyValidCredentials() {
        action(driver).keyDown(Keys.ENTER).perform();
        return new ProductsPage(driver);
    }

    //Invalid Login for stay in the login page to make assertion for error messages
    @Step("Clicking Login Button")
    public void clickLoginInvalid() {
        clickElement(driver, loginBtn);
    }

    @Step("Press Enter Key")
    public void pressEnterKeyInvalidCredentials() {
        action(driver).keyDown(Keys.ENTER).perform();
    }

    public String getErrorMessage() {
        return getText(driver, errorMessage);
    }

    public boolean checkPasswordMasked() {
        System.out.println(findElement(driver, passwordFieldTxt).getDomAttribute("type"));
        return Objects.equals(findElement(driver, passwordFieldTxt).
                getDomAttribute("type"), "password");
    }

    public boolean loginFormVisibility() {
        return isElementVisible(driver, loginContainer);
    }

}
