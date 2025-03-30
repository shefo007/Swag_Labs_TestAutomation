package utils;

import com.saucedemo.swagLabs.utils.FilesUtils;
import com.saucedemo.swagLabs.utils.LogsUtil;
import io.qameta.allure.Allure;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

public class AllureUtils {

    public static final String ALLURE_RESULTS_PATH = "test-outputs/allure-results";

    private AllureUtils() {
        super();
    }

    public static void attachLogsToAllureReport() {
        try {
            File logFile = FilesUtils.getLatestFile(LogsUtil.LOGS_PATH);
            assert logFile != null;
            if (!logFile.exists()) {
                LogsUtil.warn("Log file does not exist: " + LogsUtil.LOGS_PATH);
            }
            Allure.addAttachment("logs.log", Files.readString(Path.of(logFile.getPath())));
            LogsUtil.info("Logs attached to Allure report");
        } catch (Exception e) {
            LogsUtil.error("Failed to attach logs to Allure report: "+ e.getMessage());
        }
    }
}
