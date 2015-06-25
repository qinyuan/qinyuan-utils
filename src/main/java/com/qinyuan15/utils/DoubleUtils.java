package com.qinyuan15.utils;

/**
 * Utility class about double
 * Created by qinyuan on 15-6-23.
 */
public class DoubleUtils {

    public static boolean isNotNegative(Double value) {
        return value != null && value >= 0;
    }

    public static boolean isPositive(Double value) {
        return value != null && value > 0;
    }
}
