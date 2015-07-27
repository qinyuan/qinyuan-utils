package com.qinyuan15.utils.ip;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BaiduIpLocationQuerierTest {
    @Test
    public void test() {
        BaiduIpLocationQuerier querier = new BaiduIpLocationQuerier();
        String location = querier.getLocation("113.87.101.227");
        assertThat(location).isEqualTo("广东省深圳市 电信");
    }
}
