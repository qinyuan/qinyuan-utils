package com.qinyuan15.utils.sns.share;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;

public class XinaWeiboShareUrlBuilderTest {
    @Test
    public void testBuild() throws Exception {
        String target = "http://www.sogou.com";
        String text = "HelloWorld";
        List<String> images = Lists.newArrayList(
                "https://www.baidu.com/img/bdlogo.png",
                "http://www.sogou.com/images/logo/new/search400x150.png"
        );
        System.out.println(new XinaWeiboShareUrlBuilder().build(target, text, images));
    }
}
