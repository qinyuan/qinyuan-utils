package com.qinyuan15.utils.sns.share;

import java.util.HashMap;
import java.util.Map;

public class QzoneShareUrlBuilder extends AbstractShareUrlBuilder {
    private final Map<String, String> params = new HashMap<>();

    public QzoneShareUrlBuilder(String targetUrl, String title, String summary, String picture) {
        params.put("title", title);
        params.put("url", targetUrl);
        params.put("summary", summary);
        params.put("pics", picture);
    }

    @Override
    protected String getPageUrl() {
        return "http://sns.qzone.qq.com/cgi-bin/qzshare/cgi_qzshare_onekey";
    }

    @Override
    protected Map<String, String> getParams() {
        return params;
    }
}
