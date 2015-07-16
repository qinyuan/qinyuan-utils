package com.qinyuan15.utils.sns.share;

import com.google.common.base.Joiner;
import com.qinyuan15.utils.mvc.UrlUtils;

import java.lang.Override;import java.lang.String;import java.util.List;

/**
 * Class to build share url of sina weibo
 * Created by qinyuan on 15-7-16.
 */
public class XinaWeiboShareUrlBuilder implements ShareUrlBuilder {
    private final static String pageUrl = "http://service.weibo.com/share/share.php";

    @Override
    public String build(String target, String text, List<String> imageUrls) {
        String url = pageUrl + "?title=" + UrlUtils.encode(text);
        url += "&url=" + UrlUtils.encode(target);
        url += "&pic=" + UrlUtils.encode(Joiner.on("||").join(imageUrls));
        return url;
    }
}
