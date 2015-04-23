package com.qinyuan15.utils.http;

import org.junit.Test;

/**
 * Test Performance of HttpClientWrapper
 * Created by qinyuan on 14-12-24.
 */
public class HttpClientWrapperPerfTest {

    @Test
    public void test() throws Exception {
        /*
        ProxyPool pool = new TestProxyPool();
        HttpClientWrapper client = new HttpClientWrapper();
        String url = "http://s.etao.com/detail/40780735321.html?tbpm=20141215";

        int runTimes = 2;
        Proxy proxy = pool.next();
        client.setProxy(proxy);
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < runTimes; i++) {
            if (i > 0 && i % 10 == 0) {
                long interval = System.currentTimeMillis() - startTime;
                System.out.println(i + " " + i * 1000.0 / interval);
            }
            try {
                client.download(url, TestFileUtils.tempDir + "/crawler/etao.html");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }*/
    }
}
