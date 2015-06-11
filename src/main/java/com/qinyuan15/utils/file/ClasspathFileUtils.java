package com.qinyuan15.utils.file;

import java.io.File;
import java.net.URL;

/**
 * Get file in classpath
 * Created by qinyuan on 15-6-11.
 */
public class ClasspathFileUtils {
    public static File getFile(String relativePath) {
        URL url = ClasspathFileUtils.class.getClassLoader().getResource(relativePath);
        return url == null ? null : new File(url.getFile());
    }
}
