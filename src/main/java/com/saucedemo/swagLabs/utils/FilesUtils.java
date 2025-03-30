package com.saucedemo.swagLabs.utils;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;

public class FilesUtils {

    private FilesUtils(){
        super();
    }

    public static File getLatestFile(String dirPath) {
        File folder = new File(dirPath);
        File[] files = folder.listFiles();

        if (files == null || files.length == 0) {
            LogsUtil.warn("No files found in directory: " + dirPath);
            return null;
        }

        File latestFile = files[0];
        for (File file : files) {
            if (file.lastModified() > latestFile.lastModified()) {
                latestFile = file;
            }
        }
        return latestFile;
    }

    public static void cleanDirectory(File dirPath) {
        if (dirPath == null || !dirPath.exists()) {
            LogsUtil.warn("Directory does not exist: " + dirPath);
        }

        assert dirPath != null;
        try {
            if (FileUtils.isDirectory(dirPath)) {
                FileUtils.cleanDirectory(dirPath);
            }
        } catch (IOException e) {
            LogsUtil.error(e.getMessage());
        }
    }

}
