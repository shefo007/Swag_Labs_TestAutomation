package com.saucedemo.swagLabs.listeners;

import com.saucedemo.swagLabs.utils.FilesUtils;
import com.saucedemo.swagLabs.utils.LogsUtil;
import org.openqa.selenium.logging.Logs;
import org.testng.*;
import org.testng.annotations.BeforeSuite;
import utils.AllureUtils;

import java.io.File;

public class TestNGListeners implements IExecutionListener, IInvokedMethodListener, ITestListener {

    private static final File ALLURE_RESULTS = new File(AllureUtils.ALLURE_RESULTS_PATH);
    private static final File LOGS_DIR = new File(LogsUtil.LOGS_PATH);


    @Override
    public void onExecutionStart() {
        LogsUtil.info("Test Execution starting");
        FilesUtils.cleanDirectory(ALLURE_RESULTS);
        FilesUtils.cleanDirectory(LOGS_DIR);
    }


    @Override
    public void onExecutionFinish() {
        LogsUtil.info("Test Execution Finish");
    }

    @Override
    public void onTestStart(ITestResult result) {
        LogsUtil.info("Test case: ", result.getName(), " passed");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        LogsUtil.info("Test case: ", result.getName(), " skipped");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        LogsUtil.info("Test case: ", result.getName(), " failed");
    }

}
