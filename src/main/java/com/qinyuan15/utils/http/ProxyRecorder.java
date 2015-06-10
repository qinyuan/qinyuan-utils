package com.qinyuan15.utils.http;

/**
 * interface to record proxy use result to database
 * Created by qinyuan on 15-2-23.
 */
public interface ProxyRecorder {
    void recordSpeed(IProxy proxy, int speed);

    void recordRejection(IProxy proxy, String url);
}
