package com.qinyuan15.utils;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test ObjectUtils
 * Created by qinyuan on 15-6-14.
 */
public class ObjectUtilsTest {
    @Test
    public void testIsDifferent() throws Exception {
        assertThat(ObjectUtils.isDifferent(null, null)).isFalse();
        assertThat(ObjectUtils.isDifferent(null, "")).isTrue();
        assertThat(ObjectUtils.isDifferent("", null)).isTrue();
        assertThat(ObjectUtils.isDifferent("", "")).isFalse();
        assertThat(ObjectUtils.isDifferent(1, 1)).isFalse();
        assertThat(ObjectUtils.isDifferent("1", 1)).isTrue();
    }
}
