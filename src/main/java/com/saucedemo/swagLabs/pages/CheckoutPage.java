package com.saucedemo.swagLabs.pages;

import com.saucedemo.swagLabs.utils.BrowserActions;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.saucedemo.swagLabs.elementActions.ElementActions.*;

public class CheckoutPage {

    private final WebDriver driver;
    private final static By firstNameField = By.id("first-name");
    private final static By lastNameField = By.id("last-name");
    private final static By postalField = By.id("postal-code");
    private final static By continueBtn = By.id("continue");
    private final static By finishBtn = By.id("finish");
    private final static By orderConformation = By.id("checkout_complete_container");
    private final static By orderConformationMSG = By.cssSelector(".complete-header");
    private final static By errorMsg = By.cssSelector("h3[data-test='error']");

    public CheckoutPage(WebDriver webDriver) {
        this.driver = webDriver;
    }

    public boolean isCheckoutPageVisible() {
        return BrowserActions.getCurrentURL(driver).contains("/checkout");
    }

    public boolean isOrderCompleted() {
        return isElementVisible(driver, orderConformation);
    }

    public String getOrderConfirmationMsg() {
        return getText(driver, orderConformationMSG);
    }

    public String getErrorMsg() {
        return getText(driver, errorMsg);
    }

    @Step("Enter Checkout Info")
    public void fillInfo(String firstName, String lastName, String postalCode) {
        typeTxt(driver, firstNameField, firstName);
        typeTxt(driver, lastNameField, lastName);
        typeTxt(driver, postalField, postalCode);
    }

    @Step("Click continue button")
    public CheckoutPage clickContinue() {
        clickElement(driver, continueBtn);
        return this;
    }

    @Step("Click finish button")
    public void clickFinish() {
        clickElement(driver, finishBtn);
    }


}
