package com.qinyuan15.utils.http;

import com.qinyuan15.utils.DateUtils;
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
    private Integer speed;

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
        return DateUtils.trimMilliSecond(rejectTime);
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

    private Proxy proxy;

    public Proxy getProxy() {
        if (proxy == null) {
            proxy = new ProxyDao().getInstance(this.proxyId);
        }

        return proxy;
    }

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }
}
