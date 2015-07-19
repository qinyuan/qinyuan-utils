package com.qinyuan15.utils.sns.share;

import com.google.common.base.Joiner;
import com.qinyuan15.utils.mvc.UrlUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QQShareUrlBuilder extends AbstractShareUrlBuilder {
    private final Map<String, String> params = new HashMap<>();

    public QQShareUrlBuilder(String targetUrl, String title, String summary, List<String> pictures) {
        params.put("title", title);
        params.put("url", targetUrl);
        params.put("summary", summary);
        params.put("desc", summary);
        params.put("pics", Joiner.on("||").join(pictures));
        params.put("site", UrlUtils.getHost(targetUrl));
    }

    @Override
    protected String getPageUrl() {
        return "http://connect.qq.com/widget/shareqq/index.html";
    }

    @Override
    protected Map<String, String> getParams() {
        return params;
    }
}
