package com.qinyuan15.utils.sns.share;

import com.google.common.base.Joiner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class to build share url of sina weibo
 * Created by qinyuan on 15-7-16.
 */
public class SinaWeiboShareUrlBuilder extends AbstractShareUrlBuilder {
    private final Map<String, String> params = new HashMap<>();

    public SinaWeiboShareUrlBuilder(String targetUrl, String title, List<String> pictures) {
        params.put("title", title);
        params.put("url", targetUrl);
        params.put("pic", Joiner.on("||").join(pictures));
    }

    @Override
    protected String getPageUrl() {
        return "http://service.weibo.com/share/share.php";
    }

    @Override
    protected Map<String, String> getParams() {
        return params;
    }
}
