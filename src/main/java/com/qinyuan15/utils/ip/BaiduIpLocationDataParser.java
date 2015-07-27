package com.qinyuan15.utils.ip;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

public class BaiduIpLocationDataParser implements IpLocationDataParser {
    private final static Logger LOGGER = LoggerFactory.getLogger(BaiduIpLocationDataParser.class);

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
            if (!(data instanceof List)) {
                return null;
            }

            Object dataFirstItem = ((List) data).get(0);
            if (!(dataFirstItem instanceof Map)) {
                return null;
            }

            Map dataFirstItemMap = (Map) dataFirstItem;
            return dataFirstItemMap.get("location").toString();
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("Fail to page location, data: {}, info: {}", locationData, e);
            return null;
        }
    }
}
