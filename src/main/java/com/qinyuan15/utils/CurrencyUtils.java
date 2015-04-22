package com.qinyuan15.utils;

/**
 * Utility class of currency
 * Created by qinyuan on 15-4-16.
 */
public class CurrencyUtils {
    private CurrencyUtils() {
    }

    public static double trimCent(double value) {
        return Math.floor(value * 10) / 10.0;
        //return Math.round(value * 10) / 10.0;
    }
}
