package com.qinyuan15.utils;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test ChineseUtils
 * Created by qinyuan on 15-2-25.
 */
public class ChineseUtilsTest {
    @Test
    public void testGetPhoneticLetter() {
        String testString = "我的奋斗";
        String result = ChineseUtils.getPhoneticLetter(testString);
        assertThat(result).isEqualTo("WDFD");

        testString = "小";
        result = ChineseUtils.getPhoneticLetter(testString);
        assertThat(result).isEqualTo("X");

        testString = "gap";
        result = ChineseUtils.getPhoneticLetter(testString);
        assertThat(result).isEqualTo("GAP");
    }
}
