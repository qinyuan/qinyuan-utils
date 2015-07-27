package com.qinyuan15.utils.ip;

public class DefaultIpLocationQuerier extends ComposableIpLocationQuerier {

    public DefaultIpLocationQuerier() {
        super(new TaobaoIpLocationQuerier(), new BaiduIpLocationQuerier(), new SogouIpLocationQuerier());
    }
}
