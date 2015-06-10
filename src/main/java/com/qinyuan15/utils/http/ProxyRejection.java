package com.qinyuan15.utils.http;

import com.qinyuan15.utils.hibernate.PersistObject;

/**
 * Class to record proxy rejection information
 * Created by qinyuan on 15-6-3.
 */
public class ProxyRejection extends PersistObject {
    private int proxyId;
    private String host;
    private String url;
    private String rejectTime;

    public int getProxyId() {
        return proxyId;
    }

    public String getHost() {
        return host;
    }

    public String getUrl() {
        return url;
    }

    public String getRejectTime() {
        return rejectTime;
    }

    public void setProxyId(int proxyId) {
        this.proxyId = proxyId;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setRejectTime(String rejectTime) {
        this.rejectTime = rejectTime;
    }
}
