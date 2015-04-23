package com.qinyuan15.utils;

/**
 * Utility class of currency
 * Created by qinyuan on 15-4-16.
 */
public class CurrencyUtils {
    private CurrencyUtils() {
    }

    /**
     * delete the cent part of currency.
     * <p>
     * for example, if we delete cent part of 1.25, then will return 1.20
     * </p>
     *
     * @param value currency value to deal with
     * @return value that delete cent part
     */
    public static double trimCent(double value) {
        return Math.floor(value * 10) / 10.0;
    }
}
