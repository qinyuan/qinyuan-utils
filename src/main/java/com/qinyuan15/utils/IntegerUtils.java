package com.qinyuan15.utils;

import org.apache.commons.lang3.math.NumberUtils;

/**
 * Tool class for integer
 * Created by qinyuan on 15-2-27.
 */
public class IntegerUtils {
    public static boolean isPositive(Integer integer) {
        return integer != null && integer > 0;
    }

    public static boolean isPositive(String string) {
        return NumberUtils.isNumber(string) && NumberUtils.toInt(string) > 0;
    }
}
