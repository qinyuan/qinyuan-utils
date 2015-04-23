package com.qinyuan15.utils.http;

/**
 * interface to record proxy speed to database
 * Created by qinyuan on 15-2-23.
 */
public interface ProxySpeedRecorder {
    void recordSpeed(IProxy proxy, int speed);
}
