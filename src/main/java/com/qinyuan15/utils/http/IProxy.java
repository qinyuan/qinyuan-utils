package com.qinyuan15.utils.http;

/**
 * Interface about http proxy
 * Created by qinyuan on 15-4-23.
 */
public interface IProxy {
    public String getHost();

    public Integer getPort();

    public String getType();

    public Integer getSpeed();

    public void setHost(String host);

    public void setPort(Integer port);

    public void setType(String type);

    public void setSpeed(Integer speed);
}
