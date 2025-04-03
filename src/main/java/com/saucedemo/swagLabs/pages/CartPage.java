package com.saucedemo.swagLabs.pages;

import com.saucedemo.swagLabs.elementActions.ElementActions;
import com.saucedemo.swagLabs.utils.BrowserActions;
import com.saucedemo.swagLabs.utils.LogsUtil;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.locators.RelativeLocator;

import java.util.List;

public class CartPage extends ElementActions {

    private final static By cartItems = By.xpath("//div[@class='cart_item']");
    private final static By cartIconNotification = By.cssSelector(".shopping_cart_badge");
    private final static By checkoutBtn = By.id("checkout");

    public CartPage(WebDriver webDriver) {
        super(webDriver);
    }

    public List<WebElement> getItemsInCart() {
        return findElements(cartItems);
    }

    public Item getSpecificItemFromCart(String itemName) {
        By itemLocator = By.xpath("//div[.='" + itemName + "']/ancestor::div[@class='cart_item']");
        WebElement item = findElement(itemLocator);

        return new Item(item);
    }

    @Step("Remove specific Item from cart: {0}")
    public void removeSpecificProductFromCart(String productName) {
        LogsUtil.info("Removing " + productName + " from cart");
        By removeBtn = RelativeLocator.with(By.tagName("button"))
                .below(By.xpath("//div[.='" + productName + "']"));
        clickElement(removeBtn);
    }

    @Step("Check specific product added to cart: {0}")
    public boolean isSpecificItemRemovedFromCart(String productName) {
        By productNameLocator = By.xpath("//div[.='" + productName + "']");
        return isElementInvisible(productNameLocator);
    }

    @Step("Check is cart empty")
    public boolean isCartEmpty() {
        return isElementInvisible(cartItems);
    }

    @Step("Click Checkout Button")
    public CheckoutPage clickCheckoutBtn() {
        clickElement(checkoutBtn);
        return new CheckoutPage(driver);
    }

    public boolean checkCartPageUrl() {
        return BrowserActions.getCurrentURL(driver).contains("/cart");
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

    public static class Item {

        private final WebElement item;
        private final By itemName = By.className("inventory_item_name");
        private final By itemQuantity = By.className("cart_quantity");
        private final By itemDesc = By.className("inventory_item_desc");
        private final By itemPrice = By.className("inventory_item_price");
        private final By itemRemoveBtn = By.tagName("button");


        private Item(WebElement item) {
            this.item = item;
        }

        public String getItemName() {
            return item.findElement(itemName).getText();
        }

        public String getItemDesc() {
            return item.findElement(itemDesc).getText();
        }

        public int getItemQuantity() {
            String quantity = item.findElement(itemQuantity).getText();
            return Integer.parseInt(quantity);
        }

        public int getPrice() {
            String price = item.findElement(itemPrice).getText().
                    replace("$", "");
            return Integer.parseInt(price);
        }

        public void getRemoveBtn() {
            item.findElement(itemRemoveBtn).click();
        }
    }

}
