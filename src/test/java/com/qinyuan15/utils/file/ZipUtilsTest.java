package com.qinyuan15.utils.file;

import com.qinyuan15.utils.test.TestFileUtils;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test ZipUtils
 * Created by qinyuan on 15-6-1.
 */
public class ZipUtilsTest {
    @Test
    public void testZip() throws Exception {
        String srcPath = TestFileUtils.getAbsolutePath("baidu.html");
        String destPath = "/tmp/baidu.zip";

        File destFile = new File(destPath);
        FileUtils.deleteQuietly(destFile);
        assertThat(destFile).doesNotExist();

        ZipUtils.zip(srcPath, destPath);
        assertThat(destFile).isFile();
    }

    @Test
    public void testUnzip() throws Exception {
        String srcPath = TestFileUtils.getAbsolutePath("baidu.zip");
        String destPath = "/tmp/baidu";


        File destDir = new File(destPath);
        FileUtils.deleteQuietly(destDir);
        assertThat(destDir).doesNotExist();

        ZipUtils.unzip(srcPath, destPath);
        assertThat(destDir).isDirectory();
        assertThat(new File(destPath + "/baidu.html")).isFile();
    }
}
