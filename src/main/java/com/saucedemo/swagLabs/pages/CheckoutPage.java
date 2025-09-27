package com.saucedemo.swagLabs.pages;

import com.saucedemo.swagLabs.elementActions.ElementActions;
import com.saucedemo.swagLabs.utils.BrowserActions;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage extends ElementActions {

    private final static By firstNameField = By.id("first-name");
    private final static By lastNameField = By.id("last-name");
    private final static By postalField = By.id("postal-code");
    private final static By continueBtn = By.id("continue");
    private final static By finishBtn = By.id("finish");
    private final static By orderConformation = By.id("checkout_complete_container");
    private final static By orderConformationMSG = By.cssSelector(".complete-header");
    private final static By errorMsg = By.cssSelector("h3[data-test='error']");

    public CheckoutPage(WebDriver webDriver) {
        super(webDriver);
    }

    public boolean isCheckoutPageVisible() {
        return BrowserActions.getCurrentURL(driver).contains("/checkout");
    }

    public boolean isOrderCompleted() {
        return isElementVisible(orderConformation);
    }

    public String getOrderConfirmationMsg() {
        return getText(orderConformationMSG);
    }

    public String getErrorMsg() {
        return getText(errorMsg);
    }

    @Step("Enter Checkout Info")
    public void fillInfo(String firstName, String lastName, String postalCode) {
        typeTxt(firstNameField, firstName);
        typeTxt(lastNameField, lastName);
        typeTxt(postalField, postalCode);
    }

    @Step("Click continue button")
    public CheckoutPage clickContinue() {
        clickElement(continueBtn);
        return this;
    }

    @Step("Click finish button")
    public void clickFinish() {
        clickElement(finishBtn);
    }


}
