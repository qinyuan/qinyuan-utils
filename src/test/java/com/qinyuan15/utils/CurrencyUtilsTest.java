package com.qinyuan15.utils;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test CurrentUtils
 * Created by qinyuan on 15-4-16.
 */
public class CurrencyUtilsTest {
    @Test
    public void testTrimCent() throws Exception {
        assertThat(CurrencyUtils.trimCent(1.12)).isEqualTo(1.10);
        assertThat(CurrencyUtils.trimCent(1.05)).isEqualTo(1.00);
    }
}
