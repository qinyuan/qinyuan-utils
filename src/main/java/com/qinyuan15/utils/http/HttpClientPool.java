package com.qinyuan15.utils.http;

/**
 * Pool class of HTTP client
 * Created by qinyuan on 15-2-23.
 */
public class HttpClientPool {
    private ProxyPool proxyPool;
    private ProxySpeedRecorder proxySpeedRecorder;

    public void setProxyPool(ProxyPool proxyPool) {
        this.proxyPool = proxyPool;
    }

    public void setProxySpeedRecorder(ProxySpeedRecorder proxySpeedRecorder) {
        this.proxySpeedRecorder = proxySpeedRecorder;
    }

    public HttpClient next() {
        HttpClient clientWrapper = new HttpClient();
        if (this.proxyPool != null) {
            clientWrapper.setProxy(this.proxyPool.next());
        }
        clientWrapper.setProxySpeedRecorder(this.proxySpeedRecorder);
        return clientWrapper;
    }
}
