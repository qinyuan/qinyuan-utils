package com.qinyuan15.utils.http;

import com.qinyuan15.utils.hibernate.PersistObject;
import com.qinyuan15.utils.http.IProxy;import java.lang.Integer;import java.lang.Override;import java.lang.String;

/**
 * Class to record Proxy Server information
 * Created by qinyuan on 14-12-25.
 */
public class Proxy extends PersistObject implements IProxy {

    public final static String DEFAULT_TYPE = "http";

    private String host;
    private Integer port;
    private String type = DEFAULT_TYPE;
    private Integer speed = Integer.MAX_VALUE;

    public String getHost() {
        return host;
    }

    public Integer getPort() {
        return port;
    }

    public String getType() {
        return type;
    }

    public Integer getSpeed() {
        return speed;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    @Override
    public String toString() {
        return this.type + "://" + this.host + ":" + this.port;
    }
}
