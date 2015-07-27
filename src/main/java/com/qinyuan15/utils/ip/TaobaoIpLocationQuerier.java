package com.qinyuan15.utils.ip;

/**
 * Query ip location from taobao
 * Created by qinyuan on 15-7-27.
 */
public class TaobaoIpLocationQuerier extends NetworkIpLocationQuerier {
    @Override
    protected String getUrl(String ip) {
        return "http://ip.taobao.com/service/getIpInfo.php?ip=" + ip;
    }

    @Override
    protected IpLocationDataParser getDataParser() {
        return new TaobaoIpLocationDataParser();
    }
}
