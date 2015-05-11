package com.qinyuan15.utils.mvc;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test UrlBuilder
 * Created by qinyuan on 15-5-10.
 */
public class UrlBuilderTest {
    @Test
    public void testBuild() throws Exception {
        String url = new UrlBuilder()
                .setBaseUrl("helloWorld.html")
                .addParam("key1", 2)
                .addParam("key0", 0)
                .addParam("key3", "hello")
                .addParam("key4", null)
                .build();

        assertThat(url.equals("helloWorld.html?key1=2&key3=hello") ||
                url.equals("helloWorld.html?key3=hello&key1=2")).isTrue();

        url = new UrlBuilder().setBaseUrl("test").addParam("key", 2.212).build();
        assertThat(url).isEqualTo("test?key=2.212");
    }
}
