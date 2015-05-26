package com.qinyuan15.utils.http;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test HttpUserAgentBuilder
 * Created by qinyuan on 15-5-25.
 */
public class HttpUserAgentBuilderTest {
    @Test
    public void testBuildRandomly() throws Exception {
        HttpUserAgentBuilder builder = new HttpUserAgentBuilder();
        for (int i = 0; i < 100; i++) {
            assertThat(builder.buildRandomly()).isNotEmpty();
            //System.out.println(builder.buildRandomly());
        }
    }
}
