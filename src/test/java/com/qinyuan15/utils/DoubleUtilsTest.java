package com.qinyuan15.utils;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test DoubleUtils
 * Created by qinyuan on 15-6-23.
 */
public class DoubleUtilsTest {
    @Test
    public void testIsNotNegative() throws Exception {
        assertThat(DoubleUtils.isNotNegative(0.0)).isTrue();
        assertThat(DoubleUtils.isNotNegative(0.1)).isTrue();
        assertThat(DoubleUtils.isNotNegative(-0.1)).isFalse();
        assertThat(DoubleUtils.isNotNegative(null)).isFalse();
    }

    @Test
    public void testIsPositive() {
        assertThat(DoubleUtils.isPositive(0.0)).isFalse();
        assertThat(DoubleUtils.isPositive(0.1)).isTrue();
        assertThat(DoubleUtils.isPositive(-0.1)).isFalse();
        assertThat(DoubleUtils.isPositive(null)).isFalse();
    }
}
