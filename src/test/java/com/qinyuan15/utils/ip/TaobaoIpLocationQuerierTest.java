package com.qinyuan15.utils.ip;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TaobaoIpLocationQuerierTest {
    @Test
    public void test() {
        TaobaoIpLocationQuerier querier = new TaobaoIpLocationQuerier();
        String location = querier.getLocation("113.87.101.227");
        assertThat(location).isEqualTo("中国-华南-广东省-深圳市");
    }
}