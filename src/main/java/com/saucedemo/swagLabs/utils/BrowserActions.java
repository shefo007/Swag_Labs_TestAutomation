package com.saucedemo.swagLabs.utils;

import io.qameta.allure.Step;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class BrowserActions {

    private WebDriver driver;

    private BrowserActions() {

    }

    /***************************  URL Controlling and navigation  ***********************************/

    public static void navigateToURL(WebDriver driver, String url) {
        driver.get(url);
    }

    @Step("Getting Current URL")
    public static String getCurrentURL(WebDriver driver) {
        LogsUtil.info("Current URL: ", driver.getCurrentUrl());
        return driver.getCurrentUrl();
    }


    public static String getPageTitle(WebDriver driver){
        return driver.getTitle();
    }

    public static void navigateForward(WebDriver driver) {
        driver.navigate().forward();
    }

    public static void navigateBack(WebDriver driver) {
        driver.navigate().back();
    }

    public static void refreshCurrentPage(WebDriver driver) {
        driver.navigate().refresh();
    }

    /***************************  Cookies  ***********************************/

    public BrowserActions addCookie(Cookie cookie) {
        driver.manage().addCookie(cookie);
        return this;
    }


    public BrowserActions deleteCookie(Cookie cookie) {
        driver.manage().deleteCookie(cookie);
        return this;
    }

    public BrowserActions deleteCookieWithName(String cookieName) {
        driver.manage().deleteCookieNamed(cookieName);
        return this;
    }

    public BrowserActions deleteAllCookies() {
        driver.manage().deleteAllCookies();
        return this;
    }

    /***************************** Window Control  **********************************************/
    public BrowserActions maximizeWindows() {
        driver.manage().window().maximize();
        return this;
    }

    public BrowserActions minimizeWindow() {
        driver.manage().window().minimize();
        return this;
    }

    public BrowserActions fullScreen() {
        driver.manage().window().fullscreen();
        return this;
    }


    public BrowserActions scrollToBottom() {
        new Actions(driver).scrollByAmount(0, 2500).build().perform();
        return this;
    }
}
