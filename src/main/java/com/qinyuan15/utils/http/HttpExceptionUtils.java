package com.qinyuan15.utils.http;

import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.HttpHostConnectException;

/**
 * Utility class about http exception
 * Created by qinyuan on 15-5-17.
 */
public class HttpExceptionUtils {
    private HttpExceptionUtils() {
    }

    public static boolean isHttpException(Throwable throwable) {
        return (throwable instanceof ConnectTimeoutException) ||
                (throwable.getCause() instanceof ConnectTimeoutException) ||
                (throwable instanceof HttpHostConnectException) ||
                (throwable.getCause() instanceof HttpHostConnectException);
    }
}
