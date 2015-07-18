package com.qinyuan15.utils.sns.share;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SinaWeiboShareUrlBuilderTest {
    @Test
    public void testBuild() throws Exception {
        String targetUrl = "http://www.sogou.com";
        String title = "HelloWorld";
        List<String> images = Lists.newArrayList(
                "https://www.baidu.com/img/bdlogo.png",
                "http://www.sogou.com/images/logo/new/search400x150.png"
        );
        String url = new SinaWeiboShareUrlBuilder(targetUrl, title, images).build();
        assertThat(url).isEqualTo("http://service.weibo.com/share/share.php?title=HelloWorld&pic=https%3A%2F%2Fwww.baidu.com%2Fimg%2Fbdlogo.png%7C%7Chttp%3A%2F%2Fwww.sogou.com%2Fimages%2Flogo%2Fnew%2Fsearch400x150.png&url=http%3A%2F%2Fwww.sogou.com");
    }
}
