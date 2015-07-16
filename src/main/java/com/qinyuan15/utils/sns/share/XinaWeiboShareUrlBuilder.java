package com.qinyuan15.utils.sns.share;

import com.google.common.base.Joiner;
import com.qinyuan15.utils.mvc.UrlUtils;

import java.util.List;

/**
 * Class to build share url of sina weibo
 * Created by qinyuan on 15-7-16.
 */
public class XinaWeiboShareUrlBuilder implements ShareUrlBuilder {
    private final static String pageUrl = "http://service.weibo.com/share/share.php";

    private final String targetUrl;
    private final String title;
    private final List<String> imageUrls;


    public XinaWeiboShareUrlBuilder(String targetUrl, String title, List<String> imageUrls) {
        this.targetUrl = targetUrl;
        this.title = title;
        this.imageUrls = imageUrls;
    }

    @Override
    public String build() {
        String url = pageUrl + "?title=" + UrlUtils.encode(title);
        url += "&url=" + UrlUtils.encode(targetUrl);
        url += "&pic=" + UrlUtils.encode(Joiner.on("||").join(imageUrls));
        return url;
    }
}
