package com.saucedemo.swagLabs.basetest;

import com.saucedemo.swagLabs.config.ConfigurationManager;
import utils.AllureUtils;
import com.saucedemo.swagLabs.utils.LogsUtil;
import org.testng.annotations.*;
import com.saucedemo.swagLabs.pages.LoginPage;


public abstract class BaseTest {

    protected LoginPage loginPage;
    private static final String BROWSER_TYPE = ConfigurationManager.configuration().browser();
    private static final String URL = ConfigurationManager.configuration().url();



    @BeforeMethod
    public synchronized void setUp() {
        LogsUtil.info("Driver created on: ", BROWSER_TYPE);
        DriverManager.setDriver(BROWSER_TYPE);
        LogsUtil.info("Opening ", BROWSER_TYPE, " browser....");
        DriverManager.getDriver().get(URL);
        loginPage = new LoginPage(DriverManager.getDriver());
    }

    @AfterMethod
    public synchronized void quit() {
        LogsUtil.info("Closing the browser....");
        DriverManager.quitDriver();
    }

    @AfterSuite
    public void afterClass() {
        AllureUtils.attachLogsToAllureReport();
    }

}
