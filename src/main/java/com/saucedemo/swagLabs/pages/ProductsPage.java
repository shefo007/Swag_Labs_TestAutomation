package com.saucedemo.swagLabs.pages;

import com.saucedemo.swagLabs.elementActions.ElementActions;
import com.saucedemo.swagLabs.utils.BrowserActions;
import com.saucedemo.swagLabs.utils.LogsUtil;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;


public class ProductsPage extends ElementActions {

    private final static By productsList = By.className("inventory_list");
    private final static By burgerMenuBtn = By.id("react-burger-menu-btn");
    private final static By logoutLink = By.id("logout_sidebar_link");
    private final static By cartIcon = By.cssSelector(".shopping_cart_link");
    private final static By cartIconNotification = By.cssSelector(".shopping_cart_badge");

    public ProductsPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Step("Check products page url")
    public boolean checkProductsPageUrl() {
        return BrowserActions.getCurrentURL(driver).contains("/inventory");
    }

    public Boolean isProductsListVisible() {
        return isElementVisible(productsList);
    }

    @Step("Click on Burger Menu Icon")
    public void clickBurgerMenuBtn() {
        clickElement(burgerMenuBtn);
    }

    @Step("Click on Logout")
    public LoginPage clickLogout() {
        clickElement(logoutLink);
        return new LoginPage(driver);
    }

    @Step("Click on CartPage Icon")
    public CartPage clickCartIcon() {
        clickElement(cartIcon);
        return new CartPage(driver);
    }

    @Step("Adding specific Item to cart: {0}")
    public void addSpecificProductToCart(String productName) {
        LogsUtil.info("Adding " + productName + " to cart");
        By addToCartBtn = By.xpath("//div[.='" + productName + "']/ancestor::div[@class='inventory_item']//button");
        clickElement(addToCartBtn);
    }

    @Step("Remove specific Item from cart: {0}")
    public void removeSpecificProductFromCart(String productName) {
        LogsUtil.info("Removing " + productName + " from cart");
        By removeBtn = By.xpath("//div[.='" + productName + "']/ancestor::div[@class='inventory_item']//button");
        clickElement(removeBtn);
    }

    @Step("Check specific product added to cart: {0}")
    public Boolean isProductAddedToCart(String productName) {
        By addToCartBtn = By.xpath("//div[.='" + productName + "']/ancestor::div[@class='inventory_item']//button");
        Boolean actualValue = getText(addToCartBtn).equals("Remove");
        LogsUtil.info(productName + " added to cart successfully");
        return actualValue;
    }

    @Step("Check specific product removed from cart: {0}")
    public Boolean isProductRemovedFromCart(String productName) {
        By addToCartBtn = By.xpath("//div[.='" + productName + "']/ancestor::div[@class='inventory_item']//button");
        Boolean actualValue = getText(addToCartBtn).equals("Add to cart");
        LogsUtil.info(productName + " removed from cart successfully");
        return actualValue;
    }

    public String getNotificationTxtOnCartIcon() {
        try {
            if (isNotificationVisibleOnCart())
                return getText(cartIconNotification);
        } catch (NoSuchElementException e) {
            LogsUtil.error(e.getMessage());
        }

        return "Not Found that element";
    }

    public Boolean isNotificationVisibleOnCart() {
        return isElementVisible(cartIconNotification);
    }

}
