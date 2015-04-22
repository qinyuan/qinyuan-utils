package com.qinyuan15.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utility class about thread
 * Created by qinyuan on 15-4-19.
 */
public class ThreadUtils {
    private final static Logger LOGGER = LoggerFactory.getLogger(ThreadUtils.class);

    private ThreadUtils() {
    }

    public static void sleep(double second) {
        sleepByMillSecond((long) second * 1000);
    }

    private static void sleepByMillSecond(long millSecond) {
        try {
            Thread.sleep(millSecond);
        } catch (Exception e) {
            LOGGER.error("fail to sleep, info: {}", e);
        }
    }

    public static void sleep(int second) {
        sleepByMillSecond(second * 1000);
    }
}
