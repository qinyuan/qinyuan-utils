package com.qinyuan15.utils;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test StringUtils
 * Created by qinyuan on 15-6-16.
 */
public class StringUtilsTest {
    @Test
    public void testReplaceFirst() throws Exception {
        String from = "192.168.8.1";
        String to = "172.16.114.114";

        String test = "hello192.168.8.1world192.168.8.1";
        assertThat(StringUtils.replaceFirst(test, from, to)).isEqualTo("hello172.16.114.114world192.168.8.1");

        test = "hello192.168.8.21world192.168.8.1";
        assertThat(StringUtils.replaceFirst(test, from, to)).isEqualTo("hello192.168.8.21world172.16.114.114");

        test = "hello192.168.8.1world";
        assertThat(StringUtils.replaceFirst(test, from, to)).isEqualTo("hello172.16.114.114world");

        test = "192.168.8.1world192.168.8.1";
        assertThat(StringUtils.replaceFirst(test, from, to)).isEqualTo("172.16.114.114world192.168.8.1");
    }
}
