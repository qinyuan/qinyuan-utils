package com.qinyuan15.utils.ip;

/**
 * Query ip location from taobao
 * Created by qinyuan on 15-7-27.
 */
public class DatabaseIpLocationQuerier implements IpLocationQuerier {
    private final IpLocationDao dao = new IpLocationDao();

    @Override
    public String getLocation(String ip) {
        return dao.getLocationByIp(ip);
    }
}
