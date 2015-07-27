package com.qinyuan15.utils.ip;

/**
 * Query ip location, by network or database and so on.
 * Created by qinyuan on 15-7-27.
 */
public interface IpLocationQuerier {
    String getLocation(String ip);
}
