package com.qinyuan15.utils.file;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test ClasspathFileUtils
 * Created by qinyuan on 15-6-11.
 */
public class ClasspathFileUtilsTest {
    @Test
    public void testGetFile() throws Exception {
        assertThat(ClasspathFileUtils.getFile("baidu.html")).isFile();
        assertThat(ClasspathFileUtils.getFile("baidu2.html")).isNull();
    }
}
