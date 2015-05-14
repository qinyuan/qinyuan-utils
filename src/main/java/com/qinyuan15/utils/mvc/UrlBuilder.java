package com.qinyuan15.utils.mvc;

import com.google.common.base.Joiner;
import com.qinyuan15.utils.IntegerUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class to build url with parameters
 * Created by qinyuan on 15-5-10.
 */
public class UrlBuilder {
    private final static Logger LOGGER = LoggerFactory.getLogger(UrlBuilder.class);

    private Map<String, Object> params = new HashMap<>();
    private String baseUrl;

    public UrlBuilder addParam(String key, Object value) {
        params.put(key, value);
        return this;
    }

    public UrlBuilder setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
        return this;
    }

    public String build() {
        if (!StringUtils.hasText(this.baseUrl)) {
            LOGGER.error("invalid baseUrl '{}'", baseUrl);
            return null;
        }

        String url = baseUrl;
        if (params.size() == 0) {
            return url;
        }

        if (!url.endsWith("?")) {
            url += "?";
        }

        List<String> keyValues = new ArrayList<>();
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            Object value = entry.getValue();
            if (value == null) {
                continue;
            }

            if (value instanceof Integer && (!IntegerUtils.isPositive((Integer) value))) {
                continue;
            }

            if (value instanceof String && (!StringUtils.hasText((String) value))) {
                continue;
            }

            keyValues.add(entry.getKey() + "=" + UrlUtils.encode(value.toString()));
        }

        String paramsString = Joiner.on("&").join(keyValues);
        return url + paramsString;
    }
}
