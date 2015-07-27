package com.qinyuan15.utils.ip;

/**
 * Save ip location to database or file and so on.
 * Created by qinyuan on 15-7-27.
 */
public interface IpLocationSaver {
    void save(String ip, String location);
}
