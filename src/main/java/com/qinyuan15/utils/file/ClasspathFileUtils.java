package com.qinyuan15.utils.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

/**
 * Get file in classpath
 * Created by qinyuan on 15-6-11.
 */
public class ClasspathFileUtils {
    public static File getFile(String relativePath) {
        URL url = ClasspathFileUtils.class.getClassLoader().getResource(relativePath);
        return url == null ? null : new File(url.getFile());
    }

    public static InputStream getInputStream(String relativePath) {
        File file = getFile(relativePath);
        try {
            return new FileInputStream(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Properties getProperties(String relativePath) {
        Properties props = new Properties();
        try {
            props.load(getInputStream(relativePath));
            return props;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
