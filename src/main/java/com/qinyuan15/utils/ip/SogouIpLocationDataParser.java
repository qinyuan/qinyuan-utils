package com.qinyuan15.utils.ip;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

/**
 * Parse ip content from taobao
 * Created by qinyuan on 15-7-27.
 */
class SogouIpLocationDataParser implements IpLocationDataParser {
    private final static Logger LOGGER = LoggerFactory.getLogger(SogouIpLocationDataParser.class);

    @Override
    public String parse(String locationData) {
        if (!StringUtils.hasText(locationData)) {
            LOGGER.error("Fail to page location, location data is empty: {}", locationData);
            return null;
        }

        String location = locationData.trim().replace("\");", "").replace("');", "");
        return location.replaceAll("^.*\\s", "");
    }
}
