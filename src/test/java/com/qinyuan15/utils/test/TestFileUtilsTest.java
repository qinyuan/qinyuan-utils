package com.qinyuan15.utils.test;

import org.junit.Test;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test TestFileUtils
 * Created by qinyuan on 15-4-23.
 */
public class TestFileUtilsTest {
    @Test
    public void testGetAbsolutePath() throws Exception {
        assertThat(new File(TestFileUtils.getAbsolutePath("baidu.html")).isFile()).isTrue();
        assertThat(TestFileUtils.getAbsolutePath("baidu.htm")).isNull();
    }

    @Test
    public void testRead() throws Exception {
        assertThat(TestFileUtils.read("baidu.html")).isNotEmpty()
                .contains("百度一下，你就知道");
    }
}
