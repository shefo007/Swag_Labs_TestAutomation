package com.saucedemo.swagLabs.pages;

import com.saucedemo.swagLabs.elementActions.ElementActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage extends ElementActions {

    private final static By checkoutInfoForm = By.cssSelector(".checkout_info");
    private final static By firstNameField = By.id("first-name");
    private final static By lastNameField = By.id("last-name");
    private final static By postalField = By.id("postal-code");
    private final static By cancelBtn = By.id("cancel");
    private final static By continueBtn = By.id("continue");

    public CheckoutPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void fillInfo(String firstName, String lastName, String postalCode) {
        typeTxt(firstNameField, firstName);
        typeTxt(lastNameField, lastName);
        typeTxt(postalField, postalCode);
    }

    public CartPage clickCancel() {
        clickElement(cancelBtn);
        return new CartPage(driver);
    }

    public void clickContinue() {
        clickElement(continueBtn);
    }


}
