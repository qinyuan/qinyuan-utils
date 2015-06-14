package com.qinyuan15.utils.mvc;

import javax.servlet.http.HttpServletRequest;

/**
 * Tool Class about request
 * Created by qinyuan on 15-3-27.
 *
 * @deprecated use qinyuan tag
 */
public class RequestUtils {
    public final static long VERSION = System.currentTimeMillis();
    public final static String CSS_PREFIX = "resources/css/";
    public final static String JS_PREFIX = "resources/js/";

    private RequestUtils() {
    }

    public static String getCss(String fileName) {
        return CSS_PREFIX + fileName + ".css?t=" + VERSION;
    }

    public static String getJs(String fileName) {
        return JS_PREFIX + fileName + ".js?t=" + VERSION;
    }

    private static String getFileInURI(HttpServletRequest request) {
        return request.getRequestURI().replaceAll("^.*/", "").replace(".jsp", "");
    }

    public static String getRelativeCss(HttpServletRequest request) {
        return getCss(getFileInURI(request));
    }

    public static String getRelativeJs(HttpServletRequest request) {
        return getJs(getFileInURI(request));
    }
}
