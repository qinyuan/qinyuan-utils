package com.qinyuan15.utils;

/**
 * Utility class of currency
 * Created by qinyuan on 15-4-16.
 */
public class CurrencyUtils {
    private CurrencyUtils() {
    }

    /**
     * Delete the cent part of currency.
     * <p>
     * For example, if we delete cent part of 1.25, then will return 1.20
     * </p>
     *
     * @param value currency value to deal with
     * @return value that delete cent part
     */
    public static double trimCent(double value) {
        return Math.floor(value * 10) / 10.0;
    }

    /**
     * Delete useless decimal part of currency
     * <p>
     * For example, if currency is 12.0, deleting useless decimal part will return 12.
     * If currency is 12.5, there is no useless decimal part
     * </p>
     *
     * @param value currency
     * @return currency string deleting useless decimal part
     */
    public static String trimUselessDecimal(double value) {
        String valueString = String.valueOf(value);
        return valueString.replaceAll("\\.0+$", "");
    }
}
