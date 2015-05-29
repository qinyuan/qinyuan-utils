package com.qinyuan15.utils;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test CommandUtils
 * Created by qinyuan on 15-4-16.
 */
public class CommandUtilsTest {
    @Test
    public void testRun() throws Exception {
        CommandExecuteResult result = CommandUtils.run("mvn -version");
        assertThat(result.getExitCode()).isEqualTo(0);
        assertThat(result.getSystemOut())
                .contains("Apache Maven")
                .contains("Maven home:")
                .contains("Java version:")
                .contains("Java home:");
        assertThat(result.getSystemErr()).isEmpty();

        result = CommandUtils.run("mvn --HelloWorld");
        assertThat(result.getExitCode()).isEqualTo(1);
        assertThat(result.getSystemOut()).contains("usage: mvn [options]");
        assertThat(result.getSystemErr()).contains("Unrecognized option:");
    }
}
