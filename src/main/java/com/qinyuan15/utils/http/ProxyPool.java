package com.qinyuan15.utils.http;

/**
 * Pool of Http Proxy
 * Created by qinyuan on 14-12-24.
 */
public interface ProxyPool {

    IProxy next();

    int size();
}
