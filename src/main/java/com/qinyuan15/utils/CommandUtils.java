package com.qinyuan15.utils;

import org.apache.commons.io.IOUtils;

/**
 * Utility class about command
 * Created by qinyuan on 15-4-16.
 */
public class CommandUtils {

    /**
     * Execute external command
     *
     * @param command external command
     * @return execute result
     */
    public static CommandExecuteResult run(final String command) {
        try {
            Process process = Runtime.getRuntime().exec(command);
            String systemErr = IOUtils.toString(process.getErrorStream());
            String systemOut = IOUtils.toString(process.getInputStream());

            // we must fetch exitValue after result read, or program may block
            int exitValue = process.waitFor();

            //return Pair.of(exitValue, sb.toString());
            return new CommandExecuteResult(exitValue, systemOut, systemErr);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
