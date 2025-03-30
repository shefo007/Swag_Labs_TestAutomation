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

    protected WebDriver driver;
    protected static WebDriverWait wait;

    public ElementActions(WebDriver webDriver) {
        this.driver = webDriver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(Waits.NUMBER_OF_SECONDS));
    }

    protected WebElement findElement(By locator) {
        Waits.waitForElementVisible(driver, locator);
        Scrolling.scrollToElement(driver, locator);
        return driver.findElement(locator);
    }

    protected List<WebElement> findElements(By locator) {
        Waits.waitForElementsVisible(driver, locator);
        return driver.findElements(locator);
    }

    protected void typeTxt(By locator, String txt) {
        driver.findElement(locator).clear();
        findElement(locator).sendKeys(txt);
        LogsUtil.info("Type data: ", txt, " in the field: ", locator.toString());
    }

    protected void clickElement(By locator) {
        Waits.waitForElementClickable(driver, locator);
        driver.findElement(locator).click();
        LogsUtil.info("Click on ", locator.toString());
    }

    protected boolean isElementVisible(By locator) {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).isDisplayed();
        } catch (TimeoutException e) {
            LogsUtil.warn(e.getMessage());
            return false;
        }
    }

    protected boolean isElementInvisible(By locator) {
        try {
            return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
        } catch (TimeoutException e) {
            LogsUtil.warn(e.getMessage());
            return false;
        }
    }


    protected String getText(By locator) {
        return findElement(locator).getText();
    }

    protected Actions action() {
        return new Actions(driver);
    }


}
