package com.qinyuan15.utils.lib;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * Utils class about test file
 * Created by qinyuan on 15-1-2.
 */
public class TestFileUtils {
    public static String tempDir = System.getProperty("java.io.tmpdir");

    private TestFileUtils() {
    }

    public static String getAbsolutePath(String fileName) {
        return getFileByName(fileName).getAbsolutePath();
    }

    private static File getFileByName(String fileName) {
        return new File("src/test/resources/" + fileName);
    }

    public static String read(String fileName) throws IOException {
        File file = getFileByName(fileName);
        return FileUtils.readFileToString(file, "utf8");
    }
}
