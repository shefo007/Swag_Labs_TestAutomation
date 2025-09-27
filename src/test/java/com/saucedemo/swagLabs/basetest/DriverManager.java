package com.saucedemo.swagLabs.basetest;

import com.saucedemo.swagLabs.driver.DriverFactory;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ThreadGuard;

public class DriverManager {

    private final static ThreadLocal<WebDriver> threadDriver = new ThreadLocal<>();


    @Step("Creating driver on: {browserType}")
    public static void setDriver(String browserType) {
        threadDriver.set(ThreadGuard.protect(new DriverFactory()
                .createInstance(browserType)));
    }

    public static WebDriver getDriver() {
        return threadDriver.get();
    }

    @Step("Closing the browser")
    public static void quitDriver() {
        if (getDriver() != null) {
            getDriver().quit();
            threadDriver.remove();
        }
    }
}
