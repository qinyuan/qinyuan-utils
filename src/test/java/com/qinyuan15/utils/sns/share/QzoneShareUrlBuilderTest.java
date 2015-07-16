package com.qinyuan15.utils.sns.share;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class QzoneShareUrlBuilderTest {
    @Test
    public void testBuild() throws Exception {
        String targetUrl = "http://www.sina.com.cn";
        String title = "HelloWorld";
        String summary = "Test Summary: nice to meet you！";
        String picture = "https://www.baidu.com/img/bdlogo.png";
        String url = new QzoneShareUrlBuilder(targetUrl, title, summary, picture).build();
        assertThat(url).isEqualTo("http://sns.qzone.qq.com/cgi-bin/qzshare/cgi_qzshare_onekey?summary=Test+Summary%3A+nice+to+meet+you%EF%BC%81&pics=https%3A%2F%2Fwww.baidu.com%2Fimg%2Fbdlogo.png&title=HelloWorld&url=http%3A%2F%2Fwww.sina.com.cn");
    }
}
