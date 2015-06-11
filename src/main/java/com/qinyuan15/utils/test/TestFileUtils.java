package com.qinyuan15.utils.test;

import com.qinyuan15.utils.file.ClasspathFileUtils;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * Utils class about test file
 * Created by qinyuan on 15-1-2.
 */
public class TestFileUtils {
    public final static String tempDir = System.getProperty("java.io.tmpdir");

    private TestFileUtils() {
    }

    public static String getAbsolutePath(String fileName) {
        File file = getFileByName(fileName);
        return file == null ? null : file.getAbsolutePath();
    }

    private static File getFileByName(String fileName) {
        File file = new File("src/test/resources/" + fileName);
        if (file.exists()) {
            return file;
        } else {
            return ClasspathFileUtils.getFile(fileName);
        }
    }

    public static String read(String fileName) throws IOException {
        File file = getFileByName(fileName);
        return FileUtils.readFileToString(file, "utf8");
    }
}
