package com.qinyuan15.utils.ip;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ComposableIpLocationQuerierTest {
    @Test
    public void testGetLocation() throws Exception {
        ComposableIpLocationQuerier querier = new ComposableIpLocationQuerier(
                new BaiduIpLocationQuerier(), new TaobaoIpLocationQuerier(), new SogouIpLocationQuerier());
        assertThat(querier.getLocation("113.87.101.227")).isEqualTo("广东省深圳市 电信");
    }
}
