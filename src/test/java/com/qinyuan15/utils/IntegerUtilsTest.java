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
        assertThat(IntegerUtils.isPositive((Integer) null)).isFalse();
    }

    @Test
    public void testIsPositive2() throws Exception {
        assertThat(IntegerUtils.isPositive("1")).isTrue();
        assertThat(IntegerUtils.isPositive("0")).isFalse();
        assertThat(IntegerUtils.isPositive("-1")).isFalse();
        assertThat(IntegerUtils.isPositive("abc")).isFalse();
        assertThat(IntegerUtils.isPositive("")).isFalse();
        assertThat(IntegerUtils.isPositive((String) null)).isFalse();
    }

    @Test
    public void testIsNotNegative() {
        assertThat(IntegerUtils.isNotNegative(1)).isTrue();
        assertThat(IntegerUtils.isNotNegative(0)).isTrue();
        assertThat(IntegerUtils.isNotNegative(-1)).isFalse();
        assertThat(IntegerUtils.isNotNegative((Integer) null)).isFalse();

    }

    @Test
    public void testIsNotNegative2() {
        assertThat(IntegerUtils.isNotNegative("1")).isTrue();
        assertThat(IntegerUtils.isNotNegative("0")).isTrue();
        assertThat(IntegerUtils.isNotNegative("-1")).isFalse();
        assertThat(IntegerUtils.isNotNegative((String) null)).isFalse();
    }
}
