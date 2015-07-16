package com.qinyuan15.utils.mvc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Utility class about url
 * Created by qinyuan on 15-5-11.
 */
public class UrlUtils {
    private final static Logger LOGGER = LoggerFactory.getLogger(UrlUtils.class);

    private UrlUtils() {
    }

    public static String decode(String url) {
        try {
            return URLDecoder.decode(url, "utf-8");
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("Fail to encode url {}, info: {}", url, e);
            return url;
        }
    }

    public static String encode(String url) {
        try {
            return URLEncoder.encode(url, "utf-8");
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("Fail to encode url {}, info: {}", url, e);
            return url;
        }
    }

    public static String getHost(String url) {
        int fromIndex = url.indexOf("://");
        int endIndex;
        if (fromIndex >= 0) {
            endIndex = url.indexOf('/', fromIndex + 3);
        } else {
            endIndex = url.indexOf('/');
        }

        if (endIndex > 0) {
            return url.substring(0, endIndex);
        } else {
            return url;
        }
    }
}
