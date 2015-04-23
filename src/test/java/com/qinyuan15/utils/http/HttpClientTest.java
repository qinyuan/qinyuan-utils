package com.qinyuan15.utils.http;

import com.qinyuan15.utils.test.TestFileUtils;
import org.junit.Before;
import org.junit.Test;

public class HttpClientTest {
    private HttpClient client;
    //private ProxyPool proxyPool;
    private String url = "http://s.etao.com/detail/8573171861127423250.html";
    //private String url = "http://www.baidu.com";
    //private String url = "http://localhost/test/test.php";

    @Before
    public void setUp() throws Exception {
        client = new HttpClient();
        //proxyPool = new TestProxyPool();
        /*
        Proxy proxy = new Proxy();
        proxy.setHost("183.207.228.119");
        proxy.setPort(84);
        client.setProxy(proxy);
        */
        //client.setProxy(proxyPool.next());
    }

    @Test
    public void testGetContent() throws Exception {
        //String result = client.getContent(url);
        //System.out.println(result);
        //assertThat(result).contains("百度一下");
        System.out.println(client.get(url));
    }

    @Test
    public void testDownload() throws Exception {
        String filePath = TestFileUtils.tempDir + "/crawler/baidu.html";
        client.download(url, filePath);
    }
}
