package com.saucedemo.swagLabs.pages;

import com.saucedemo.swagLabs.elementActions.ElementActions;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import java.util.Objects;


public class LoginPage extends ElementActions {

    private final static By usernameFieldTxt = By.id("user-name");
    private final static By passwordFieldTxt = By.id("password");
    private final static By loginBtn = By.id("login-button");
    private final static By errorMessage = By.cssSelector("h3[data-test='error']");
    private final static By loginContainer = By.id("login_button_container");

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Step("Entering username: {0}")
    public void typeUsername(String username) {
        typeTxt(usernameFieldTxt, username);
    }

    @Step("Entering password: {0}")
    public void typePassword(String password) {
        typeTxt(passwordFieldTxt, password);
    }

    @Step("Clicking Login Button")
    public ProductsPage clickLoginValid() {
        clickElement(loginBtn);
        return new ProductsPage(driver);
    }

    @Step("Press Enter Key")
    public ProductsPage pressEnterKeyValidCredentials() {
        action().keyDown(Keys.ENTER).perform();
        return new ProductsPage(driver);
    }

    //Invalid Login for stay in the login page to make assertion for error messages
    @Step("Clicking Login Button")
    public void clickLoginInvalid() {
        clickElement(loginBtn);
    }

    @Step("Press Enter Key")
    public void pressEnterKeyInvalidCredentials() {
        action().keyDown(Keys.ENTER).perform();
    }

    public String getErrorMessage() {
        return getText(errorMessage);
    }

    public boolean checkPasswordMasked() {
        System.out.println(findElement(passwordFieldTxt).getDomAttribute("type"));
        return Objects.equals(findElement(passwordFieldTxt).
                getDomAttribute("type"), "password");
    }

    public boolean loginFormVisibility() {
        return isElementVisible(loginContainer);
    }

}
