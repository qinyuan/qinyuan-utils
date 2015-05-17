package com.qinyuan15.utils.http;

/**
 * Interface about http proxy
 * Created by qinyuan on 15-4-23.
 */
public interface IProxy {
    String getHost();

    Integer getPort();

    String getType();

    Integer getSpeed();

    void setHost(String host);

    void setPort(Integer port);

    void setType(String type);

    void setSpeed(Integer speed);

    String getSpeedUpdateTime();

    void setSpeedUpdateTime(String time);
}
