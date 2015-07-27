package com.qinyuan15.utils.ip;

import com.qinyuan15.utils.test.TestFileUtils;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TaobaoIpLocationDataParserTest {
    @Test
    public void testParse() throws Exception {
        TaobaoIpLocationDataParser parser = new TaobaoIpLocationDataParser();
        String content = parser.parse(TestFileUtils.read("taobao-ip-location-data.json"));
        assertThat(content).isEqualTo("中国-华南-广东省-深圳市");
    }
}
