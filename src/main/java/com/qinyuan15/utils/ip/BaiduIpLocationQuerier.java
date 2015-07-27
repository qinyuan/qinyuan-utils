package com.qinyuan15.utils.ip;

/**
 * Query ip location from taobao
 * Created by qinyuan on 15-7-27.
 */
public class BaiduIpLocationQuerier extends NetworkIpLocationQuerier {
    @Override
    protected String getUrl(String ip) {
        return "https://sp0.baidu.com/8aQDcjqpAAV3otqbppnN2DJv/api.php?resource_id=6006&query=" + ip;
    }

    @Override
    protected IpLocationDataParser getDataParser() {
        return new BaiduIpLocationDataParser();
    }
}
