package com.qinyuan15.utils.http;

import org.springframework.util.StringUtils;

/**
 * Utility class about ip
 * Created by qinyuan on 15-5-17.
 */
public class IpUtils {
    private IpUtils() {
    }

    /**
     * Test if the string match IPv4 pattern
     *
     * @param string ip string
     * @return true if match and false if not
     */
    public static boolean isIPv4(String string) {
        return StringUtils.hasText(string) &&
                string.matches("^\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}$");
    }
}
