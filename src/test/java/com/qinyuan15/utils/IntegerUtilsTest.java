package com.qinyuan15.utils;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test IntegerUtils
 * Created by qinyuan on 15-4-23.
 */
public class IntegerUtilsTest {
    @Test
    public void testIsPositive() throws Exception {
        assertThat(IntegerUtils.isPositive(1)).isTrue();
        assertThat(IntegerUtils.isPositive(0)).isFalse();
        assertThat(IntegerUtils.isPositive(-1)).isFalse();
        assertThat(IntegerUtils.isPositive(null)).isFalse();
    }
}
