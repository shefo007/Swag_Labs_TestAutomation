package com.saucedemo.swagLabs.basetest;

import com.saucedemo.swagLabs.config.ConfigurationManager;
import com.saucedemo.swagLabs.pages.ProductsPage;
import utils.AllureUtils;
import com.saucedemo.swagLabs.utils.LogsUtil;
import org.testng.annotations.*;
import com.saucedemo.swagLabs.pages.LoginPage;
import utils.JsonUtils;


public class BaseTest {

    protected LoginPage loginPage;
    protected ProductsPage productsPage;
    private static final String BROWSER_TYPE = ConfigurationManager.configuration().browser();
    private static final String URL = ConfigurationManager.configuration().url();
    protected static final JsonUtils LOGIN_DATA = new JsonUtils("LoginJsonData");


    @BeforeMethod(onlyForGroups = "TestLogin")
    public synchronized void globalSetUp() {
        LogsUtil.info("Driver created on: ", BROWSER_TYPE);
        DriverManager.setDriver(BROWSER_TYPE);
        LogsUtil.info("Opening ", BROWSER_TYPE, " browser....");
        DriverManager.getDriver().get(URL);
        loginPage = new LoginPage(DriverManager.getDriver());
    }

    @BeforeMethod(onlyForGroups = {"validLogin"})
    public synchronized void validLogin() {
        globalSetUp();
        loginPage.typeUsername(LOGIN_DATA.getJsonData("validUsername"));
        loginPage.typePassword(LOGIN_DATA.getJsonData("validPassword"));
        productsPage = loginPage.clickLoginValid();
    }

    @AfterMethod(alwaysRun = true)
    public synchronized void quit() {
        LogsUtil.info("Closing the browser....");
        DriverManager.quitDriver();
    }

    @AfterSuite
    public void afterClass() {
        AllureUtils.attachLogsToAllureReport();
    }

}
