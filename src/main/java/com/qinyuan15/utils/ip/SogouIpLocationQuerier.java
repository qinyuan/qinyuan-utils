package com.qinyuan15.utils.ip;

/**
 * Query ip location from taobao
 * Created by qinyuan on 15-7-27.
 */
public class SogouIpLocationQuerier extends NetworkIpLocationQuerier {
    @Override
    protected String getUrl(String ip) {
        return "http://www.sogou.com/websearch/features/ipsearch.jsp?ip=" + ip;
    }

    @Override
    protected IpLocationDataParser getDataParser() {
        return new SogouIpLocationDataParser();
    }
}
