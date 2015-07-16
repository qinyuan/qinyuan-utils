package com.qinyuan15.utils.sns.share;

import com.qinyuan15.utils.mvc.UrlUtils;

import java.util.Map;

public abstract class AbstractShareUrlBuilder implements ShareUrlBuilder {
    @Override
    public String build() {
        String url = getPageUrl();
        for (Map.Entry<String, String> entry : getParams().entrySet()) {
            if (url.contains("?")) {
                url += "&";
            } else {
                url += "?";
            }
            url += entry.getKey() + "=" + UrlUtils.encode(entry.getValue());
        }
        return url;
    }

    protected abstract String getPageUrl();

    protected abstract Map<String, String> getParams();
}
