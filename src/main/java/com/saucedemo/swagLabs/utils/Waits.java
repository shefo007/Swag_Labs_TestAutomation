package com.saucedemo.swagLabs.utils;

import com.saucedemo.swagLabs.config.ConfigurationManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Waits {

    public static final int NUMBER_OF_SECONDS = ConfigurationManager.configuration().seconds();

    // Waits for single element
    public static WebElement waitForElementPresent(WebDriver driver, By locator) {
        LogsUtil.info("Waiting for element: ", locator.toString(), " to be present.");
        return new WebDriverWait(driver, Duration.ofSeconds(NUMBER_OF_SECONDS)).
                until(d -> d.findElement(locator));
    }

    public static WebElement waitForElementVisible(WebDriver driver, By locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(NUMBER_OF_SECONDS)).
                until(d -> {
                    WebElement element = waitForElementPresent(driver, locator);
                    LogsUtil.info("Waiting for element: ", locator.toString(), " to be visible.");
                    return element.isDisplayed() ? element : null;
                });
    }

    public static void waitForElementClickable(WebDriver driver, By locator) {
        new WebDriverWait(driver, Duration.ofSeconds(NUMBER_OF_SECONDS)).
                until(d -> {
                    WebElement element = waitForElementVisible(driver, locator);
                    LogsUtil.info("Waiting for element: ", locator.toString(), " to be clickable.");
                    return element.isEnabled() ? element : null;
                });
    }

    // Waits for elements
    public static List<WebElement> waitForElementsPresent(WebDriver driver, By locator) {
        LogsUtil.info("Waiting for elements: ", locator.toString(), " to be present.");
        return new WebDriverWait(driver, Duration.ofSeconds(NUMBER_OF_SECONDS))
                .until(d -> {
                    List<WebElement> elements = d.findElements(locator);
                    return !elements.isEmpty() ? elements : null;
                });
    }

    public static List<WebElement> waitForElementsVisible(WebDriver driver, By locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(NUMBER_OF_SECONDS))
                .until(d -> {
                    List<WebElement> elements = waitForElementsPresent(driver, locator);
                    LogsUtil.info("Waiting for elements: ", locator.toString(), " to be visible.");
                    return elements.stream().allMatch(WebElement::isDisplayed) ? elements : null;
                });
    }

    public static List<WebElement> waitForElementsClickable(WebDriver driver, By locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(NUMBER_OF_SECONDS))
                .until(d -> {
                    List<WebElement> elements = waitForElementsVisible(driver, locator);
                    LogsUtil.info("Waiting for elements: ", locator.toString(), " to be clickable.");
                    return elements.stream().allMatch(WebElement::isEnabled) ? elements : null;
                });
    }

}
