package com.qinyuan15.utils;

/**
 * Utility class about String
 * Created by qinyuan on 15-6-16.
 */
public class StringUtils {
    private StringUtils() {
    }

    public static String replaceFirst(String string, String from, String to) {
        if (string == null || string.isEmpty()) {
            return string;
        }

        int index = string.indexOf(from);
        if (index < 0) {
            return string;
        }

        if (index == 0) {
            return to + string.substring(from.length());
        } else if (index + from.length() >= string.length()) {
            return string.substring(0, index) + to;
        } else {
            return string.substring(0, index) + to + string.substring(index + from.length());
        }
    }

    public static int getChineseStringLength(String string) {
        if (string == null) {
            return 0;
        }

        int len = 0;
        for (int i = 0; i < string.length(); i++) {
            if (string.codePointAt(i) > 127) {
                len += 2;
            } else {
                len++;
            }
        }
        return len;
    }
}
