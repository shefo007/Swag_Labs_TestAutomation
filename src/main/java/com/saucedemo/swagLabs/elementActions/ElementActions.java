package com.saucedemo.swagLabs.elementActions;

import com.saucedemo.swagLabs.utils.LogsUtil;
import com.saucedemo.swagLabs.utils.Scrolling;
import com.saucedemo.swagLabs.utils.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;


public class ElementActions {

    private ElementActions() {
        throw new AssertionError();
    }

    public static WebElement findElement(WebDriver driver, By locator) {
        Waits.waitForElementVisible(driver, locator);
        Scrolling.scrollToElement(driver, locator);
        return driver.findElement(locator);
    }

    public static List<WebElement> findElements(WebDriver driver, By locator) {
        Waits.waitForElementsVisible(driver, locator);
        return driver.findElements(locator);
    }

    public static void typeTxt(WebDriver driver, By locator, String txt) {
        driver.findElement(locator).clear();
        findElement(driver, locator).sendKeys(txt);
        LogsUtil.info("Type data: ", txt, " in the field: ", locator.toString());
    }

    public static void clickElement(WebDriver driver, By locator) {
        Waits.waitForElementClickable(driver, locator);
        driver.findElement(locator).click();
        LogsUtil.info("Click on ", locator.toString());
    }

    public static boolean isElementVisible(WebDriver driver, By locator) {
        try {
            WebElement element = Waits.waitForElementVisible(driver, locator);
            if (element.isDisplayed()) {
                return true;
            }
        } catch (TimeoutException e) {
            LogsUtil.warn(e.getMessage());
            return false;
        }
        return false;
    }

    public static boolean isElementInvisible(WebDriver driver, By locator) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Waits.NUMBER_OF_SECONDS));
            return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
        } catch (TimeoutException e) {
            LogsUtil.warn(e.getMessage());
            return false;
        }
    }


    public static String getText(WebDriver driver, By locator) {
        return findElement(driver, locator).getText();
    }

    public static Actions action(WebDriver driver) {
        return new Actions(driver);
    }


}
