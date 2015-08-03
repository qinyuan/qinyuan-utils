package com.qinyuan15.utils.html;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HtmlUtilsTest {
    @Test
    public void testToText() throws Exception {
        assertThat(HtmlUtils.toText("<p>HelloWorld</p>")).isEqualTo("HelloWorld");
        assertThat(HtmlUtils.toText("<h1>HelloWorld</h1>")).isEqualTo("HelloWorld");
        assertThat(HtmlUtils.toText("<h1>HelloWorld<input type='text' name='text' /></h1>")).isEqualTo("HelloWorld");
    }
}
