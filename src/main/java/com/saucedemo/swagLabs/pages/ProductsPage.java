package com.saucedemo.swagLabs.pages;


import com.saucedemo.swagLabs.utils.BrowserActions;
import com.saucedemo.swagLabs.utils.LogsUtil;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.saucedemo.swagLabs.elementActions.ElementActions.*;


public class ProductsPage {

    private final WebDriver driver;
    private final static By productsList = By.className("inventory_list");
    private final static By burgerMenuBtn = By.id("react-burger-menu-btn");
    private final static By logoutLink = By.id("logout_sidebar_link");
    private final static By cartIcon = By.cssSelector(".shopping_cart_link");
    private final static By cartIconNotification = By.cssSelector(".shopping_cart_badge");
    // itemCard is the box of the product with all its details name, price, desc, img and addToCart button
    private final static By itemCard = By.cssSelector(".inventory_item");
    private final static By addToCartRemoveButton = By.tagName("button");
    private final static By itemPrice = By.cssSelector(".inventory_item_price");


    public ProductsPage(WebDriver webDriver) {
        this.driver = webDriver;
    }

    @Step("Check products page url")
    public boolean checkProductsPageUrl() {
        return BrowserActions.getCurrentURL(driver).contains("/inventory");
    }

    public Boolean isProductsListVisible() {
        return isElementVisible(driver, productsList);
    }

    @Step("Click on Burger Menu Icon")
    public void clickBurgerMenuBtn() {
        clickElement(driver, burgerMenuBtn);
    }

    @Step("Click on Logout")
    public LoginPage clickLogout() {
        clickElement(driver, logoutLink);
        return new LoginPage(driver);
    }

    @Step("Click on CartPage Icon")
    public CartPage clickCartIcon() {
        clickElement(driver, cartIcon);
        return new CartPage(driver);
    }

    @Step("Adding specific Item to cart: {0}")
    public void addSpecificProductToCart(String productName) {
        LogsUtil.info("Adding " + productName + " to cart");
        clickAddToCartRemoveButton(productName);
    }

    @Step("Remove specific Item from cart: {0}")
    public void removeSpecificProductFromCart(String productName) {
        LogsUtil.info("Removing " + productName + " from cart");
        clickAddToCartRemoveButton(productName);
    }

    @Step("Check specific product added to cart: {0}")
    public Boolean isProductAddedToCart(String productName) {
        LogsUtil.info(productName + " added to cart successfully");
        return checkProductStatus(productName, "Remove");
    }

    @Step("Check specific product removed from cart: {0}")
    public Boolean isProductRemovedFromCart(String productName) {
        LogsUtil.info(productName + " removed from cart successfully");
        return checkProductStatus(productName, "Add to cart");
    }

    public String getNotificationTxtOnCartIcon() {
        try {
            if (isNotificationVisibleOnCart())
                return getText(driver, cartIconNotification);
        } catch (NoSuchElementException e) {
            LogsUtil.error(e.getMessage());
        }

        return "Not Found that element";
    }

    public Boolean isNotificationVisibleOnCart() {
        return isElementVisible(driver, cartIconNotification);
    }

    // This because this button can add item to cart and with a second click remove
    // what I mean, if button is "Add to cart" within clicking it will add item to cart
    // and will be "Remove", if I click it again will remove the item from cart
    private void clickAddToCartRemoveButton(String productName) {
        long start = System.nanoTime();
        List<WebElement> items = findElements(driver, itemCard);
        WebElement specificItem = items.stream()
                .filter(item -> item.getText().contains(productName))
                .findFirst().orElseThrow(() -> new RuntimeException("item not found"));
        specificItem.findElement(addToCartRemoveButton).click();
        long end = System.nanoTime();
        double seconds = (end - start) / 1_000_000_000.0;
        System.out.println("Execution time: " + seconds + " seconds");
    }

    private Boolean checkProductStatus(String productName, String status) {
        List<WebElement> items = findElements(driver, itemCard);
        WebElement specificItem = items.stream()
                .filter(item -> item.getText().contains(productName))
                .findFirst().orElseThrow(() -> new RuntimeException("item not found"));

        Boolean actualValue = specificItem.findElement(addToCartRemoveButton).getText().equals(status);
        return actualValue;
    }

}
