package com.qinyuan15.utils.http;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

/**
 * Wrap HttpClient of Apache
 * Created by qinyuan on 14-12-24.
 */
public class HttpClient {

    private final static Logger LOGGER = LoggerFactory.getLogger(HttpClient.class);
    // default connection timeout is 10 seconds
    public final static int DEFAULT_TIMEOUT = 10000;
    private final static HttpUserAgentBuilder userAgentBuilder = new HttpUserAgentBuilder();

    private final HttpClientBuilder clientBuilder = HttpClientBuilder.create();
    private IProxy proxy;
    private int timeout = DEFAULT_TIMEOUT;
    private int requestTimeout = DEFAULT_TIMEOUT;
    private String userAgent = userAgentBuilder.buildRandomly();
    private int lastConnectTime = Integer.MAX_VALUE;
    private ProxyRecorder proxyRecorder;

    public void setProxy(IProxy proxy) {
        this.proxy = proxy;
    }

    public IProxy getProxy() {
        return this.proxy;
    }

    public void setProxyRecorder(ProxyRecorder proxyRecorder) {
        this.proxyRecorder = proxyRecorder;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public void setRequestTimeout(int requestTimeout) {
        this.requestTimeout = requestTimeout;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public int getLastConnectTime() {
        return this.lastConnectTime;
    }

    public CloseableHttpResponse getResponse(String url) throws IOException {
        if (!url.contains("://")) {
            url = "http://" + url;
        }
        HttpGet get = new HttpGet(url);
        get.setHeader("User-Agent", this.userAgent);

        // set config
        RequestConfig.Builder configBuilder = RequestConfig.custom()
                .setConnectTimeout(this.timeout)
                .setConnectionRequestTimeout(this.requestTimeout);
        if (proxy != null) {
            configBuilder.setProxy(new HttpHost(proxy.getHost(), proxy.getPort(), proxy.getType()));
        }
        get.setConfig(configBuilder.build());

        LOGGER.info("connecting {} with proxy {}", url, proxy);
        return clientBuilder.build().execute(get);
    }

    public HttpResponse get(String url) {
        try {
            long startTime = System.currentTimeMillis();
            CloseableHttpResponse response = this.getResponse(url);
            LOGGER.info("connected to {} with proxy {}", url, proxy);
            String content = EntityUtils.toString(response.getEntity());
            LOGGER.info("parse content of {}", url);

            int status = response.getStatusLine().getStatusCode();
            LOGGER.info("parse status of {}", url);

            this.lastConnectTime = (int) (System.currentTimeMillis() - startTime);
            return new HttpResponse(content, status);
        } catch (Exception e) {
            LOGGER.error("fail to connect or parse {} with proxy {}, info: {}", url, proxy, e);
            this.lastConnectTime = Integer.MAX_VALUE;
            throw new java.lang.RuntimeException(e);
        } finally {
            this.recordSpeed();
        }
    }

    private void recordSpeed() {
        if (this.proxyRecorder != null) {
            this.proxyRecorder.recordSpeed(proxy, this.lastConnectTime);
        }
    }

    /**
     * After request, feed back that the request is rejected by validating the result
     */
    public void feedbackRejection(String url) {
        this.proxyRecorder.recordRejection(proxy, url);
        // TODO remove this line someday
        feedbackError();
    }

    public void feedbackError() {
        this.lastConnectTime = Integer.MAX_VALUE;
        this.recordSpeed();
    }

    public String getContent(String url) {
        return get(url).getContent();
    }

    public void download(String url, String fileName) throws IOException {
        download(url, new File(fileName));
    }

    public void download(String url, File file) throws IOException {
        String content = getContent(url);
        FileUtils.write(file, content);
    }
}
