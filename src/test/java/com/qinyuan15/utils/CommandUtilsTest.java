package com.qinyuan15.utils;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test CommandUtils
 * Created by qinyuan on 15-4-16.
 */
public class CommandUtilsTest {
    @Test
    public void testRun() throws Exception {
        Pair<Integer, String> result = CommandUtils.run("mvn -version");
        assertThat(result.getKey()).isEqualTo(0);
        assertThat(result.getLeft()).isEqualTo(0);
        assertThat(result.getRight()).isEqualTo(result.getValue())
                .contains("Apache Maven")
                .contains("Maven home:")
                .contains("Java version:")
                .contains("Java home:");
    }
}
