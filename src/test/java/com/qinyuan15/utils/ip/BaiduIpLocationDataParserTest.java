package com.qinyuan15.utils.ip;

import com.qinyuan15.utils.test.TestFileUtils;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BaiduIpLocationDataParserTest {
    @Test
    public void testParse() throws Exception {
        BaiduIpLocationDataParser parser = new BaiduIpLocationDataParser();
        String location = parser.parse(TestFileUtils.read("baidu-ip-location-data.json"));
        assertThat(location).isEqualTo("广东省深圳市 电信");
    }
}
