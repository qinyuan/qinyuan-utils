package com.qinyuan15.utils.http;

import com.qinyuan15.utils.ip.IpUtils;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test IpUtils
 * Created by qinyuan on 15-5-17.
 */
public class IpUtilsTest {
    @Test
    public void testIsIPv4() throws Exception {
        assertThat(IpUtils.isIPv4("192.168.8.1")).isTrue();
        assertThat(IpUtils.isIPv4("0.0.0.0")).isTrue();
        assertThat(IpUtils.isIPv4("0.0.0.0.124")).isFalse();
        assertThat(IpUtils.isIPv4("helloWorld")).isFalse();
    }
}
