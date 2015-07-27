package com.qinyuan15.utils.ip;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * Parse ip content from taobao
 * Created by qinyuan on 15-7-27.
 */
class TaobaoIpLocationDataParser implements IpLocationDataParser {
    private final static Logger LOGGER = LoggerFactory.getLogger(TaobaoIpLocationDataParser.class);

    @Override
    public String parse(String locationData) {
        if (!StringUtils.hasText(locationData)) {
            LOGGER.error("Fail to page location, location data is empty: {}", locationData);
            return null;
        }

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Object object = objectMapper.readValue(locationData, Object.class);
            if (!(object instanceof Map)) {
                return null;
            }

            Object data = ((Map) object).get("data");
            if (!(data instanceof Map)) {
                return null;
            }

            Map dataMap = (Map) data;
            return dataMap.get("country") + "-" + dataMap.get("area") + "-" + dataMap.get("region") + "-" + dataMap.get("city");
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("Fail to page location, data: {}, info: {}", locationData, e);
            return null;
        }
    }
}
