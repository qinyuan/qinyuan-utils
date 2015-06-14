package com.qinyuan15.utils;

/**
 * Utility class about object
 * Created by qinyuan on 15-6-14.
 */
public class ObjectUtils {
    private ObjectUtils() {
    }

    public static boolean isDifferent(Object o1, Object o2) {
        if (o1 == null) {
            return o2 != null;
        } else {
            return !o1.equals(o2);
        }
    }
}
