package com.qinyuan15.utils.http;

import java.util.ArrayList;
import java.util.List;

/**
 * Pool class of HTTP client
 * Created by qinyuan on 15-2-23.
 */
public class HttpClientPool {
    private ProxyPool proxyPool;
    private ProxyRecorder proxyRecorder;

    public void setProxyPool(ProxyPool proxyPool) {
        this.proxyPool = proxyPool;
    }

    public void setProxyRecorder(ProxyRecorder proxyRecorder) {
        this.proxyRecorder = proxyRecorder;
    }

    public List<HttpClient> next(int n) {
        List<HttpClient> httpClients = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            httpClients.add(next());
        }
        return httpClients;
    }

    public HttpClient next() {
        HttpClient clientWrapper = new HttpClient();
        if (this.proxyPool != null) {
            clientWrapper.setProxy(this.proxyPool.next());
        }
        clientWrapper.setProxyRecorder(this.proxyRecorder);
        return clientWrapper;
    }
}
