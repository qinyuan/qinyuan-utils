package com.qinyuan15.utils.mvc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Utility class about url
 * Created by qinyuan on 15-5-11.
 */
public class UrlUtils {
    private final static Logger LOGGER = LoggerFactory.getLogger(UrlUtils.class);

    private UrlUtils() {
    }

    public static String encode(String url) {
        try {
            return URLEncoder.encode(url, "utf-8");
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("Fail to encode url {}, info: {}", url, e);
            return url;
        }
    }
}
