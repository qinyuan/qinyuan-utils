package com.qinyuan15.utils;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test CurrentUtils
 * Created by qinyuan on 15-4-16.
 */
public class CurrencyUtilsTest {
    @Test
    public void testTrimCent() {
        assertThat(CurrencyUtils.trimCent(1.12)).isEqualTo(1.10);
        assertThat(CurrencyUtils.trimCent(1.05)).isEqualTo(1.00);
    }

    @Test
    public void testTrimUselessDecimal() {
        assertThat(CurrencyUtils.trimUselessDecimal(1)).isEqualTo("1");
        assertThat(CurrencyUtils.trimUselessDecimal(1.0)).isEqualTo("1");
        assertThat(CurrencyUtils.trimUselessDecimal(1.000)).isEqualTo("1");
        assertThat(CurrencyUtils.trimUselessDecimal(1.256)).isEqualTo("1.256");
        assertThat(CurrencyUtils.trimUselessDecimal(1.3000)).isEqualTo("1.3");
    }
}
