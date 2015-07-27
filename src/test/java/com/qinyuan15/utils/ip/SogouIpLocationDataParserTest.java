package com.qinyuan15.utils.ip;

import com.qinyuan15.utils.test.TestFileUtils;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SogouIpLocationDataParserTest {
    @Test
    public void testParse() throws Exception {
        String location = new SogouIpLocationDataParser().parse(TestFileUtils.read("sogou-ip-location-data.txt"));
        assertThat(location).isEqualTo("广东省深圳市电信");
    }
}
