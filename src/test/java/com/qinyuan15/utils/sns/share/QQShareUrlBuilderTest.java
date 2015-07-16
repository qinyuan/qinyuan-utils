package com.qinyuan15.utils.sns.share;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class QQShareUrlBuilderTest {
    @Test
    public void testBuild() throws Exception {
        String targetUrl = "http://www.sina.com.cn";
        String title = "HelloWorld";
        String desc = "Test Summary: nice to meet you！";
        List<String> pictures = Lists.newArrayList(
                "https://www.baidu.com/img/bdlogo.png",
                "http://www.sogou.com/images/logo/new/search400x150.png"
        );
        String url = new QQShareUrlBuilder(targetUrl, title, desc, pictures).build();
        assertThat(url).isEqualTo("http://connect.qq.com/widget/shareqq/index.html?site=vip&summary=Test+Summary%3A+nice+to+meet+you%EF%BC%81&pics=https%3A%2F%2Fwww.baidu.com%2Fimg%2Fbdlogo.png%7C%7Chttp%3A%2F%2Fwww.sogou.com%2Fimages%2Flogo%2Fnew%2Fsearch400x150.png&title=HelloWorld&desc=Test+Summary%3A+nice+to+meet+you%EF%BC%81&url=http%3A%2F%2Fwww.sina.com.cn");
    }
}
